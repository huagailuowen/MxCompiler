package Utility.error;

import Utility.position.Position;

public class error {
  private String message;
  private Position position;

  public error(String message) {
    this.message = message;
    this.position = null;
  }

  public error(String message, Position position) {
    this.message = message;
    this.position = position;
  }
  
  public error(String mString, int row, int column) {
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
