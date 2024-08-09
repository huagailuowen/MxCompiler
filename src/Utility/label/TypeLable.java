package Utility.label;

@lombok.Getter
@lombok.Setter
public class TypeLable extends Lable {
  protected boolean isBaseType;
  protected int dimension;
  public boolean getIsBaseType(){
    return this.isBaseType;
  }
  public TypeLable(String name) {
    super(name);
    if(name.equals("int")||name.equals("bool")||name.equals("void")||name.equals("string")){
      this.isBaseType=true;
    }
    else{
      this.isBaseType=false;
    }
    this.dimension = 0;
  }
  public TypeLable(String name,int dim) {
    super(name);
    if(name.equals("int")||name.equals("bool")||name.equals("void")||name.equals("string")){
      this.isBaseType=true;
    }
    else{
      this.isBaseType=false;
    }
    this.dimension = dim;
  }
  
}
