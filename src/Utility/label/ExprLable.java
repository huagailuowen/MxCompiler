package Utility.label;

import Utility.label.Label;
import Utility.label.TypeLable;


@lombok.Getter
@lombok.Setter
public class ExprLable extends Label {
  private final TypeLable type;
  private final boolean isLvalue;
  public ExprLable(String name, TypeLable type, boolean isLvalue) {
    super(name);
    this.type = type;
    this.isLvalue = isLvalue;
  }
  
}
