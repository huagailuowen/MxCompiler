package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;
import AST.Node.typ.ASTType;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTAtomExpr extends ASTExpr {
  enum AtomType {
    INT, FLOAT, BOOL, STRING, NULL , VOID , THIS , IDENTIFIER ,ARRAY
  }
  private final AtomType type;
  private final String value;//if this is not a atom, then this is null
  private final ArrayList<ASTExpr> array;  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
}
