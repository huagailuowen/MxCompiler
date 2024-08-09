package AST.Node.stmt;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTIfStmt extends ASTStmt {
  protected ASTExpr cond;
  protected ASTStmt thenStmt;
  protected ASTStmt elseStmt;
  

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
