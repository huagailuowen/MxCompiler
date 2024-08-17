

import Grammar.MxparserLexer;
import Ir.IRBuilder;
import Ir.Node.IRNode;
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

import java.io.IOException;

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
    System.out.println(ir.toString());
  }
}
/*
Undefined Identifier
Undefined Identifier
 */

/*

int a;

class b{
  b() {
    this.a = a;
  }
  int a;
};
int pp=2;
int f(int x,int y){
  return x+y;
}
int main(){
  int i=pp+1,j=3;
  {
    int i=j+1;
  }
  for(i=1;i<10;i=i+1){
    int j=1;
    j=f(j,1);
  }
  return j;
}
 */
