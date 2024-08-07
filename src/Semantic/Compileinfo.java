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
    if(info == null)
      return;
    if(Content == null)
      Content = new String();
    if(!info.Content.isEmpty()&& !this.Content.isEmpty()){
      this.Content += "\n";
    }
    if(!info.Content.isEmpty())
      this.Content += info.toString();
  }
  public String toString() {
    return "Compile Error:"+Content+(pos==null?"":pos.toString());
  }

}
