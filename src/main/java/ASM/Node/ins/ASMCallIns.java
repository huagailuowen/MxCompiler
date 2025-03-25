package ASM.Node.ins;
@lombok.Getter
@lombok.Setter
public class ASMCallIns extends ASMIns{
  String funcName;
  public ASMCallIns(String funcName){
    this.funcName = funcName;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "call") + funcName;
  }
}
