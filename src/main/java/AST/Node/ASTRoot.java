package AST.Node;

import AST.Node.def.ASTDef;
import Scope.Scope;

import java.util.ArrayList;


@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTRoot extends ASTNode {
  protected ArrayList<ASTDef> defList;
  protected Scope scope;
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
