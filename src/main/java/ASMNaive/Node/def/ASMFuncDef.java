package ASMNaive.Node.def;

import ASMNaive.Node.ASMNode;
import ASMNaive.Node.stmt.ASMBlockStmt;

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
  @Override
  public String toString(){
    StringBuilder sb = new StringBuilder();
    sb.append(name+":\n");
    for (ASMBlockStmt block : blockList)
    {
      sb.append(block.toString());
      sb.append("\n");
    }
    return sb.toString();
  }
}
