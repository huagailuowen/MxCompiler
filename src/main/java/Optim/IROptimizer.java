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
    new Mem2Reg().visit(root);
  }
}
