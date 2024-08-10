package AST.Node.expr;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTMemExpr extends ASTExpr {
  protected ASTExpr expr;
  protected String member; 
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
    @Override
        public String toString() {
            return expr.toString()+"."+member;
        }
}
