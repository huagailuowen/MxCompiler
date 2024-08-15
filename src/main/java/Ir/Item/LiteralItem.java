package Ir.Item;

import Ir.Type.IRBaseType;

@lombok.Getter
@lombok.Setter
public class LiteralItem extends Item {
    public LiteralItem(IRBaseType typename, int value) {
      super(typename, Integer.toString(value));
      if(typename.equals(IRBaseType.getPtrType()) && value==0){
        super.setName("null");
      }
    }
    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
