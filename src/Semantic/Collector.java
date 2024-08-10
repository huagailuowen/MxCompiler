package Semantic;

import AST.ASTVisitor;
import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import AST.Node.expr.*;
import AST.Node.stmt.*;
import AST.Node.typ.ASTType;
import Scope.Scope;
import Utility.error.ErrorBasic;
import Utility.label.FuncLable;
import Utility.label.Lable;
import Utility.label.TypeLable;

public class Collector implements ASTVisitor<Compileinfo>{
  protected Scope globalScope;
  protected Scope currentScope;
  private void init()
  {
    globalScope = new Scope(true);
    currentScope = globalScope;
  }
  private void stepIn()
  {
    currentScope = new Scope(currentScope);
  }
  private void stepOut()
  {
    currentScope = currentScope.getParent();
  }
  public Compileinfo visit(ASTRoot node) {
    init();
    var info = new Compileinfo();
    node.setScope(currentScope);
    for (ASTDef def : node.getDefList()) {
    
      if(def instanceof ASTClassDef ){
        String name = ((ASTClassDef)def).getLabel().getName();
        if(currentScope.get(name)!=null){
          info.append(new Compileinfo("class "+name+" has been defined",def.getPosition()));
        }else{
          currentScope.declareClass(((ASTClassDef)def).getLabel().getType());
          info.append(def.accept(this));
        }
      }else if(def instanceof ASTFuncDef){
        String name = ((ASTFuncDef)def).getLabel().getName();
        if(currentScope.get(name)!=null){
          info.append(new Compileinfo("function "+name+" has been defined",def.getPosition()));
        }else{
          currentScope.declareFunc(((ASTFuncDef)def).getLabel());
          info.append(def.accept(this));
        }
      }else if(def instanceof ASTVarDef){
        // throw new ErrorBasic("TO DO, in checker",def.getPosition());
        continue;
        // if(currentScope.get(((ASTVarDef)def).getName())!=null){
        //   info.append(new Compileinfo("variable "+((ASTVarDef)def).getName()+" has been defined",def.getPosition()));
        // }else{
        //   currentScope.declareVar(((ASTVarDef)def).getLabel());
        // }
      }
    }
    //not .equals, but ==, to check if the two scopes are the same
    assert(globalScope==currentScope);
    Lable mainFunc = globalScope.get("main",Scope.QueryType.FUNC);
    if(mainFunc==null || !(mainFunc instanceof FuncLable)){
      info.append(new Compileinfo("function main has not been defined",node.getPosition()));
    }
    return info;
  }
  public Compileinfo visit(ASTDef node) {
    throw new ErrorBasic("visit ASTDef, access denied",node.getPosition());
  }

  public Compileinfo visit(ASTClassDef node) {
    stepIn();
    node.setScope(currentScope);
    currentScope.setClass(true);
    currentScope.setName(node.getLabel().getName());
    var info = new Compileinfo();
    if(node.getConstructor()==null){
      throw new ErrorBasic("class "+node.getLabel().getName()+" has no constructor",node.getPosition());
    }
    info.append(node.getConstructor().accept(this));
    node.getConstructor().getLabel().setReturnType((TypeLable) globalScope.get("void",Scope.QueryType.CLASS));
    //we should not visit the varDef, for it is not allowed to define variable former quote
    
    for (ASTFuncDef f : node.getFuncDefs()) {
      String name = f.getLabel().getName();
      if(currentScope.get(name)!=null){
        info.append(new Compileinfo("function "+name+" has been defined",f.getPosition()));
      }else{
        info.append(f.accept(this));
        String funcname = new String(node.getLabel().getName()+"."+f.getLabel().getName());
        globalScope.declareFunc(funcname,f.getLabel());
      }
    }
    stepOut();
    return info;
  }

  public Compileinfo visit(ASTFuncDef node) {
    stepIn();
    node.setScope(currentScope);
    currentScope.setFunc(true);
    currentScope.setName(node.getLabel().getName());
    var info = new Compileinfo();
    for (ASTVarDef v : node.getParaList()) {
      info.append(v.accept(this));
    }
    stepOut();
    return info;

  }

  public Compileinfo visit(ASTVarDef node) {
    // throw new ErrorBasic("visit ASTVarDef, access denied",node.getPosition());
    var info = new Compileinfo();
    String name = node.getLabel().getName();
    if(currentScope.get(name)!=null){
      return new Compileinfo("variable "+name+" has been defined",node.getPosition());
    }else{
      //we are sure that there is no init expression
      currentScope.declareVar(node.getLabel());
    }
     return info;
  }

  public Compileinfo visit(ASTBlockStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTBreakStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTContStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTEmptyStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }

  public Compileinfo visit(ASTExprStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTForStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTIfStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTRetStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTVarDefStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTWhileStmt node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }

  public Compileinfo visit(ASTArrayExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTAssignExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTAtomExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTBinaryExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTCallExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTChildExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTFStrExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTMemExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTNewExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTPreExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTSuffixExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTTernaryExpr node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }

  public Compileinfo visit(ASTType node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
  public Compileinfo visit(ASTNode node) {
    throw new ErrorBasic("visit access denied",node.getPosition());
  }
}