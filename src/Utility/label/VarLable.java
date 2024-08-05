package Utility.label;

import Utility.label.TypeLable;

@lombok.Getter
@lombok.Setter
public class VarLable extends Label {
  private final TypeLable type;
  
  public VarLable(String name, TypeLable type) {
    super(name);
    this.type = type;
  }

}
