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
    System.out.println("Collector:");
    Compileinfo info = new Collector().visit((ASTRoot)ast);
    System.out.println(info);
    System.out.println("Checker:");
    info = new Checker().visit((ASTRoot)ast);
    System.out.println(info);
  }
}
/*

int main() {
  int i;
  int j;
  int k;
  int[][] A = new int[3][4];
  int[][] B = new int[4][2];
  int[][] C = new int[3][2];
  for (i = 0; i < 3; ++i)
    for (j = 0; j < 4; ++j)
      A[i][j] = i + j;
  for (i = 0; i < 4; ++i)
    for (j = 0; j < 2; ++j)
      B[i][j] = i * j;
  for (i = 0; i < 3; ++i)
    for (j = 0; j < 2; ++j)
      C[i][j] = 0;
  for (i = 0; i < 3; ++i)
    for (j = 0; j < 2; ++j)
      for (k = 0; k < 4; ++k)
        C[i][j] = C[i][j] + A[i][k] * B[k][j];
  int ans = 0;
  for (i = 0; i < 3; ++i)
    for (j = 0; j < 2; ++j)
      ans = ans + C[i][j];
  print(toString(ans));
  return 0;
}

int main ()
{
  int a=7,b=2;
  return 0;
}
 */
