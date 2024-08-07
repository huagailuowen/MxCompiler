package Semantic;

import AST.ASTVisitor;
import AST.Node.ASTRoot;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import AST.Node.expr.ASTExpr;
import AST.Node.stmt.ASTBlockStmt;
import AST.Node.stmt.ASTBreakStmt;
import AST.Node.stmt.ASTContStmt;
import AST.Node.stmt.ASTExprStmt;
import AST.Node.stmt.ASTForStmt;
import AST.Node.stmt.ASTIfStmt;
import AST.Node.stmt.ASTRetStmt;
import AST.Node.stmt.ASTStmt;
import AST.Node.stmt.ASTVarDefStmt;
import AST.Node.stmt.ASTWhileStmt;
import AST.Node.typ.ASTType;
import Scope.Scope;
import Utility.error.ErrorBasic;
import Utility.label.VarLable;

public class Checker implements ASTVisitor<Compileinfo>{
  private final Scope globalScope;
  private final Scope currentScope;
  private void init(Scope rootScope)
  {
    globalScope = rootScope;
    currentScope = globalScope;
  }
  private void stepIn(Scope tarScope,boolean isNew)
  {
    //if tarScope is null, create a new scope
    if(isNew)
      currentScope = new Scope(currentScope);
    else{
      if(tarScope==null)
        throw new ErrorBasic("Checker: stepIn: tarScope is null");
      if(tarScope.getParent()!=currentScope)
        throw new ErrorBasic("Checker: stepIn: tarScope is not the child of currentScope");
      currentScope = tarScope;

    }
  }
  private void stepOut()
  {
    currentScope = currentScope.getParent();
  }
  public Compileinfo visit(ASTRoot node) {
    //we assume that the collector has built the global scope
    init(node.getScope());
    var info = new Compileinfo();
    for (ASTDef def : node.getDefList()) {
      if(def instanceof ASTClassDef ){
        //we assume the collector has checked the class
        info.append(def.accept(this));
      }else if(def instanceof ASTFuncDef){
        //we assume the collector has checked the function
        info.append(def.accept(this));
      }else if(def instanceof ASTVarDef){
        //in here we check the variable, for it not allow former declaration
        info.append(def.accept(this));
      }
      assert(currentScope==globalScope);
    }
    assert(currentScope==globalScope);
    return info;
  }
  public Compileinfo visit(ASTClassDef node) {
    //we assume that the collector has built the global scope
    if(node.getScope() == null){
      throw new ErrorBasic("Checker: visit ASTClassDef: scope is null, is class defined not in global Scope?");
    }
    stepIn(node.getScope(),false);
    var info = new Compileinfo();
    for (ASTVarDef v : node.getVarList()) {
      info.append(v.accept(this));
    }

    info.append(node.getConstructor().accept(this));
    for(ASTFuncDef func:node.getFuncDefs()){
      info.append(func.accept(this));
    }
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTFuncDef node) {
    //we assume that the collector has built the parameter scope
    //we do not stepIn, for the function scope is not the child of the parameter scope
    if(node.getScope() == null){
      throw new ErrorBasic("Checker: visit ASTFuncDef: scope is null, is function defined not in global Scope?");
    }
    stepIn(node.getScope(),false);
    var info = new Compileinfo();
    for(ASTStmt stmt:node.getStmtList()){
      stmt.setScope(node.getScope());
      info.append(stmt.accept(this));
    }
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTVarDef node) {
    //we assume that the collector has built the global scope
    node.setScope(currentScope);
    var info = new Compileinfo();
    String name = ((VarLable)node.getLable()).getName();
    String type = ((VarLable)node.getLable()).getType().getName();
    if(currentScope.get(name,Scope.QueryType.FUNC)!=null){
      info.append(new Compileinfo("variable "+name+" has been defined as a function",node.getPosition()));
    }else if(currentScope.get(name,Scope.QueryType.VAR)!=null){
      info.append(new Compileinfo("variable "+name+" has been defined as a variable",node.getPosition()));
    }else if(currentScope.get(type,Scope.QueryType.CLASS,true)==null){
      info.append(new Compileinfo("type undefined",node.getPosition()));
    }else{
      currentScope.declareVar(node.getLabel());
      if(node.getInit()!=null){
        info.append(node.getInit().accept(this));
      }
    }
    return info;
  }
  public Compileinfo visit(ASTDef node) {
    throw new ErrorBasic("visit ASTDef, access denied",node.getPosition());
  }
  public Compileinfo visit(ASTStmt node) {
    throw new ErrorBasic("visit ASTStmt, access denied",node.getPosition());
  }
  public Compileinfo visit(ASTBlockStmt node) {
    boolean isNew = node.getScope()==null;
    if(isNew);{
      stepIn(node.getScope(),true);
      node.setScope(currentScope);
      //the first time to visit the block, create a new scope
    }
    var info = new Compileinfo();
    for(ASTStmt stmt:node.getStmts()){
      info.append(stmt.accept(this));
    }
    if(isNew)
      stepOut();
    return info;
  }
  public Compileinfo visit(ASTBreakStmt node) {
    node.setScope(currentScope);
    Scope Loop = currentScope.findLoop(currentScope);
    if(Loop==null){
      return new Compileinfo("break statement not in loop",node.getPosition());
    }else{
      return new Compileinfo();
    }
  }
  public Compileinfo visit(ASTContStmt node) {
    node.setScope(currentScope);
    Scope Loop = currentScope.findLoop(currentScope);
    if(Loop==null){
      return new Compileinfo("continue statement not in loop",node.getPosition());
    }else{
      return new Compileinfo();
    }
  }
  public Compileinfo visit(ASTExprStmt node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    for(var expr:node.getExprList()){
      info.append(expr.accept(this));
    }
    return info;
  }
  public Compileinfo visit(ASTForStmt node) {
    stepIn(currentScope, true);
    node.setScope(currentScope);
    currentScope.setLoop(true);
    var info = new Compileinfo();
    if(node.getInit()!=null){
      info.append(node.getInit().accept(this));
    }
    if(node.getCond()!=null){
      info.append(node.getCond().accept(this));
    }
    if(node.getStep()!=null){
      info.append(node.getStep().accept(this));
    }
    node.getContent().setScope(currentScope);
    info.append(node.getContent().accept(this));
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTIfStmt node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getCond().accept(this));
    info.append(node.getThen().accept(this));
    if(node.getElse()!=null){
      info.append(node.getElse().accept(this));
    }
    
    return info;
  }
  public Compileinfo visit(ASTRetStmt node) {
    node.setScope(currentScope);
    Scope Func = currentScope.findFunc(currentScope);
    if(Func==null){
      return new Compileinfo("return statement not in Func",node.getPosition());
    }else{
      return node.getRetExpr().accept(this);
    }
  }
  public Compileinfo visit(ASTVarDefStmt node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    for(ASTVarDef var:node.getVarList()){
      info.append(var.accept(this));
    }
    return info;
  }
  public Compileinfo visit(ASTWhileStmt node) {
    stepIn(currentScope, true);
    node.setScope(currentScope);
    currentScope.setLoop(true);
    var info = new Compileinfo();
    info.append(node.getCond().accept(this));
    node.getContent().setScope(currentScope);
    info.append(node.getContent().accept(this));
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTExpr node) {
    throw new ErrorBasic("visit ASTExpr, access denied",node.getPosition());
  }


  public Compileinfo visit(ASTType node) {
    throw new ErrorBasic("visit ASTType, access denied, it should not be on the tree",node.getPosition());
  }

}
