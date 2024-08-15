package Ir.Node;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Node.def.IRFuncDef;
import Ir.Node.def.IRGlobalDef;
import Ir.Type.IRBaseType;
import Ir.Utility.IRLable;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class IRRoot extends IRNode{
  ArrayList<IRFuncDef> funcList;
  IRFuncDef initFunc;
  ArrayList<IRGlobalDef> globalDefList;
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

    for (IRNode globalDef : globalDefList) {
      string.append(globalDef.toString());
    }
    string.append(initFunc.toString());
    for (IRNode funcDef : funcList) {
      string.append(funcDef.toString());
    }


    return string.toString();
    throw new ErrorBasic("TO DO: the link to c libary");
  }

}
