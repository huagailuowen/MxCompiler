package AST.Node.stmt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import AST.Node.stmt.ASTStmt;
import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTForStmt extends ASTStmt {
  private final ASTStmt init;
  private final ASTExpr cond;
  private final ASTStmt update;
  private final ArrayList<ASTStmt> stmtList;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
  
}
