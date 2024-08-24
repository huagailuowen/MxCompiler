package Allocator;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;

public class GraphAllocator {
  LifeTimeMonitor lifeTimeMonitor;
  static int K = 24;
  //largest number of registers
  public GraphAllocator() {
    lifeTimeMonitor = new LifeTimeMonitor();
  }
  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for (var func : node.getFuncList()) {
      visit(func);
    }
  }
  public void visit(IRFuncDef node) {
    lifeTimeMonitor.visit(node);

  }
  /*TO DO List:
   * 1. Implement the Graph Builder
   * 2. Implement the Graph Colored
   * 3. Implement the Graph Spiller
   * 4. Fix the PhiRemover
   */

}
