package ASMNaive.Utility;

import ASMNaive.Node.ASMNode;
@lombok.Getter
@lombok.Setter
public class ASMLable extends ASMNode {
  protected String lable;
  public ASMLable(String lable) {
    this.lable = lable;
  }
  @Override
  public String toString() {
    return '#'+lable;
  }

}
