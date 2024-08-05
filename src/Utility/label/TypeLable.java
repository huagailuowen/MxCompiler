package Utility.label;

@lombok.Getter
@lombok.Setter
public class TypeLable extends Label {
  private final boolean isBaseType;
  private final int dimension;
  public TypeLable(String name,int dim) {
    super(name);
    if(name=="int"||name=="bool"||name=="void"||name=="string"){
      this.isBaseType=true;
    }
    else{
      this.isBaseType=false;
    }
    this.dimension = dim;
  }
  
}
