package Ir.Node.ins;

import Ir.Item.Item;
import Ir.Item.RegItem;

@lombok.Getter
@lombok.Setter
public class IRBranchIns extends IRIns {
  protected Item condition;
  protected String trueLabel, falseLabel;

  public IRBranchIns(Item condition, String trueLabel, String falseLabel) {
    this.condition = condition;
    this.trueLabel = trueLabel;
    this.falseLabel = falseLabel;
  }
  @Override
  public String toString() {
    return "br " + condition.toString() + ", label " + trueLabel + ", label " + falseLabel;
  }
}
