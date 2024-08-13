package Ir.Utility;

import java.util.TreeMap;

@lombok.Getter
@lombok.Setter
public class IRLable {
  protected String name;
  public IRLable()
  {
    name = null;
  }
  public IRLable(String name)
  {
    this.name = name;
  }
  public String realName()
  {
    return name;
  }
}
