package AST.Node.expr;

import AST.ASTVisitor;
import AST.Node.typ.ASTType;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTNewExpr extends ASTExpr {
  protected ASTType type; 
  protected ASTAtomExpr expr;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
    @Override
    public String toString() {
      if(expr==null) return "new "+type.toString();
      return "new "+type.toString()+'('+expr.toString()+')';
    }
}
