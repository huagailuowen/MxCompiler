package ASMNaive;

import ASMNaive.Item.ASMAddr;
import ASMNaive.Item.ASMReg;
import ASMNaive.Node.ASMNode;
import ASMNaive.Node.ASMRoot;
import ASMNaive.Node.def.ASMFuncDef;
import ASMNaive.Node.def.ASMStrDef;
import ASMNaive.Node.def.ASMVarDef;
import ASMNaive.Node.ins.*;
import ASMNaive.Node.stmt.ASMBlockStmt;
import ASMNaive.Node.stmt.ASMStmt;
import ASMNaive.Utility.ASMPhysicReg;
import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.LiteralItem;
import Ir.Item.RegItem;
import Ir.Item.StringItem;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.def.IRGlobalDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Node.stmt.IRStmt;
import Ir.Type.IRClassType;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.TreeMap;

public class ASMBuilder implements IRVisitor<ASMNode> {
  TreeMap<String,Integer> curVarOffset;
  //relative to the origin sp register
  int curStackOffset;
  //total stack offset, relative to the origin sp register
  TreeMap<String,Integer> memberOffset;
  //"class.[name].index" -> offset
  public ASMAddr getAddr(String name, ASMStmt stmt)
  {
    if(curVarOffset.containsKey(name)){
      return new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(name));
    }else{
      //must be a global variable
      if(!name.startsWith("@")){
        throw new ErrorBasic("variable not found");
      }
//      name = name.substring(1);
      stmt.addIns(new ASMLoadAddrIns(ASMPhysicReg.t6,name));
      //tmp register
      return new ASMAddr(ASMPhysicReg.t6,0);
    }
  }
  public ASMAddr getAddr(String name,ASMReg dest,ASMStmt stmt){
    if(curVarOffset.containsKey(name)){
      var addr = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(name));
      stmt.addIns(new ASMLoadRegIns(dest,addr));
      return addr;
    }else{
      //must be a global variable
      if(!name.startsWith("@")){
        throw new ErrorBasic("variable not found");
      }
//      name = name.substring(1);
      stmt.addIns(new ASMLoadAddrIns(ASMPhysicReg.t6,name));
      //tmp register
      var addr = new ASMAddr(ASMPhysicReg.t6,0);
      stmt.addIns(new ASMLoadRegIns(dest,addr));
      return addr;
    }
  }
  public void init()
  {
    curVarOffset = null;
    curStackOffset = 0;
    memberOffset = new TreeMap<>();
  }
  @Override
  public ASMNode visit(IRNode node) throws ErrorBasic {
    throw new ErrorBasic("ASMBuilder visit IRNode");
  }

  @Override
  public ASMNode visit(IRRoot node) throws ErrorBasic {
    init();
    var root = new ASMRoot();
    for(var def : node.getGlobalDefList()){
      var item = def.getValue();
      if(item instanceof RegItem){
        root.addVarDef((ASMVarDef) def.accept(this));
      }else if(item instanceof StringItem){
        root.addStrDef((ASMStrDef) def.accept(this));
      }else{
        //type def
        //we do not need to handle this
        var classdef = (IRClassType)item.getType();
        var classname = classdef.getName();
        int index=0;
        int tmpoffset = 0;
        for(var mem : classdef.getMemberVariables()) {
          var memname = mem.getName();
          var key = classname + "." + index;
          if (memberOffset.containsKey(key)) {
            throw new ErrorBasic("class member already exist");
          }
          if(tmpoffset%mem.getSize()!=0){
            tmpoffset = (tmpoffset + mem.getSize()-1) / mem.getSize() * mem.getSize();
          }
          memberOffset.put(key, tmpoffset);
//          tmpoffset += mem.getSize();
          tmpoffset += 4;
          index++;

        }
        memberOffset.put(classname,tmpoffset);
      }
    }
    root.addFuncDef((ASMFuncDef) node.getInitFunc().accept(this));
    for(var def : node.getFuncList()){
      root.addFuncDef((ASMFuncDef) def.accept(this));
    }
    return root;
  }

  @Override
  public ASMNode visit(IRFuncDef node) throws ErrorBasic {
    var func = new ASMFuncDef(node.getName().getName());
    curVarOffset = new TreeMap<>();
    //design for every function, in naive version, all the parameters are stored in the stack
    //but the return value is stored in ra

    //handle the entry block: parameters and local variables
    ASMBlockStmt initBlock = new ASMBlockStmt("init");
    int paramSize = 0;
    ArrayList<Integer>paramOffset = new ArrayList<>();
    for(var param : node.getParamList()){
      //mark the offset of the parameter
      paramOffset.add(paramSize);
//      paramSize += param.getType().getSize();
      paramSize += 4;
    }


    curStackOffset = 4;
    //the first 4 bytes are used to store the return address
    for(var block : node.getBlockList()){
      //the exitIns do not have allocate need
      for(var ins : block.getInsList()){
        if(IRIns.needAlloca(ins)){
          var name = IRIns.getAllocaName(ins);
          if(curVarOffset.containsKey(name)){
            throw new ErrorBasic("variable redefined");
          }
          curVarOffset.put(name,curStackOffset);
//          curStackOffset += ins.getDest().getType().getSize();
          curStackOffset += 4;
        }
      }
    }
    curStackOffset = (curStackOffset + 15) / 16 * 16;
    for(int i=0;i< node.getParamList().size();i++){
      var param = node.getParamList().get(i);
      var offset = paramOffset.get(i) + curStackOffset;
      curVarOffset.put(param.getName(),offset);
    }
    //align the stack

    boolean first = true;
    for(var block : node.getBlockList()){
      ASMBlockStmt blockStmt = (ASMBlockStmt) block.accept(this);
      if(first){
        first = false;
        blockStmt.addInsBegin(new ASMStoreIns(ASMPhysicReg.ra,new ASMAddr(ASMPhysicReg.sp,0)));
        blockStmt.addInsBegin(new ASMUnaryIns("addi", ASMPhysicReg.sp, ASMPhysicReg.sp, -curStackOffset));
        //the put ins order is reversed
        //actually: addi sp,sp,-curStackOffset; sw ra,0(sp)
      }
      func.addBlock(blockStmt);
    }
    return func;
  }

  @Override
  public ASMNode visit(IRGlobalDef node) throws ErrorBasic {
    var item = node.getValue();
    if(item instanceof RegItem) {
      ASMVarDef varDef = new ASMVarDef(item.getName(),0);
      return varDef;
    }else if(item instanceof StringItem){
      ASMStrDef strDef = new ASMStrDef(item.getName(),StringItem.convert(((StringItem)item).getValue()));
      //have already converted the string
      return strDef;
    }else{
      //type def
      //we do not need to handle this
      throw new ErrorBasic("ASMBuilder visit IRGlobalDef");
    }
  }

  @Override
  public ASMNode visit(IRAllocIns node) throws ErrorBasic {
    return new ASMStmt();
    //nothing to do
  }

  @Override
  public ASMNode visit(IRArithIns node) throws ErrorBasic {
    var stmt = new ASMStmt();

    ASMReg dest = ASMPhysicReg.t0;
    ASMReg src1 = ASMPhysicReg.t1;
    ASMReg src2 = ASMPhysicReg.t2;
    Item src1Item = node.getLhs();
    Item src2Item = node.getRhs();
    RegItem destItem = node.getDest();
    var opt = node.getOp();
    if(opt.equals("add") ){
      if(src1Item instanceof LiteralItem){
        var tmp = src1Item;
        src1Item = src2Item;
        src2Item = tmp;
      }

      if(src1Item instanceof LiteralItem){
        //two constant
        int val = 0;
        if(opt.equals("add")){
          val = ((LiteralItem) src1Item).getValue() + ((LiteralItem) src2Item).getValue();
        }
//        else{
//          val = ((LiteralItem) src1Item).getValue() - ((LiteralItem) src2Item).getValue();
//        }
        stmt.addIns(new ASMLoadImmIns(dest,val));
      }else if(src2Item instanceof LiteralItem) {
        //one constant
        ASMAddr addr1 = getAddr(src1Item.getName(),src1,stmt);
//        stmt.addIns(new ASMLoadRegIns(src1,addr1));
        if(opt.equals("add")){
          stmt.addIns(new ASMUnaryIns("addi",dest,src1,((LiteralItem) src2Item).getValue()));
        }
//        else{
//          stmt.addIns(new ASMUnaryIns("addi",dest,src1,-((LiteralItem) src2Item).getValue()));
//        }
      }else{
        //two variable
        ASMAddr addr1 = getAddr(src1Item.getName(),src1,stmt);
//        stmt.addIns(new ASMLoadRegIns(src1,addr1));
        ASMAddr addr2 = getAddr(src2Item.getName(),src2,stmt);
//        stmt.addIns(new ASMLoadRegIns(src2,addr2));
        stmt.addIns(new ASMBinaryIns(opt,dest,src1,src2));
        ASMAddr addr0 = getAddr(destItem.getName(),stmt);
        stmt.addIns(new ASMStoreIns(dest,addr0));
      }
    }else{
      if(src1Item instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(src1,((LiteralItem) src1Item).getValue()));
      }else{
        ASMAddr addr1 = getAddr(src1Item.getName(),src1,stmt);
//        stmt.addIns(new ASMLoadRegIns(src1,addr1));
      }
      if(src2Item instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(src2,((LiteralItem) src2Item).getValue()));
      }else{
        ASMAddr addr2 =  getAddr(src2Item.getName(),src2,stmt);
//        stmt.addIns(new ASMLoadRegIns(src2,addr2));
      }
      ASMAddr addr0 = getAddr(destItem.getName(),stmt);
      if(opt.equals("eq")){
        stmt.addIns(new ASMBinaryIns("sub",dest,src1,src2));
        stmt.addIns(new ASMUnaryIns("seqz",dest,dest));
        stmt.addIns(new ASMStoreIns(dest,addr0));
      }else if(opt.equals("ne")){
        stmt.addIns(new ASMBinaryIns("sub",dest,src1,src2));
        stmt.addIns(new ASMUnaryIns("snez",dest,dest));
        stmt.addIns(new ASMStoreIns(dest,addr0));
      }else if(opt.equals("slt")) {
        stmt.addIns(new ASMBinaryIns("slt", dest, src1, src2));
        stmt.addIns(new ASMStoreIns(dest, addr0));
      }else if(opt.equals("sle")) {
        stmt.addIns(new ASMBinaryIns("sgt", dest, src1, src2));
        stmt.addIns(new ASMUnaryIns("seqz", dest, dest));
        stmt.addIns(new ASMStoreIns(dest, addr0));
      }else if(opt.equals("sgt")) {
        stmt.addIns(new ASMBinaryIns("sgt", dest, src1, src2));
        stmt.addIns(new ASMStoreIns(dest, addr0));
      }else if(opt.equals("sge")) {
        stmt.addIns(new ASMBinaryIns("slt", dest, src1, src2));
        stmt.addIns(new ASMUnaryIns("seqz", dest, dest));
        stmt.addIns(new ASMStoreIns(dest, addr0));
      }
      else{
        //the other operation
        if(opt.equals("sdiv")){
          opt = "div";
        }else if(opt.equals("srem")){
          opt = "rem";
        }else if(opt.equals("shl")){
          opt = "sll";
        }else if(opt.equals("ashr")){
          opt = "sra";
        }
        stmt.addIns(new ASMBinaryIns(opt,dest,src1,src2));
        stmt.addIns(new ASMStoreIns(dest,addr0));
      }
    }

    return stmt;
  }

  @Override
  public ASMNode visit(IRBranchIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    ASMReg cond = ASMPhysicReg.t0;
    if(node.getCondition() instanceof LiteralItem) {
      stmt.addIns(new ASMLoadImmIns(cond, ((LiteralItem) node.getCondition()).getValue()));
    }else{
      ASMAddr addr = getAddr(node.getCondition().getName(),cond,stmt);
//      stmt.addIns(new ASMLoadRegIns(cond,addr));
    }
    stmt.addIns(new ASMBeqzIns(cond,node.getFalseLabel()));
//    stmt.addIns(new ASMJmpIns(node.getTrueLabel()));
    return stmt;
  }

  @Override
  public ASMNode visit(IRCallIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    int paramSize = 0;
    ArrayList<Integer>paramOffset = new ArrayList<>();
    for(var param : node.getArgs()) {
      paramOffset.add(paramSize);
//      paramSize += param.getType().getSize();
      paramSize += 4;
    }
    paramSize = (paramSize + 15) / 16 * 16;
    //align the stack
    stmt.addIns(new ASMUnaryIns("addi",ASMPhysicReg.sp,ASMPhysicReg.sp,-paramSize));
    for(int i=0;i<node.getArgs().size();i++){
      var param = node.getArgs().get(i);
      if(param instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(ASMPhysicReg.t0,((LiteralItem) param).getValue()));
      }else{
        var addr = getAddr(param.getName(),ASMPhysicReg.t0,stmt);
//        stmt.addIns(new ASMLoadRegIns(ASMPhysicReg.t0,addr));
      }
      stmt.addIns(new ASMStoreIns(ASMPhysicReg.t0,new ASMAddr(ASMPhysicReg.sp,paramOffset.get(i))));
    }
    stmt.addIns(new ASMCallIns(node.getFuncName()));
    if(node.getDest()!=null){
      var addr = getAddr(node.getDest().getName(),stmt);
      stmt.addIns(new ASMStoreIns(ASMPhysicReg.a0,addr));
    }
    stmt.addIns(new ASMUnaryIns("addi",ASMPhysicReg.sp,ASMPhysicReg.sp,paramSize));
    return stmt;
  }

  @Override
  public ASMNode visit(IRGetEleIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    ASMReg dest = ASMPhysicReg.t0;
    ASMReg base = ASMPhysicReg.t1;
    ASMReg index = ASMPhysicReg.t2;
    ASMReg tmp = ASMPhysicReg.t3;
//    stmt.addIns(new ASMLoadRegIns(base,getAddr(node.getSrc().getName())));
    getAddr(node.getSrc().getName(),base,stmt);
    boolean first = true;
    var typename = node.getType();
    int typesize = 0;
    if(typename.equals("i32")){
      typesize = 4;
    }else if(typename.equals("ptr")){
      typesize = 4;
    } else if (typename.equals("i1") || typename.equals("i8")) {
      typesize = 1;
    } else {
      typesize = memberOffset.get(typename);
    }
    for(var indexItem : node.getIndexList()){
      if(first){
        first = false;
        if(indexItem instanceof LiteralItem){
          stmt.addIns(new ASMLoadImmIns(index,((LiteralItem) indexItem).getValue()));
        }else{
//          stmt.addIns(new ASMLoadRegIns(index,getAddr(indexItem.getName())));
          getAddr(indexItem.getName(),index,stmt);
        }
        stmt.addIns(new ASMLoadImmIns(tmp,typesize));
        stmt.addIns(new ASMBinaryIns("mul",index,index,tmp));
        stmt.addIns(new ASMBinaryIns("add",dest,base,index));
      }else{
        if(indexItem instanceof LiteralItem){
          stmt.addIns(new ASMLoadImmIns(index,((LiteralItem) indexItem).getValue()));
        }else{
//          stmt.addIns(new ASMLoadRegIns(index,getAddr(indexItem.getName())));
          getAddr(indexItem.getName(),index,stmt);
        }

        stmt.addIns(new ASMLoadImmIns(tmp,4));
        //if the alignment is not satisfied, then there is trouble to calculate the real offset dynamically
        stmt.addIns(new ASMBinaryIns("mul",index,index,tmp));
        stmt.addIns(new ASMBinaryIns("add",dest,dest,index));
      }
    }
    var addr = getAddr(node.getDest().getName(),stmt);
    stmt.addIns(new ASMStoreIns(dest,addr));
    return stmt;
  }

  @Override
  public ASMNode visit(IRJmpIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    stmt.addIns(new ASMJmpIns(node.getLabel()));
    return stmt;
  }

  @Override
  public ASMNode visit(IRLoadIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    ASMReg dest = ASMPhysicReg.t0;
    ASMAddr addr = getAddr(node.getAddr().getName(),dest,stmt);
//    stmt.addIns(new ASMLoadRegIns(dest,addr));
    ASMAddr addr0 = getAddr(node.getDest().getName(),stmt);
    stmt.addIns(new ASMStoreIns(dest,addr0));
    return stmt;
  }

  @Override
  public ASMNode visit(IRPhiIns node) throws ErrorBasic {
    throw new ErrorBasic("ASMBuilder visit IRPhiIns");
  }

  @Override
  public ASMNode visit(IRRetIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    var dest = ASMPhysicReg.a0;
    //the return value is stored in a0
    if(!node.getType().getName().equals("void")){
      if(node.getValue() instanceof LiteralItem) {
        stmt.addIns(new ASMLoadImmIns(dest, ((LiteralItem) node.getValue()).getValue()));
      }else if (node.getValue() instanceof RegItem){
        var addr = getAddr(node.getValue().getName(),dest,stmt);
//        stmt.addIns(new ASMLoadRegIns(dest,addr));
      }else{
        throw new ErrorBasic("return value error");
      }
    }
    var retAddr = new ASMAddr(ASMPhysicReg.sp,0);
    //must store the value into the ra
    stmt.addIns(new ASMLoadRegIns(ASMPhysicReg.ra,retAddr));
    //change the sp
    stmt.addIns(new ASMUnaryIns("addi",ASMPhysicReg.sp,ASMPhysicReg.sp,curStackOffset));
    stmt.addIns(new ASMRetIns());
    return stmt;
  }

  @Override
  public ASMNode visit(IRStoreIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    ASMReg value = ASMPhysicReg.t1;

    Item val = node.getValue();
    if(val instanceof LiteralItem){
      stmt.addIns(new ASMLoadImmIns(value,((LiteralItem) val).getValue()));
    }else{
      ASMAddr addr1 = getAddr(val.getName(),value,stmt);
//      stmt.addIns(new ASMLoadRegIns(value,addr1));
    }
    ASMAddr addr = getAddr(node.getAddr().getName(),stmt);
    stmt.addIns(new ASMStoreIns(value,addr));
    return stmt;
  }

  @Override
  public ASMNode visit(IRBlockStmt node) throws ErrorBasic {
    ASMStmt stmt = new ASMBlockStmt(node.getLableName());
    for(var ins : node.getInsList()){
      stmt.addStmt((ASMStmt) ins.accept(this));
    }
    //do not forget the exitIns
    stmt.addStmt((ASMStmt) node.getExitIns().accept(this));
    return stmt;
  }
}
