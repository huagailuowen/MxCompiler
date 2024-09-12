package Ir.Item;

import Ir.Type.IRBaseType;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;
import Utility.label.TypeLable;

@lombok.Getter
@lombok.Setter
public class RegItem extends Item implements java.lang.Comparable<RegItem>{
    IRBaseType valueType;
    TypeLable realType;
    RegAddr regAddr;
    int degree;

    public String getNameReg() {
        return name ;//+ " : " + (regAddr== null?" Stack ": regAddr.getRegIndex());
    }

    public RegItem(IRBaseType type, String name ,TypeLable realType) {
        super(type, name);
        //it is very sepcial that the name of register should start with % or @
        if(!name.startsWith("%") && !name.startsWith("@")) {
            throw new ErrorBasic("Register name should start with % or @");
        }
        degree = 0;
        regAddr = new RegAddr();
        this.realType = realType;
    }
    public void addDegree() {
        degree++;
    }

    @Override
    public String toString() {
//        return type.getName() + " " + name;
        return type.getName() + " " + getName();

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
