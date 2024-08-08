package Semantic;

import AST.ASTVisitor;
import AST.Node.ASTRoot;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import AST.Node.expr.ASTArrayExpr;
import AST.Node.expr.ASTAssignExpr;
import AST.Node.expr.ASTAtomExpr;
import AST.Node.expr.ASTBinaryExpr;
import AST.Node.expr.ASTCallExpr;
import AST.Node.expr.ASTChildExpr;
import AST.Node.expr.ASTExpr;
import AST.Node.expr.ASTFStrExpr;
import AST.Node.expr.ASTMemExpr;
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
import Utility.label.ExprLable;
import Utility.label.FuncLable;
import Utility.label.Label;
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

    for(ASTDef def:node.getDefList()){
      if(!(def instanceof ASTClassDef)){
        continue;
      }
      if(def.getScope() == null){
        throw new ErrorBasic("Checker: visit ASTRoot: scope is null, is class defined not in global Scope?");
      }
      stepIn(def.getScope(),false);
      for(ASTVarDef var:((ASTClassDef)def).getVarDefs()){
        var name = new String(((ASTClassDef)def).getLabel().getName()+'.'+((VarLable)var.getLable()).getName());
        info.append(var.accept(this));
        if(globalScope.get(name)!=null){
          info.append(new Compileinfo("variable "+name+" has been defined",var.getPosition()));
        }else{
          globalScope.declareVar(name, (VarLable)var.getLable());
        }
        
      }
      stepOut();
    }

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
    // for (ASTVarDef v : node.getVarList()) {
    //   info.append(v.accept(this));
    // }
    //we will not do this in here, but in the visitprogram

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
    if(globalScope.get(node.getLabel().getReturnType().getName(),Scope.QueryType.CLASS)==null){
      info.append(new Compileinfo("return type undefined",node.getPosition()));
    for(ASTVarDef var:node.getParaList()){
      if(globalScope.get(var.getLabel().getType().getName(),Scope.QueryType.CLASS)==null){
        info.append(new Compileinfo("parameter type undefined",var.getPosition()));
    }
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

  public Compileinfo visit(ASTArrayExpr node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    node.getLabel().setType(node.getExpr().getLabel().getType());
    if(node.getLabel().getValueType() != ExprLable.ValueType.LVALUE){
      info.append(new Compileinfo("array should be lvalue",node.getPosition()));
    }else{
      node.getLabel().setValueType(ExprLable.ValueType.LVALUE);
    }
    int dim = node.getLabel().getType().getDimension();
    if(dim != node.getArray().size()){
      info.append(new Compileinfo("dimension not match",node.getPosition()));
      // return info;
    }
    for(var expr:node.getArray()){
      info.append(expr.accept(this));
      if(expr.getLabel().getType().getName()!="int"){
        info.append(new Compileinfo("array index should be int",node.getPosition()));
      }
      if(expr.getLabel().getValueType()== ExprLable.ValueType.ABANDON){
        info.append(new Compileinfo("array index should be lvalue or rvalue",node.getPosition()));
      }
    }
    node.getLabel().getType().setDimension(0);
    return info;
  }

  public Compileinfo visit(ASTAssignExpr node) {
    node.setScope(currentScope);
    
    var info = new Compileinfo();
    if(!node.getOp().equals("=")){
      throw new ErrorBasic("Checker: visit ASTAssignExpr: op is not =",node.getPosition());
    }
    info.append(node.getLhs().accept(this));
    info.append(node.getRhs().accept(this));
    
    if(!node.getLhs().getLabel().getType().getName() || !node.getRhs().getLabel().getType().getName()){
      info.append(new Compileinfo("type null",node.getPosition()));
    }else{ 
      if(node.getLhs().getLabel().getType().getDimension()!=0){
        info.append(new Compileinfo("array should not be assigned",node.getPosition()));
      }
      if(node.getRhs().getLabel().getType().getDimension()!=0){
        info.append(new Compileinfo("array should not assign",node.getPosition()));
      }
      if(!node.getLhs().getLabel().getType().getName().equals(node.getRhs().getLabel().getType().getName())){
        info.append(new Compileinfo("type not match",node.getPosition()));
      }if(node.getLhs().getLabel().getValueType()!= ExprLable.ValueType.LVALUE){
        info.append(new Compileinfo("lhs is not lvalue",node.getPosition()));
      }
    }
    throw new ErrorBasic("Checker: visit ASTAssignExpr: access denied",node.getPosition());
    node.getLabel().getType().setDimension(0);
    node.getLabel().setType(node.getLhs().getLabel().getType());
    node.getLabel().setValueType(ExprLable.ValueType.ABANDON);
    //ban the value of the assign expr
    return info;
  }
  
  public Compileinfo visit(ASTAtomExpr node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    //atomexpr has no lable until now
    if(node.getType() == ASTAtomExpr.AtomType.IDENTIFIER){
      Label var = currentScope.get(node.getValue(),true);
      if(var==null){
        info.append(new Compileinfo("Identifier "+node.getValue()+" not defined",node.getPosition()));
        node.setLabel(new ExprLable(null, globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
      }else if(var instanceof VarLable){
        node.setLabel(new ExprLable(null, ((VarLable)var).getType(),ExprLable.ValueType.LVALUE));
      }else if(var instanceof FuncLable){
        node.setLabel(new ExprLable(node.getValue(), ((FuncLable)var).getReturnType(),ExprLable.ValueType.ABANDON));
        //the single func can not be a expression
      }else{
        //class must be recognize as type in .g4
        info.append(new Compileinfo("Identifier "+node.getValue()+" is a class name",node.getPosition()));
      }
    }else if(node.getType() == ASTAtomExpr.AtomType.ARRAY){
      node.getLabel().setType(globalScope.get("null",Scope.QueryType.CLASS,true));
      node.getLabel().setValueType(ExprLable.ValueType.ABANDON);
      for(var expr:node.getArray()){
        info.append(expr.accept(this));
        if(expr.getLabel().getType().getName().equals("null")){
          continue;
        }
        if(!expr.getLabel().getValueType().equals(ExprLable.ValueType.RVALUE)){
          info.append(new Compileinfo("element should be constant",node.getPosition()));
        }else if(node.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
          node.getLabel().setType(expr.getLabel().getType());
        }else{
          if(node.getLabel().getType().getDimension()!=expr.getLabel().getType().getDimension()){
            info.append(new Compileinfo("dimension not match",node.getPosition()));
          }else{
            if(node.getLabel().getType().getName().equals("null")){
              node.getLabel().setType(expr.getLabel().getType());
            }else{
              if(!node.getLabel().getType().getName().equals(expr.getLabel().getType().getName())){
                info.append(new Compileinfo("type not match",node.getPosition()));
              }
            }
          }
        }
      }
      if(node.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
        node.getLabel().setValueType(ExprLable.ValueType.RVALUE);
      }
      node.getLabel().getType().setDimension(node.getLabel().getType().getDimension()+1);

    }else if(node.getType() == ASTAtomExpr.AtomType.BOOL){
      node.setLabel(new ExprLable(null,globalScope.get("bool",Scope.QueryType.CLASS,true),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.INT){
      node.setLabel(new ExprLable(null,globalScope.get("int",Scope.QueryType.CLASS,true),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.NULL){
      node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS,true),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.STRING){
      node.setLabel(new ExprLable(null,globalScope.get("string",Scope.QueryType.CLASS,true),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.THIS){
      Scope Class = currentScope.findClass(currentScope);
      if(Class==null){
        info.append(new Compileinfo("this not in class",node.getPosition()));
        node.setLabel(new ExprLable(null, globalScope.get("null",Scope.QueryType.CLASS), ExprLable.ValueType.ABANDON));
      }else{
        node.setLabel(new ExprLable(Class.getName(),globalScope.get("this",Scope.QueryType.CLASS,true),ExprLable.ValueType.ABANDON));

      }
    }else if(node.getType() == ASTAtomExpr.AtomType.VOID){
      node.setLabel(new ExprLable(null,globalScope.get("void",Scope.QueryType.CLASS,true),ExprLable.ValueType.RVALUE));
    }else{
      throw new ErrorBasic("Checker: visit ASTAtomExpr: type not match",node.getPosition());
    }
    return info;
  }

  public Compileinfo visit(ASTBinaryExpr node)
  {
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getLhs().accept(this));
    info.append(node.getRhs().accept(this));
    if(node.getLhs().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)
      ||node.getRhs().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
      info.append(new Compileinfo("can not compute the ABANDON",node.getPosition()));
      node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
      return info;
    }
    if(!node.getLhs().getLabel().getType().getIsBaseType() 
      || !node.getRhs().getLabel().getType().getIsBaseType()){
      info.append(new Compileinfo("can not compute the non-base type",node.getPosition()));
    }
    if(node.getOp().equals("+")
    ||node.getOp().equals("-")
    ||node.getOp().equals("*")
    ||node.getOp().equals("/")
    ||node.getOp().equals("%")
    ||node.getOp().equals("<<")
    ||node.getOp().equals(">>")
    ||node.getOp().equals("|")
    ||node.getOp().equals("&")
    ||node.getOp().equals("^")){
      if(node.getLhs().getLabel().getType().getName().equals("int")
        && node.getRhs().getLabel().getType().getName().equals("int")){
        node.setLabel(new ExprLable(null,globalScope.get("int",Scope.QueryType.CLASS),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else if(node.getOp().equals("&&")
    ||node.getOp().equals("||")){
      if(node.getLhs().getLabel().getType().getName().equals("bool")
        && node.getRhs().getLabel().getType().getName().equals("bool")){
        node.setLabel(new ExprLable(null,globalScope.get("bool",Scope.QueryType.CLASS),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }
    if(node.getLhs().getLabel().getType().getName().equals(node.getRhs().getLabel().getType().getName())
      && node.getLhs().getLabel().getType().getDimension() == node.getRhs().getLabel().getType().getDimension()){
      node.setLabel(new ExprLable(null,node.getLhs().getLabel().getType(),ExprLable.ValueType.RVALUE));
      if(node.getOp().equals("==")
        ||node.getOp().equals("<=")
        ||node.getOp().equals(">=")
        ||node.getOp().equals("<")
        ||node.getOp().equals(">")
        ||node.getOp().equals("!=")){
        node.getLabel().setType(globalScope.get("bool",Scope.QueryType.CLASS));
      }
        
    }else{
      node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
      info.append(new Compileinfo("type not match",node.getPosition()));
    }
    return info;
  }

  public Compileinfo visit(ASTCallExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    ASTExpr expr = node.getExpr();
    info.append(expr.accept(this));
    if(expr.getLabel().getValueType()!=ExprLable.ValueType.ABANDON){
      info.append(new Compileinfo("call to a non-function",node.getPosition()));
      return info;
    }
    var name = expr.getLabel().getName();
    node.setLabel(new ExprLable(null, globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
    if(name==null){
      info.append(new Compileinfo("call to a non-function",node.getPosition()));
      return info;
    }else if(globalScope.get(name,Scope.QueryType.FUNC)==null){
      info.append(new Compileinfo("function "+name+" not defined",node.getPosition()));
      return info;
    }else{
      FuncLable func = (FuncLable)globalScope.get(name,Scope.QueryType.FUNC);
      if(func.getParamTypes().size()!=node.getArgs().size()){
        info.append(new Compileinfo("parameter number not match",node.getPosition()));
        return info;
      }
      for(int i=0;i<func.getParamTypes().size();i++){
        info.append(node.getArgs().get(i).accept(this));
        if(!node.getArgs().get(i).getLabel().getType().getName().equals(func.getParamTypes().get(i).getName())
          || node.getArgs().get(i).getLabel().getType().getDimension() != func.getParamTypes().get(i).getDimension()){
          info.append(new Compileinfo("parameter type not match",node.getPosition()));
        }
      }
      node.setLabel(new ExprLable(null,func.getReturnType(),ExprLable.ValueType.RVALUE));
      if(func.getReturnType().getName().equals("void")){
        node.getLabel().setValueType(ExprLable.ValueType.ABANDON);
      }
      return info;
    }
    
  }
  public Compileinfo visit(ASTChildExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    node.getLabel().setType(node.getExpr().getLabel().getType());
    return info;
  }

  public Compileinfo visit(ASTFStrExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    node.getLabel().setType(globalScope.get("string",Scope.QueryType.CLASS));
    node.getLabel().setValueType(ExprLable.ValueType.RVALUE);
    for(var expr:node.getArgs()){
      info.append(expr.accept(this));
      if(!expr.getLabel().getType().getIsBaseType())||expr.getLabel().getType().getName().equals("void"){
        info.append(new Compileinfo("format string should not contain non-base type",node.getPosition()));
      }
      if(expr.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
        info.append(new Compileinfo("element should be constant",node.getPosition()));
      }
    }
    return info;
  }

  void visit(ASTMemExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    String typename = node.getExpr().getLabel().getType().getName();
    if(typename == null){
      info.append(new Compileinfo("null has no member",node.getPosition()));
      node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
      return info;
    }
    if(typename.equals("this")){
      var Class = currentScope.findClass(currentScope);
      if(Class==null){
        info.append(new Compileinfo("this not in class",node.getPosition()));
        node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
        return info;
      }
      typename = Class.getName();
    }else if(node.getExpr().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
      info.append(new Compileinfo("member access to a non-class",node.getPosition()));
      node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),ExprLable.ValueType.ABANDON));
      return info;
    }
    node.setLabel(new ExprLable(null,globalScope.get("null",Scope.QueryType.CLASS),node.getExpr().getLabel().getValueType()));
    
    Lable item = globalScope.get(typename+'.'+node.getMember(),Scope.QueryType.ANY);
    if(item==null){
      info.append(new Compileinfo("member "+node.getMember()+" not defined",node.getPosition()));
      return info;
    }else if(item instanceof VarLable){
      node.setLabel(new ExprLable(null,((VarLable)item).getType(),node.getExpr().getLabel().getValueType()));
    }else if(item instanceof FuncLable){
      node.setLabel(new ExprLable(((FuncLable)item).getName(),((FuncLable)item).getReturnType(),ExprLable.ValueType.ABANDON));
    }else{
      info.append(new Compileinfo("member "+node.getMember()+" is a class name",node.getPosition()));
    }
    return info;
  }


  public Compileinfo visit(ASTType node) {
    throw new ErrorBasic("visit ASTType, access denied, it should not be on the tree",node.getPosition());
  }

}
