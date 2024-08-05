package AST.Node.expr;

import AST.ASTVisitor;
import AST.Node.ASTNode;
import Utility.label.*;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public abstract class ASTExpr extends ASTNode {
  private final ExprLable label;  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
