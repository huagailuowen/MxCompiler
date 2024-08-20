package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRBranchIns extends IRIns {
  protected Item condition;
  protected String trueLabel, falseLabel;

  public IRBranchIns(Item condition, String trueLabel, String falseLabel) {
    if(condition == null){
      throw new ErrorBasic("condition is null");
    }
    this.condition = condition;
    this.trueLabel = trueLabel;
    this.falseLabel = falseLabel;
  }
  @Override
  public String toString() {
    assert condition.getType().getName().equals("i1");
    return "br " + "i1 " + condition.getName() + ", label " + '%'+ trueLabel + ", label " + '%'+ falseLabel;
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
