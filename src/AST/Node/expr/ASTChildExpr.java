package AST.Node.expr;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;
import AST.Node.typ.ASTType;


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