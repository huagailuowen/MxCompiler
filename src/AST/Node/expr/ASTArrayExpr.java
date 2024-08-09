package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTArrayExpr extends ASTExpr {
  protected ASTExpr expr;
  protected ArrayList<ASTExpr> array;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}