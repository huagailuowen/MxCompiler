package AST.Node.def;

import AST.ASTVisitor;
import AST.Node.ASTNode;
import Utility.label.Label;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public abstract class ASTDef extends ASTNode {
  private Lable lable;
  
  
  @Override 
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  } 
}
