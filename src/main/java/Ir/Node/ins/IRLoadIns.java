package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class IRLoadIns extends IRIns {
  protected RegItem addr;
  protected RegItem dest;


  public IRLoadIns(RegItem addr, RegItem dest) {
    this.addr = addr;
    this.dest = dest;
//    this.type = type;
  }

  @Override
  public String toString() {
    return dest.getName() + " = load " + dest.getType().toString() + ", ptr " + addr.getName();
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
