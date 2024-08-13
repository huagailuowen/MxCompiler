package Ir.Node;

import Ir.IRVisitor;
import Utility.error.ErrorBasic;

@lombok.Setter
@lombok.Getter
public class IRNode {

  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
