package ASMNaive.Item;
@lombok.Getter
@lombok.Setter
public class ASMAddr {
  protected ASMReg base;
  protected int offset;

  public ASMAddr(ASMReg base, int offset) {
    this.base = base;
    this.offset = offset;
  }
  @Override
  public String toString() {
    return offset + "(" + base + ")";
  }
}
