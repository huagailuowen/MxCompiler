package ASM.Node.stmt;

import ASM.Node.ins.ASMIns;
import ASM.Node.ins.ASMJmpIns;
import ASM.Utility.ASMLable;

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
  public String toString(String nextLable){
    StringBuilder sb = new StringBuilder();
    if(!lable.equals("entry") && !lable.equals("defaultStart"))
      sb.append(lable.toString()+":\n");
    for (int i = 0;i < insList.size();i++)
    {
      var ins = insList.get(i);
      if(i == insList.size() - 1 && ins instanceof ASMJmpIns && ((ASMJmpIns) ins).getLable().equals(nextLable))
        continue;
      sb.append("  ");
      sb.append(ins.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
