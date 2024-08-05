package AST.Node.expr;

import AST.ASTVisitor;
import AST.Node.ASTNode;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public abstract class ASTExpr extends ASTNode {
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
