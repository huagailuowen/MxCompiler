package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

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
}
