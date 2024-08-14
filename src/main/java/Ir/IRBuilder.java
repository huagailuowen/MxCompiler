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
import Ir.Item.RegItem;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Ir.Node.def.IRClassDef;
import Ir.Node.def.IRFuncDef;
import Ir.Node.def.IRGlobalDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Node.stmt.IRStmt;
import Ir.Type.IRBaseType;
import Ir.Type.IRClassType;
import Ir.Utility.*;
import Scope.*;
import Utility.error.ErrorBasic;
import Utility.label.FuncLable;

import java.util.ArrayList;

public class IRBuilder implements ASTVisitor<IRNode>{
  Counter counter;
  private Scope globalScope;// currentScope;

  public void init(ASTRoot root) {
    counter = new Counter();
    globalScope = root.getScope();
//    currentScope = globalScope;
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
    var funcName = node.getScope().getName() ;
    //this is the real name
    IRBaseType retType = new IRBaseType(node.getLabel().getReturnType());
    IRLable lable = new IRLable('@'+funcName);
    ArrayList<RegItem>  paramList = new ArrayList<>();
    node.getScope().setRetItem(new RegItem(new IRBaseType(node.getLabel().getReturnType()),".retval."+funcName));
    if(node.getScope().getIrThisName() != null) {
      paramList.add(new RegItem(IRBaseType.getPtrType(),node.getScope().getIrThisName()));
    }
    for(var param : node.getParaList()) {
      paramList.add(new RegItem(new IRBaseType(param.getLabel().getType()),param.getLabel().getName()));
    }
    irFuncDef.setName(lable);
    irFuncDef.setReturnType(retType);
    irFuncDef.setParamList(paramList);

    IRStmt irStmt = new IRStmt();
    for(var stmt : node.getStmtList()) {
      irStmt.addStmt((IRStmt) stmt.accept(this));
    }
    irStmt.addIns(new IRLable("func."+funcName+".end"));
    irStmt.addIns(new IRRetIns(retType,(RegItem)node.getScope().getRetItem()));
    irFuncDef.setBlockList(IRBlockStmt.makeBlock(irStmt,funcName));
    return irFuncDef;
  }
  @Override
  public IRNode visit(ASTVarDef node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    String varname = node.getIrName();
    if(node.getScope() != globalScope){
      varname = "@"+varname;
    }else{
      varname = "%"+varname;
    }

    if(node.getInit()!=null){
      IRStmt initStmt = (IRStmt)node.getInit().accept(this);
      irStmt.addStmt(initStmt);
      RegItem regItem = new RegItem(new IRBaseType(node.getLabel().getType()),varname);
      irStmt.addIns(new IRStoreIns(initStmt.getDest(),regItem));
      counter.addItem(node.getIrName(),regItem);
    }

    return irStmt;
  }
  @Override
  public IRNode visit(ASTClassDef node) throws ErrorBasic{
    IRClassDef irClassDef = new IRClassDef();
    ArrayList<IRBaseType> memberList = new ArrayList<>();
    for(var varDef : node.getVarDefs()) {
      memberList.add(new IRBaseType(varDef.getLabel().getType()));
    }
    ArrayList<IRFuncDef> funcList = new ArrayList<>();
    for(var funcDef : node.getFuncDefs()) {
      funcList.add((IRFuncDef)funcDef.accept(this));
    }
    irClassDef.setFuncList(funcList);
    return irClassDef;
  }

  @Override
  public IRNode visit(ASTBlockStmt node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    for(var stmt : node.getStmts()) {
      irStmt.addStmt((IRStmt)stmt.accept(this));
    }
    return irStmt;
  }
  @Override
  public IRNode visit(ASTBreakStmt node) throws ErrorBasic{
    var loopScope = node.getScope().findLoop(node.getScope());
    if(loopScope == null) {
      throw new ErrorBasic("break not in loop");
    }
    IRStmt irStmt = new IRStmt();
    int index = loopScope.getIndex();
    irStmt.addIns(new IRJmpIns("loop."+index+".end"));
    return irStmt;
  }
  @Override
  public IRNode visit(ASTContStmt node) throws ErrorBasic{
    var loopScope = node.getScope().findLoop(node.getScope());
    if(loopScope == null) {
      throw new ErrorBasic("continue not in loop");
    }
    IRStmt irStmt = new IRStmt();
    int index = loopScope.getIndex();
    irStmt.addIns(new IRJmpIns("loop."+index+".update"));
    return irStmt;
  }
  @Override
  public IRNode visit(ASTEmptyStmt node) throws ErrorBasic{
    return new IRStmt();
  }
  @Override
  public IRNode visit(ASTExprStmt node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    for(var expr : node.getExprList()) {
      irStmt.addStmt((IRStmt)expr.accept(this));
    }
    return irStmt;
  }
  @Override
  public IRNode visit(ASTForStmt node) throws ErrorBasic{
    IRLable conditionLable = new IRLable("loop."+node.getScope().getIndex()+".condition");
    IRLable bodyLable = new IRLable("loop."+node.getScope().getIndex()+".body");
    IRLable updateLable = new IRLable("loop."+node.getScope().getIndex()+".update");
    IRLable endLable = new IRLable("loop."+node.getScope().getIndex()+".end");

    IRStmt irStmt = new IRStmt();

    var initStmt = (IRStmt)node.getInit().accept(this);
    irStmt.addStmt(initStmt);
    irStmt.addIns(new IRJmpIns(conditionLable.getName()));
    irStmt.addIns(conditionLable);
    var condStmt = (IRStmt)node.getCond().accept(this);
    irStmt.addStmt(condStmt);
    if(condStmt.getInsList().isEmpty()){
      irStmt.addIns(new IRJmpIns(bodyLable.getName()));
    }
    irStmt.addIns(new IRBranchIns(condStmt.getDest(),bodyLable.getName(),endLable.getName()));
    var bodyStmt = (IRStmt)node.getContent().accept(this);
    irStmt.addIns(bodyLable);
    for(var stmt : bodyStmt.getInsList()) {
      irStmt.addIns(stmt);
    }
    irStmt.addIns(new IRJmpIns(updateLable.getName()));
    irStmt.addIns(updateLable);
    var updateStmt = (IRStmt)node.getUpdate().accept(this);
    irStmt.addStmt(updateStmt);
    irStmt.addIns(new IRJmpIns(conditionLable.getName()));
    irStmt.addIns(endLable);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTIfStmt node) throws ErrorBasic{
    IRLable endLable = new IRLable("if."+node.getScope().getIndex()+".end");
    IRLable thenLable = new IRLable("if."+node.getScope().getIndex()+".then");
    IRLable elseLable = new IRLable("if."+node.getScope().getIndex()+".else");

    IRStmt irStmt = new IRStmt();
    var condStmt = (IRStmt)node.getCond().accept(this);
    irStmt.addStmt(condStmt);
    irStmt.addIns(new IRBranchIns(condStmt.getDest(),thenLable.getName(),elseLable.getName()));
    irStmt.addIns(thenLable);
    var thenStmt = (IRStmt)node.getThenStmt().accept(this);
    irStmt.addStmt(thenStmt);
    irStmt.addIns(new IRJmpIns(endLable.getName()));
    irStmt.addIns(elseLable);
    if(node.getElseStmt() != null) {
      var elseStmt = (IRStmt)node.getElseStmt().accept(this);
      irStmt.addStmt(elseStmt);
    }
    irStmt.addIns(new IRJmpIns(endLable.getName()));
    irStmt.addIns(endLable);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTRetStmt node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var funScope = node.getScope().findFunc(node.getScope());
    if(funScope == null) {
      throw new ErrorBasic("return not in function");
    }
    if(node.getRetExpr() != null) {
      var exprStmt = (IRStmt)node.getRetExpr().accept(this);
      irStmt.addStmt(exprStmt);
      irStmt.addIns(new IRStoreIns(exprStmt.getDest(),(RegItem) funScope.getRetItem()));
    }
    irStmt.addIns(new IRJmpIns("func."+funScope.getName()+".end"));
    return irStmt;
  }
  @Override
  public IRNode visit(ASTVarDefStmt node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    for(var varDef : node.getVarList()) {
      irStmt.addStmt((IRStmt)varDef.accept(this));
    }
    return irStmt;
  }
  @Override
  public IRNode visit(ASTWhileStmt node) throws ErrorBasic{
    IRLable conditionLable = new IRLable("loop."+node.getScope().getIndex()+".condition");
    IRLable bodyLable = new IRLable("loop."+node.getScope().getIndex()+".body");
    IRLable endLable = new IRLable("loop."+node.getScope().getIndex()+".end");

    IRStmt irStmt = new IRStmt();
    irStmt.addIns(new IRJmpIns(conditionLable.getName()));
    irStmt.addIns(conditionLable);
    var condStmt = (IRStmt)node.getCondition().accept(this);
    irStmt.addStmt(condStmt);
    irStmt.addIns(new IRBranchIns(condStmt.getDest(),bodyLable.getName(),endLable.getName()));
    irStmt.addIns(bodyLable);
    var bodyStmt = (IRStmt)node.getContent().accept(this);
    irStmt.addStmt(bodyStmt);
    irStmt.addIns(new IRJmpIns(conditionLable.getName()));
    irStmt.addIns(endLable);
    return irStmt;
  }

  @Override
  public IRNode visit(ASTArrayExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var exprStmt = (IRStmt)node.getExpr().accept(this);
    irStmt.addStmt(exprStmt);
    String typename = "%class."+node.getExpr().getLabel().getType().getName();
    if(node.getExpr().getLabel().getType().getIsBaseType()){
      var tmp = new IRBaseType(node.getExpr().getLabel().getType());
      typename = tmp.getName();
    }
    var dest = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()));
    counter.addGeteleIndex();
    counter.addItem(dest.getName(),dest);
    irStmt.setDest(dest);
    var indexList = new ArrayList<Item>();
    for(var index : node.getArray()) {
      var indexStmt = (IRStmt)index.accept(this);
      irStmt.addStmt(indexStmt);
      indexList.add(indexStmt.getDest());
    }
    var geteleIns = new IRGetEleIns(dest,typename,exprStmt.getDest(),indexList);
    irStmt.addIns(geteleIns);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTAssignExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var rhsStmt = (IRStmt)node.getRhs().accept(this);
    irStmt.addStmt(rhsStmt);
    var lhsStmt = (IRStmt)node.getLhs().accept(this);
    irStmt.addStmt(lhsStmt);
    irStmt.addIns(new IRStoreIns(rhsStmt.getDest(),lhsStmt.getDest()));
    return irStmt;
  }
  @Override
  public IRNode visit(ASTAtomExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();


    return irStmt;
  }
  @Override
  public IRNode visit(ASTBinaryExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%arith."+String.valueOf(counter.getArithIndex()));
    counter.addArithIndex();
    counter.addItem(dest.getName(),dest);
    if(node.getOp().equals("&&") || node.getOp().equals("||")) {

      IRLable rhsLable = new IRLable("arith."+String.valueOf(counter.getArithIndex())+".rhs");
      IRLable endLable = new IRLable("arith."+String.valueOf(counter.getArithIndex())+".end");
      counter.addArithIndex();
      var lhsStmt = (IRStmt)node.getLhs().accept(this);
      irStmt.addStmt(lhsStmt);
      //loadlhs
      irStmt.addIns(new IRLoadIns(lhsStmt.getDest(),dest,IRBaseType.getBoolType()));

      irStmt.addIns(new IRBranchIns(lhsStmt.getDest(),node.getOp().equals("&&")?rhsLable.getName():endLable.getName(),node.getOp().equals("&&")?endLable.getName():rhsLable.getName()));
      irStmt.addIns(rhsLable);
      var rhsStmt = (IRStmt)node.getRhs().accept(this);
      irStmt.addStmt(rhsStmt);
      irStmt.addIns(new IRBranchIns(rhsStmt.getDest(),endLable.getName(),endLable.getName()));
      //loadrhs
      irStmt.addIns(new IRLoadIns(rhsStmt.getDest(),dest,IRBaseType.getBoolType()));
      irStmt.addIns(endLable);
      return irStmt;
    }
    var lhsStmt = (IRStmt)node.getLhs().accept(this);
    irStmt.addStmt(lhsStmt);
    var rhsStmt = (IRStmt)node.getRhs().accept(this);
    irStmt.addStmt(rhsStmt);

    irStmt.setDest(dest);
    var arithIns = new IRArithIns(lhsStmt.getDest(),rhsStmt.getDest(),dest,node.getOp());
    irStmt.addIns(arithIns);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTCallExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    ArrayList<Item> paramList = new ArrayList<>();
    var exprStmt = (IRStmt)node.getExpr().accept(this);
    irStmt.addStmt(exprStmt);
    for(var param : node.getArgs()) {
      var paramStmt = (IRStmt)param.accept(this);
      irStmt.addStmt(paramStmt);
      paramList.add(paramStmt.getDest());
    }
    var funcname = node.getExpr().getLabel().getName();
    if(funcname == null) {
      throw new ErrorBasic("call expr with no func name");
    }
    var funcInfo = node.getScope().getDetail(funcname,true);
    funcname = funcInfo.b;

    var dest = new RegItem(new IRBaseType(((FuncLable)funcInfo.a).getReturnType()),"%call."+String.valueOf(counter.getCallIndex()));
    counter.addCallIndex();
    counter.addItem(dest.getName(),dest);
    irStmt.setDest(dest);
    irStmt.addIns(new IRCallIns(funcname,dest,paramList));
    return irStmt;
  }
  @Override
  public IRNode visit(ASTChildExpr node) throws ErrorBasic{
    return node.getExpr().accept(this);
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
    throw new ErrorBasic("IRBuilder visit ASTType");
  }
}


