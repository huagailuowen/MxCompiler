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

    @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            ret.append(super.toString());
            ret.append("while(");
            ret.append(condition.toString());
            ret.append(")\n");
            content.setTabNum(tabNum+1);
            ret.append(content.toString());
            return ret.toString();
        }
}
