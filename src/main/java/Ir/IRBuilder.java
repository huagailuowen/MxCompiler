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
import Utility.label.ExprLable;
import Utility.label.FuncLable;
import Utility.label.TypeLable;

import java.util.ArrayList;
import java.util.List;

public class IRBuilder implements ASTVisitor<IRNode>{
  Counter counter;

  private Scope globalScope;// currentScope;

  public void init(ASTRoot root) {
    counter = root.getCounter();
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
    irRoot.setCounter(counter);
    irRoot.setScope(globalScope);
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
          if(ins instanceof IRAllocIns && ((IRAllocIns)ins).getDest().getName().startsWith("@")){
            irRoot.addGlobalDef(new IRGlobalDef(((IRAllocIns)ins).getDest()));
          }else {
            initInsList.add(ins);
          }
        }

      }
    }
    for(var str : counter.getStringMap().entrySet()) {
      StringItem stringItem = new StringItem(str.getValue().getName(),str.getKey());
      irRoot.addGlobalDef(new IRGlobalDef(stringItem));
    }
    //handle the function
    //handle the function
    initInsList.add(new IRRetIns(IRBaseType.getVoidType(),null));
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
      irStmt.addIns(new IRCallIns("__init__", null,new ArrayList<>()));
    }
    IRBaseType retType = new IRBaseType(node.getLabel().getReturnType());
    IRLable lable = new IRLable(funcName);
    ArrayList<RegItem>  paramList = new ArrayList<>();


    var retItem = new RegItem(IRBaseType.getPtrType(),"%.retaddr."+funcName,node.getLabel().getReturnType().getPtrType());
    retItem.setValueType(retType);
    if(!retType.getName().equals("void"))
      irStmt.addIns(new IRAllocIns(retType,retItem));

    node.getScope().setRetItem(retItem);
    if(node.getScope().getIrThisName() != null) {
      var typeName = Scope.findClass(node.getScope()).getName();
      var type = new TypeLable(typeName,1);
      //the type of this
      var in = new RegItem(IRBaseType.getPtrType(),"%"+node.getScope().getIrThisName()+".in",type);
      paramList.add(in);
      RegItem ptr = new RegItem(IRBaseType.getPtrType(),"%"+node.getScope().getIrThisName(), type.getPtrType());
      ptr.setValueType(IRBaseType.getPtrType());
      counter.addItem(node.getScope().getIrThisName(),ptr);
      irStmt.addIns(new IRAllocIns(IRBaseType.getPtrType(),ptr));
      irStmt.addIns(new IRStoreIns(ptr,in));
    }
    for(var param : node.getParaList()) {

      var varname = node.getScope().getDetail(param.getLabel().getName(),false).b;
      var type = param.getLabel().getType();
      RegItem ptr=new RegItem(IRBaseType.getPtrType(),"%"+varname,type.getPtrType());
      counter.addItem(varname,ptr);
      var valueTypeName = new IRBaseType(param.getLabel().getType());
      RegItem in = new RegItem(valueTypeName,"%"+varname+".in",type);
      ptr.setValueType(valueTypeName);
      paramList.add(in);
      irStmt.addIns(new IRAllocIns(valueTypeName,ptr));
      irStmt.addIns(new IRStoreIns(ptr,in));
    }
    irFuncDef.setName(lable);
    irFuncDef.setReturnType(retType);
    irFuncDef.setParamList(paramList);

    for(var stmt : node.getStmtList()) {
      irStmt.addStmt((IRStmt) stmt.accept(this));
    }

    irStmt.addIns(new IRLable("func."+funcName+".end"));
    var retvalItem = new RegItem(retType,"%.retval."+funcName,node.getLabel().getReturnType());
    if(!retType.getName().equals("void"))
      irStmt.addIns(new IRLoadIns(retItem,retvalItem));
    irStmt.addIns(new IRRetIns(retType,retvalItem));

    irFuncDef.setBlockList(IRBlockStmt.makeBlock(irStmt,funcName));
    return irFuncDef;
  }
  @Override
  public IRNode visit(ASTVarDef node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    String varname = node.getIrName();
    if(node.getScope() == globalScope){
      varname = "@"+varname;
    }else{
      varname = "%"+varname;
    }
    RegItem regItem = new RegItem(IRBaseType.getPtrType(),varname,node.getLabel().getType().getPtrType());
    regItem.setValueType(new IRBaseType(node.getLabel().getType()));
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
    funcList.add((IRFuncDef) node.getConstructor().accept(this));

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
  public static ArrayList<IRIns> makeLoop(String namePreffix,ArrayList<IRIns> insList,ArrayList<IRIns> conditionList,ArrayList<IRIns> bodyList,ArrayList<IRIns> updateList,Item conditionDest){
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
    if(conditionDest == null){
      loopList.add(new IRJmpIns(bodyLable.getName()));
    }else {
      loopList.add(new IRBranchIns(conditionDest, bodyLable.getName(), endLable.getName()));
    }
    loopList.add(bodyLable);
    loopList.addAll(bodyList);
    loopList.add(new IRJmpIns(updateLable.getName()));
    if(namePreffix.equals("loop.7")){
      int pp=1;
    }
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
    Item conditionDest = null;
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
      irStmt.addIns(new IRStoreIns((RegItem) funScope.getRetItem(),exprStmt.getDest()));
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
    IRLable updateLable = new IRLable("loop."+node.getScope().getIndex()+".update");
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
    irStmt.addIns(new IRJmpIns(updateLable.getName()));
    irStmt.addIns(updateLable);
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
    Item dest = exprStmt.getDest();


//    var indexList = new ArrayList<Item>();
    int index_num=0;
    for(var index : node.getArray()) {
      index_num++;
      var indexStmt = (IRStmt)index.accept(this);
      irStmt.addStmt(indexStmt);
//      RegItem arithItem = new RegItem(IRBaseType.getIntType(),"%arith."+String.valueOf(counter.getArithIndex()));
//      counter.addArithIndex();
//      irStmt.addIns(new IRArithIns(indexStmt.getDest(),new LiteralItem(IRBaseType.getIntType(),1),arithItem,"+"));
      //the zero index is the size of the array

      var type = IRBaseType.getPtrType();
      if(node.getLabel().getType().getDimension()==0 && index_num==node.getArray().size()) {
        if (node.getLabel().getType().getName().equals("int")) {
          type = IRBaseType.getIntType();
          //the basic type
        }else if(node.getLabel().getType().getName().equals("bool")){
          type = IRBaseType.getBoolType();
        }
      }
      var curType = new TypeLable(node.getLabel().getType().getName(),node.getLabel().getType().getDimension()+node.getArray().size() - index_num);
      RegItem geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()),curType.getPtrType());
      counter.addGeteleIndex();
      irStmt.addIns(new IRGetEleIns(geteleItem,type.getName(),dest,new ArrayList<Item>(List.of(indexStmt.getDest()))));
      dest = new RegItem(type,"%load."+String.valueOf(counter.getLoadIndex()),curType);
      counter.addLoadIndex();


      irStmt.addIns(new IRLoadIns(geteleItem,(RegItem)dest));
      irStmt.setDestAddr(geteleItem);
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
    Item dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%load."+String.valueOf(counter.getLoadIndex()),node.getLabel().getType());
    counter.addLoadIndex();
    if(node.getType() == ASTAtomExpr.AtomType.IDENTIFIER
      || node.getType() == ASTAtomExpr.AtomType.THIS) {
      if(node.getLabel().getName() != null){
        //it means it is a function
        //there is noting to do with
        //it not contain 'this'
        var info = node.getScope().getDetail(node.getLabel().getName(),true);
        if(info.b.contains(".")){
          //have this
          Scope funcScope =  Scope.findFunc(node.getScope());
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
          dest.setType(IRBaseType.getPtrType());
          irStmt.addIns(new IRLoadIns(thisItem,(RegItem) dest));
          irStmt.setDest(dest);
        }
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
            RegItem loadItem = new RegItem(IRBaseType.getPtrType(),"%load."+String.valueOf(counter.getLoadIndex()),thisItem.getRealType().getvalType());
            counter.addLoadIndex();
            irStmt.addIns(new IRLoadIns(thisItem,loadItem));
            RegItem geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()),node.getLabel().getType());
            counter.addGeteleIndex();
            int index = counter.queryClassMem(varname);
            if(index == -1) {
              throw new ErrorBasic("class member not in class");
            }
            irStmt.addIns(new IRGetEleIns(geteleItem,"%class."+tmp[0],loadItem,new ArrayList<Item>(List.of(new LiteralItem(IRBaseType.getIntType(),0),new LiteralItem(IRBaseType.getIntType(),index)))));
//            srcItem = new RegItem(IRBaseType.getPtrType(),"%load."+String.valueOf(counter.getLoadIndex()));
//            counter.addLoadIndex();
            srcItem = geteleItem;
//            irStmt.addIns(new IRLoadIns(geteleItem,srcItem));

          }
        }
        if(srcItem == null){
          srcItem = (RegItem) counter.queryItem(varname);
        }
        irStmt.setDestAddr(srcItem);
        irStmt.addIns(new IRLoadIns((RegItem) srcItem,(RegItem) dest));
      }

    }else if(node.getType() == ASTAtomExpr.AtomType.STRING){
      if(counter.queryString(node.getValue()) == null) {
        RegItem stringItem = new RegItem(IRBaseType.getPtrType(),"@string."+String.valueOf(counter.getStringIndex()),new TypeLable("string",1));
        stringItem.setValueType(IRBaseType.getPtrType());
        counter.addStringIndex();
        counter.addString(node.getValue(),stringItem);
      }
      dest = counter.queryString(node.getValue());
      //warning can string be a lvlaue???
//      irStmt.addIns(new IRLoadIns(item,dest));
    }else if(node.getType() == ASTAtomExpr.AtomType.ARRAY){
      int size = node.getArray().size();
      var args = new ArrayList<Item>();
      args.add(new LiteralItem(IRBaseType.getIntType(),size));
      if(node.getLabel().getType().getDimension()>1){
        args.add(new LiteralItem(IRBaseType.getIntType(),IRBaseType.getPtrType().getSize()));
      }else if(node.getLabel().getType().getName().equals("int")){
        args.add(new LiteralItem(IRBaseType.getIntType(),IRBaseType.getIntType().getSize()));
      }else if(node.getLabel().getType().getName().equals("bool")){
        args.add(new LiteralItem(IRBaseType.getIntType(),IRBaseType.getBoolType().getSize()));
      }else{
        args.add(new LiteralItem(IRBaseType.getIntType(),IRBaseType.getPtrType().getSize()));
      }


      irStmt.addIns(new IRCallIns("__malloc_array",(RegItem) dest,args));
      //the maintainance of size is up to the buildin function

      for(int i = 0; i < node.getArray().size(); i++) {
        var index = node.getArray().get(i);
        var indexStmt = (IRStmt)index.accept(this);
        irStmt.addStmt(indexStmt);
        var geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()),((RegItem) dest).getRealType());
        counter.addGeteleIndex();
//        counter.addItem(geteleItem.getName(),geteleItem);
        args = new ArrayList<>();
        args.add(new LiteralItem(IRBaseType.getIntType(),i));
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
    }else if(node.getType() == ASTAtomExpr.AtomType.NULL){
      dest = new LiteralItem(IRBaseType.getPtrType(),0);
    }else{
      //normal type
      IRBaseType type = new IRBaseType(node.getLabel().getType());
      if(type.getName().equals("i1")){
        if(node.getValue().equals("true")){
          dest = new LiteralItem(type,1);
        }else if(node.getValue().equals("false")) {
          dest = new LiteralItem(type, 0);
        }else{
          throw new ErrorBasic("bool type not true or false");
        }
      }else if(type.getName().equals("i32")){
        dest = new LiteralItem(type,Integer.parseInt(node.getValue()));
      }else{
        throw new ErrorBasic("not support type");
      }

    }
    irStmt.setDest(dest);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTBinaryExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%arith."+String.valueOf(counter.getArithIndex()),node.getLabel().getType());
    counter.addArithIndex();
    irStmt.setDest(dest);
    if(node.getLhs().getLabel().getType().getName().equals("string")){
      //using the call to handle the string
      var lhsStmt = (IRStmt)node.getLhs().accept(this);
      irStmt.addStmt(lhsStmt);
      var rhsStmt = (IRStmt)node.getRhs().accept(this);
      irStmt.addStmt(rhsStmt);
      var args = new ArrayList<Item>();
      args.add(lhsStmt.getDest());
      args.add(rhsStmt.getDest());
      var tmpdest = new RegItem(IRBaseType.getIntType(),"%arith."+String.valueOf(counter.getArithIndex()),new TypeLable("bool"));
      //the res store in the form of int, but it is actually a bool
      counter.addArithIndex();

      if(node.getOp().equals("+")){
        irStmt.addIns(new IRCallIns("__string_concat",dest,args));
      }else if(node.getOp().equals("<")) {
        irStmt.addIns(new IRCallIns("__string_lt", tmpdest, args));
        irStmt.addIns(new IRArithIns(tmpdest,new LiteralItem(IRBaseType.getIntType(),1),dest,"=="));
      }else if(node.getOp().equals(">")) {
        irStmt.addIns(new IRCallIns("__string_gt", tmpdest, args));
        irStmt.addIns(new IRArithIns(tmpdest,new LiteralItem(IRBaseType.getIntType(),1),dest,"=="));
      }else if(node.getOp().equals("<=")) {
        irStmt.addIns(new IRCallIns("__string_le", tmpdest, args));
        irStmt.addIns(new IRArithIns(tmpdest,new LiteralItem(IRBaseType.getIntType(),1),dest,"=="));
      }else if(node.getOp().equals(">=")) {
        irStmt.addIns(new IRCallIns("__string_ge", tmpdest, args));
        irStmt.addIns(new IRArithIns(tmpdest,new LiteralItem(IRBaseType.getIntType(),1),dest,"=="));
      }else if(node.getOp().equals("==")) {
        irStmt.addIns(new IRCallIns("__string_eq", tmpdest, args));
        irStmt.addIns(new IRArithIns(tmpdest,new LiteralItem(IRBaseType.getIntType(),1),dest,"=="));
      }else if(node.getOp().equals("!=")) {
        irStmt.addIns(new IRCallIns("__string_ne", tmpdest, args));
        irStmt.addIns(new IRArithIns(tmpdest,new LiteralItem(IRBaseType.getIntType(),1),dest,"=="));
      }else{
        throw new ErrorBasic("not support string op");
      }
      return irStmt;
    }
    if(node.getOp().equals("&&") || node.getOp().equals("||")) {
      //short circuit
      var destAddr = new RegItem(IRBaseType.getPtrType(),"%arith."+String.valueOf(counter.getArithIndex())+".addr",new TypeLable("bool",1));
      destAddr.setValueType(IRBaseType.getBoolType());
      irStmt.addIns(new IRAllocIns(IRBaseType.getBoolType(),destAddr));
      IRLable rhsLable = new IRLable("arith."+String.valueOf(counter.getArithIndex())+".rhs");
      IRLable endLable = new IRLable("arith."+String.valueOf(counter.getArithIndex())+".end");
      counter.addArithIndex();
      var newLable = false;
      if(node.getEndLable() == null){
        node.setEndLable(endLable);
        node.setEndRes(destAddr);
        newLable = true;
      }else{
//        endLable = node.getEndLable();
        destAddr = node.getEndRes();
      }
      if(node.getLhs() instanceof ASTBinaryExpr ){
        if(((ASTBinaryExpr) node.getLhs()).getOp().equals(node.getOp())){
          ((ASTBinaryExpr) node.getLhs()).setEndLable(node.getEndLable());
          ((ASTBinaryExpr) node.getLhs()).setEndRes(destAddr);
        }
      }
      if(node.getRhs() instanceof ASTBinaryExpr ){
        if(((ASTBinaryExpr) node.getRhs()).getOp().equals(node.getOp())){
          ((ASTBinaryExpr) node.getRhs()).setEndLable(node.getEndLable());
          ((ASTBinaryExpr) node.getRhs()).setEndRes(destAddr);
        }
      }

      var lhsStmt = (IRStmt)node.getLhs().accept(this);
      irStmt.addStmt(lhsStmt);
      //storelhs
      irStmt.addIns(new IRStoreIns(destAddr,lhsStmt.getDest()));

      irStmt.addIns(new IRBranchIns(lhsStmt.getDest(),node.getOp().equals("&&")?rhsLable.getName():node.getEndLable().getName(),node.getOp().equals("&&")?node.getEndLable().getName():rhsLable.getName()));

      irStmt.addIns(rhsLable);
      var rhsStmt = (IRStmt)node.getRhs().accept(this);
      irStmt.addStmt(rhsStmt);
      //storerhs
      irStmt.addIns(new IRStoreIns(destAddr,rhsStmt.getDest()));
      irStmt.addIns(new IRJmpIns(endLable.getName()));
      irStmt.addIns(endLable);


      irStmt.addIns(new IRLoadIns(destAddr,dest));
      irStmt.setDestAddr(null);
      //still a rvalue
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

    if(exprStmt.getDest()!=null){
      //add the this
      paramList.add(exprStmt.getDest());
    }

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
    var dest = new RegItem(new IRBaseType(((FuncLable)funcInfo.a).getReturnType()),"%call."+String.valueOf(counter.getCallIndex()),((FuncLable)funcInfo.a).getReturnType());
    counter.addCallIndex();
    irStmt.setDest(dest);
    if(dest.getType().getName().equals("void")) {
      irStmt.addIns(new IRCallIns(funcname,null,paramList));
      //the dest will never be used
    }else{
      irStmt.addIns(new IRCallIns(funcname,dest,paramList));
    }

    return irStmt;
  }
  @Override
  public IRNode visit(ASTChildExpr node) throws ErrorBasic{
    return node.getExpr().accept(this);
  }
  @Override
  public IRNode visit(ASTFStrExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()),new TypeLable("string",1));
    counter.addStringIndex();
    counter.addItem(dest.getName(),dest);
    if(node.getStrs().size()==0){
      throw new ErrorBasic("empty string");
    }
    var tmp = ASTAtomExpr.builder()
            .type(ASTAtomExpr.AtomType.STRING)
            .value(node.getStrs().get(0))
            .label(new ExprLable(null,new TypeLable("string"), ExprLable.ValueType.RVALUE))
            .build();
    var str = (IRStmt)tmp.accept(this);
    irStmt.addStmt(str);
//    irStmt.addIns(new IRLoadIns((RegItem)str.getDest(),dest));
    //must be a regItem
    dest = (RegItem) str.getDest();
    for(int i=0;i<node.getArgs().size();i++){
      var expr = (IRStmt)node.getArgs().get(i).accept(this);
      irStmt.addStmt((IRStmt)expr);

      var exprdest = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()), new TypeLable("string",1));
      counter.addStringIndex();

      if(node.getArgs().get(i).getLabel().getType().getName().equals("string")) {
        exprdest = (RegItem) expr.getDest();
      }else if(node.getArgs().get(i).getLabel().getType().getName().equals("int")) {
        var callArgs = new ArrayList<Item>();
        callArgs.add(expr.getDest());
        irStmt.addIns(new IRCallIns("toString",exprdest,callArgs));
      }else if(node.getArgs().get(i).getLabel().getType().getName().equals("bool")) {
        var callArgs = new ArrayList<Item>();
        callArgs.add(expr.getDest());
        irStmt.addIns(new IRCallIns("toString_bool", exprdest, callArgs));
      }else{
        throw new ErrorBasic("not support type");
      }

      {
        var callArgs = new ArrayList<Item>();
        callArgs.add(dest);
        callArgs.add(exprdest);
        dest = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()),new TypeLable("string",1));
        counter.addStringIndex();
        irStmt.addIns(new IRCallIns("__string_concat",dest,callArgs));
      }


      tmp = ASTAtomExpr.builder()
              .type(ASTAtomExpr.AtomType.STRING)
              .value(node.getStrs().get(i+1))
              .label(new ExprLable(null,new TypeLable("string"), ExprLable.ValueType.RVALUE))
              .build();
      str = (IRStmt)tmp.accept(this);

      var callArgs = new ArrayList<Item>();
      callArgs.add(dest);
      callArgs.add(str.getDest());
      dest = new RegItem(IRBaseType.getPtrType(),"%string."+String.valueOf(counter.getStringIndex()),new TypeLable("string",1));
      counter.addStringIndex();

      irStmt.addIns(new IRCallIns("__string_concat",dest,callArgs));
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
    var geteleItem = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()),node.getLabel().getType().getPtrType());
    counter.addGeteleIndex();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%load."+String.valueOf(counter.getLoadIndex()),node.getLabel().getType());
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
  public IRNode visit(ASTNewExpr node) throws ErrorBasic {
    IRStmt irStmt = new IRStmt();
    if (node.getExpr() != null) {
      //there is no "new int[10] {1,2,3,4,4}"
      //so it is a normal new
      return node.getExpr().accept(this);
    }
    if (node.getType().getLabel().getDimension() == 0) {
      //the new value
      IRBaseType type = null;
      if (node.getType().getLabel().getName().equals("int")) {
        type = IRBaseType.getIntType();
      } else if (node.getType().getLabel().getName().equals("bool")) {
        type = IRBaseType.getBoolType();
      } else {
        type = IRBaseType.getPtrType();
      }
      counter.addLoopIndex();
      var mallocDest = new RegItem(IRBaseType.getPtrType(), "%malloc." + String.valueOf(counter.getMallocIndex()) + ".addr",node.getType().getLabel().getPtrType());
      mallocDest.setValueType(new IRBaseType(node.getType().getLabel()));
      counter.addMallocIndex();
      var classname = node.getType().getLabel().getName();
      var mallocSize = counter.queryTypeSize("%class."+classname);
      if (classname.equals("int")) {
        mallocSize = 4;
      } else if (classname.equals("bool")) {
        mallocSize = 1;
      }
      irStmt.addIns(new IRCallIns("__malloc", mallocDest, new ArrayList<Item>(List.of(new LiteralItem(IRBaseType.getIntType(), 1),new LiteralItem(IRBaseType.getIntType(), mallocSize)))));
//      irStmt.addIns(new IRLoadIns(mallocDest,dest));
      if (node.getType().getLabel().getName().equals("int")
              || node.getType().getLabel().getName().equals("bool")) {
        irStmt.addIns(new IRStoreIns(mallocDest, new LiteralItem(type, 0)));
      } else {
        var constructorname = node.getType().getLabel().getName() + "." + node.getType().getLabel().getName();
        irStmt.addIns(new IRCallIns(constructorname, null, new ArrayList<Item>(List.of(mallocDest))));
      }
      irStmt.setDest(mallocDest);
      return irStmt;

    }

    //self made the for loop to handle the new array
    //like int[10][y]
    var typename = node.getType().getLabel().getName();
    var dest = new RegItem(IRBaseType.getPtrType(), "%malloc." + String.valueOf(counter.getMallocIndex()),new TypeLable(typename,node.getType().getLabel().getDimension()+1));
    dest.setValueType(IRBaseType.getPtrType());
    counter.addMallocIndex();


    int index_length = 0;
    var indexList = new ArrayList<IRStmt>();

    var ptrList = new ArrayList<RegItem>();
    var loopVarList = new ArrayList<RegItem>();
    ptrList.add(dest);
    for (var index : node.getType().getDimList()) {
      if (index == null) {
        break;
      }
      index_length++;
      var indexStmt = (IRStmt) index.accept(this);
      irStmt.addStmt(indexStmt);
      //handle strictly in order
      indexList.add(indexStmt);
//      var indexAddOne = new RegItem(IRBaseType.getIntType(), "%arith." + String.valueOf(counter.getArithIndex()));
//      counter.addArithIndex();

//      irStmt.addIns(new IRArithIns(indexStmt.getDest(), new LiteralItem(IRBaseType.getIntType(), 1), indexAddOne, "+"));
//      indexAddOneList.add(indexAddOne);
    }



    int loopIndex = counter.getLoopIndex();
    counter.addLoopIndex();
  {
    var mallocdest = new RegItem(IRBaseType.getPtrType(), "%malloc." + String.valueOf(counter.getMallocIndex()),new TypeLable(typename,node.getType().getLabel().getDimension()));
    counter.addMallocIndex();
    irStmt.setDest(mallocdest);
    counter.addGeteleIndex();
    for (int i = 0; i < index_length ; i++) {
      RegItem ptr = new RegItem(IRBaseType.getPtrType(), "%malloc." + String.valueOf(counter.getMallocIndex()) + ".addr",new TypeLable(typename,node.getType().getLabel().getDimension()-i));
      ptr.setValueType(IRBaseType.getPtrType());
      ptrList.add(ptr);
      counter.addMallocIndex();
//      counter.addItem(ptrList.get(i).getName(), ptrList.get(i));
      irStmt.addIns(new IRAllocIns(IRBaseType.getPtrType(), ptrList.get(i)));
    }
    for (int i = 0; i < index_length; i++) {
      var loopVar = new RegItem(IRBaseType.getPtrType(), "%loopvar." + String.valueOf(loopIndex) + "." + String.valueOf(i),new TypeLable("int",1));
      loopVar.setValueType(IRBaseType.getIntType());
      loopVarList.add(loopVar);

      //      counter.addItem(loopVarList.get(i).getName(), loopVarList.get(i));
//      irStmt.addIns(new IRAllocIns(IRBaseType.getIntType(), loopVarList.get(i)));
    }

    {
      var type = IRBaseType.getPtrType();
      if(node.getType().getLabel().getDimension()==1 ){
        if(node.getType().getLabel().getName().equals("int")){
          type = IRBaseType.getIntType();
        }else if(node.getType().getLabel().getName().equals("bool")){
          type = IRBaseType.getBoolType();
        }
      }
      IRBaseType finalType = type;
      irStmt.addIns(new IRCallIns("__malloc_array", mallocdest,
              new ArrayList<Item>() {{
                add(indexList.get(0).getDest());
                add(new LiteralItem(IRBaseType.getIntType(), finalType.getSize()));
              }}
       ));

      irStmt.addIns(new IRStoreIns(ptrList.get(0),mallocdest));
    }
  }
    ArrayList<IRIns>insBuilder = new ArrayList<>();
    for(int i = index_length - 2; i >= 0; i--) {
      var geteledest = new RegItem(IRBaseType.getPtrType(),"%getele."+String.valueOf(counter.getGeteleIndex()),ptrList.get(i).getRealType().getvalType());
      counter.addGeteleIndex();
      var arithdest = new RegItem(IRBaseType.getIntType(),"%arith."+String.valueOf(counter.getArithIndex()),new TypeLable("bool"));
      counter.addArithIndex();
      ArrayList<IRIns> body = new ArrayList<>();
      int finalI = i;
      var iItem = new RegItem(IRBaseType.getIntType(),"%load."+String.valueOf(counter.getLoadIndex()),new TypeLable("int"));
      counter.addLoadIndex();
      body.add(new IRLoadIns(loopVarList.get(i),iItem));

      var ptrItem = new RegItem(IRBaseType.getPtrType(),"%load."+String.valueOf(counter.getLoadIndex()),geteledest.getRealType());
      counter.addLoadIndex();
      body.add(new IRLoadIns(ptrList.get(i),ptrItem));
      var eletype = IRBaseType.getPtrType();
      if(i == index_length -1){
        if(node.getType().getLabel().getName().equals("int")){
          eletype = IRBaseType.getIntType();
        }else if(node.getType().getLabel().getName().equals("bool")){
          eletype = IRBaseType.getBoolType();
        }
      }
      body.add(new IRGetEleIns(geteledest, eletype.getName(), ptrItem, new ArrayList<Item>((List.of(iItem)))));

      if(i==index_length-1) {
        body.add(new IRStoreIns(geteledest, new LiteralItem(eletype, 0)));
      }else{
        var ptrItem1 = new RegItem(IRBaseType.getPtrType(),"%malloc."+String.valueOf(counter.getMallocIndex()),ptrList.get(i+1).getRealType().getvalType());
        counter.addMallocIndex();
        body.add(new IRCallIns("__malloc_array", ptrItem1, new ArrayList<Item>() {{
          add(indexList.get(finalI+1).getDest());
          add(new LiteralItem(IRBaseType.getIntType(), 4));
        }}));
        body.add(new IRStoreIns(ptrList.get(i+1), ptrItem1));
        body.add(new IRStoreIns(geteledest, ptrItem1));

//        body.add(new IRGetEleIns(geteledest, "ptr", ptrList.get(i + 1), new ArrayList<Item>() {{
//          add(new LiteralItem(IRBaseType.getIntType(), 0));
//        }}));
//        body.add(new IRStoreIns(geteledest, indexList.get(i + 1).getDest()));
        //we store the length before the beginning of the array
      }

      body.addAll(insBuilder);
      insBuilder = new ArrayList<>();
      ArrayList<IRIns> init = new ArrayList<>();
      init.add(new IRAllocIns(IRBaseType.getIntType(),loopVarList.get(i)));
      init.add(new IRStoreIns( loopVarList.get(i),new LiteralItem(IRBaseType.getIntType(), 0)));

      ArrayList<IRIns> update = new ArrayList<>();

      var loadItem = new RegItem(IRBaseType.getIntType(),"%load."+String.valueOf(counter.getLoadIndex()),new TypeLable("int"));
      counter.addLoadIndex();
      var arithItem = new RegItem(IRBaseType.getIntType(),"%arith."+String.valueOf(counter.getArithIndex()),new TypeLable("int"));
      counter.addArithIndex();
      update.add(new IRLoadIns(loopVarList.get(i),loadItem));
      update.add(new IRArithIns(loadItem, new LiteralItem(IRBaseType.getIntType(), 1), arithItem, "+"));
      update.add(new IRStoreIns(loopVarList.get(i),arithItem));

      //warning: do we need to Load and Store the loopVar??
      ArrayList<IRIns> condition = new ArrayList<>();
      var loadItem1 = new RegItem(IRBaseType.getIntType(),"%load."+String.valueOf(counter.getLoadIndex()),new TypeLable("int"));
      counter.addLoadIndex();
      condition.add(new IRLoadIns(loopVarList.get(i),loadItem1));
      condition.add(new IRArithIns(loadItem1, indexList.get(i).getDest(), arithdest, "<"));


      insBuilder = makeLoop("loop."+loopIndex+"."+i,init,condition,body,update,arithdest);
    }
    irStmt.addIns(insBuilder);
    return irStmt;
  }
  @Override
  public IRNode visit(ASTPreExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%arith."+String.valueOf(counter.getArithIndex()),node.getLabel().getType());
    counter.addArithIndex();
    counter.addItem(dest.getName(),dest);
    var exprStmt = (IRStmt)node.getExpr().accept(this);
    irStmt.addStmt(exprStmt);
    irStmt.setDest(dest);
    IRIns arithIns = null;
    IRIns storeIns = null;
    if(node.getOp().equals("++")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "+");
      storeIns = new IRStoreIns((RegItem) exprStmt.getDestAddr(),dest);
      irStmt.setDestAddr(exprStmt.getDestAddr());
      //lvalue
    }else if(node.getOp().equals("--")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "-");
      storeIns = new IRStoreIns((RegItem) exprStmt.getDestAddr(),dest);
      irStmt.setDestAddr(exprStmt.getDestAddr());
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
    if(storeIns != null) {
      irStmt.addIns(storeIns);
    }
    return irStmt;
  }
  @Override
  public IRNode visit(ASTSuffixExpr node) throws ErrorBasic{
    IRStmt irStmt = new IRStmt();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%arith."+String.valueOf(counter.getArithIndex()),node.getLabel().getType());
    counter.addArithIndex();
    counter.addItem(dest.getName(),dest);
    var exprStmt = (IRStmt)node.getExpr().accept(this);
    irStmt.addStmt(exprStmt);
    irStmt.setDest(dest);
    IRIns arithIns = null;
    IRIns storeIns = null;
    if(node.getOp().equals("++")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "+");
      storeIns = new IRStoreIns((RegItem) exprStmt.getDestAddr(),dest);
    }else if(node.getOp().equals("--")) {
      arithIns = new IRArithIns(exprStmt.getDest(), new LiteralItem(new IRBaseType(node.getLabel().getType()), 1), dest, "-");
      storeIns = new IRStoreIns((RegItem) exprStmt.getDestAddr(),dest);
    }else {
      throw new ErrorBasic("not support op");
    }
    irStmt.addIns(arithIns);
    irStmt.addIns(storeIns);
    irStmt.setDest(exprStmt.getDest());//rvalue
    //not the dest
    return irStmt;
  }
  @Override
  public IRNode visit(ASTTernaryExpr node) throws ErrorBasic{
    int index = counter.getBranchIndex();
    counter.addBranchIndex();
    IRLable thenLable = new IRLable("ternary."+index+".then");
    IRLable elseLable = new IRLable("ternary."+index+".else");
    IRLable endLable = new IRLable("ternary."+index+".end");

    var destAddr = new RegItem(IRBaseType.getPtrType(),"%ternary."+String.valueOf(counter.getArithIndex())+".addr",node.getLabel().getType().getPtrType());
    destAddr.setValueType(new IRBaseType(node.getLabel().getType()));
    counter.addArithIndex();
    var dest = new RegItem(new IRBaseType(node.getLabel().getType()),"%load."+String.valueOf(counter.getLoadIndex()),node.getLabel().getType());
    counter.addLoadIndex();
    IRStmt irStmt = new IRStmt();
    irStmt.setDest(dest);
    irStmt.setDestAddr(destAddr);
    if(!node.getLabel().getType().getName().equals("void"))
      irStmt.addIns(new IRAllocIns(destAddr.getValueType(),destAddr));

    var condStmt = (IRStmt)node.getCond().accept(this);
    irStmt.addStmt(condStmt);
    irStmt.addIns(new IRBranchIns(condStmt.getDest(),thenLable.getName(),elseLable.getName()));
    irStmt.addIns(thenLable);
    var thenStmt = (IRStmt)node.getTrueExpr().accept(this);
    irStmt.addStmt(thenStmt);
    if(!thenStmt.getDest().getType().getName().equals("void"))
      irStmt.addIns(new IRStoreIns(destAddr,thenStmt.getDest()));
    irStmt.addIns(new IRJmpIns(endLable.getName()));
    //-----------------------
    irStmt.addIns(elseLable);
    var elseStmt = (IRStmt)node.getFalseExpr().accept(this);
    irStmt.addStmt(elseStmt);
    if(!elseStmt.getDest().getType().getName().equals("void"))
      irStmt.addIns(new IRStoreIns(destAddr,elseStmt.getDest()));
    irStmt.addIns(new IRJmpIns(endLable.getName()));

    irStmt.addIns(endLable);
    if(!dest.getType().getName().equals("void"))
      irStmt.addIns(new IRLoadIns(destAddr,dest));
    return irStmt;
  }

  @Override
  public IRNode visit(ASTType node) throws ErrorBasic{
    throw new ErrorBasic("IRBuilder visit ASTType");
  }
}


