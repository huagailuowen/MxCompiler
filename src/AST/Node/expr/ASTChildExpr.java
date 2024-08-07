package AST.Node.expr;

import AST.ASTVisitor;


@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTChildExpr extends ASTExpr {
  private final ASTExpr expr;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
}