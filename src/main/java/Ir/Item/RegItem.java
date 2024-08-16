package Ir.Item;

import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class RegItem extends Item {


    public RegItem(IRBaseType type, String name) {
        super(type, name);
        //it is very sepcial that the name of register should start with % or @
        if(!name.startsWith("%") && !name.startsWith("@")) {
            throw new ErrorBasic("Register name should start with % or @");
        }
    }

    @Override
    public String toString() {
        return type.toString() + " " + name;
    }
}
