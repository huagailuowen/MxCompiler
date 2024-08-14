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
  int entryBlockIndex;
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
