package AST.Node.typ;

import AST.Node.ASTNode;
import Utility.label.TypeLable;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTType extends ASTNode{
  private final TypeLable label;
  
}
