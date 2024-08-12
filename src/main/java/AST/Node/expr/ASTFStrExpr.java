package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTFStrExpr extends ASTExpr {
  protected ArrayList<ASTExpr> args;
  protected ArrayList<String> strs;
  //the strs size is always args.size() + 1
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("f\"");
        for (int i = 0; i < args.size(); i++) {
          sb.append(strs.get(i));
          sb.append("{");
          sb.append(args.get(i).toString());
          sb.append("}");
        }
        sb.append(strs.get(strs.size()-1));
        sb.append("\"");
        return sb.toString();
    }
}
