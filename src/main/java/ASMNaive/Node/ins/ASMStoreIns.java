package ASMNaive.Node.ins;

import ASMNaive.Item.ASMReg;

@lombok.Getter
@lombok.Setter
public class ASMStoreIns extends  ASMIns{
  ASMReg src;
  ASMReg addr;
  public ASMStoreIns(ASMReg src, ASMReg addr){
    this.src = src;
    this.addr = addr;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "sw") + src.toString() + ", " + addr.toString();
  }
}
