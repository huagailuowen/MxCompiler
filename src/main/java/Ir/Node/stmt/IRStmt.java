package Ir.Node.stmt;

import Ir.Item.RegItem;
import Ir.Node.IRNode;
import Ir.Node.ins.IRIns;

import java.util.ArrayList;
@lombok.Getter
@lombok.Setter
public class IRStmt extends IRNode {
  protected RegItem dest;
  //the key res will store in this.dest
  protected ArrayList<IRIns>  insList;
  public IRStmt() {
    this.dest = null;
    this.insList = new ArrayList<>();
  }
  public IRStmt(RegItem dest) {
    this.dest = dest;
    this.insList = new ArrayList<>();
  }
  public IRStmt(RegItem dest, ArrayList<IRIns> insList) {
    this.dest = dest;
    this.insList = insList;
  }
  public IRStmt(ArrayList<IRIns> insList) {
    this.dest = null;
    this.insList = insList;
  }


  public void addIns(IRIns ins){
    insList.add(ins);
  }
  public void addIns(ArrayList<IRIns> ins){
    insList.addAll(ins);
  }
}
