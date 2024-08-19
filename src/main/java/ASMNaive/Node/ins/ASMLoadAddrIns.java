package ASMNaive.Node.ins;

import ASMNaive.Item.ASMReg;

@lombok.Getter
@lombok.Setter
public class ASMLoadAddrIns extends ASMIns{
  protected ASMReg dest;
  protected String label;
  public ASMLoadAddrIns(ASMReg dest, String label){
    this.dest = dest;
    this.label = label;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "la") + dest.toString() + ", " + label;
  }
}
