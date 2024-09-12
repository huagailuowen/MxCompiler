package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class IRMoveIns extends IRIns {
  protected RegItem  dest;
  protected Item src;
  public IRMoveIns() {
    super();
  }

  public IRMoveIns(Item src, RegItem dest) {
    super();
    this.src = src;
    this.dest = dest;
  }

  @Override
  public String toString() {
    return "";
//    return dest.getNameReg() + " = " + src.getName();
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    throw new ErrorBasic("IRMoveIns getUseRegs error, not SSA");
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    throw new ErrorBasic("IRMoveIns getUseRegs error, not SSA");
  }
}
