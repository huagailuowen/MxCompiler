import Grammar.MxparserLexer;
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
    lexer.addErrorListener(new BaseErrorListener());
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    var parser = new MxparserParser(tokens);
    parser.removeErrorListeners();
    parser.addErrorListener(new BaseErrorListener());
    var tree = parser.program();
    ASTNode ast = new ASTBuilder().visit(tree);
    int i=1;
    System.out.print(ast.toString());
//    System.out.println("Collector:");
//    Compileinfo info = new Collector().visit((ASTRoot)ast);
//    System.out.println(info);
//    System.out.println("Checker:");
//    info = new Checker().visit((ASTRoot)ast);
//    System.out.println(info);
  }
}
/*
int main()
{
  a++;
  int s=f"342\\347893274\"{}{}$i+1+f"39847293$i=1$"$ 458934\\$$";
}
int a=1;
 */
