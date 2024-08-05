package AST.Node.def;

import AST.Node.ASTNode;
import AST.Node.expr.ASTExpr;
import Utility.label.VarLable;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTVarDef extends ASTDef {
  private final VarLable label;
  private final ASTExpr init;
}
