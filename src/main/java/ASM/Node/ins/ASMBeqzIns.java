package ASM.Node.ins;

import ASM.Item.ASMReg;

@lombok.Getter
@lombok.Setter
public class ASMBeqzIns extends ASMIns{
  protected ASMReg reg;
  protected String label;
  protected static int cnt = 0;
  public ASMBeqzIns(ASMReg reg, String label){
    this.reg = reg;
    this.label = label;
  }
  @Override
  public String toString(){
    String blabel =  "bneqzlable." + cnt++;
    String str = String.format("%-6s", "bnez") + reg.toString() + ", " + blabel;
    str += "\n  ";
    str += String.format("%-6s", "j") + label;
    str += "\n";
    str += blabel + ":";
    return str;
  }
}
