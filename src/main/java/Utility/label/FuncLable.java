package Utility.label;

import java.util.ArrayList;

import AST.Node.def.ASTVarDef;
import AST.Node.typ.ASTType;
import Utility.label.TypeLable;

@lombok.Getter
@lombok.Setter
public class FuncLable extends Lable {
  protected TypeLable returnType;
  protected ArrayList<TypeLable> paramTypes;
  public FuncLable(String name, TypeLable returnType, ArrayList<TypeLable> paramTypes) {
    super(name);
    this.returnType = returnType;
    this.paramTypes = paramTypes;
  }
  public FuncLable(String name, TypeLable returnType, TypeLable... paramTypes) {
    super(name);
    this.returnType = returnType;
    this.paramTypes = new ArrayList<TypeLable>();
    for(TypeLable t : paramTypes) {
      this.paramTypes.add(t);
    }
  }
  public FuncLable(String name, ASTType type,ArrayList<ASTVarDef> paramDefs) {
    super(name);
    if(type == null)
      this.returnType = new TypeLable("void");
    else
      this.returnType = ((TypeLable)type.getLabel());
    this.paramTypes = new ArrayList<TypeLable>();
    for(ASTVarDef v : paramDefs) {
      this.paramTypes.add(((VarLable)v.getLabel()).getType());
    }
  }
    @Override
        public String toString() {
            StringBuilder ret = new StringBuilder();
            ret.append(returnType.toString()).append(" ");
            ret.append(super.toString());
            return ret.toString();
        }

  
}
