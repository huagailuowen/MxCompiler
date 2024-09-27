package Optim;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;

import java.util.HashSet;

public class Inline {
  HashSet<IRFuncDef> inlined;
  boolean judge(IRFuncDef node){
    return false;
  }
  public void visit(IRRoot root) {
    inlined = new HashSet<>();
    if(judge(root.getInitFunc())){
      inlined.add(root.getInitFunc());
    }
    for(var func : root.getFuncList()){
      if(judge(func)){
        inlined.add(func);
      }
    }
    for(var func : inlined){
      root.getFuncList().remove(func);
    }
  }

}
