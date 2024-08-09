package Utility.label;

import Utility.label.TypeLable;

@lombok.Getter
@lombok.Setter
public class VarLable extends Lable {
  protected TypeLable type;
  
  public VarLable(String name, TypeLable type) {
    super(name);
    this.type = type;
  }

}
