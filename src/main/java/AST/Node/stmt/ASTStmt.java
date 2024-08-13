package AST.Node.stmt;

import AST.ASTVisitor;
import AST.Node.ASTNode;
import Scope.Scope;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public abstract class ASTStmt extends ASTNode {
  protected int tabNum = 1;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

    @Override
    public String toString() {
        return "  ".repeat(tabNum);
    }
}
