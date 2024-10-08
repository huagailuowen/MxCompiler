package Ir.Node.stmt;

import Ir.IRVisitor;
import Ir.Item.RegItem;
import Ir.Node.ins.*;
import Ir.Utility.IRLable;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

@lombok.Getter
@lombok.Setter
public class IRBlockStmt extends IRStmt {
  //CFG
  ArrayList<IRBlockStmt> pred;
  ArrayList<IRBlockStmt> succ;
  //Phi
  IRBlockStmt iDom;
  int treeDepth;
  int loopDepth;
  boolean isLoopEntry;
  ArrayList<IRBlockStmt> domChild;
  boolean isAbandoned;
  TreeMap<String, IRPhiIns> phi;
  HashSet<RegItem> phiDef;
  HashMap<IRBlockStmt,IRBlockStmt> replacePred;
  HashSet<RegItem> phiLiveOut;

  protected String lableName;
  protected IRIns exitIns;
  protected int index;
  public IRBlockStmt() {
    super();
    this.lableName = null;
    this.exitIns = null;
    pred = new ArrayList<>();
    succ = new ArrayList<>();
    phi = new TreeMap<>();
    phiDef = new HashSet<>();
    replacePred = new HashMap<>();
    domChild = new ArrayList<>();
    treeDepth = -1;
  }
  public IRBlockStmt(String lableName) {
    super();
    this.lableName = lableName;
    this.exitIns = null;
    pred = new ArrayList<>();
    succ = new ArrayList<>();
    phi = new TreeMap<>();
    phiDef = new HashSet<>();
    replacePred = new HashMap<>();
    domChild = new ArrayList<>();
    treeDepth = -1;
  }
  public void addPred(IRBlockStmt predBlock) {
    pred.add(predBlock);
  }
  public void addSucc(IRBlockStmt succBlock) {
    succ.add(succBlock);
  }
  public void addDomChild(IRBlockStmt domChildBlock) {
    domChild.add(domChildBlock);
  }
  public static ArrayList<IRBlockStmt> makeBlock(IRStmt stmts,String funcname) {
    ArrayList<IRBlockStmt> blocks = new ArrayList<>();
    //the entry block is the very first block
    IRBlockStmt entryblock = new IRBlockStmt("entry");
    IRBlockStmt block = null;
    blocks.add(entryblock);
    for(IRIns ins : stmts.getInsList()) {
      if(ins instanceof IRLable){
        if(block == null){
          block = new IRBlockStmt(((IRLable)ins).getName());
        }else if(block.getExitIns() == null) {
          block.setExitIns(new IRJmpIns(((IRLable)ins).getName()));
          blocks.add(block);
          block = new IRBlockStmt(((IRLable)ins).getName());
        }else{
          blocks.add(block);
          block = new IRBlockStmt(((IRLable)ins).getName());
        }
//        block.addIns(ins);
        continue;
      }
      if(block == null){
        block = new IRBlockStmt("defaultStart");
//        throw new ErrorBasic("the first should be lable");
        //the block will begin with no lable
      }
      if(ins instanceof IRAllocIns){
        entryblock.addIns(ins);
      }
      if(block.getExitIns() != null){
        System.err.println("this Ins will never be executed");
        continue;
      }
      if(ins instanceof IRJmpIns || ins instanceof IRBranchIns || ins instanceof IRRetIns){
        block.setExitIns(ins);
        continue;
      }
      else if(!(ins instanceof IRAllocIns)){
        block.addIns(ins);
      }

    }

    if (block != null) {
      if(block.getExitIns() == null){
        throw new ErrorBasic("block exit is null");
      }
      blocks.add(block);
    }
    if(blocks.size() == 1){
      throw new ErrorBasic("the func without any return");
    }
    blocks.get(0).setExitIns(new IRJmpIns(blocks.get(1).getLableName()));
    return blocks;
  }
  @Override
  public String toString()
  {
    if(this.isAbandoned){
      return "";
    }
    StringBuilder sb = new StringBuilder();
    sb.append(lableName).append(":\n");
    for(var entry : phi.entrySet()){
      sb.append("  ").append(entry.getValue().toString()).append('\n');
    }
    for(IRIns ins : insList)
    {
      sb.append("  ").append(ins.toString()).append('\n');
    }
    sb.append("  ").append(exitIns.toString()).append('\n');
    return sb.toString();
  }
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
