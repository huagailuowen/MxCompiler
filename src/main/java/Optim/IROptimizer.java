package Optim;

import Ir.Node.IRRoot;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class IROptimizer {
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
    inline.visit(root);
    new SCCP().visit(root);
    new ADCE().visit(root);
////    //after ADCE, the useless arith and getele ins have been removed
    new GVN().visit(root);
    new GCM().visit(root);
    new ADCE().visit(root);

    inline.visit(root);
    new SCCP().visit(root);
    new ADCE().visit(root);
    //after ADCE, the useless arith and getele ins have been removed
    new GVN().visit(root);
    new GCM().visit(root);
    new ADCE().visit(root);
  }
}
