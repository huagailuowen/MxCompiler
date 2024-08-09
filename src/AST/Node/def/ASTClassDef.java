package AST.Node.def;

import java.util.ArrayList;

import AST.ASTVisitor;
import Utility.label.ClassLable;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTClassDef extends ASTDef {
  protected ClassLable label;
  protected ArrayList<ASTFuncDef> funcDefs;
  protected ArrayList<ASTVarDef> varDefs;
  protected ASTFuncDef constructor;
  @Override 
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  } 

}
