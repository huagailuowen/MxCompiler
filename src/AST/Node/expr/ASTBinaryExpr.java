package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;
import AST.Node.typ.ASTType;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTBinaryExpr extends ASTExpr {
  private final ASTExpr lhs;
  private final ASTExpr rhs;
  private final String op;
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
  
}
