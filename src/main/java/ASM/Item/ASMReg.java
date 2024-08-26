package ASM.Item;

@lombok.Getter
@lombok.Setter
public class ASMReg {
  protected String name;
  protected int index;
  protected int stackOffset = 0;

  public ASMReg(String name, int index, int stackOffset) {
    this.name = name;
    this.index = index;
    this.stackOffset = stackOffset;
  }
  @Override
  public String toString() {
    return name;
  }
}
