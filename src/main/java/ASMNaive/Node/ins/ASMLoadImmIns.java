package ASMNaive.Node.ins;

import ASMNaive.Item.ASMReg;
import Ir.Item.RegItem;

@lombok.Getter
@lombok.Setter
public class ASMLoadImmIns extends ASMIns{
  ASMReg reg;
  int imm;
  public ASMLoadImmIns(ASMReg reg, int imm){
    this.reg = reg;
    this.imm = imm;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "li") + reg + " " + imm;
  }
}
