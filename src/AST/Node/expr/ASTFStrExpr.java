package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;

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
