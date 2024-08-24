package Allocator;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;

public class LifeTimeMonitor {
  //this should be done after the PHI remove
  public void visit(IRRoot node)
  {
    for(var func : node.getFuncList()){
      visit(func);
    }
  }
  public void visit(IRFuncDef node)
  {

  }
}
