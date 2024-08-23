package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRCallIns extends IRIns {
  String funcName;
  RegItem dest;
  IRBaseType type;
  ArrayList<Item> args;
  public IRCallIns(String funcName, RegItem dest, ArrayList<Ir.Item.Item> args) {
    this.funcName = funcName;
    this.dest = dest;
    this.args = args;
    if(dest == null){
      type = IRBaseType.getVoidType();
    }else{
      type = dest.getType();
    }
  }
  @Override
  public String toString() {
    return (dest==null?"":dest.getName() + " = ") + "call " + (dest==null?"void ":dest.getType().getName())  + '@' + funcName + "(" + args.stream().map(Ir.Item.Item::toString).reduce((a, b) -> a + ", " + b).orElse("") + ")";
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    for(int i = 0; i < args.size(); i++){
      if(map.containsKey(dest)){
        throw new ErrorBasic("IRCallIns replaceUse error");
      }
      if(map.containsKey(args.get(i))){
        args.set(i, map.get(args.get(i)));
      }
    }
  }

}
