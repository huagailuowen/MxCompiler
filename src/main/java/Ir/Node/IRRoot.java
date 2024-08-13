package Ir.Node;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Node.def.IRFuncDef;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class IRRoot extends IRNode{
  ArrayList<IRFuncDef> funcList;
  ArrayList<IRNode> globalDefList;
  public IRRoot(){
    funcList = new ArrayList<>();
    globalDefList = new ArrayList<>();
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }

  public void addFunc(IRNode funcDef){
    funcList.add((IRFuncDef) funcDef);
  }
  public void addGlobalDef(IRNode globalDef){
    globalDefList.add(globalDef);
  }
}
