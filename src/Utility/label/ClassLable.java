package Utility.label;

import java.util.ArrayList;
import java.util.Map;

import Utility.label.Label;
import Utility.label.FuncLable;
import Utility.label.VarLable;


@lombok.Getter
@lombok.Setter
public class ClassLable extends Label{
  private final Map<String, FuncLable> funcTable;
  private final Map<String, VarLable> varTable;
  public ClassLable(String name, Map<String, FuncLable> funcTable, Map<String, VarLable> varTable) {
    super(name);
    this.funcTable = funcTable;
    this.varTable = varTable;
  }
  public ClassLable(String name, ArrayList<FuncLable> funcList, ArrayList<VarLable> varList) {
    super(name);
    this.funcTable = new HashMap<String, FuncLable>();
    this.varTable = new HashMap<String, VarLable>();
    for(FuncLable f : funcList) {
      this.funcTable.put(f.getName(), f);
    }
    for(VarLable v : varList) {
      this.varTable.put(v.getName(), v);
    }
  }
  public ClassLable(String name, ArrayList<ASTFuncDef> funcDefs, ArrayList<ASTVarDef> varDefs) {
    super(name);
    this.funcTable = new HashMap<String, FuncLable>();
    this.varTable = new HashMap<String, VarLable>();
    for(ASTFuncDef f : funcDefs) {
      this.funcTable.put(f.getName(), new FuncLable(f.getName(), f.getReturnType(), f.getParamTypes()));
    }
    for(ASTVarDef v : varDefs) {
      this.varTable.put(v.getName(), new VarLable(v.getName(), v.getType()));
    }
  }
}
