package AST.Node.def;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.ASTNode;
import AST.Node.stmt.ASTStmt;
import AST.Node.def.ASTVarDef;
import Utility.label.FuncLable;


@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTFuncDef extends ASTDef{
  private final FuncLable label;
  private final ArrayList<ASTVarDef> paraList;
  private final ArrayList<ASTStmt> stmtList;
}
