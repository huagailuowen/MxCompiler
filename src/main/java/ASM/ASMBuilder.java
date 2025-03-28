package ASM;

import ASM.Item.ASMAddr;
import ASM.Item.ASMReg;
import ASM.Node.ASMNode;
import ASM.Node.ASMRoot;
import ASM.Node.def.ASMFuncDef;
import ASM.Node.def.ASMStrDef;
import ASM.Node.def.ASMVarDef;
import ASM.Node.ins.*;
import ASM.Node.stmt.ASMBlockStmt;
import ASM.Node.stmt.ASMStmt;
import ASM.Utility.ASMLable;
import ASM.Utility.ASMPhysicReg;
import Allocator.GraphAllocator;
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
import Ir.Type.IRBaseType;
import Ir.Type.IRClassType;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.TreeMap;

import static java.lang.Math.min;
import static java.lang.System.exit;
import static java.lang.System.in;

public class ASMBuilder implements IRVisitor<ASMNode> {
  public static HashMap<Integer,Integer>calleeMap = new HashMap<>();
  public static HashMap<Integer,Integer>callerMap = new HashMap<>();
  boolean recursiveFlag = false;
  String recursiveName;
  String funcName;
  public ASMBuilder(){
    calleeMap = new HashMap<>();
    callerMap = new HashMap<>();
    for(int i=0;i<ASMPhysicReg.calleeReg.length;i++){
      calleeMap.put(ASMPhysicReg.calleeReg[i].getIndex(),i);
    }
    for(int i=0;i<ASMPhysicReg.callerReg.length;i++){
      callerMap.put(ASMPhysicReg.callerReg[i].getIndex(),i);
    }
  }
  TreeMap<String,Integer> curVarOffset;
  //relative to the origin sp register
  int curStackOffset;
  int calloffset;
  boolean haveCalled;
  protected ArrayList<ASMVarDef>  varDefs;
  //total stack offset, relative to the origin sp register
  TreeMap<String,Integer> memberOffset;
  //"class.[name].index" -> offset
  public void storeRes(RegItem reg, ASMStmt stmt, ASMReg res)
  {
    var name = reg.getName();
    if (!name.startsWith("@") && !reg.getRegAddr().isSpilled()) {
      var tmp = ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()];
      if(haveCalled && ASMBuilder.callerMap.containsKey(tmp.getIndex()) && needStore.get(ASMBuilder.callerMap.get(tmp.getIndex()))){
        //the caller saved register
        stmt.addIns(new ASMStoreIns(res,new ASMAddr(ASMPhysicReg.sp,calloffset+4*tmp.getStackOffset())));
        return ;
      }
      if(ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()] != res){
        stmt.addIns(new ASMMoveIns(ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()],res));
      }
    } else if(curVarOffset.containsKey(name)){
      var addr = new ASMAddr(ASMPhysicReg.sp,calloffset+curVarOffset.get(name));
      stmt.addIns(new ASMStoreIns(res,addr));
    }else{
      //must be a global variable
      if(!name.startsWith("@")){
        throw new ErrorBasic("variable not found");
      }
//      name = name.substring(1);
      stmt.addIns(new ASMLoadAddrIns(ASMPhysicReg.t1,name));
      //tmp register
//      if(!name.startsWith("@string.")){
//        throw new ErrorBasic("this won't happen");
//      }
      assert res != ASMPhysicReg.t1;
      var addr = new ASMAddr(ASMPhysicReg.t1,0);
      if(name.startsWith("@string.")) {
        throw new ErrorBasic("this won't happen");
      }
      stmt.addIns(new ASMStoreIns(res,addr));
    }
  }
  public ASMReg findAddr(RegItem reg, ASMReg dest)
  {
    var name = reg.getName();
    if (!name.startsWith("@") && !reg.getRegAddr().isSpilled()) {
      if(ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()] != dest){
        dest = ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()];
      }
    }
    return dest;
  }
  public ASMReg getAddr(RegItem reg,ASMReg dest,ASMStmt stmt, boolean replace){
    var name = reg.getName();
    if (!name.startsWith("@") && !reg.getRegAddr().isSpilled()) {
      if(reg.getRegAddr().getRegIndex()==-1){
        int y=1;
      }
      var tmp = ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()];
      if(haveCalled && ASMBuilder.callerMap.containsKey(tmp.getIndex()) && needStore.get(ASMBuilder.callerMap.get(tmp.getIndex()))){
        //the caller saved register
        if(replace){
          throw new ErrorBasic("caller saved register should not be replaced");
        }
        stmt.addIns(new ASMLoadRegIns(dest,new ASMAddr(ASMPhysicReg.sp,calloffset+4*tmp.getStackOffset())));
        return dest;
      }
      if(ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()] != dest){
        if(replace) {
          dest = ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()];
        }else{
          stmt.addIns(new ASMMoveIns(dest,ASMPhysicReg.availableReg[reg.getRegAddr().getRegIndex()]));
        }
      }
    } else if(curVarOffset.containsKey(name)){
      var addr = new ASMAddr(ASMPhysicReg.sp,calloffset+curVarOffset.get(name));
      stmt.addIns(new ASMLoadRegIns(dest,addr));
    }else{
      //must be a global variable
      //therefore, the ins must be load or store
      //just let the dest be the value of its address
      if(!name.startsWith("@")){
        exit(0);
        throw new ErrorBasic("variable not found");
      }
//      name = name.substring(1);
      stmt.addIns(new ASMLoadAddrIns(dest,name));
      //tmp register

//      if(name.startsWith("@string.")){
//        stmt.addIns(new ASMMoveIns(dest,ASMPhysicReg.t6));
//      }else {
////        stmt.addIns(new ASMLoadRegIns(dest, addr));
//        stmt.addIns(new ASMMoveIns(dest,ASMPhysicReg.t6));
//        //        throw new ErrorBasic("this won't happen");
//      }
    }
    return dest;
  }
  public void init()
  {
    curVarOffset = null;
    curStackOffset = 0;
    calloffset = 0;
    memberOffset = new TreeMap<>();
    varDefs = null;
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
    varDefs = root.getVarDefs();
    root.addFuncDef((ASMFuncDef) node.getInitFunc().accept(this));
    for(var def : node.getFuncList()){
      root.addFuncDef((ASMFuncDef) def.accept(this));
    }
    return root;
  }
  BitSet usedCallee;
  BitSet needStore;
  @Override
  public ASMNode visit(IRFuncDef node) throws ErrorBasic {
    recursiveFlag = false;
    usedCallee = new BitSet(ASMPhysicReg.calleeReg.length);
//    usedCallee.set(0,ASMPhysicReg.calleeReg.length);
    var func = new ASMFuncDef(node.getName().getName());
    curVarOffset = new TreeMap<>();
    //design for every function, in naive version, all the parameters are stored in the stack
    //but the return value is stored in ra

    //handle the entry block: parameters and local variables
    int paramSize = 0;
    ArrayList<Integer>paramOffset = new ArrayList<>();
    int cnt = 0;
    for(var param : node.getParamList()){
      //mark the offset of the parameter
      paramOffset.add(paramSize);
//      paramSize += param.getType().getSize();
      if(cnt>=8){
        paramSize += 4;
      }else{
//        param.setRegAddr(new RegAddr(cnt+10));
        //a0 ~ a7
        //we are sure they won't conflict with other registers
      }
      cnt++;
    }

    curStackOffset = 4;
    // the ra
    curStackOffset += 4* GraphAllocator.K;
    //the first 4 bytes are used to store the return address
    for(var block : node.getBlockList()){
      //the exitIns do not have allocate need
      for(var entry : block.getPhi().entrySet()){
        if(!entry.getValue().getDest().getRegAddr().isSpilled()){
          continue;
        }
        var name = entry.getValue().getDest().getName();
        int id = entry.getValue().getDest().getRegAddr().getRegIndex();
        if(calleeMap.containsKey(id)){
          usedCallee.set(calleeMap.get(id),true);
        }
        if(curVarOffset.containsKey(name)){
          continue;
//          throw new ErrorBasic("variable redefined");
        }
        curVarOffset.put(name,curStackOffset);
//        curStackOffset += entry.getValue().getType().getSize();
        curStackOffset += 4;
      }
      for(var ins : block.getInsList()){
        if(IRIns.needAlloca(ins,usedCallee)){
          var name = IRIns.getAllocaName(ins);
          if(curVarOffset.containsKey(name)){
            continue;
//            throw new ErrorBasic("variable redefined");
          }
          curVarOffset.put(name,curStackOffset);
//          curStackOffset += ins.getDest().getType().getSize();
          curStackOffset += 4;
          if(ins instanceof IRAllocIns){
            curStackOffset += 4;
            //the size of the allocated memory
          }
        }
      }
    }
    curStackOffset = (curStackOffset + 15) / 16 * 16;
    for(int i=8;i< node.getParamList().size();i++){
      var param = node.getParamList().get(i);
      var offset = paramOffset.get(i) + curStackOffset;
      curVarOffset.put(param.getName(),offset);
    }
    //align the stack
    funcName = node.getName().getName();
    recursiveName = node.getName().getName() + "_recursive";
    boolean first = true;
    for(var block : node.getBlockList()){
      ASMBlockStmt blockStmt = (ASMBlockStmt) block.accept(this);
      if(first){
        first = false;
        ArrayList<ASMIns>beginList = new ArrayList<>();
        beginList.add(new ASMUnaryIns("addi", ASMPhysicReg.sp, ASMPhysicReg.sp, -curStackOffset));
        beginList.add(new ASMStoreIns(ASMPhysicReg.ra,new ASMAddr(ASMPhysicReg.sp,0)));
        int index = 0;
        for(var reg : ASMPhysicReg.calleeReg){
          if(usedCallee.get(index)){
            beginList.add(new ASMStoreIns(reg,new ASMAddr(ASMPhysicReg.sp,4*reg.getStackOffset())));
          }
          index++;
        }
        //the put ins order is reversed
        //actually: addi sp,sp,-curStackOffset; sw ra,0(sp)
        var recursivePoint = new ASMBlockStmt(node.getName().getName() + "_recursive");

        recursivePoint.setInsList(blockStmt.getInsList());

        blockStmt.setInsList(beginList);
//        beginList.addAll(blockStmt.getInsList());
//        assert(blockStmt.getInsList().isEmpty());
        func.addBlock(blockStmt);
        func.addBlock(recursivePoint);
        continue;
      }
      func.addBlock(blockStmt);
    }
    return func;
  }

  @Override
  public ASMNode visit(IRGlobalDef node) throws ErrorBasic {
    var item = node.getValue();
    if(item instanceof RegItem) {
      ASMVarDef varDef = new ASMVarDef(item.getName(),0,((RegItem) item).getValueType().getName());
      return varDef;
    }else if(item instanceof StringItem){
      ASMStrDef strDef = new ASMStrDef(item.getName(),StringItem.convert_string(((StringItem)item).getValue()));
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
    var stmt = new ASMStmt();
    stmt.addIns(new ASMUnaryIns("addi",ASMPhysicReg.t0,ASMPhysicReg.sp,4+curVarOffset.get(node.getDest())));
    var addr = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(node.getDest()));
    stmt.addIns(new ASMStoreIns(ASMPhysicReg.t0,addr));
    return stmt;
  }

  @Override
  public ASMNode visit(IRArithIns node) throws ErrorBasic {
    var stmt = new ASMStmt();

    ASMReg dest = ASMPhysicReg.t0;
    ASMReg src1 = ASMPhysicReg.t1;
    ASMReg src2 = ASMPhysicReg.t0;
    Item src1Item = node.getLhs();
    Item src2Item = node.getRhs();
    RegItem destItem = node.getDest();
    var opt = node.getOp();
    dest = findAddr(destItem,dest);
    if(opt.equals("add") || opt.equals("or") || opt.equals("and") || opt.equals("xor")){
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
        } else if (opt.equals("or")) {
          val = ((LiteralItem) src1Item).getValue() | ((LiteralItem) src2Item).getValue();
        }else if(opt.equals("and")){
          val = ((LiteralItem) src1Item).getValue() & ((LiteralItem) src2Item).getValue();
        }else if(opt.equals("xor")){
          val = ((LiteralItem) src1Item).getValue() ^ ((LiteralItem) src2Item).getValue();
        }
        stmt.addIns(new ASMLoadImmIns(dest,val));
      }else if(src2Item instanceof LiteralItem) {
        //one constant
        src1 = getAddr((RegItem) src1Item,src1,stmt,true);
//        stmt.addIns(new ASMLoadRegIns(src1,addr1));
        if(opt.equals("add")){
          stmt.addIns(new ASMUnaryIns("addi",dest,src1,((LiteralItem) src2Item).getValue()));
        }else if(opt.equals("or")){
          stmt.addIns(new ASMUnaryIns("ori",dest,src1,((LiteralItem) src2Item).getValue()));
        }else if(opt.equals("and")){
          stmt.addIns(new ASMUnaryIns("andi",dest,src1,((LiteralItem) src2Item).getValue()));
        }else if(opt.equals("xor")){
          stmt.addIns(new ASMUnaryIns("xori",dest,src1,((LiteralItem) src2Item).getValue()));
        }
      }else{
        //two variable
        src1 = getAddr((RegItem) src1Item,src1,stmt,true);
//        stmt.addIns(new ASMLoadRegIns(src1,addr1));
        src2 = getAddr((RegItem) src2Item,src2,stmt,true);
//        stmt.addIns(new ASMLoadRegIns(src2,addr2));
        stmt.addIns(new ASMBinaryIns(opt,dest,src1,src2));
      }
      storeRes(destItem,stmt,dest);
    } else if (( opt.equals("sub") ||opt.equals("shl") || opt.equals("ashr") )&& src2Item instanceof LiteralItem ) {
      if(src1Item instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(src1,((LiteralItem) src1Item).getValue()));
      }else {
        src1 = getAddr((RegItem) src1Item, src1, stmt, true);
      }
      if(opt.equals("sub")){
        stmt.addIns(new ASMUnaryIns("addi",dest,src1,-((LiteralItem) src2Item).getValue()));
      }else if(opt.equals("shl")){
        stmt.addIns(new ASMUnaryIns("slli",dest,src1,((LiteralItem) src2Item).getValue()));
      }else if(opt.equals("ashr")){
        stmt.addIns(new ASMUnaryIns("srai",dest,src1,((LiteralItem) src2Item).getValue()));
      }
    } else{
      if(src1Item instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(src1,((LiteralItem) src1Item).getValue()));
      }else{
        src1 = getAddr((RegItem) src1Item,src1,stmt,true);
//        stmt.addIns(new ASMLoadRegIns(src1,addr1));
      }
      if(src2Item instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(src2,((LiteralItem) src2Item).getValue()));
      }else{
        src2 = getAddr((RegItem) src2Item,src2,stmt,true);
//        stmt.addIns(new ASMLoadRegIns(src2,addr2));
      }
//      ASMAddr addr0 = getAddr(destItem,stmt);
      if(opt.equals("eq")){
        stmt.addIns(new ASMBinaryIns("sub",dest,src1,src2));
        stmt.addIns(new ASMUnaryIns("seqz",dest,dest));
//        stmt.addIns(new ASMStoreIns(dest,addr0));
        storeRes(destItem,stmt,dest);
      }else if(opt.equals("ne")){

        stmt.addIns(new ASMBinaryIns("sub",dest,src1,src2));
        stmt.addIns(new ASMUnaryIns("snez",dest,dest));
        storeRes(destItem,stmt,dest);
      }else if(opt.equals("slt")) {
        stmt.addIns(new ASMBinaryIns("slt", dest, src1, src2));
        storeRes(destItem,stmt,dest);
      }else if(opt.equals("sle")) {
        stmt.addIns(new ASMBinaryIns("sgt", dest, src1, src2));
        stmt.addIns(new ASMUnaryIns("seqz", dest, dest));
        storeRes(destItem,stmt,dest);
      }else if(opt.equals("sgt")) {
        stmt.addIns(new ASMBinaryIns("sgt", dest, src1, src2));
        storeRes(destItem,stmt,dest);
      }else if(opt.equals("sge")) {
        stmt.addIns(new ASMBinaryIns("slt", dest, src1, src2));
        stmt.addIns(new ASMUnaryIns("seqz", dest, dest));
        storeRes(destItem,stmt,dest);
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
        storeRes(destItem,stmt,dest);
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
      cond = getAddr((RegItem) node.getCondition(),cond,stmt,true);
//      stmt.addIns(new ASMLoadRegIns(cond,addr));
    }
    stmt.addIns(new ASMBeqzIns(cond,node.getFalseLabel()));
    stmt.addIns(new ASMJmpIns(node.getTrueLabel()));
    return stmt;
  }

  @Override
  public ASMNode visit(IRCallIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    int paramSize = 0;
    ArrayList<Integer>paramOffset = new ArrayList<>();
    int cnt = 0;
    for(var param : node.getArgs()) {
      paramOffset.add(paramSize);
//      paramSize += param.getType().getSize();
      if(cnt>=8){
        paramSize += 4;
      }
      cnt++;
    }
    //align the stack
    paramSize = (paramSize + 15) / 16 * 16;
    if(paramSize != 0){
      recursiveFlag = false;
    }
    //store the reg
    needStore = new BitSet(ASMPhysicReg.callerReg.length);
//    needStore.set(0,ASMPhysicReg.callerReg.length);
//    for(int i=0;i<min(node.getArgs().size(),8);i++){
//      needStore.set(i,true);
//    }
    for(var reg : node.getLiveIn()){
//      if(!node.getLiveOut().contains(reg)){
//        continue;
//      }
      //can still optimize
      int id = reg.getRegAddr().getRegIndex();

      if(id != -1){
        id = ASMPhysicReg.availableReg[id].getIndex();
      }
      if(callerMap.containsKey(id)){
        needStore.set(callerMap.get(id),true);
      }
    }
    int index = 0;
    for(var reg : ASMPhysicReg.callerReg){
      if(needStore.get(index))
        stmt.addIns(new ASMStoreIns(reg,new ASMAddr(ASMPhysicReg.sp,4*reg.getStackOffset())));
      index++;
    }
    if(paramSize>0)
      stmt.addIns(new ASMUnaryIns("addi",ASMPhysicReg.sp,ASMPhysicReg.sp,-paramSize));
    calloffset = paramSize;
    haveCalled = true;
    var dest = ASMPhysicReg.t0;
    for(int i=0;i<node.getArgs().size();i++){
      var param = node.getArgs().get(i);
      if(i<8){
        dest = ASMPhysicReg.callerReg[i];
      }else{
        dest = ASMPhysicReg.t0;
      }
      if(param instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(dest,((LiteralItem) param).getValue()));
      }else{
        dest = getAddr((RegItem) param, dest, stmt,false);
//        stmt.addIns(new ASMLoadRegIns(ASMPhysicReg.t0,addr));
      }
      if(i>=8) {
        stmt.addIns(new ASMStoreIns(dest,new ASMAddr(ASMPhysicReg.sp,paramOffset.get(i))));
      }
    }
    if(recursiveFlag){
      stmt.addIns(new ASMJmpIns(recursiveName));
    }else{
      stmt.addIns(new ASMCallIns(node.getFuncName()));
    }
    if(paramSize>0)
      stmt.addIns(new ASMUnaryIns("addi",ASMPhysicReg.sp,ASMPhysicReg.sp,paramSize));
    calloffset = 0;
    if(node.getDest()!=null && (node.getDest().getRegAddr() == null || node.getDest().getRegAddr().isSpilled() || node.getDest().getRegAddr().getRegIndex() != GraphAllocator.K + 1)){
//      var addr = getAddr(node.getDest(),stmt);
//      stmt.addIns(new ASMStoreIns(ASMPhysicReg.a0,addr));
      storeRes(node.getDest(),stmt,ASMPhysicReg.a0);
    }
    haveCalled = false;
    //load the reg
    index = 0;
    for(var reg : ASMPhysicReg.callerReg){
      if(needStore.get(index))
        stmt.addIns(new ASMLoadRegIns(reg,new ASMAddr(ASMPhysicReg.sp,4*reg.getStackOffset())));
      index++;
    }


    return stmt;
  }

  @Override
  public ASMNode visit(IRGetEleIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    ASMReg dest = ASMPhysicReg.t0;
    ASMReg base = ASMPhysicReg.t1;
    ASMReg index = ASMPhysicReg.t2;
//    ASMReg tmp = ASMPhysicReg.t6;
//    stmt.addIns(new ASMLoadRegIns(base,getAddr(node.getSrc().getName())));
    if(node.getSrc() instanceof LiteralItem){
      stmt.addIns(new ASMLoadImmIns(base,((LiteralItem) node.getSrc()).getValue()));
    }else {
      base = getAddr((RegItem) node.getSrc(), base, stmt,false);
    }
    Item indexItem = null;
    if(node.getIndexList().size() == 1){
      indexItem = node.getIndexList().get(0);
    }else if(node.getIndexList().size() == 2){
      indexItem = node.getIndexList().get(1);
    }else{
      throw new ErrorBasic("GetEleIns index error");
    }
    index = ASMPhysicReg.t0;
    if(indexItem instanceof LiteralItem) {
      stmt.addIns(new ASMLoadImmIns(index, ((LiteralItem) indexItem).getValue()));
    }else {
      index = getAddr((RegItem) indexItem, index, stmt,false);
    }
    dest = findAddr(node.getDest(),dest);
    stmt.addIns(new ASMUnaryIns("slli",dest,index,2));
    stmt.addIns(new ASMBinaryIns("add",dest,dest,base));
//    boolean first = true;
//    var typename = node.getType();
//    int typesize = 0;
//    if(typename.equals("i32")){
//      typesize = 4;
//    }else if(typename.equals("ptr")){
//      typesize = 4;
//    } else if (typename.equals("i1") || typename.equals("i8")) {
////      typesize = 1;
//      typesize = 4;
//    } else {
//      typename = typename.substring(7);
//      typesize = memberOffset.get(typename);
//    }
//    for(var indexItem : node.getIndexList()){
//      if(first){
//        first = false;
//        if(indexItem instanceof LiteralItem){
//          stmt.addIns(new ASMLoadImmIns(index,((LiteralItem) indexItem).getValue()));
//        }else{
////          stmt.addIns(new ASMLoadRegIns(index,getAddr(indexItem.getName())));
//          index = getAddr((RegItem) indexItem,index,stmt,false);
//        }
//        stmt.addIns(new ASMLoadImmIns(dest,typesize));
//        stmt.addIns(new ASMBinaryIns("mul",index,index,dest));
//        stmt.addIns(new ASMBinaryIns("add",dest,base,index));
//      }else{
//        if(indexItem instanceof LiteralItem){
//          stmt.addIns(new ASMLoadImmIns(index,((LiteralItem) indexItem).getValue()));
//        }else{
////          stmt.addIns(new ASMLoadRegIns(index,getAddr(indexItem.getName())));
//          index = getAddr((RegItem) indexItem,index,stmt,false);
//        }
//
//        stmt.addIns(new ASMLoadImmIns(base,4));
//        //if the alignment is not satisfied, then there is trouble to calculate the real offset dynamically
//        stmt.addIns(new ASMBinaryIns("mul",index,index,base));
//        stmt.addIns(new ASMBinaryIns("add",dest,dest,index));
//      }
//    }
    storeRes(node.getDest(),stmt,dest);
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
    ASMReg addrdest = ASMPhysicReg.t1;
    addrdest = getAddr(node.getAddr(),addrdest,stmt,true);
//    stmt.addIns(new ASMLoadRegIns(dest,addr));
    ASMReg tmp = ASMPhysicReg.t0;
//    if(addr.getBase().equals(ASMPhysicReg.sp)) {
//
//    }
    tmp = findAddr(node.getDest(),tmp);
    stmt.addIns(new ASMLoadRegIns(tmp, new ASMAddr(addrdest, 0)));
    storeRes(node.getDest(),stmt,tmp);
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
        getAddr((RegItem) node.getValue(),dest,stmt,false);
//        stmt.addIns(new ASMLoadRegIns(dest,addr));
      }else{
        throw new ErrorBasic("return value error");
      }
    }
    var retAddr = new ASMAddr(ASMPhysicReg.sp,0);
    //must load the value into the callee
    stmt.addIns(new ASMLoadRegIns(ASMPhysicReg.ra,retAddr));
    int index = 0;
    for(var reg : ASMPhysicReg.calleeReg){
      if(usedCallee.get(index))
        stmt.addIns(new ASMLoadRegIns(reg,new ASMAddr(ASMPhysicReg.sp,4*reg.getStackOffset())));
      index++;
    }
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
      value = getAddr((RegItem) val,value,stmt,true);
//      stmt.addIns(new ASMLoadRegIns(value,addr1));
    }
    ASMReg addrdest = ASMPhysicReg.t0;
    addrdest = getAddr(node.getAddr(),addrdest,stmt,true);
//    if(addr.getBase().equals(ASMPhysicReg.sp)) {
//
//    }
    ASMAddr addr = new ASMAddr(addrdest,0);
//    ASMAddr addr = getAddr(node.getAddr().getName(),stmt);
    stmt.addIns(new ASMStoreIns(value,addr));
    return stmt;
  }

  @Override
  public ASMNode visit(IRMoveIns node) throws ErrorBasic {
    var stmt = new ASMStmt();
    ASMReg src = ASMPhysicReg.t0;
    Item srcItem = node.getSrc();
    if(srcItem instanceof LiteralItem){
      src = findAddr(node.getDest(),src);
      stmt.addIns(new ASMLoadImmIns(src,((LiteralItem) srcItem).getValue()));
    }else{
      src = getAddr((RegItem) srcItem,src,stmt,true);
//      stmt.addIns(new ASMLoadRegIns(src,addr));
    }
    storeRes(node.getDest(),stmt,src);
    return stmt;

  }

  @Override
  public ASMNode visit(IRBlockStmt node) throws ErrorBasic {
    ASMStmt stmt = new ASMBlockStmt(node.getLableName());
    for(int i = 0; i < node.getInsList().size(); i++){
      var ins = node.getInsList().get(i);
      if(i+1 ==node.getInsList().size() || i+2 == node.getInsList().size() && (node.getInsList().get(i+1) instanceof IRMoveIns)){
      if(node.getExitIns() instanceof IRJmpIns jmpIns){
        if(jmpIns.getLabel().endsWith(".end") && jmpIns.getLabel().startsWith("func.")) {
          if(ins instanceof IRCallIns callIns){
            if(callIns.getFuncName().equals(funcName)){
              recursiveFlag = true;
            }
          }
        }
      }
      }
      stmt.addStmt((ASMStmt) ins.accept(this));
      recursiveFlag = false;
    }
    //do not forget the exitIns
    stmt.addStmt((ASMStmt) node.getExitIns().accept(this));
    return stmt;
  }
}
