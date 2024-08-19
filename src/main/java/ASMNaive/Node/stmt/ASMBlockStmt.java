package ASMNaive.Node.stmt;

import ASMNaive.Node.ins.ASMIns;
import ASMNaive.Utility.ASMLable;

import java.util.ArrayList;
@lombok.Getter
@lombok.Setter
public class ASMBlockStmt extends ASMStmt{
  ASMLable lable;
//  ASMIns exitIns;
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(lable.toString()+":\n");
    for (ASMIns ins : insList)
    {
      sb.append(ins.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
