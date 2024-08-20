package ASMNaive.Node.ins;

import ASMNaive.Item.ASMReg;

public class ASMUnaryIns extends ASMIns{
  protected String opt;
  protected ASMReg dest,src;
  protected int imm;
  public ASMUnaryIns(String opt, ASMReg dest, ASMReg src, int imm){
    this.opt = opt;
    this.dest = dest;
    this.src = src;
    this.imm = imm;
  }
  @Override
  public String toString(){
    if(imm == 0){
      return String.format("%-6s", opt) + " " + dest.toString() + ", " + src.toString();
    }else{
      return String.format("%-6s", opt) + " " + dest.toString() + ", " + src.toString() + ", " + imm;
    }
  }
}
