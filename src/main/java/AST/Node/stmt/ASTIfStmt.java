package AST.Node.stmt;

import AST.ASTVisitor;
import AST.Node.expr.ASTExpr;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTIfStmt extends ASTStmt {
  protected ASTExpr cond;
  protected ASTStmt thenStmt;
  protected ASTStmt elseStmt;
  

  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

    @Override
    public String toString() {
        thenStmt.setTabNum(tabNum+1);
        if(elseStmt!=null) elseStmt.setTabNum(tabNum+1);
        return super.toString()+"if("+cond.toString()+")\n"+thenStmt.toString()+(elseStmt==null?"":"\n"+super.toString()+"else\n"+elseStmt.toString());
    }


}

