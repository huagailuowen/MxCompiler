package Ir.Node.stmt;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Node.IRNode;
import Ir.Node.ins.IRIns;
import Ir.Node.ins.IRMoveIns;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
@lombok.Getter
@lombok.Setter
public class IRStmt extends IRNode {
  protected Item dest;
  protected Item destAddr;
  //the key res will store in this.dest
  protected ArrayList<IRIns>  insList;
  protected ArrayList<IRIns>  moveList;

  public IRStmt() {
    this.dest = null;
    this.destAddr = null;
    this.insList = new ArrayList<>();
  }
  public IRStmt(Item dest) {
    this.dest = dest;
    this.destAddr = null;
    this.insList = new ArrayList<>();
  }
  public IRStmt(Item dest, ArrayList<IRIns> insList) {
    this.dest = dest;
    this.insList = insList;
  }
  public IRStmt(ArrayList<IRIns> insList) {
    this.dest = null;
    this.destAddr = null;
    this.insList = insList;
  }


  public void addIns(IRIns ins){
    insList.add(ins);
  }
  public void addMoveIns(IRIns ins){
    moveList.add(ins);
  }

  public void addIns(ArrayList<IRIns> ins){
    insList.addAll(ins);
  }
  public void addStmt(IRStmt stmt){
    insList.addAll(stmt.getInsList());
  }
}
