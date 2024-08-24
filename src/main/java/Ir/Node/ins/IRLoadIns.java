package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRLoadIns extends IRIns {
  protected RegItem addr;
  protected RegItem dest;


  public IRLoadIns(RegItem addr, RegItem dest) {
    this.addr = addr;
    this.dest = dest;
    if(dest.getType().getName().equals("void")){
      throw new ErrorBasic("Load to void");
    }
//    this.type = type;
  }

  @Override
  public String toString() {
    return dest.getName() + " = load " + dest.getType().toString() + ", ptr " + addr.getName();
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
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    ret.add(addr);
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    ret.add(dest);
    return ret;
  }

}
