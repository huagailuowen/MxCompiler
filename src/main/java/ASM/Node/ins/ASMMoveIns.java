package ASM.Node.ins;

import ASM.Item.ASMReg;

@lombok.Getter
@lombok.Setter
public class ASMMoveIns extends ASMIns {
  protected ASMReg dest;
  protected ASMReg src;
  public ASMMoveIns(ASMReg dest, ASMReg src){
    this.dest = dest;
    this.src = src;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "mv") + dest.toString() + ", " + src.toString();
  }
}
