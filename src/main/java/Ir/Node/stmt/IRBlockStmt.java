package Ir.Node.stmt;

import Ir.Node.ins.*;
import Ir.Utility.IRLable;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class IRBlockStmt extends IRStmt {
  protected String lableName;
  protected IRIns exitIns;
  public IRBlockStmt() {
    super();
    this.lableName = null;
    this.exitIns = null;
  }
  public IRBlockStmt(String lableName) {
    super();
    this.lableName = lableName;
    this.exitIns = null;
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
          throw new ErrorBasic("block exit is null");
        }else{
          blocks.add(block);
          block = new IRBlockStmt(((IRLable)ins).getName());
        }
        block.addIns(ins);
        continue;
      }
      if(block == null){
        block = new IRBlockStmt("defaultStart");
//        throw new ErrorBasic("the first should be lable");
        //the block will begin with no lable
      }

      if(ins instanceof IRJmpIns || ins instanceof IRBranchIns || ins instanceof IRRetIns){
        if(block.getExitIns() != null){
          System.err.println("this Ins will never be executed");
          continue;
        }
        block.setExitIns(ins);
        continue;
      }
      if(ins instanceof IRAllocIns){
        entryblock.addIns(ins);
      }else{
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
}
