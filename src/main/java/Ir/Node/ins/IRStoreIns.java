package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

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
}
