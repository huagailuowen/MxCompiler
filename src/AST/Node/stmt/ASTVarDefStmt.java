package AST.Node.stmt;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.def.ASTVarDef;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTVarDefStmt extends ASTStmt{
  // protected ASTType type;
  //this line is useless because the type is already defined in the varList
  protected ArrayList<ASTVarDef> varList;
  
  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }
}
