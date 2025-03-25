package Optim;

import Ir.Node.IRRoot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class IROptimizer {
  public int calcBlockNum(IRRoot root) {
    int cnt = 0;
    cnt += root.getInitFunc().getBlockList().size();
    for (var func : root.getFuncList()) {
      cnt += func.getBlockList().size();
    }
    return cnt;
  }
  public void visit(IRRoot root) throws FileNotFoundException {
    new LoopTransformer().visit(root);
//    PrintStream output;
//    output= new PrintStream(new FileOutputStream("src/test/mx/output.ll"));
//    output.println(root);
    new CFGBuilder().visit(root);
    new GlobalKiller().visit(root);
    new Mem2Reg().visit(root);
    new SinglePhiRemover().visit(root);
    var inline = new Inline();
    if(true)
    if(calcBlockNum(root) < 5000)
    {
      inline.visit(root);
      new SCCP().visit(root);
      new ADCE().visit(root);
////    //after ADCE, the useless arith and getele ins have been removed
      new GVN().visit(root);
      new GCM().visit(root);
      new ADCE().visit(root);
//    new SinglePhiRemover().visit(root);

      inline.visit(root);
      new SCCP().visit(root);
      new ADCE().visit(root);
      //after ADCE, the useless arith and getele ins have been removed
      new GVN().visit(root);
      new GCM().visit(root);
      new ADCE().visit(root);
//    new SinglePhiRemover().visit(root);
    }else{
      new SCCP().visit(root);
      new ADCE().visit(root);
      new GVN().visit(root);
      new GCM().visit(root);
      new ADCE().visit(root);
    }

  }
}
