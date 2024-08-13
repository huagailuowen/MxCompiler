package Ir;


import AST.ASTVisitor;
import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import AST.Node.expr.*;
import AST.Node.stmt.*;
import AST.Node.typ.ASTType;
import Ir.Item.Item;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Ir.Node.def.IRClassDef;
import Ir.Node.def.IRFuncDef;
import Ir.Node.def.IRGlobalDef;
import Ir.Type.IRBaseType;
import Ir.Type.IRClassType;
import Ir.Utility.*;
import Scope.*;
import Utility.error.ErrorBasic;
import Utility.label.FuncLable;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor<IRNode>{
  Counter counter;
  private Scope globalScope, currentScope;
  public void init(ASTRoot root) {
    counter = new Counter();
    globalScope = root.getScope();
    currentScope = globalScope;
    for(IRLable lable : BasicClassFunc.BuildInFuncLable) {
      counter.addIndex(lable.getName());
      //prevent the name from being used
    }
  }
  //this is of no use
//  void stepIn(ASTNode node) {
//    if(node.getScope() != null) {
//      currentScope = node.getScope();
//    }else{
//      throw new ErrorBasic("stepIn with no scope");
//    }
//  }
//  void stepOut() {
//    currentScope = currentScope.getParent();
//  }
  @Override
  public IRNode visit(ASTNode node) throws ErrorBasic {
    throw new ErrorBasic("IRBuilder visit ASTNode");
  }
  @Override
  public IRNode visit(ASTRoot node) throws ErrorBasic{
    init(node);
    IRRoot irRoot = new IRRoot();
    //handle the class type
    for(var def : node.getDefList()) {
      if(!(def instanceof ASTClassDef)) {
        continue;
      }
      var memberList = new ArrayList<IRBaseType>();
      for(var varDef : ((ASTClassDef)def).getVarDefs()) {
        memberList.add(new IRBaseType(varDef.getLabel().getType()));
      }
      var name = "%class."+((ASTClassDef)def).getLabel().getName();
      var type = new IRClassType(((ASTClassDef)def).getLabel().getName(),memberList);
      counter.addTypeSize(name, type);
      counter.addClassMem((ASTClassDef)def);
      irRoot.addGlobalDef(new IRGlobalDef(new Item(type,name)));

    }
    //handle the constructor and function of class
    for(var def : node.getDefList()) {
      if(def instanceof ASTClassDef) {
        IRClassDef classDef = (IRClassDef)def.accept(this);
        for(var funcDef : classDef.getFuncList()) {
          irRoot.addFunc(funcDef);
        }
      }
      if(def instanceof ASTFuncDef) {
        irRoot.addFunc((IRFuncDef)def.accept(this));
      }
      if(def instanceof ASTVarDef) {
        irRoot.addGlobalDef(def.accept(this));
      }
    }
    //handle the function

    return irRoot;
  }

  @Override
  public IRNode visit(ASTFuncDef node) throws ErrorBasic{
    IRFuncDef irFuncDef = new IRFuncDef();
    var funcName = node.getLabel().getName();
    if(node.getScope().getParent() != globalScope) {
      funcName = node.getScope().getParent().getName() + "." + funcName;
    }
    IRLable lable = new IRLable('@'+funcName);
    irFuncDef.setName(lable);
    irFuncDef.setReturnType(new IRBaseType(node.getLabel().getReturnType()));
    irFuncDef.setParamList(new ArrayList<>());
    irFuncDef.setBlockList(new ArrayList<>());

    return irFuncDef;
  }
  @Override
  public IRNode visit(ASTVarDef node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTClassDef node) throws ErrorBasic{
    return new IRNode();
  }

  @Override
  public IRNode visit(ASTBlockStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTBreakStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTContStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTEmptyStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTExprStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTForStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTIfStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTRetStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTVarDefStmt node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTWhileStmt node) throws ErrorBasic{
    return new IRNode();
  }

  @Override
  public IRNode visit(ASTArrayExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTAssignExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTAtomExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTBinaryExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTCallExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTChildExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTFStrExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTMemExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTNewExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTPreExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTSuffixExpr node) throws ErrorBasic{
    return new IRNode();
  }
  @Override
  public IRNode visit(ASTTernaryExpr node) throws ErrorBasic{
    return new IRNode();
  }

  @Override
  public IRNode visit(ASTType node) throws ErrorBasic{
    return new IRNode();
  }
}


