package ASMNaive.Node.ins;
@lombok.Getter
@lombok.Setter
public class ASMJmpIns extends ASMIns{
  String lable;
  public ASMJmpIns(String lable){
    this.lable = lable;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "j") + lable;
  }
}
