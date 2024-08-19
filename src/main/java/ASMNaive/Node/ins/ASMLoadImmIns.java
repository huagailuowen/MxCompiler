package ASMNaive.Node.ins;
@lombok.Getter
@lombok.Setter
public class ASMLoadImmIns extends ASMIns{
  String reg;
  int imm;
  public ASMLoadImmIns(String reg, int imm){
    this.reg = reg;
    this.imm = imm;
  }
  @Override
  public String toString(){
    return String.format("%-6s", "li") + reg + " " + imm;
  }
}
