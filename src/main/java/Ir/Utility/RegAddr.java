package Ir.Utility;

@lombok.Getter
@lombok.Setter
public class RegAddr {
  boolean isSpilled;
  int regIndex;
  //0 ~ 27  the number of Physical register
  public RegAddr()
  {
    this.regIndex = -1;
    isSpilled = false;
  }
  public RegAddr(int regIndex)
  {
    this.regIndex = regIndex;
    isSpilled = false;
    if(regIndex == -1){
      isSpilled = true;
    }
  }
}
