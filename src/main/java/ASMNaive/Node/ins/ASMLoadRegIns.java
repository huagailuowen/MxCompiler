package ASMNaive.Node.ins;

import ASMNaive.Item.ASMAddr;
import ASMNaive.Item.ASMReg;

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
//    if(lable != null){
//      return String.format("%-6s", "la") + dest.toString() + ", " + lable;
//    }else{
      return String.format("%-6s", "lw") + dest.toString() + ", " + addr.toString();
//    }
  }

}
