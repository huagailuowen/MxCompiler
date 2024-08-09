package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTFStrExpr extends ASTExpr {
  protected ArrayList<ASTExpr> args;
  protected ArrayList<String> strs;
  //the strs size is always args.size() + 1
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  
}
