package ASMNaive.Node.ins;

import ASMNaive.Item.ASMReg;
import ASMNaive.Utility.ASMPhysicReg;

import static java.lang.Math.abs;

public class ASMUnaryIns extends ASMIns{
  protected String opt;
  protected ASMReg dest,src;
  protected int imm;
  boolean isImm = false;
  public ASMUnaryIns(String opt, ASMReg dest, ASMReg src){
    this.opt = opt;
    this.dest = dest;
    this.src = src;
    this.imm = 0;
    this.isImm = false;
  }
  public ASMUnaryIns(String opt, ASMReg dest, ASMReg src, int imm){
    this.opt = opt;
    this.dest = dest;
    this.src = src;
    this.imm = imm;
    this.isImm = true;
  }
  @Override
  public String toString(){
    if(!isImm){
      return String.format("%-6s", opt) + " " + dest.toString() + ", " + src.toString();
    }else{

      if(opt.equals("addi") && abs(imm) >= 2048){
        String str = null;
        str = new ASMLoadImmIns(ASMPhysicReg.t5, imm).toString() + "\n";
        str += new ASMBinaryIns("add", dest, src, ASMPhysicReg.t5).toString();
        return str;
      }

      return String.format("%-6s", opt) + " " + dest.toString() + ", " + src.toString() + ", " + imm;
    }
  }
}
