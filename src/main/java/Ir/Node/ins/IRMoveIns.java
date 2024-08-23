package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRMoveIns extends IRIns {
  protected RegItem  dest;
  protected Item src;
  public IRMoveIns() {
    super();
  }

  public IRMoveIns(Item src, RegItem dest) {
    super();
    this.src = src;
    this.dest = dest;
  }

  @Override
  public String toString() {
    return dest.getName() + " = " + src.getName();
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
