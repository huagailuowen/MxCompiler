package ASM.Node.def;

import ASM.Node.ASMNode;
import ASM.Node.stmt.ASMBlockStmt;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class ASMFuncDef extends ASMNode {
  ArrayList<ASMBlockStmt> blockList;
  String name;
  public ASMFuncDef(String name){
    this.name = name;
    blockList = new ArrayList<>();
  }
  public void addBlock(ASMBlockStmt block){
    blockList.add(block);
  }
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append("  .globl "+name+"\n");
//    sb.append("  .text\n");
    sb.append(name+":\n");
    for (int i = 0;i < blockList.size();i++)
    {
      var block = blockList.get(i);
      if(i + 1 != blockList.size())
        sb.append(block.toString(blockList.get(i+1).getLable()));
      else
        sb.append(block.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
