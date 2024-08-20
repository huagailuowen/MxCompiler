package ASMNaive;

import ASMNaive.Item.ASMReg;
import ASMNaive.Node.ASMNode;
import ASMNaive.Node.ASMRoot;
import ASMNaive.Node.def.ASMFuncDef;
import ASMNaive.Node.def.ASMStrDef;
import ASMNaive.Node.def.ASMVarDef;
import ASMNaive.Node.ins.ASMBinaryIns;
import ASMNaive.Node.stmt.ASMBlockStmt;
import ASMNaive.Node.stmt.ASMStmt;
import Ir.IRVisitor;
import Ir.Item.RegItem;
import Ir.Item.StringItem;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.def.IRGlobalDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Node.stmt.IRStmt;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.TreeMap;

public class ASMBuilder implements IRVisitor<ASMNode> {
  TreeMap<String,Integer> curVarOffset;
  //relative to the origin sp register
  int curStackOffset;
  //total stack offset, relative to the origin sp register
  public void init()
  {
    curVarOffset = null;
    curStackOffset = 0;
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
//      if(first){
//        first = false;
//        //skip the entry block
//        continue;
//      }
      func.addBlock((ASMBlockStmt) block.accept(this));
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

    ASMReg dest = new ASMReg("t0",5);
    ASMReg src1 = new ASMReg("t1",6);
    ASMReg src2 = new ASMReg("t2",7);
    var opt = node.getOp();
    if(opt.equals("sdiv")){
      opt = "div";
    }else if(opt.equals("srem")){
      opt = "rem";
    }else if(opt.equals("shl")){
      opt = "sll";
    }else if(opt.equals("ashr")){
      opt = "sra";
    }else if(opt.equals("eq")){
      opt = "seq";
    }else if(opt.equals("ne")){
      opt = "sne";
    }

    stmt.addIns(new ASMBinaryIns(opt,dest,src1,src2));

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
    return null;
  }
}
