package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;
import AST.Node.typ.ASTType;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTTernaryExpr extends ASTExpr {
  private final ASTExpr cond;
  private final ASTExpr trueExpr;
  private final ASTExpr falseExpr;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
  
}
