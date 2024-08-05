package AST.Node.stmt;

import AST.ASTVisitor;
import AST.Node.ASTNode;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public abstract class ASTStmt extends ASTNode {
  

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
  
}
