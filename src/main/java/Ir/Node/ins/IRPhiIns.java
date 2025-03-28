package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRPhiIns extends IRIns {
  RegItem dest;
  RegItem tmpreg;
  IRBaseType type;
  protected ArrayList<Pair<Item,String>> valueList;
  public ArrayList<String> findBlock(RegItem item){
    ArrayList<String> ret = new ArrayList<>();
    for(Pair<Item,String> pair : valueList){
      if(pair.a == item){
        ret.add(pair.b);
      }
    }
    return ret;
  }
  public IRPhiIns(RegItem dest, IRBaseType type)
  {
    this.dest = dest;
    if(type == null){
      throw new ErrorBasic("IRPhiIns type is null");
    }
    this.type = type;
    valueList = new ArrayList<>();
  }
  public IRPhiIns(RegItem dest, IRBaseType type, ArrayList<Pair<Item,String>> valueList) {
    this.dest = dest;
    this.type = type;
    this.valueList = valueList;
  }
  @Override
  public String toString() {
    return dest.getNameReg() + " = phi " + type.toString() + " " + valueList.stream().map(pair -> "[" + pair.a.getName() + ", %" + pair.b + "]").reduce((a, b) -> a + ", " + b).orElse("");
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

  public void addBranch(Item item, String lableName) {
    valueList.add(new Pair<>(item, lableName));
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    ArrayList<RegItem> last = new ArrayList<>();
    for(Pair<Item,String> pair : valueList){
      if(pair.a instanceof RegItem){
        if(pair.b.contains("condition_out")){
          last.add((RegItem)pair.a);
        }else{
          ret.add((RegItem)pair.a);
        }
      }
    }
    ret.addAll(last);
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    ret.add(dest);
    return ret;
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    for(int i = 0;i < valueList.size();i++){
      if(map.containsKey(valueList.get(i).a)){
        valueList.set(i, new Pair<>(map.get(valueList.get(i).a), valueList.get(i).b));
      }
    }
  }
  @Override public void replaceDef(RegItem newReg) {
    dest = newReg;
  }
  @Override
  public IRPhiIns copy() {
    ArrayList<Pair<Item,String>> newValueList = new ArrayList<>();
    for(Pair<Item,String> pair : valueList){
      newValueList.add(new Pair<>(pair.a, pair.b));
    }
    return new IRPhiIns(dest, type, newValueList);
  }

}
