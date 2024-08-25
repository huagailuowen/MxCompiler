package Ir.Utility;

@lombok.Getter
@lombok.Setter
public class RegAddr {
  boolean isSpilled;
  int regIndex;
  //0 ~ 27  the number of Physical register
  RegAddr()
  {
    this.regIndex = -1;
    isSpilled = true;
  }
  public RegAddr(int regIndex)
  {
    this.regIndex = regIndex;
    isSpilled = false;
  }
}
