package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTCallExpr extends ASTExpr {
  protected ASTExpr expr;
  protected ArrayList<ASTExpr> args;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

  @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(expr.toString());
        sb.append("(");
        for (int i = 0; i < args.size(); i++) {
          sb.append(args.get(i).toString());
          if (i != args.size() - 1) {
            sb.append(", ");
          }
        }
        sb.append(")");
        return sb.toString();
    }
}
