package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRGetEleIns extends IRIns {
  protected RegItem dest;
  protected String type;
  protected Item src;
  protected ArrayList<Item> indexList;

  public IRGetEleIns()
  {
    dest = null;
    src = null;
    indexList = new ArrayList<>();
  }
  public IRGetEleIns(RegItem dest, String type, Item src, ArrayList<Item> indexList) {
    this.dest = dest;
    if(type.equals("i1")){
      type = "i8";
    }
    this.type = type;

    this.src = src;
    this.indexList = indexList;
  }
  @Override
  public String toString() {
    return dest.getName() + " = getelementptr " + type + ", " + src.getType().toString() + " " + src.getName() + ", " + indexList.stream().map(Item::toString).reduce((a, b) -> a + ", " + b).orElse("");
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    if(map.containsKey(src)){
      src = map.get(src);
    }
    for(int i = 0; i < indexList.size(); i++){
      if(map.containsKey(indexList.get(i))){
        indexList.set(i, map.get(indexList.get(i)));
      }
    }
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    if(src instanceof RegItem){
      ret.add((RegItem)src);
    }
    for(Item item : indexList){
      if(item instanceof RegItem){
        ret.add((RegItem)item);
      }
    }
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    ret.add(dest);
    return ret;
  }
}
