package AST.Node.expr;

import AST.ASTVisitor;
import AST.Node.typ.ASTType;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTNewExpr extends ASTExpr {
  private final ASTType type; 
  private final ASTAtomExpr expr;

  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
}
