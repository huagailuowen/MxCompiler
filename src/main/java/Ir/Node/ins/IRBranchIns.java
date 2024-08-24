package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRBranchIns extends IRIns {
  protected Item condition;
  protected String trueLabel, falseLabel;

  public IRBranchIns(Item condition, String trueLabel, String falseLabel) {
    if(condition == null){
      throw new ErrorBasic("condition is null");
    }
    this.condition = condition;
    this.trueLabel = trueLabel;
    this.falseLabel = falseLabel;
  }
  @Override
  public String toString() {
    assert condition.getType().getName().equals("i1");
    return "br " + "i1 " + condition.getName() + ", label " + '%'+ trueLabel + ", label " + '%'+ falseLabel;
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public void redirectLable(String origin, String target) {
    if(origin.equals(trueLabel)){
      trueLabel = target;
    }else if(origin.equals(falseLabel)){
      falseLabel = target;
    }else{
      throw new ErrorBasic("IRBranchIns redirectLable error");
    }
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    if(map.containsKey(condition)){
      condition = map.get(condition);
    }
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    if(condition instanceof RegItem){
      ret.add((RegItem)condition);
    }
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    return new ArrayList<>();
  }
}
