package AST.Node.stmt;

import java.lang.reflect.Array;
import java.util.ArrayList;
import AST.Node.stmt.ASTStmt;
import AST.Node.typ.ASTType;
import AST.Node.expr.ASTExpr;
import AST.Node.def.ASTVarDef;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTVarDefStmt {
  // private final ASTType type;
  //this line is useless because the type is already defined in the varList
  private final ArrayList<ASTVarDef> varList;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
