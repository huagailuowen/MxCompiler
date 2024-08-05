package AST.Node.stmt;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTIfStmt extends ASTStmt {
  private final ASTExpr cond;
  private final ASTStmt thenStmt;
  private final ASTStmt elseStmt;
  

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
