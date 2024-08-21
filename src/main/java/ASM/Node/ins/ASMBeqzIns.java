package ASM.Node.ins;

import ASM.Item.ASMReg;

@lombok.Getter
@lombok.Setter
public class ASMBeqzIns extends ASMIns {
  protected ASMReg reg;
  protected String label;
  public ASMBeqzIns(ASMReg reg, String label){
    this.reg = reg;
    this.label = label;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "beqz") + reg.toString() + ", " + label;
  }
}
