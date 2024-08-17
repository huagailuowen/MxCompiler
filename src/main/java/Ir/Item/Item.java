package Ir.Item;

import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class Item {
  IRBaseType type;
  String name;
  public Item() {
    this.type = null;
    this.name = null;
  }
  public Item(IRBaseType type, String name) {
    this.type = type;
    this.name = name;
  }
  public String toString() {
    return name;
  }
  public String globalDef() {
    if(!(this instanceof RegItem)&&!(this instanceof StringItem)){
      return type.toString() ;
    }
    throw new ErrorBasic("globalDef not implemented");
  }

}
