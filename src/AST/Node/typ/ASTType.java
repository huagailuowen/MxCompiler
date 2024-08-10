package AST.Node.typ;

import java.util.ArrayList;

import AST.Node.ASTNode;
import AST.Node.expr.ASTExpr;
import Utility.label.TypeLable;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTType extends ASTNode{
  protected TypeLable label;
  protected ArrayList<ASTExpr> dimList;

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append(label.getName());
    for (ASTExpr e : dimList) {
      if(e!=null)
        sb.append("[").append(e.toString()).append("]");
      else
        sb.append("["+"]");
    }
    return sb.toString();
  }
}
