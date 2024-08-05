package AST.Node.stmt;

import AST.Node.expr.ASTExpr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Visitor.Visitor;
import AST.Node.stmt.ASTStmt;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTBlockStmt extends ASTStmt {
  private final ArrayList<ASTStmt> stmts;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
  
}
