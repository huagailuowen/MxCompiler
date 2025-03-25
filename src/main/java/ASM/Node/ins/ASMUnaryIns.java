package ASM.Node.ins;

import ASM.Item.ASMReg;
import ASM.Utility.ASMPhysicReg;

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

      if(opt.equals("addi") && (imm == -2147483648 || abs(imm) >= 2048)){
        String str = null;
        str = new ASMLoadImmIns(ASMPhysicReg.t5, imm).toString() + "\n";
        str += new ASMBinaryIns("add", dest, src, ASMPhysicReg.t5).toString();
        return str;
      }else if(opt.equals("slli") && (imm == -2147483648 || abs(imm) >= 2048)){
        String str = null;
        str = new ASMLoadImmIns(ASMPhysicReg.t5, imm).toString() + "\n";
        str += new ASMBinaryIns("sll", dest, src, ASMPhysicReg.t5).toString();
        return str;
      }else if(opt.equals("slti") && (imm == -2147483648 || abs(imm) >= 2048)){
        String str = null;
        str = new ASMLoadImmIns(ASMPhysicReg.t5, imm).toString() + "\n";
        str += new ASMBinaryIns("slt", dest, src, ASMPhysicReg.t5).toString();
        return str;
      }else if(opt.equals("xori") && (imm == -2147483648 || abs(imm) >= 2048)){
        String str = null;
        str = new ASMLoadImmIns(ASMPhysicReg.t5, imm).toString() + "\n";
        str += new ASMBinaryIns("xor", dest, src, ASMPhysicReg.t5).toString();
        return str;
      }else if(opt.equals("andi") && (imm == -2147483648 || abs(imm) >= 2048)){
        String str = null;
        str = new ASMLoadImmIns(ASMPhysicReg.t5, imm).toString() + "\n";
        str += new ASMBinaryIns("and", dest, src, ASMPhysicReg.t5).toString();
        return str;
      }else if(opt.equals("ori") && (imm == -2147483648 || abs(imm) >= 2048)) {
        String str = null;
        str = new ASMLoadImmIns(ASMPhysicReg.t5, imm).toString() + "\n";
        str += new ASMBinaryIns("or", dest, src, ASMPhysicReg.t5).toString();
        return str;
      }

      return String.format("%-6s", opt) + " " + dest.toString() + ", " + src.toString() + ", " + imm;
    }
  }
}
