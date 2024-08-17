package Ir.Utility;

import Ir.Node.ins.IRIns;

import java.util.TreeMap;

@lombok.Getter
@lombok.Setter
public class IRLable extends IRIns {
  protected String name;
  public IRLable()
  {
    name = null;
  }
  public IRLable(String name)
  {
    this.name = name;
  }
  @Override
  public String toString()
  {
    return name;
  }
//  public String realName()
//  {
//    return name;
//  }
}
