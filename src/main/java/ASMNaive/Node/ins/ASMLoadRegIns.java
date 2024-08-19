package ASMNaive.Node.ins;

import ASMNaive.Item.ASMAddr;
import ASMNaive.Item.ASMReg;

@lombok.Getter
@lombok.Setter
public class ASMLoadRegIns extends ASMIns{
  ASMReg dest;
  ASMAddr addr;
  public ASMLoadRegIns(ASMReg dest, ASMAddr addr){
    this.dest = dest;
    this.addr = addr;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "lw") + dest.toString() + ", " + addr.toString();
  }

}
