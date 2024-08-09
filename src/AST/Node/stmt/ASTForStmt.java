package AST.Node.stmt;

import java.lang.reflect.Array;
import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.stmt.ASTStmt;
import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTForStmt extends ASTStmt {
  protected ASTStmt init;
  protected ASTExpr cond;
  protected ASTExpr update;
  protected ASTStmt content;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
  
}
