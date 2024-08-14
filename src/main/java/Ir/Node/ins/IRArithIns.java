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
  public IRArithIns()
  {
    this.lhs = null;
    this.rhs = null;
    this.dest = null;
    this.op = null;
  }
  public IRArithIns(Item lhs, Item rhs, RegItem dest, String op) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.dest = dest;
    this.op = op;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
