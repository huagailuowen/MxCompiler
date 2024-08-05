package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;
import AST.Node.typ.ASTType;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTFStrExpr extends ASTExpr {
  private final ArrayList<ASTExpr> args;
  private final ArrayList<String> strs;
  //the strs size is always args.size() + 1
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
  
}
