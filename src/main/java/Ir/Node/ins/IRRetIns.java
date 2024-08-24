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
public class IRRetIns extends IRIns {
  protected IRBaseType type;
  protected Item value;

  public IRRetIns(IRBaseType type, Item value) {
    this.type = type;
    this.value = value;
  }

  @Override
  public String toString() {
    return "ret " + type.toString() + (type.getName().equals("void")?"":" " + value.getName());
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    if(map.containsKey(value)){
      value = map.get(value);
    }
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    if(type.getName().equals("void")){
      return ret;
    }
    if(value instanceof RegItem){
      ret.add((RegItem)value);
    }
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    return new ArrayList<>();
  }
}
