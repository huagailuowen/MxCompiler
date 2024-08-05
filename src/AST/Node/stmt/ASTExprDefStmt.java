package AST.Node.stmt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import AST.Node.stmt.ASTStmt;
import AST.Node.expr.ASTExpr;
import AST.Node.def.ASTVarDef;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTExprDefStmt extends ASTStmt {
  private final ArrayList<ASTExpr> exprList;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

}
