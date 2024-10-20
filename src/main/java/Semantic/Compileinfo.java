package Semantic;

import Utility.position.Position;

@lombok.Getter
@lombok.Setter
public class Compileinfo {
  private String Content;
  private Position pos;

  public  Compileinfo(){
    Content = new String("");
  }
  public Compileinfo(String Content) {
    if(Content.isEmpty()){
      int i=1;
    }
    this.Content = Content;
    this.pos = null;
  }
  public Compileinfo(String Content, Position pos) {
    if(Content.isEmpty()){
      int i=1;
    }
    this.Content = Content;
    this.pos = pos;
  }
  public void append(Compileinfo info)
  {
    if(info == null)
      return;
    if(!this.getContent().isEmpty())
      return;
    if(Content == null)
      Content = new String();
    if(info.Content.isEmpty())
      return;
    if(!info.Content.isEmpty()&& !this.Content.isEmpty()){
      this.Content += "\n";
    }
    if(!info.Content.isEmpty())
      this.Content += info.toString();
  }
//  public String toString() {
//    return (Content.isEmpty()?"":"Compile Error:"+Content+(pos==null?"":' '+pos.toString()));
//  }
  public String toString() {
    return Content == null ? "" : Content ;//+ (pos == null ? "" : ' ' + pos.toString());
  }
  public boolean empty() {
    return Content.isEmpty();
  }
}
