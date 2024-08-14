package AST.Node;

import AST.Node.def.ASTDef;
import Ir.Utility.Counter;
import Scope.Scope;

import java.util.ArrayList;


@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTRoot extends ASTNode {
  protected Counter counter;
  //this is to calculate the ir lable
  protected ArrayList<ASTDef> defList;
  //this must be the global scope
  @Override
  public String toString() {
    StringBuilder ret = new StringBuilder();
    for(ASTDef def : defList) {
      ret.append(def.toString()).append("\n");
    }
    return ret.toString();
  }
  
  
}
