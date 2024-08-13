package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRArithIns extends IRIns {
  Item lhs, rhs;
  RegItem dest;
  String op;
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
