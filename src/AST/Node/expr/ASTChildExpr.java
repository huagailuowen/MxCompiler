package AST.Node.expr;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;


@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTChildExpr extends ASTExpr {
  protected ASTExpr expr;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}