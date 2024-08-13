package Ir.Node.ins;

import Ir.IRVisitor;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRJmpIns extends IRIns {
  protected String label;

  public IRJmpIns(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return "jmp " + label;
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
