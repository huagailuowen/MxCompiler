package Ir.Node.ins;

import AST.ASTVisitor;
import Ir.IRVisitor;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class IRAllocIns extends IRIns {
  protected IRBaseType type;
  protected RegItem dest;

  public IRAllocIns(IRBaseType type, RegItem dest) {
    this.type = type;
    this.dest = dest;
  }
  @Override
  public String toString() {
    return dest.getName() + " = alloca " + type.toString();
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    return new ArrayList<>();
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    ret.add(dest);
    return ret;
  }
}
