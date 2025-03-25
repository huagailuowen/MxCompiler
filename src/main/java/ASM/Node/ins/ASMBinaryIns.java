package ASM.Node.ins;

import ASM.Item.ASMReg;

@lombok.Getter
@lombok.Setter
public class ASMBinaryIns extends ASMIns{
  protected String opt;
  protected ASMReg dest,lhs,rhs;
  public ASMBinaryIns(String opt, ASMReg dest, ASMReg lhs, ASMReg rhs){
    this.opt = opt;

    this.dest = dest;
    this.lhs = lhs;
    this.rhs = rhs;
    if(this.opt.equals("sgt")){
      this.opt = "slt";
      ASMReg tmp = this.lhs;
      this.lhs = this.rhs;
      this.rhs = tmp;
    }
  }
  @Override
  public String toString(){
    return String.format("%-6s", opt) + " " + dest.toString() + ", " + lhs.toString() + ", " + rhs.toString();
  }
}
