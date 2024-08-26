package ASM.Item;

@lombok.Getter
@lombok.Setter
public class ASMReg {
  protected String name;
  protected int index;

  public ASMReg(String name, int index) {
    this.name = name;
    this.index = index;
  }
  @Override
  public String toString() {
    return name;
  }
}
