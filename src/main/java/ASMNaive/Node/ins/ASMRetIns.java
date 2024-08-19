package ASMNaive.Node.ins;
@lombok.Getter
@lombok.Setter
public class ASMRetIns extends ASMIns{
  @Override
  public String toString(){
    return String.format("%-6s", "ret");
  }
}
