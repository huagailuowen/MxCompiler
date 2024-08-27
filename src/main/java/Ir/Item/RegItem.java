package Ir.Item;

import Ir.Type.IRBaseType;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;

@lombok.Getter
@lombok.Setter
public class RegItem extends Item implements java.lang.Comparable<RegItem>{
    IRBaseType valueType;
    RegAddr regAddr;
    int degree;
    public RegItem(IRBaseType type, String name) {
        super(type, name);
        //it is very sepcial that the name of register should start with % or @
        if(!name.startsWith("%") && !name.startsWith("@")) {
            throw new ErrorBasic("Register name should start with % or @");
        }
        degree = 0;
        regAddr = new RegAddr();
    }
    public void addDegree() {
        degree++;
    }

    @Override
    public String toString() {
        return type.getName() + " " + name;
    }
    @Override
    public String globalDef() {
        //if call this, must be global variable
        return name + " = global " + valueType.toString()  + " " + (valueType.getName().equals("ptr")?"null":"0");
    }

    @Override
    public int compareTo(RegItem o) {
        return 0;
    }
}
