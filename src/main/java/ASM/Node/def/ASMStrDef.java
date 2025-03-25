package ASM.Node.def;

import ASM.Node.ASMNode;

@lombok.Getter
@lombok.Setter
public class ASMStrDef extends ASMNode {
  protected String name;
  protected String value;
  public ASMStrDef(String name, String value){
    this.name = name;
    this.value = value;
  }
  @Override
  public String toString(){
    return name+":\n  .string \""+value+ "\\00\"";
  }
}
