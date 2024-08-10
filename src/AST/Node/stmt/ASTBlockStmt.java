package AST.Node.stmt;

import AST.Node.expr.ASTExpr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.stmt.ASTStmt;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTBlockStmt extends ASTStmt {
  protected ArrayList<ASTStmt> stmts;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append("  ".repeat(tabNum-1));
        ret.append("{\n");
        for(ASTStmt stmt : stmts) {
          stmt.setTabNum(tabNum);
          ret.append(stmt.toString()).append("\n");
        }
        ret.append("  ".repeat(tabNum-1)).append("}");
        return ret.toString();
    }
}
