package AST.Node.expr;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTPreExpr extends ASTExpr {
  protected ASTExpr expr;
  protected String op;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
    @Override
        public String toString() {
            return op+expr.toString();
        }
}
