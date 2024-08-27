package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRJmpIns extends IRIns {
  protected String label;

  public IRJmpIns(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return "br label " + '%'+ label;
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public void redirectLable(String origin, String target) {
    if(origin.equals(label)){
      label = target;
    }else{
      throw new ErrorBasic("IRJmpIns redirectLable error");
    }
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    return ;
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    return new ArrayList<>();
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    return new ArrayList<>();
  }
}
