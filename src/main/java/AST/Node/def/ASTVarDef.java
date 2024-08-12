package AST.Node.def;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;
import Utility.label.VarLable;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTVarDef extends ASTDef {
  protected VarLable label;
  protected ASTExpr init;
  @Override 
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  } 

  @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(label.toString());
        if(init != null) {
        ret.append(" = ").append(init.toString());
        }
        return ret.toString();
    }
}

