package AST.Node;

@lombok.experimental.SuperBuilder
public class ASTRoot extends ASTNode {
  private final ArrayList<ASTDef> defList;
  public ASTRoot() {
    super();
  }
  
}
