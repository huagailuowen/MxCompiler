package Ir.Node.def;

import Ir.IRVisitor;
import Ir.Type.IRBaseType;
import Utility.error.ErrorBasic;

import java.util.ArrayList;

@lombok.experimental.SuperBuilder
@lombok.Setter
@lombok.Getter
public class IRClassDef extends IRDef{
    protected ArrayList<IRBaseType> memberList;
    protected ArrayList<IRFuncDef> funcList;
    //also contain the constructor
    //get the this pointer, and return void
    public IRClassDef(ArrayList<IRBaseType> memberList, ArrayList<IRFuncDef> funcList) {
      this.memberList = memberList;
      this.funcList = funcList;
    }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
}
