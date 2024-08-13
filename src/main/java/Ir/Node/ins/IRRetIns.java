package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

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
    return "ret " + type.toString() + " " + value.toString();
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
