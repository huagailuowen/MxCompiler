package ASMNaive.Node.def;

import ASMNaive.Node.ASMNode;

@lombok.Getter
@lombok.Setter
public class ASMVarDef extends ASMNode {
  protected String name;
  protected int value;
  protected String type;
  public ASMVarDef(String name, int value, String type){
//    if(name.startsWith("@"))name = name.substring(1);
    this.name = name;
    this.value = value;
    this.type = type;
  }
  @Override
  public String toString(){
    String str = " .align 4\n"+name+":\n  .word "+value;
//    if(this.type.equals("i1")||this.type.equals("i8")||this.type.equals("i32")){
      str+= "\n  .word 0";
//    }
    return str;
  }
}
