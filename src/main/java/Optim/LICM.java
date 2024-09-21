package Optim;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;

public class LICM {
    public void visit(IRRoot node) {
      visit(node.getInitFunc());
      for(var func : node.getFuncList()){
        visit(func);
      }


    }
    void visit(IRFuncDef node)
    {

    }
}
