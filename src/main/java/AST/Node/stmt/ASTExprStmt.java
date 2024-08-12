package AST.Node.stmt;

import java.lang.reflect.Array;
import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.stmt.ASTStmt;
import AST.Node.expr.ASTExpr;
import AST.Node.def.ASTVarDef;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTExprStmt extends ASTStmt {
  protected ArrayList<ASTExpr> exprList;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

    @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            ret.append(super.toString());
            for(ASTExpr expr : exprList) {
            ret.append(expr.toString()).append(";");
            }
            return ret.toString();
        }
}
