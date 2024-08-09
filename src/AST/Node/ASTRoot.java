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
  
  
}
