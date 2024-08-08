package Utility.label;

@lombok.Getter
@lombok.Setter
public class ExprLable extends Label {
  private final TypeLable type;
  enum ValueType {
    LVALUE,
    RVALUE,
    ABANDON
  }
  private final ValueType valueType;
  //only LVALUE and some ABANDON has its name
  public ExprLable(String name, TypeLable type, ValueType valueType = ValueType.ABANDON) {
    super(name);
    this.type = type;
    this.isLvalue = valueType;
  }
  
}
