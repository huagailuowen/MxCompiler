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
  protected String type;
  protected Item src;
  protected ArrayList<Item> indexList;

  public IRGetEleIns()
  {
    dest = null;
    src = null;
    indexList = new ArrayList<>();
  }
  public IRGetEleIns(RegItem dest, String type, Item src, ArrayList<Item> indexList) {
    this.dest = dest;
    if(type.equals("i1")){
      type = "i8";
    }
    this.type = type;

    this.src = src;
    this.indexList = indexList;
  }
  @Override
  public String toString() {
    return dest.getName() + " = getelementptr " + type + ", " + src.getType().toString() + " " + src.getName() + ", " + indexList.stream().map(Item::toString).reduce((a, b) -> a + ", " + b).orElse("");
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

}
