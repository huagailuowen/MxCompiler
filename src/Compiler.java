import org.antlr.v4.runtime.CharStreams;

import AST.ASTBuilder;
import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import Grammar.MxparserBaseListener;
import Grammar.MxparserParser;
import Semantic.Checker;
import Semantic.Collector;
import Semantic.Compileinfo;

public class Compiler {
  public static void main(String[] args){
    var input = CharStreams.fromStream(System.in);
    var parser = new MxparserParser(input);
    parser.addErrorListener(new MxparserBaseListener());
    var tree = parser.program();
    ASTNode ast = new ASTBuilder().visit(parser.program());
    System.out.println("Collector:");
    Compileinfo info = new Collector().visit((ASTRoot)ast);
    System.out.println(info);
    System.out.println("Checker:");
    info = new Checker().visit((ASTRoot)ast);
    System.out.println(info);
  }
}
