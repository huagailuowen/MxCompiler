package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Node.IRNode;
import Ir.Node.def.IRClassDef;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRIns extends IRNode {
  public static boolean needAlloca(IRIns ins)
  {
    if(ins instanceof IRJmpIns
      || ins instanceof IRBranchIns
      || ins instanceof IRRetIns
      || ins instanceof IRStoreIns
      || ins instanceof IRMoveIns){
      return false;
    }else{
      if(ins instanceof IRCallIns){
        if(((IRCallIns) ins).getDest() == null){
          return false;
        }
      }
      return true;
    }
  }
  public static String getAllocaName(IRIns ins)
  {
    if(ins instanceof IRAllocIns){
      return ((IRAllocIns) ins).getDest().getName();
    }else if(ins instanceof IRArithIns) {
      return ((IRArithIns) ins).getDest().getName();
    }else if(ins instanceof IRGetEleIns){
      return ((IRGetEleIns) ins).getDest().getName();
    }else if(ins instanceof IRCallIns){
      return ((IRCallIns) ins).getDest().getName();
    }else if(ins instanceof IRLoadIns){
      return ((IRLoadIns) ins).getDest().getName();
    }else{
      throw new ErrorBasic("getAllocaName error");
    }
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  public void redirectLable(String origin, String target) {
    throw new ErrorBasic("IRIns redirectLable error");
  }
  public void replaceUse(HashMap<RegItem, Item> map) {
    throw new ErrorBasic("IRIns replaceUse error");
  }
  public ArrayList<RegItem> getUseRegs() {
    throw new ErrorBasic("IRIns getUseRegs error");
  }
  public ArrayList<RegItem> getDefRegs() {
    throw new ErrorBasic("IRIns getDefRegs error");
  }

}
