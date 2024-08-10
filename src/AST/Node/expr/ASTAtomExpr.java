package AST.Node.expr;

import java.util.ArrayList;

import AST.ASTVisitor;
import Utility.error.ErrorBasic;

@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTAtomExpr extends ASTExpr {
  public enum AtomType {
    INT , BOOL, STRING, NULL , VOID , THIS , IDENTIFIER ,ARRAY
  }
  protected AtomType type;
  protected String value;//if this is not a atom, then this is null
  // {2+1,3} is not allowed
  protected ArrayList<ASTAtomExpr> array;  
  @Override
  public <T> T accept(ASTVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

  @Override
    public String toString() {
        if(type==AtomType.ARRAY){
          StringBuilder sb = new StringBuilder();
          sb.append("{");
          for (ASTAtomExpr e : array) {
          sb.append(e.toString()+",");
          }
          sb.deleteCharAt(sb.length()-1);
          sb.append("}");
          return sb.toString();
        }
        if(type==AtomType.STRING){
          return "\""+value+"\"";
        }
        return value;
    }
}
