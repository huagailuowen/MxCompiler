package AST.Node.def;

import java.util.ArrayList;

import AST.ASTVisitor;
import AST.Node.stmt.ASTStmt;
import Utility.label.FuncLable;


@lombok.experimental.SuperBuilder
@lombok.Getter
@lombok.Setter
public class ASTFuncDef extends ASTDef{
  protected FuncLable label;
  protected ArrayList<ASTVarDef> paraList;
  protected ArrayList<ASTStmt> stmtList;
  @Override
  public <T> T accept(ASTVisitor<T> visitor) {
    return visitor.visit(this);
  }

  @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        ret.append(label.toString());
        ret.append("(");
        for(int i = 0; i < paraList.size(); i++) {
        ret.append(paraList.get(i).toString());
        if(i != paraList.size() - 1) {
            ret.append(", ");
        }
        }
        ret.append(") {\n");
        for(ASTStmt stmt : stmtList) {
            stmt.setTabNum(1);
        ret.append(stmt.toString()).append("\n");
        }
        ret.append("}\n");
        return ret.toString();
    }
}


