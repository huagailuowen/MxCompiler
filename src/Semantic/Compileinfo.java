package Semantic;

import Utility.position.Position;

@lombok.Getter
@lombok.Setter
public class Compileinfo {
  private String Content;
  private Position pos;

  public Compileinfo(String Content, Position pos) {
    this.Content = Content;
    this.pos = pos;
  }
  public void append(Compileinfo info)
  {
    this.Content += '\n' + info.toString();
  }
  public String toString() {
    return "Compile Error:"+Content+(pos==null?"":pos.toString());
  }

}
