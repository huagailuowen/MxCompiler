

import ASM.ASMBuilder;
import ASM.NaiveASMBuilder;
import ASM.Node.ASMNode;
import Allocator.GraphAllocator;
import Allocator.NaiveGraphAllocator;
import Grammar.MxparserLexer;
import Ir.IRBuilder;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Optim.*;
import Utility.error.MyErrorListener;
import org.antlr.v4.runtime.CharStreams;

import AST.ASTBuilder;
import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import Grammar.MxparserParser;
import Semantic.Checker;
import Semantic.Collector;
import Semantic.Compileinfo;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
public class Compiler {
  static boolean fileout = false;
  public static void run(MxparserParser.ProgramContext root, boolean opt) throws FileNotFoundException {


    ASTNode ast = new ASTBuilder().visit(root);
//    int i=1;
//    System.out.print(ast.toString());
//    System.out.println("Collector:");
    Compileinfo info = new Collector().visit((ASTRoot)ast);
    System.out.println(info);
    if(!info.empty())throw new RuntimeException(info.getContent());
//    System.out.println("Checker:");
    info = new Checker().visit((ASTRoot)ast);
    System.out.println(info);
    if(!info.empty())throw new RuntimeException(info.getContent());
    IRNode ir = new IRBuilder().visit((ASTRoot) ast);

    if(opt){
      long startTime = System.currentTimeMillis();
      new IROptimizer().visit((IRRoot) ir);
      long endTime = System.currentTimeMillis();
      System.err.println("Optimize Time cost: " + (endTime - startTime)/1000 + "s");
//      System.out.println(ir.toString());
      int cnt = new IROptimizer().calcBlockNum((IRRoot) ir);
      if(cnt>5000){
        new NaiveGraphAllocator().visit((IRRoot) ir);
      }
      else{
        new GraphAllocator().visit((IRRoot) ir);
      }
//      new NaiveGraphAllocator().visit((IRRoot) ir);

    }

    PrintStream output = null;
    if(fileout){
      output= new PrintStream(new FileOutputStream("src/test/mx/output.ll"));
      output.println(ir);
      output.close();
    }
//    System.out.println(ir.toString());
    //------------------------------------------------------------
    //erase the phi
    if(opt){
      new PhiRemover().visit((IRRoot) ir);
    }
//    System.out.println(ir.toString());
    new RubbishBlockRemover().visit((IRRoot) ir);


    ASMNode asm = new ASMBuilder().visit((IRRoot) ir);
    if(!fileout){
      System.out.println(asm);
    }

    if(!opt){
      if(fileout){
        output = new PrintStream(new FileOutputStream("src/test/mx/output.s"));
        output.println(asm);
        output.close();
      }
      return;
    }

    if(fileout){
      output = new PrintStream(new FileOutputStream("src/test/mx/test.s"));
      output.println(asm);
      output.close();
    }


  }
  public static void main(String[] args) throws IOException {
    long startTime = System.currentTimeMillis();
    boolean opt = false;
    var input = CharStreams.fromStream(System.in);
    var lexer = new MxparserLexer(input);
    lexer.removeErrorListeners();
    lexer.addErrorListener(new MyErrorListener());
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    var parser = new MxparserParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(new MyErrorListener());
    var tree = parser.program();
//    run(tree,false);
    run(tree, true);
    var builtin = CharStreams.fromFileName("src/main/c/builtin.s");
    //print the builtin
    if(!fileout)
      System.out.println(builtin);
    long endTime = System.currentTimeMillis();
    System.err.println("Time cost: " + (endTime - startTime)/1000 + "s");
  }
}
/*
Undefined Identifier
Undefined Identifier
 */

/*

int[][] s = new int[][]{{1,3,3},{},{3,5,3}};
int a=0,b=0;
int main()
{
  if((a++==b++)&&(a++==b++)){
  print("97987");
  }
  printInt(a);
}

 */
