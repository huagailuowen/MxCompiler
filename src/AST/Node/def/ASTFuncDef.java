package AST.Node.def;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.stmt.ASTStmt;
import Utility.label.FuncLable;


@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTFuncDef extends ASTDef{
  private final FuncLable label;
  private final ArrayList<ASTVarDef> paraList;
  private final ArrayList<ASTStmt> stmtList;
  @Override 
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  } 
}
