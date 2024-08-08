package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTAtomExpr extends ASTExpr {
  enum AtomType {
    INT , BOOL, STRING, NULL , VOID , THIS , IDENTIFIER ,ARRAY
  }
  private final AtomType type;
  private final String value;//if this is not a atom, then this is null
  // {2+1,3} is not allowed
  private final ArrayList<ASTAtomExpr> array;  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws BaseError {
    return visitor.visit(this);
  }
}
