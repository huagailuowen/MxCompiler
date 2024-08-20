package ASMNaive.Item;
@lombok.Getter
@lombok.Setter
public class ASMAddr {
  protected ASMReg base;
  protected int offset;
//  protected String label;
  //have label means it is a global variable

  public ASMAddr(ASMReg base, int offset) {
    this.base = base;
    this.offset = offset;
  }
//  public ASMAddr(String label){
//    this.base = null;
//    this.offset = 0;
//    this.label = label;
//  }
  @Override
  public String toString() {
    return offset + "(" + base + ")";
  }
}
