package ASMNaive.Node.ins;

import ASMNaive.Item.ASMAddr;
import ASMNaive.Item.ASMReg;
import ASMNaive.Utility.ASMPhysicReg;

import static java.lang.Math.abs;

@lombok.Getter
@lombok.Setter
public class ASMStoreIns extends  ASMIns{
  ASMReg src;
  ASMAddr addr;
  public ASMStoreIns(ASMReg src, ASMAddr addr){
    this.src = src;
    this.addr = addr;
  }
  @Override
  public String toString(){
    if(abs(addr.getOffset())>=2048){
      String str = null;
      str = new ASMLoadImmIns(ASMPhysicReg.t5, addr.getOffset()).toString() + "\n";
      str += new ASMBinaryIns("add", ASMPhysicReg.t5, ASMPhysicReg.t5, addr.getBase()).toString() + "\n";
      str += new ASMStoreIns(src, new ASMAddr(ASMPhysicReg.t5, 0)).toString();
      return str;
    }
    return String.format("%-6s", "sw") + src.toString() + ", " + addr.toString();
  }
}
