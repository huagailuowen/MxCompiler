package Ir.Node.stmt;

import Ir.Node.ins.IRIns;

@lombok.Getter
@lombok.Setter
public class IRBlockStmt extends IRStmt {
  protected String label;
  protected IRIns defalutexit;

}
