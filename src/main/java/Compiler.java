

import ASMNaive.ASMBuilder;
import ASMNaive.Node.ASMNode;
import Grammar.MxparserLexer;
import Ir.IRBuilder;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Utility.error.MyErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStreams;

import AST.ASTBuilder;
import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import Grammar.MxparserBaseListener;
import Grammar.MxparserParser;
import Semantic.Checker;
import Semantic.Collector;
import Semantic.Compileinfo;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class Compiler {
  public static void main(String[] args) throws IOException {
    var input = CharStreams.fromStream(System.in);
    var lexer = new MxparserLexer(input);
    lexer.removeErrorListeners();
    lexer.addErrorListener(new MyErrorListener());
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    var parser = new MxparserParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(new MyErrorListener());
    var tree = parser.program();
    ASTNode ast = new ASTBuilder().visit(tree);
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
//    System.out.println(ir.toString());
    var output = new PrintStream(new FileOutputStream("src/test/mx/output.ll"));
    output.println(ir);
    output.close();

    ASMNode asm = new ASMBuilder().visit((IRRoot) ir);
    System.out.println(asm);
    output = new PrintStream(new FileOutputStream("src/test/mx/output.s"));
    output.println(asm);
    output.close();
  }
}
/*
Undefined Identifier
Undefined Identifier
 */

/*

int[][] s = new int[][]{{1,3,3},{},{3,5,3}};
int p=1;
int main()
{
  int x=1,n=10;
  for(int i=1;i<=n;i++){
    return -1;
  }
  s[4][4]=1;
  if(x+x==x){
    x=x%10+5;
  }


    return 0;
}

 */
