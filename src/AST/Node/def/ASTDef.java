package AST.Node.def;

import AST.ASTVisitor;
import AST.Node.ASTNode;
import Scope.Scope;
import Utility.label.Lable;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public abstract class ASTDef extends ASTNode {
  private Lable lable;
  private final Scope scope;
  
  @Override 
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  } 
}
