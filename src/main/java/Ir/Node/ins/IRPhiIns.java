package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
@lombok.Getter
@lombok.Setter
public class IRPhiIns extends IRIns {
  RegItem dest;
  IRBaseType type;
  protected ArrayList<Pair<Item,String>> valueList;

  public IRPhiIns(RegItem dest, IRBaseType type, ArrayList<Pair<Item,String>> valueList) {
    this.dest = dest;
    this.type = type;
    this.valueList = valueList;
  }
  @Override
  public String toString() {
    return dest.getName() + " = phi " + type.toString() + " " + valueList.stream().map(pair -> "[" + pair.a.toString() + ", " + pair.b + "]").reduce((a, b) -> a + ", " + b).orElse("");
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

}
