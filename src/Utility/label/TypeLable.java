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
    if(name.equals("int")||name.equals("bool")||name.equals("void")){
      this.isBaseType=true;
    }
    else{
      this.isBaseType=false;
    }
    this.dimension = 0;
  }
  public TypeLable(String name,int dim) {
    super(name);
    if(name.equals("int")||name.equals("bool")||name.equals("void")){
      this.isBaseType=true;
    }
    else{
      this.isBaseType=false;
    }
    this.dimension = dim;
  }

  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    ret.append(super.toString());
    ret.append("[]".repeat(Math.max(0, dimension)));
    return ret.toString();
  }
}
