package Ir.Node.stmt;
@lombok.Getter
@lombok.Setter
public class IRLoopStmt extends IRStmt {
  protected String condLable, bodyLable, endLable,updateLable;

}
