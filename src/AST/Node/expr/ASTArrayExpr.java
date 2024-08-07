package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTArrayExpr extends ASTExpr {
  private final ASTExpr expr;
  private final ArrayList<ASTExpr> array;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
}