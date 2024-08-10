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

  @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(type.toString());
        ret.append(" : ");
        ret.append(super.toString());
        return ret.toString();
    }

}
