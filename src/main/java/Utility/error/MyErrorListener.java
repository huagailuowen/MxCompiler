package Utility.error;

import Utility.position.Position;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class MyErrorListener extends BaseErrorListener {
  @Override
  public void syntaxError(Recognizer<?, ?> recognizer,
                          Object offendingSymbol,
                          int line, int charPositionInLine,
                          String msg,
                          RecognitionException e) {
    System.out.println("Invalid Identifier");
    throw new ErrorBasic("");
  }
}
