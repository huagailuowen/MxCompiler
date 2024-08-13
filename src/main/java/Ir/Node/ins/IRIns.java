package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Node.IRNode;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRIns extends IRNode {
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
