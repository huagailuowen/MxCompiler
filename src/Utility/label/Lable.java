package Utility.label;

@lombok.Getter
@lombok.Setter
public class Lable {
  protected String name;
  public Lable(String name) {
    this.name = name;
  }

  public String toString() {
    if(name == null) return "";
    return name;
  }
  public Lable clone(){
    throw new RuntimeException("clone not implemented");
  }

}
