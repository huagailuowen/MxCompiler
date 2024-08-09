package AST.Node;

import AST.ASTVisitor;
import Utility.position.Position;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTNode {
  protected Position position;
  protected ASTNode father;

  public String toString() {
    return "ASTNode at " + (position == null ? "unknown position" : position.toString());
  }

  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  } 
}
