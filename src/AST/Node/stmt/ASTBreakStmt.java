package AST.Node.stmt;

import AST.Node.expr.ASTExpr;
import AST.ASTVisitor;
import AST.Node.stmt.ASTStmt;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTBreakStmt extends ASTStmt {
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
  
}
