package Utility.label;

@lombok.Getter
@lombok.Setter
public class ExprLable extends Lable {
  protected TypeLable type;
  public enum ValueType {
    LVALUE,
    RVALUE,
    ABANDON,
  }
  protected boolean isThis = false;
  public boolean getThis() {
    return isThis;
  }
  protected ValueType valueType;
  //only LVALUE and some ABANDON has its name
  public ExprLable() {
    super(null);
    this.type = new TypeLable("void");
    this.valueType = ValueType.ABANDON;
    this.isThis = false;
  }
  public ExprLable(String name, TypeLable type, ValueType valueType ) {
    super(name);
    this.type = type;
    this.valueType = valueType;
    this.isThis = false;
  }
  @Override
  public ExprLable clone(){
    return new ExprLable(this.getName(),this.type,this.valueType);
  }
  
}
