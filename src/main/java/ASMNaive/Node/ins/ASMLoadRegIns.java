package ASMNaive.Node.ins;

import ASMNaive.Item.ASMAddr;
import ASMNaive.Item.ASMReg;
import ASMNaive.Utility.ASMPhysicReg;

import static java.lang.Math.abs;

@lombok.Getter
@lombok.Setter
public class ASMLoadRegIns extends ASMIns{
  ASMReg dest;
  ASMAddr addr;
//  String lable = null;
  public ASMLoadRegIns(ASMReg dest, ASMAddr addr){
    this.dest = dest;
    this.addr = addr;
//    if(addr.getLabel() != null){
//      lable = addr.getLabel();
//    }else{
//      lable = null;
//    }
  }
  @Override
  public String toString(){
    if(abs(addr.getOffset())>=2048){
      String str = null;
      str = new ASMLoadImmIns(ASMPhysicReg.t5, addr.getOffset()).toString() + "\n";
      str += new ASMBinaryIns("add", ASMPhysicReg.t5, ASMPhysicReg.t5, addr.getBase()).toString() + "\n";
      str += new ASMLoadRegIns(dest, new ASMAddr(ASMPhysicReg.t5, 0)).toString();
      return str;
    }
    return String.format("%-6s", "lw") + dest.toString() + ", " + addr.toString();

  }

}
