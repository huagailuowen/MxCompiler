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

    @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            ret.append(super.toString());
            ret.append("for(");
            init.setTabNum(0);
            ret.append(init.toString());
            if(cond!=null)
              ret.append(cond.toString());
            ret.append(";");
            if(update!=null)
              ret.append(update.toString());
            ret.append(")\n");
            content.setTabNum(tabNum+1);
            ret.append(content.toString());
            return ret.toString();
        }
}
