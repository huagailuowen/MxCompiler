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
import Ir.Item.LiteralItem;
import Ir.Item.RegItem;
import Ir.Item.StringItem;
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
import java.util.List;

public class IRBuilder implements ASTVisitor<IRNode>{
  Counter counter;

  private Scope globalScope;// currentScope;

  public void init(ASTRoot root) {
    counter = new Counter();
    globalScope = root.getScope();
//    currentScope = globalScope;
    for(FuncLable lable : BasicClassFunc.BuildInFunc) {
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
    var initInsList = new ArrayList<IRIns>();
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
        IRStmt irStmt = (IRStmt)def.accept(this);
        for(var ins : irStmt.getInsList()) {
          if(ins instanceof IRAllocIns){
            irRoot.addGlobalDef(new IRGlobalDef(((IRAllocIns)ins).getDest()));
          }else {
            initInsList.add(ins);
          }
        }

      }
    }
    for(var str : counter.getStringMap().entrySet()) {
      StringItem stringItem = new StringItem(str.getKey(),str.getValue().getName());
      irRoot.addGlobalDef(new IRGlobalDef(stringItem));
    }
    //handle the function
    initInsList.add(new IRRetIns(IRBaseType.getVoidType(),new LiteralItem(IRBaseType.getIntType(),0)));
    irRoot.getInitFunc().setBlockList(IRBlockStmt.makeBlock(new IRStmt(initInsList),"__init__"));
    return irRoot;
  }

  @Override
  public IRNode visit(ASTFuncDef node) throws ErrorBasic{
    IRFuncDef irFuncDef = new IRFuncDef();
    IRStmt irStmt = new IRStmt();
    var funcName = node.getScope().getName() ;
    //this is the real name
    if(funcName.equals("main")) {
      irStmt.addIns(new IRCallIns("__init__", new RegItem(IRBaseType.getIntType(),"..."),new ArrayList<>()));
    }
    IRBaseType retType = new IRBaseType(node.getLabel().getReturnType());
    IRLable lable = new IRLable(funcName);
    ArrayList<RegItem>  paramList = new ArrayList<>();


    var retItem = new RegItem(IRBaseType.getPtrType(),".retaddr."+funcName);
    irStmt.addIns(new IRAllocIns(retType,retItem));
    node.getScope().setRetItem(retItem);
    if(node.getScope().getIrThisName() != null) {
      paramList.add(new RegItem(IRBaseType.getPtrType(),node.getScope().getIrThisName()));
    }
    for(var param : node.getParaList()) {

      var varname = node.getScope().getDetail(param.getLabel().getName(),true).b;
      RegItem ptr=new RegItem(IRBaseType.getPtrType(),varname);
      counter.addItem(varname,ptr);
      RegItem in = new RegItem(new IRBaseType(param.getLabel().getType()),varname+".in");
      paramList.add(in);
      irStmt.addIns(new IRAllocIns(new IRBaseType(param.getLabel().getType()),ptr));
      irStmt.addIns(new IRStoreIns(ptr,in));
    }
    irFuncDef.setName(lable);
    irFuncDef.setReturnType(retType);
    irFuncDef.setParamList(paramList);

    for(var stmt : node.getStmtList()) {
      irStmt.addStmt((IRStmt) stmt.accept(this));
    }

    irStmt.addIns(new IRLable("func."+funcName+".end"));
    irStmt.addIns(new IRLoadIns(retItem,new RegItem(retType,".retval."+funcName)));
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
    RegItem regItem = new RegItem(IRBaseType.getPtrType(),varname);
    counter.addItem(node.getIrName(),regItem);
    irStmt.addIns(new IRAllocIns(new IRBaseType(node.getLabel().getType()),regItem));
    if(node.getInit()!=null){
      IRStmt initStmt = (IRStmt)node.getInit().accept(this);
      irStmt.addStmt(initStmt);
      irStmt.addIns(new IRStoreIns(regItem,initStmt.getDest()));

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
  public static ArrayList<IRIns> makeLoop(String namePreffix,ArrayList<IRIns> insList,ArrayList<IRIns> conditionList,ArrayList<IRIns> bodyList,ArrayList<IRIns> updateList,RegItem conditionDest){
    IRLable conditionLable = new IRLable(namePreffix+".condition");
    IRLable bodyLable = new IRLable(namePreffix+".body");
    IRLable updateLable = new IRLable(namePreffix+".update");
    IRLable endLable = new IRLable(namePreffix+".end");

    if(conditionList == null){
      conditionList = new ArrayList<>();
    }
    if(updateList == null){
      updateList = new ArrayList<>();
    }

    ArrayList<IRIns> loopList = new ArrayList<>(insList);
    loopList.add(new IRJmpIns(conditionLable.getName()));
    loopList.add(conditionLable);
    loopList.addAll(conditionList);
    loopList.add(new IRBranchIns(conditionDest,bodyLable.getName(),endLable.getName()));
    loopList.add(bodyLable);
    loopList.addAll(bodyList);
    loopList.add(new IRJmpIns(updateLable.getName()));
    loopList.add(updateLable);
    loopList.addAll(updateList);
    loopList.add(new IRJmpIns(conditionLable.getName()));
    loopList.add(endLable);
    return loopList;
  }
  @Override
  public IRNode visit(ASTForStmt node) throws ErrorBasic{
    int index = node.getScope().getIndex();
    IRStmt irStmt = new IRStmt();
    var initStmt = (IRStmt)node.getInit().accept(this);
    IRStmt condStmt = null;
    if(node.getCond() != null) {
      condStmt = (IRStmt)node.getCond().accept(this);
    }
    var bodyStmt = (IRStmt)node.getContent().accept(this);
    IRStmt updateStmt = null;
    if(node.getUpdate() != null) {
      updateStmt = (IRStmt)node.getUpdate().accept(this);
    }
    RegItem conditionDest = null;
    if(condStmt != null) {
      conditionDest = condStmt.getDest();
    }
    irStmt.addIns(makeLoop("loop."+index,initStmt.getInsList(),condStmt==null?null:condStmt.getInsList(),bodyStmt.getInsList(),updateStmt==null?null:updateStmt.getInsList(),conditionDest));
    return irStmt;
//    IRLable conditionLable = new IRLable("loop."+node.getScope().getIndex()+".condition");
//    IRLable bodyLable = new IRLable("loop."+node.getScope().getIndex()+".body");
//    IRLable updateLable = new IRLable("loop."+node.getScope().getIndex()+".update");
//    IRLable endLable = new IRLable("loop."+node.getScope().getIndex()+".end");
//
//    IRStmt irStmt = new IRStmt();
//
//    var initStmt = (IRStmt)node.getInit().accept(this);
//    irStmt.addStmt(initStmt);
//    irStmt.addIns(new IRJmpIns(conditionLable.getName()));
//    irStmt.addIns(conditionLable);
//    var condStmt = (IRStmt)node.getCond().accept(this);
//    irStmt.addStmt(condStmt);
//    if(condStmt.getInsList().isEmpty()){
//      irStmt.addIns(new IRJmpIns(bodyLable.getName()));
//    }
//    irStmt.addIns(new IRBranchIns(condStmt.getDest(),bodyLable.getName(),endLable.getName()));
//    var bodyStmt = (IRStmt)node.getContent().accept(this);
//    irStmt.addIns(bodyLable);
//    for(var stmt : bodyStmt.getInsList()) {
//      irStmt.addIns(stmt);
//    }
//    irStmt.addIns(new IRJmpIns(updateLable.getName()));
//    irStmt.addIns(updateLable);
//    var updateStmt = (IRStmt)node.getUpdate().accept(this);
//    irStmt.addStmt(updateStmt);
//    irStmt.addIns(new IRJmpIns(conditionLable.getName()));
//    irStmt.addIns(endLable);
//    return irStmt;
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
    var funScope = Scope.findFunc(node.getScope());
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
    RegItem dest = exprStmt.getDest();


//    var indexList = new ArrayList<Item>();
    int index_num=0;
    for(var index : node.getArray()) {
      index_num++;
      var indexStmt = (IRStmt)index.accept(this);
      irStmt.addStmt(indexStmt);
      RegItem arithItem = new RegItem(IRBaseType.getIntType(),"%arith."+String.valueOf(counter.getArithIndex()));
      counter.addArithIndex();
      irStmt.addIns(new IRArithIns(indexStmt.getDest(),new LiteralItem(IRBaseType.getIntType(),1),arithItem,"+"));
      //the zero index is the size of the array
      RegItem geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()));
      counter.addGeteleIndex();
      irStmt.addIns(new IRGetEleIns(geteleItem,"ptr",dest,new ArrayList<Item>(List.of(arithItem))));
      dest = new RegItem(IRBaseType.getPtrType(),"%load."+String.valueOf(counter.getLoadIndex()));
      counter.addLoadIndex();
      if(node.getLabel().getType().getDimension()==0 && index_num==node.getArray().size()) {
        if (node.getLabel().getName().equals("int")
                || node.getLabel().getName().equals("bool")) {
          dest.setType(IRBaseType.getIntType());
          //the basic type
        }
      }
      irStmt.addIns(new IRLoadIns(geteleItem,dest));
    }
//    var geteleIns = new IRGetEleIns(dest,typename,exprStmt.getDest(),indexList);
    irStmt.setDest(dest);
//    irStmt.addIns(geteleIns);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTAssignExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var rhsStmt = (IRStmt)node.getRhs().accept(this);
    irStmt.addStmt(rhsStmt);
    var lhsStmt = (IRStmt)node.getLhs().accept(this);
    irStmt.addStmt(lhsStmt);
//    throw new ErrorBasic("the lhs of assign now not a lvalue");
    irStmt.addIns(new IRStoreIns((RegItem) lhsStmt.getDestAddr(),rhsStmt.getDest()));
    return irStmt;
  }
  @Override
  public IRNode visit(ASTAtomExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    RegItem dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%load."+String.valueOf(counter.getLoadIndex()));
    counter.addLoadIndex();
    irStmt.setDest(dest);
    if(node.getType() == ASTAtomExpr.AtomType.IDENTIFIER
      || node.getType() == ASTAtomExpr.AtomType.THIS) {
      if(node.getLabel().getName() != null){
        //it means it is a function
        //there is noting to do with
        return irStmt;
      }else{
        //then variable or this
        var varname = node.getIrName();
        RegItem srcItem = null;
        if(varname.contains(".")){
          String [] tmp = varname.split("\\.");
          if(tmp.length == 2 && !tmp[1].matches("\\d+")){
            //this is a class member
            var funcScope = Scope.findFunc(node.getScope());
            if(funcScope == null) {
              throw new ErrorBasic("class member not in class");
            }
            if(funcScope.getIrThisName() == null) {
              throw new ErrorBasic("class member not in class");
            }
            RegItem thisItem = (RegItem) counter.queryItem(funcScope.getIrThisName());
            if(thisItem == null) {
              throw new ErrorBasic("class member not in class");
            }
            RegItem loadItem = new RegItem(IRBaseType.getPtrType(),"%load."+String.valueOf(counter.getLoadIndex()));
            counter.addLoadIndex();
            irStmt.addIns(new IRLoadIns(thisItem,loadItem));
            RegItem geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()));
            counter.addGeteleIndex();
            int index = counter.queryClassMem(varname);
            if(index == -1) {
              throw new ErrorBasic("class member not in class");
            }
            irStmt.addIns(new IRGetEleIns(geteleItem,"%class."+tmp[0],loadItem,new ArrayList<Item>(List.of(new LiteralItem(IRBaseType.getIntType(),index)))));
            srcItem = new RegItem(IRBaseType.getPtrType(),"%load."+String.valueOf(counter.getLoadIndex()));
            counter.addLoadIndex();
            irStmt.addIns(new IRLoadIns(geteleItem,srcItem));

          }
        }
        if(srcItem == null){
          srcItem = (RegItem) counter.queryItem(varname);
        }
        irStmt.setDestAddr(srcItem);
        irStmt.addIns(new IRLoadIns((RegItem) srcItem,dest));
      }
    }else if(node.getType() == ASTAtomExpr.AtomType.STRING){
      if(counter.queryString(node.getValue()) == null) {
        RegItem stringItem = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()));
        counter.addStringIndex();
        counter.addString(node.getValue(),stringItem);
      }
      Item item = counter.queryString(node.getValue());
      irStmt.setDestAddr(item);
      //warning can string be a lvlaue???
      irStmt.addIns(new IRLoadIns(item,dest));
    }else if(node.getType() == ASTAtomExpr.AtomType.ARRAY){
      int size = node.getArray().size();
      var args = new ArrayList<Item>();
      args.add(new LiteralItem(IRBaseType.getIntType(),size));
      irStmt.addIns(new IRCallIns("__malloc",dest,args));

      irStmt.addIns(new IRLoadIns(new LiteralItem(IRBaseType.getIntType(),size),dest));

      for(int i = 0; i < node.getArray().size(); i++) {
        var index = node.getArray().get(i);
        var indexStmt = (IRStmt)index.accept(this);
        irStmt.addStmt(indexStmt);
        var geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()));
        counter.addGeteleIndex();
//        counter.addItem(geteleItem.getName(),geteleItem);
        args = new ArrayList<>();
        args.add(new LiteralItem(IRBaseType.getIntType(),i+1));
        var typename = "ptr";
        if(index.getType() == ASTAtomExpr.AtomType.INT) {
          typename = "i32";
        }else if(index.getType() == ASTAtomExpr.AtomType.BOOL) {
          typename = "i1";
        }
        var geteleIns = new IRGetEleIns(geteleItem,typename,dest,args);
        irStmt.addIns(geteleIns);
//        irStmt.setDestAddr(geteleItem);
        //there is no addr
        irStmt.addIns(new IRStoreIns(geteleItem,indexStmt.getDest()));
      }
    }else{
      //normal type
      IRBaseType type = new IRBaseType(node.getLabel().getType());
      irStmt.addIns(new IRLoadIns(new LiteralItem(type,Integer.parseInt(node.getValue())),dest));
    }
    return irStmt;
  }
  @Override
  public IRNode visit(ASTBinaryExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%arith."+String.valueOf(counter.getArithIndex()));
    counter.addArithIndex();
    counter.addItem(dest.getName(),dest);
    if(node.getOp().equals("+") && node.getLabel().getType().getName().equals("string")){
      //using the call to handle the string
      var lhsStmt = (IRStmt)node.getLhs().accept(this);
      irStmt.addStmt(lhsStmt);
      var rhsStmt = (IRStmt)node.getRhs().accept(this);
      irStmt.addStmt(rhsStmt);
      var args = new ArrayList<Item>();
      args.add(lhsStmt.getDest());
      args.add(rhsStmt.getDest());
      irStmt.addIns(new IRCallIns("__string_add",dest,args));
      return irStmt;
    }
    if(node.getOp().equals("&&") || node.getOp().equals("||")) {

      IRLable rhsLable = new IRLable("arith."+String.valueOf(counter.getArithIndex())+".rhs");
      IRLable endLable = new IRLable("arith."+String.valueOf(counter.getArithIndex())+".end");
      counter.addArithIndex();
      var lhsStmt = (IRStmt)node.getLhs().accept(this);
      irStmt.addStmt(lhsStmt);
      //loadlhs
      irStmt.addIns(new IRLoadIns(lhsStmt.getDest(),dest));

      irStmt.addIns(new IRBranchIns(lhsStmt.getDest(),node.getOp().equals("&&")?rhsLable.getName():endLable.getName(),node.getOp().equals("&&")?endLable.getName():rhsLable.getName()));
      irStmt.addIns(rhsLable);
      var rhsStmt = (IRStmt)node.getRhs().accept(this);
      irStmt.addStmt(rhsStmt);
      irStmt.addIns(new IRBranchIns(rhsStmt.getDest(),endLable.getName(),endLable.getName()));
      //loadrhs
      irStmt.addIns(new IRLoadIns(rhsStmt.getDest(),dest));
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
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()));
    counter.addStringIndex();
    counter.addItem(dest.getName(),dest);
    if(node.getStrs().size()==0){
      throw new ErrorBasic("empty string");
    }
    var tmp = ASTAtomExpr.builder()
            .type(ASTAtomExpr.AtomType.STRING)
            .value(node.getStrs().get(0))
            .build();
    var str = (IRStmt)tmp.accept(this);
    irStmt.addStmt(str);
    irStmt.addIns(new IRLoadIns(str.getDest(),dest));
    for(int i=0;i<node.getArgs().size();i++){
      var expr = (IRStmt)node.getArgs().get(i).accept(this);
      irStmt.addStmt((IRStmt)expr);

      var exprdest = new RegItem(IRBaseType.getPtrType(),"string."+String.valueOf(counter.getStringIndex()));
      counter.addStringIndex();
      counter.addItem(exprdest.getName(),exprdest);
      if(node.getArgs().get(i).getLabel().getType().getName().equals("string")) {
        irStmt.addIns(new IRLoadIns(expr.getDest(), exprdest));
      }else if(node.getArgs().get(i).getLabel().getType().getName().equals("int")) {
        var callArgs = new ArrayList<Item>();
        callArgs.add(expr.getDest());
        irStmt.addIns(new IRCallIns("__int_to_string",exprdest,callArgs));
      }else if(node.getArgs().get(i).getLabel().getType().getName().equals("bool")) {
        var callArgs = new ArrayList<Item>();
        callArgs.add(expr.getDest());
        irStmt.addIns(new IRCallIns("__bool_to_string", exprdest, callArgs));
      }else{
        throw new ErrorBasic("not support type");
      }

      {
        var callArgs = new ArrayList<Item>();
        callArgs.add(dest);
        callArgs.add(exprdest);
        dest = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()));
        counter.addStringIndex();
        counter.addItem(dest.getName(),dest);
        irStmt.addIns(new IRCallIns("__string_add",dest,callArgs));
      }


      tmp = ASTAtomExpr.builder()
              .type(ASTAtomExpr.AtomType.STRING)
              .value(node.getStrs().get(i+1))
              .build();
      str = (IRStmt)tmp.accept(this);

      var callArgs = new ArrayList<Item>();
      callArgs.add(dest);
      callArgs.add(str.getDest());
      dest = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()));
      counter.addStringIndex();
      counter.addItem(dest.getName(),dest);
      irStmt.addIns(new IRCallIns("__string_add",dest,callArgs));
    }

    irStmt.setDest(dest);

    return irStmt;
  }
  @Override
  public IRNode visit(ASTMemExpr node) throws ErrorBasic{

    IRStmt irStmt = new IRStmt();
    var exprStmt = (IRStmt)node.getExpr().accept(this);
    irStmt.addStmt(exprStmt);
    if(node.getLabel().getName()!=null){
      //it means it is a function
      //it contains the this pointer
      //just return the exprStmt
      return exprStmt;
    }
    var geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()));
    counter.addGeteleIndex();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%load."+String.valueOf(counter.getLoadIndex()));
    counter.addLoadIndex();
//    counter.addItem(dest.getName(),dest);
    irStmt.setDest(dest);
    var classname = node.getExpr().getLabel().getType().getName();
    var membername = classname+'.'+node.getMember();
    var index = counter.queryClassMem(membername);
    if(index == -1) {
      throw new ErrorBasic("class member not exist");
    }
    var args = new ArrayList<Item>();
    args.add(new LiteralItem(IRBaseType.getIntType(),0));
    args.add(new LiteralItem(IRBaseType.getIntType(),index));
    irStmt.addIns(new IRGetEleIns(geteleItem,"%class."+classname,exprStmt.getDest(),args));

    irStmt.addIns(new IRLoadIns(geteleItem,dest));
    irStmt.setDestAddr(geteleItem);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTNewExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    if(node.getExpr()!=null){
      //there is no "new int[10] {1,2,3,4,4}"
      //so it is a normal new
      return node.getExpr().accept(this);
    }
    if(node.getType().getLabel().getDimension()==0){
      //the new value
      IRBaseType type = null;
      if(node.getType().getLabel().getName().equals("int")){
        type = IRBaseType.getIntType();
      }else if(node.getType().getLabel().getName().equals("bool")) {
        type = IRBaseType.getBoolType();
      }else{
        type = IRBaseType.getPtrType();
      }
      counter.addLoopIndex();
      var mallocArgs = new ArrayList<Item>();
      var mallocDest = new RegItem(IRBaseType.getPtrType(),"%malloc."+String.valueOf(counter.getMallocIndex()));
      counter.addMallocIndex();
      irStmt.addIns(new IRCallIns("__malloc",mallocDest,mallocArgs));
      var classname = node.getType().getLabel().getName();
      var mallocSize = counter.queryTypeSize(classname);
      if(classname.equals("int")){
        mallocSize = 4;
      }else if(classname.equals("bool")){
        mallocSize = 1;
      }
      irStmt.addIns(new IRCallIns("__malloc",mallocDest,new ArrayList<Item>(List.of(new LiteralItem(IRBaseType.getIntType(),mallocSize)))));
//      irStmt.addIns(new IRLoadIns(mallocDest,dest));
      if(node.getType().getLabel().getName().equals("int")
        || node.getType().getLabel().getName().equals("bool")) {
        irStmt.addIns(new IRStoreIns(mallocDest,new LiteralItem(type,0)));
      }else{
        var constructorname = node.getType().getLabel().getName() + "." + node.getType().getLabel().getName();
        var tmpItem = new RegItem(IRBaseType.getPtrType(),"%malloc."+String.valueOf(counter.getMallocIndex()));
        counter.addMallocIndex();
        irStmt.addIns(new IRCallIns(constructorname,tmpItem,new ArrayList<Item>(List.of(mallocDest))));
      }
      irStmt.setDest(mallocDest);
      return irStmt;

    }

    //self made the for loop to handle the new array
    //like int[10][y]
    var dest = new RegItem(IRBaseType.getPtrType(),"%malloc."+String.valueOf(counter.getMallocIndex()));
    counter.addMallocIndex();


    int index_length = 0;
    var indexList = new ArrayList<IRStmt>();
    var indexAddOneList = new ArrayList<RegItem>();
    var ptrList = new ArrayList<RegItem>();
    var loopVarList = new ArrayList<RegItem>();
    ptrList.add(dest);
    for(var index : node.getType().getDimList()) {
      if(index == null){
        break;
      }
      index_length++;
      var indexStmt = (IRStmt)index.accept(this);
      irStmt.addStmt(indexStmt);
      //handle strictly in order
      indexList.add(indexStmt);
      var indexAddOne = new RegItem(IRBaseType.getIntType(),"%arith."+String.valueOf(counter.getArithIndex()));
      counter.addArithIndex();

      irStmt.addIns(new IRArithIns(indexStmt.getDest(),new LiteralItem(IRBaseType.getIntType(),1),indexAddOne,"+"));
      indexAddOneList.add(indexAddOne);
    }
    var geteledest = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()));
    counter.addGeteleIndex();
    var arithdest = new RegItem(IRBaseType.getIntType(),"%arith."+String.valueOf(counter.getArithIndex()));
    counter.addArithIndex();


    int loopIndex = counter.getLoopIndex();
    counter.addLoopIndex();

    for(int i=0;i<index_length-1;i++){
      ptrList.add(new RegItem(IRBaseType.getPtrType(),"%malloc."+String.valueOf(counter.getMallocIndex())));
      counter.addMallocIndex();
    }
    for(int i=0;i<index_length;i++){
      loopVarList.add(new RegItem(IRBaseType.getIntType(),"%loopvar."+String.valueOf(loopIndex)+"."+String.valueOf(i)));
      counter.addItem(loopVarList.get(i).getName(),loopVarList.get(i));
    }

    {
      irStmt.addIns(new IRCallIns("__malloc_array", ptrList.get(0), new ArrayList<Item>() {{
        add(indexList.get(0).getDest());
      }}));
      irStmt.addIns(new IRGetEleIns(geteledest, "ptr", ptrList.get(0), new ArrayList<Item>() {{
        add(new LiteralItem(IRBaseType.getIntType(), 0));
      }}));
      irStmt.addIns(new IRStoreIns(geteledest, indexList.get(0).getDest()));
    }

    ArrayList<IRIns>insBuilder = new ArrayList<>();
    for(int i = index_length - 1; i >= 0; i--) {

      ArrayList<IRIns> body = new ArrayList<>();
      int finalI = i;
      body.add(new IRGetEleIns(geteledest, "ptr", ptrList.get(i), new ArrayList<Item>() {{
        add(new LiteralItem(IRBaseType.getIntType(), 0));
        add(loopVarList.get(finalI));
      }}));
      if(i==index_length-1) {
        var eletype = IRBaseType.getPtrType();
        if(node.getType().getLabel().getName().equals("int")){
          eletype = IRBaseType.getIntType();
        }else if(node.getType().getLabel().getName().equals("bool")){
          eletype = IRBaseType.getBoolType();
        }
        body.add(new IRStoreIns(geteledest, new LiteralItem(eletype, 0)));
      }else{
        body.add(new IRCallIns("__malloc_array", ptrList.get(i + 1), new ArrayList<Item>() {{
          add(indexList.get(finalI + 1).getDest());
        }}));
        body.add(new IRStoreIns(geteledest, ptrList.get(i + 1)));

        body.add(new IRGetEleIns(geteledest, "ptr", ptrList.get(i + 1), new ArrayList<Item>() {{
          add(new LiteralItem(IRBaseType.getIntType(), 0));
        }}));
        body.add(new IRStoreIns(geteledest, indexList.get(i + 1).getDest()));
        //we store the length in the beginning of the array
        //but for the bool array, we need to let bool be the int
      }

      body.addAll(insBuilder);
      insBuilder = new ArrayList<>();
      ArrayList<IRIns> init = new ArrayList<>();
      init.add(new IRLoadIns(new LiteralItem(IRBaseType.getIntType(), 0), loopVarList.get(i)));

      ArrayList<IRIns> update = new ArrayList<>();
      update.add(new IRArithIns(loopVarList.get(i), new LiteralItem(IRBaseType.getIntType(), 1), loopVarList.get(i), "+"));

      //warning: do we need to Load and Store the loopVar??
      ArrayList<IRIns> condition = new ArrayList<>();
      condition.add(new IRArithIns(loopVarList.get(i), indexAddOneList.get(i), arithdest, "<"));


      insBuilder = makeLoop("loop."+loopIndex+"."+i,init,condition,body,update,arithdest);
    }
    irStmt.addIns(insBuilder);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTPreExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%arith."+String.valueOf(counter.getArithIndex()));
    counter.addArithIndex();
    counter.addItem(dest.getName(),dest);
    var exprStmt = (IRStmt)node.getExpr().accept(this);
    irStmt.addStmt(exprStmt);
    irStmt.setDest(dest);
    IRIns arithIns = null;
    if(node.getOp().equals("++")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "+");
    }else if(node.getOp().equals("--")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "-");
    }else if(node.getOp().equals("+")) {
      arithIns = new IRArithIns(new LiteralItem(new IRBaseType(node.getLabel().getType()), 0), exprStmt.getDest(), dest, "+");
    }else if(node.getOp().equals("-")) {
      arithIns = new IRArithIns(new LiteralItem(new IRBaseType(node.getLabel().getType()), 0), exprStmt.getDest(), dest, "-");
    }else if(node.getOp().equals("~")) {
      arithIns = new IRArithIns(new LiteralItem(new IRBaseType(node.getLabel().getType()), -1), exprStmt.getDest(), dest, "^");
    }else if(node.getOp().equals("!")) {
      arithIns = new IRArithIns(new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), exprStmt.getDest(), dest, "^");
    }else {
      throw new ErrorBasic("not support op");
    }
    irStmt.addIns(arithIns);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTSuffixExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%arith."+String.valueOf(counter.getArithIndex()));
    counter.addArithIndex();
    counter.addItem(dest.getName(),dest);
    var exprStmt = (IRStmt)node.getExpr().accept(this);
    irStmt.addStmt(exprStmt);
    irStmt.setDest(dest);
    IRIns arithIns = null;
    if(node.getOp().equals("++")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "+");
    }else if(node.getOp().equals("--")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "-");
    }else {
      throw new ErrorBasic("not support op");
    }
    irStmt.addIns(arithIns);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTTernaryExpr node) throws ErrorBasic{
    int index = counter.getBranchIndex();
    counter.addBranchIndex();
    IRLable thenLable = new IRLable("ternary."+index+".then");
    IRLable elseLable = new IRLable("ternary."+index+".else");
    IRLable endLable = new IRLable("ternary."+index+".end");

    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%ternary."+String.valueOf(counter.getArithIndex()));
    counter.addArithIndex();
    counter.addItem(dest.getName(),dest);
    IRStmt irStmt = new IRStmt();
    irStmt.setDest(dest);

    var condStmt = (IRStmt)node.getCond().accept(this);
    irStmt.addStmt(condStmt);
    irStmt.addIns(new IRBranchIns(condStmt.getDest(),thenLable.getName(),elseLable.getName()));
    irStmt.addIns(thenLable);
    var thenStmt = (IRStmt)node.getTrueExpr().accept(this);
    irStmt.addStmt(thenStmt);
    irStmt.addIns(new IRLoadIns(thenStmt.getDest(),dest));
    irStmt.addIns(new IRJmpIns(endLable.getName()));
    //-----------------------
    irStmt.addIns(elseLable);
    var elseStmt = (IRStmt)node.getFalseExpr().accept(this);
    irStmt.addStmt(elseStmt);
    irStmt.addIns(new IRLoadIns(elseStmt.getDest(),dest));
    irStmt.addIns(new IRJmpIns(endLable.getName()));
    irStmt.addIns(endLable);
    return irStmt;
  }

  @Override
  public IRNode visit(ASTType node) throws ErrorBasic{
    throw new ErrorBasic("IRBuilder visit ASTType");
//    var irStmt = new IRStmt();
//    int size = 0;
//    for(var dim : node.getDimList()) {
//      if(dim == null){
//        break;
//      }
//      size++;
//      var dimStmt = (IRStmt)dim.accept(this);
//      irStmt.addStmt(dimStmt);
////      size = Integer.parseInt(dim.getValue());
//    }
//
//
//    return irStmt;

  }
}


