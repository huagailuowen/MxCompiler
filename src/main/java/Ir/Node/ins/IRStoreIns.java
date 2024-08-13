package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRStoreIns extends IRIns {
  protected Item addr;
  protected RegItem value;

  public IRStoreIns(Item addr, RegItem value) {
    this.addr = addr;
    this.value = value;
  }

  @Override
  public String toString() {
    return "store" + " " + addr.toString() + ", " + value.toString();
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
