package Utility.error;

import Utility.position.Position;

public class ErrorBasic extends RuntimeException{
  private String message;
  private Position position;

  public ErrorBasic(String message) {
    this.message = message;
    this.position = null;
  }

  public ErrorBasic(String message, Position position) {
    this.message = message;
    this.position = position;
  }
  
  public ErrorBasic(String mString, int row, int column) {
    this.message = mString;
    this.position = new Position(row, column);
  }
  public String toString() {
    return "Error: " + this.message + " at " + (this.position == null ? "unknown position" : this.position.toString());
  }

  public String what() {
    return this.message;
  }
  
}
