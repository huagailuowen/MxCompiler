package Ir.Item;

import Ir.Type.IRBaseType;

@lombok.Getter
@lombok.Setter
public class FuncItem extends Item{
    IRBaseType returnType;
    public FuncItem(String name, IRBaseType retType) {
        super(new IRBaseType("func"), name);
        this.returnType = retType;
    }

    @Override
    public String toString() {
        return returnType.toString() + " " + super.toString();
    }
}
