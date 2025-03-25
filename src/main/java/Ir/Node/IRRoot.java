package Ir.Node;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Node.def.IRFuncDef;
import Ir.Node.def.IRGlobalDef;
import Ir.Type.IRBaseType;
import Ir.Utility.Counter;
import Ir.Utility.IRLable;
import Scope.BasicClassFunc;
import Scope.Scope;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class IRRoot extends IRNode{
  ArrayList<IRFuncDef> funcList;
  IRFuncDef initFunc;
  ArrayList<IRGlobalDef> globalDefList;
  Counter counter ;
  Scope scope;
  public IRRoot(){
    funcList = new ArrayList<>();
    globalDefList = new ArrayList<>();
    initFunc = new IRFuncDef();
    initFunc.setName(new IRLable("__init__"));
    initFunc.setReturnType(IRBaseType.getVoidType());
    initFunc.setParamList(new ArrayList<>());
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

  public void addFunc(IRNode funcDef){
    funcList.add((IRFuncDef) funcDef);
  }
  public void addGlobalDef(IRGlobalDef globalDef){
    globalDefList.add(globalDef);
  }
  public void addinitStmt(IRFuncDef initFunc){
    this.initFunc = initFunc;
  }
  @Override
  public String toString() {
    StringBuilder string = new StringBuilder();

    for(var func : BasicClassFunc.BuildInFunc){
      var irname = scope.getIrLable(func.getName(),false);
      string.append("declare "+ (new IRBaseType(func.getReturnType())) +" @"+irname+"(");
      for(int i = 0; i < func.getParamTypes().size(); i++){
        if(i != 0) string.append(", ");
        string.append((new IRBaseType(func.getParamTypes().get(i))));
      }
      string.append(")\n");
    }
    string.append("declare ptr @__malloc(i32, i32)\n");
    string.append("declare ptr @__malloc_array(i32, i32)\n");
    string.append("declare i32 @__string_eq(ptr, ptr)\n");
    string.append("declare i32 @__string_ne(ptr, ptr)\n");
    string.append("declare i32 @__string_lt(ptr, ptr)\n");
    string.append("declare i32 @__string_le(ptr, ptr)\n");
    string.append("declare i32 @__string_gt(ptr, ptr)\n");
    string.append("declare i32 @__string_ge(ptr, ptr)\n");
    string.append("declare ptr @__string_concat(ptr, ptr)\n");
    string.append("declare ptr @toString_bool(i1)\n");

    for (IRNode globalDef : globalDefList) {
      string.append(globalDef.toString());
      string.append("\n");
    }
    string.append(initFunc.toString());
    for (IRNode funcDef : funcList) {
      string.append(funcDef.toString());
    }


    return string.toString();
//    throw new ErrorBasic("TO DO: the link to c libary");
  }

}
