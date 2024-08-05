package AST.Node.expr;

import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public abstract class ASTChildExpr extends ASTExpr {
  private final ASTExpr child;
  
}