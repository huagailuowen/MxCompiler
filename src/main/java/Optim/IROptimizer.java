package Optim;

import Ir.Node.IRRoot;

public class IROptimizer {
  public void visit(IRRoot root){
    new CFGBuilder().visit(root);
    new Mem2Reg().visit(root);
  }
}
