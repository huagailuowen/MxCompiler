package Ir.Item;

import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class RegItem extends Item {
    IRBaseType valueType;
    public RegItem(IRBaseType type, String name) {
        super(type, name);
        //it is very sepcial that the name of register should start with % or @
        if(!name.startsWith("%") && !name.startsWith("@")) {
            throw new ErrorBasic("Register name should start with % or @");
        }
    }

    @Override
    public String toString() {
        //if call this, must be global variable
        return type.getName() + " " + name;
    }
    @Override
    public String globalDef() {
        //if call this, must be global variable
        return name + " = global " + valueType.toString()  + " 0, align "+valueType.getSize();
    }

}
