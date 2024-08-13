package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRCallIns extends IRIns {
  String funcName;
  RegItem dest;
  IRBaseType type;
  java.util.ArrayList<Item> args;
  public IRCallIns(String funcName, RegItem dest, java.util.ArrayList<Ir.Item.Item> args) {
    this.funcName = funcName;
    this.dest = dest;
    this.args = args;
  }
  @Override
  public String toString() {
    return dest.getName() + " = call " + funcName + "(" + args.stream().map(Ir.Item.Item::toString).reduce((a, b) -> a + ", " + b).orElse("") + ")";
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
