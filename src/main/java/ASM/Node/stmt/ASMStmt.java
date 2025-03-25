package ASM.Node.stmt;

import ASM.Node.ASMNode;
import ASM.Node.ins.ASMIns;

import java.util.ArrayList;
@lombok.Getter
@lombok.Setter
public class ASMStmt extends ASMNode {
  protected ArrayList<ASMIns> insList;
  public ASMStmt(){
    insList = new ArrayList<>();
  }
  public void addInsBegin(ASMIns ins){
    insList.add(0,ins);
  }
  public void addIns(ASMIns ins){
    insList.add(ins);
  }
  public void addIns(ArrayList<ASMIns> ins){
    insList.addAll(ins);
  }
  public void addStmt(ASMStmt stmt){
    insList.addAll(stmt.getInsList());
  }

}
