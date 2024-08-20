package Ir.Item;

import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class LiteralItem extends Item {
    int value;
    public LiteralItem(IRBaseType typename, int value) {
      super(typename, Integer.toString(value));
      if(typename.equals(IRBaseType.getPtrType()) && value==0){
        super.setName("null");
      }
      this.value = value;
    }
    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
    @Override
    public String globalDef() {
        throw new ErrorBasic("globalDef not implemented");
    }
}
