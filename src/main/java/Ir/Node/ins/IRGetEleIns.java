package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
@lombok.Getter
@lombok.Setter
public class IRGetEleIns extends IRIns {
  protected RegItem dest;
  protected Item src;
  protected ArrayList<Item> indexList;

  public IRGetEleIns(RegItem dest, Item src, ArrayList<Item> indexList) {
    this.dest = dest;
    this.src = src;
    this.indexList = indexList;
  }
  @Override
  public String toString() {
    return dest.getName() + " = getelementptr " + src.getType().toString() + ", " + src.getType().toString() + "* " + src.getName() + ", " + indexList.stream().map(Item::toString).reduce((a, b) -> a + ", " + b).orElse("");
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

}
