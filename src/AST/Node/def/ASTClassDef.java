package AST.Node.def;

import java.util.ArrayList;

import Utility.label.ClassLable;
import AST.ASTVisitor;
import AST.Node.def.*;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTClassDef extends ASTDef {
  private final ClassLable label;
  private final ArrayList<ASTFuncDef> funcDefs;
  private final ArrayList<ASTVarDef> varDefs;
  private final ASTFuncDef constructor;
  @Override 
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  } 

}
