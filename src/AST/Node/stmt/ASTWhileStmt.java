package AST.Node.stmt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import AST.Node.stmt.ASTStmt;
import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTWhileStmt extends ASTStmt {
  private final ASTExpr condition;
  private final ArrayList<ASTStmt> stmtList;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
  
}
