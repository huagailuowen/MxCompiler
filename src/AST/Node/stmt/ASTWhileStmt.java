package AST.Node.stmt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import AST.Node.stmt.ASTStmt;
import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTWhileStmt extends ASTStmt {
  protected ASTExpr condition;
  protected ASTStmt content;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
  
}
