package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRStoreIns extends IRIns {
  protected RegItem addr;
  protected Item value;

  public IRStoreIns(RegItem addr, Item value) {
    this.addr = addr;
    this.value = value;
  }

  @Override
  public String toString() {
    return "store " + value.getType().getName() + ' '+ value.getName() + ", "+ addr.toString()  ;
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    if(map.containsKey(addr)){
      addr = (RegItem) map.get(addr);
    }
    if(map.containsKey(value)){
      value = map.get(value);
    }
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    if(value instanceof RegItem){
      ret.add((RegItem)value);
    }
    ret.add(addr);
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    return new ArrayList<>();
  }

}
