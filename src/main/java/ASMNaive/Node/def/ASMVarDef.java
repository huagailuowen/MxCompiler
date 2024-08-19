package ASMNaive.Node.def;

import ASMNaive.Node.ASMNode;

@lombok.Getter
@lombok.Setter
public class ASMVarDef extends ASMNode {
  protected String name;
  protected int value;
  public ASMVarDef(String name, int value){
    this.name = name;
    this.value = value;
  }
  @Override
  public String toString(){
    return name+":\n  .word "+value;
  }
}
