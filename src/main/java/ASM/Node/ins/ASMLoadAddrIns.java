package ASM.Node.ins;

import ASM.Item.ASMReg;

public class ASMLoadAddrIns extends ASMIns{
  protected ASMReg dest;
  protected String addr;
  public ASMLoadAddrIns(ASMReg dest, String addr){
    this.dest = dest;
    this.addr = addr;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "la") + dest + ", " + addr;
  }
}
