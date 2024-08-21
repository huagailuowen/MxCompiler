package Ir.Type;

import Utility.label.TypeLable;

@lombok.Getter
@lombok.Setter
public class IRBaseType {
    protected String name;
    //ptr void i32 i1 func
    public int getSize()
    {
//        if(name.equals("i1"))return 1;
        return 4;
    }
    public IRBaseType(String name) {
        this.name = name;
    }
    public IRBaseType(TypeLable lable) {
        if(lable.getDimension()!=0){
          this.name = "ptr";
        }else if(lable.getName().equals("int")){
          this.name = "i32";
        }else if(lable.getName().equals("bool")){
          this.name = "i1";
        }else if(lable.getName().equals("void")){
          this.name = "void";
        } else {
          this.name = "ptr";
        }
    }
    public boolean equals(IRBaseType other) {
        return name.equals(other.name);
    }
    public static IRBaseType getVoidType() {
        return new IRBaseType("void");
    }
    public static IRBaseType getIntType() {
        return new IRBaseType("i32");
    }
    public static IRBaseType getBoolType() {
        return new IRBaseType("i1");
    }
    public static IRBaseType getPtrType() {
        return new IRBaseType("ptr");
    }
    @Override
    public String toString() {
        return name;
    }
}
