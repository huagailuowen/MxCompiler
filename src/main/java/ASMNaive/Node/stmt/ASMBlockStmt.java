package ASMNaive.Node.stmt;

import ASMNaive.Node.ins.ASMIns;
import ASMNaive.Utility.ASMLable;

import java.util.ArrayList;
@lombok.Getter
@lombok.Setter
public class ASMBlockStmt extends ASMStmt{
  String lable;
//  ASMIns exitIns;
  public ASMBlockStmt(String lable){
    this.lable = lable;
    insList = new ArrayList<>();
  }
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    if(!lable.equals("entry") && !lable.equals("defaultStart"))
      sb.append(lable.toString()+":\n");
    for (ASMIns ins : insList)
    {
      sb.append("  ");
      sb.append(ins.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
