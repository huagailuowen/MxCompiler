package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRLoadIns extends IRIns {
  protected Item value;
  protected RegItem dest;
  protected IRBaseType type;


  public IRLoadIns(Item value, RegItem dest) {
    this.value = value;
    this.dest = dest;
//    this.type = type;
  }

  @Override
  public String toString() {
    return dest.getName() + " = load " + type.toString() + ", " + value.getName();
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
