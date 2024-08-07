package Semantic;

import java.lang.classfile.Label;

import AST.ASTVisitor;
import AST.Node.ASTRoot;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import AST.Node.expr.ASTExpr;
import AST.Node.stmt.ASTStmt;
import AST.Node.typ.ASTType;
import Scope.Scope;
import Utility.label.FuncLable;

public class Collector implements ASTVisitor<Compileinfo>{
  private final Scope globalScope;
  private final Scope currentScope;
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
          currentScope.declareClass(((ASTClassDef)def).getLabel());
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
        // throw ErrorBasic("TO DO, in checker",def.getPosition());
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
    Label mainFunc = globalScope.get("main",Scope.QueryType.FUNC);
    if(mainFunc==null || !(mainFunc instanceof FuncLable)){
      info.append(new Compileinfo("function main has not been defined",node.getPosition()));
    }
    return info;
  }
  public Compileinfo visit(ASTDef node) {
    throw ErrorBasic("visit ASTDef, access denied",node.getPosition());
  }

  public Compileinfo visit(ASTClassDef node) {
    stepIn();
    node.setScope(currentScope);
    var info = new Compileinfo();
    if(node.getConstructor()==null){
      throw ErrorBasic("class "+node.getName()+" has no constructor",node.getPosition());
    }
    info.append(node.getConstructor().accept(this));
    //we should not visit the varDef, for it is not allowed to define variable former quote
    
    for (ASTFuncDef f : node.getFuncList()) {
      String name = f.getLabel().getName();
      if(currentScope.get(name)!=null){
        info.append(new Compileinfo("function "+name+" has been defined",f.getPosition()));
      }else{
        currentScope.declareFunc(f.getLabel());
        info.append(f.accept(this));
      }
    }
    stepOut();
    return info;
  }

  public Compileinfo visit(ASTFuncDef node) {
    stepIn();
    node.setScope(currentScope);
    currentScope.setFunc(true);
    var info = new Compileinfo();
    for (ASTVarDef v : node.getParaList()) {
      info.append(v.accept(this));
    }
    stepOut();
    return info;

  }

  public Compileinfo visit(ASTVarDef node) {
    // throw ErrorBasic("visit ASTVarDef, access denied",node.getPosition());
    var info = new Compileinfo();
    String name = node.getLabel().getName();
    if(currentScope.get(name)!=null){
      return new Compileinfo("variable "+name+" has been defined",node.getPosition());
    }else{
      //we are sure that there is no init expression
      currentScope.declareVar(node.getLabel());
    }
    // return info;
  }

  public Compileinfo visit(ASTStmt node) {
    throw ErrorBasic("visit ASTStmt, access denied",node.getPosition());
  }

  public Compileinfo visit(ASTExpr node) {
    throw ErrorBasic("visit ASTExpr, access denied",node.getPosition());
  }

  public Compileinfo visit(ASTType node) {
    throw ErrorBasic("visit ASTType, access denied",node.getPosition());
  }
  
}