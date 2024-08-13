package Ir.Node.stmt;

@lombok.Getter
@lombok.Setter
public class IRIfStmt extends IRStmt {
  protected String condLable, trueLable, falseLable,endLable;

}
