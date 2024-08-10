package Utility.label;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeMap;

import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import Utility.label.Lable;
import Utility.label.FuncLable;
import Utility.label.VarLable;


@lombok.Getter
@lombok.Setter
public class ClassLable extends Lable{
  protected  TypeLable type;
  protected TreeMap<String, FuncLable> funcTable;
  protected TreeMap<String, VarLable> varTable;
  public ClassLable(String name){
    super(name);
    this.funcTable = new TreeMap<String, FuncLable>();
    this.varTable = new TreeMap<String, VarLable>();
  }
  public ClassLable(String name, TreeMap<String, FuncLable> funcTable, TreeMap<String, VarLable> varTable) {
    super(name);
  this.type = new TypeLable(name);
    this.funcTable = funcTable;
    this.varTable = varTable;
  }
//  public ClassLable(String name, ArrayList<FuncLable> funcList, ArrayList<VarLable> varList) {
//    super(name);
//    this.type = new TypeLable(name);
//    this.funcTable = new TreeMap<String, FuncLable>();
//    this.varTable = new TreeMap<String, VarLable>();
//    for(FuncLable f : funcList) {
//      this.funcTable.put(f.getName(), f);
//    }
//    for(VarLable v : varList) {
//      this.varTable.put(v.getName(), v);
//    }
//  }
  public ClassLable(String name, ArrayList<ASTFuncDef> funcDefs, ArrayList<ASTVarDef> varDefs) {
    super(name);
    this.type = new TypeLable(name);
    this.funcTable = new TreeMap<String, FuncLable>();
    this.varTable = new TreeMap<String, VarLable>();
    for(ASTFuncDef f : funcDefs) {
      this.funcTable.put(f.getLabel().getName(), new FuncLable(f.getLabel().getName(), f.getLabel().getReturnType(), f.getLabel().getParamTypes()));
    }
    for(ASTVarDef v : varDefs) {
      this.varTable.put(v.getLabel().getName(), new VarLable(v.getLabel().getName(), v.getLabel().getType()));
    }
  }

}
