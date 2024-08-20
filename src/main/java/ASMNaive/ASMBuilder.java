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
          tmpoffset += mem.getSize();
          index++;
        }
      }
    }
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
    var entryBlock = node.getBlockList().get(0);
    ASMBlockStmt initBlock = new ASMBlockStmt("init");
    int stackSize = 0;
    int paramSize = 0;
    ArrayList<Integer>paramOffset = new ArrayList<>();
    for(var param : node.getParamList()){
      //mark the offset of the parameter
      paramOffset.add(paramSize);
//      paramSize += param.getType().getSize();
      paramSize += 4;
    }

    for(int i=0;i< node.getParamList().size();i++){
      var param = node.getParamList().get(i);
      var offset = paramOffset.get(i) - paramSize;
      curVarOffset.put(param.getName(),offset);
    }

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
    //align the stack

    boolean first = true;
    for(var block : node.getBlockList()){
      ASMBlockStmt blockStmt = (ASMBlockStmt) block.accept(this);
      if(first){
        first = false;
        blockStmt.addInsBegin(new ASMUnaryIns("addi", ASMPhysicReg.sp, ASMPhysicReg.sp, -curStackOffset));
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
        ASMAddr addr1 = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(src1Item.getName()));
        stmt.addIns(new ASMLoadRegIns(src1,addr1));
        if(opt.equals("add")){
          stmt.addIns(new ASMUnaryIns("addi",dest,src1,((LiteralItem) src2Item).getValue()));
        }
//        else{
//          stmt.addIns(new ASMUnaryIns("addi",dest,src1,-((LiteralItem) src2Item).getValue()));
//        }
      }else{
        //two variable
        ASMAddr addr1 = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(src1Item.getName()));
        ASMAddr addr2 = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(src2Item.getName()));
        stmt.addIns(new ASMLoadRegIns(src1,addr1));
        stmt.addIns(new ASMLoadRegIns(src2,addr2));
        stmt.addIns(new ASMBinaryIns(opt,dest,src1,src2));
        ASMAddr addr0 = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(destItem.getName()));
        stmt.addIns(new ASMStoreIns(dest,addr0));
      }
    }else{
      if(src1Item instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(src1,((LiteralItem) src1Item).getValue()));
      }else{
        ASMAddr addr1 = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(src1Item.getName()));
        stmt.addIns(new ASMLoadRegIns(src1,addr1));
      }
      if(src2Item instanceof LiteralItem){
        stmt.addIns(new ASMLoadImmIns(src2,((LiteralItem) src2Item).getValue()));
      }else{
        ASMAddr addr2 = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(src2Item.getName()));
        stmt.addIns(new ASMLoadRegIns(src2,addr2));
      }
      if(opt.equals("eq")){
        opt = "seq";


      }else if(opt.equals("ne")){
        opt = "sne";
      }else{
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
        ASMAddr addr0 = new ASMAddr(ASMPhysicReg.sp,curVarOffset.get(destItem.getName()));
        stmt.addIns(new ASMStoreIns(dest,addr0));
      }

    }

    return stmt;
  }

  @Override
  public ASMNode visit(IRBranchIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRCallIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRGetEleIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRJmpIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRLoadIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRPhiIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRRetIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRStoreIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRBlockStmt node) throws ErrorBasic {
    ASMStmt stmt = new ASMBlockStmt(node.getLableName());
    for(var ins : node.getInsList()){
      stmt.addStmt((ASMStmt) ins.accept(this));
    }
    stmt.addStmt((ASMStmt) node.getExitIns().accept(this));
    return stmt;
  }
}
