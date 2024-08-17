package Ir.Node.def;

import Ir.IRVisitor;
import Ir.Item.RegItem;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Type.IRBaseType;
import Ir.Utility.IRLable;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.TreeMap;

@lombok.Getter
@lombok.Setter
public class IRFuncDef extends IRDef {
  IRLable name;
  IRBaseType returnType;
  ArrayList<RegItem> paramList;
  ArrayList<IRBlockStmt> blockList;
//  int entryBlockIndex; must be 0
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("define ").append(returnType.toString()).append(" ").append("@").append(name.toString()).append("(");
    for (int i = 0; i < paramList.size(); i++) {
      if (i != 0) sb.append(", ");
      sb.append(paramList.get(i).getType().toString()).append(" ").append(paramList.get(i).getName());
    }
    sb.append(") {\n");
    for (IRBlockStmt block : blockList) {
      sb.append(block.toString());
      sb.append('\n');
    }
    sb.append("}\n");
    return sb.toString();
  }
}
