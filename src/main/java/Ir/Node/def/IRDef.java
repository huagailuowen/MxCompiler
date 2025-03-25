package Ir.Node.def;

import Ir.IRVisitor;
import Ir.Node.IRNode;
import Utility.error.ErrorBasic;

@lombok.Setter
@lombok.Getter
public class IRDef extends IRNode {
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
