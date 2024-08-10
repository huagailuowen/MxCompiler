package AST.Node.expr;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTTernaryExpr extends ASTExpr {
  protected ASTExpr cond;
  protected ASTExpr trueExpr;
  protected ASTExpr falseExpr;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
    @Override
        public String toString() {
            return cond.toString()+"?"+trueExpr.toString()+":"+falseExpr.toString();
        }
  
}
