package AST.Node;

import Scope.Scope;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTRoot extends ASTNode {
  private final ArrayList<ASTDef> defList;
  private final Scope scope;
  //this must be the global scope
  
  
}
