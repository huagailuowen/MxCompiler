package Ir.Type;

import Utility.label.TypeLable;

@lombok.Getter
@lombok.Setter
public class IRBaseType {
    protected String name;
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

    public String toString() {
        return name;
    }
}
