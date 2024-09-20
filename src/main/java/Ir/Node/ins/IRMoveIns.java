package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

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
//    return "";
    return dest.getNameReg() + " = " + src.getName();
  }

  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
//    throw new ErrorBasic("IRMoveIns getUseRegs error, not SSA");
    ArrayList<RegItem> ret = new ArrayList<>();
    if(src instanceof RegItem) {
      ret.add((RegItem) src);
    }
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
//    throw new ErrorBasic("IRMoveIns getUseRegs error, not SSA");
    ArrayList<RegItem> ret = new ArrayList<>();
    ret.add(dest);
    return ret;
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> replaceMap) {
    if(src instanceof RegItem && replaceMap.containsKey(src)) {
      src = replaceMap.get(src);
    }
  }
  @Override
  public void replaceDef(RegItem newReg) {
    dest = newReg;
  }
  @Override
  public IRMoveIns copy() {
    return new IRMoveIns(src, dest);
  }
}
