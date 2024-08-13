package Ir.Node.def;

import Ir.IRVisitor;
import Ir.Item.Item;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRGlobalDef extends IRDef {
  Item value;
  public IRGlobalDef() {
  }
  public IRGlobalDef(Item value) {
    this.value = value;
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
