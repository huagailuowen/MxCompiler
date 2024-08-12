package AST.Node.expr;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTBinaryExpr extends ASTExpr {
  protected ASTExpr lhs;
  protected ASTExpr rhs;
  protected String op;
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

  @Override
    public String toString() {
        return lhs.toString()+" "+op+" "+rhs.toString();
    }
  
}
