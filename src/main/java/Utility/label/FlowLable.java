package Utility.label;


@lombok.Getter
@lombok.Setter
public class FlowLable extends Lable {
  public FlowLable(String name) {
    super(name);
  }
  public boolean isBreak() {
    return name.equals("break");
  }
  public boolean isContinue() {
    return name.equals("continue");
  }
  public boolean isReturn() {
    return name.equals("return");
  }
  public boolean isFor() {
    return name.equals("for");
  }
  public boolean isWhile() {
    return name.equals("while");
  }
  public boolean isLoop() {
    return isFor()||isWhile();
  }

    @Override
    public String toString() {
        return name;
    }
}
