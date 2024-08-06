package Utility.label;

import java.util.ArrayList;

import AST.Node.def.VarDef;
import AST.Node.typ.ASTType;
import Utility.label.TypeLable;

@lombok.Getter
@lombok.Setter
public class FuncLable extends Label {
  private final TypeLable returnType;
  private final ArrayList<TypeLable> paramTypes;
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
  public FuncLable(String name, ASTType type,ArrayList<VarDef> paramDefs) {
    super(name);
    this.returnType = ((TypeLable)type.getLabel());
    this.paramTypes = new ArrayList<TypeLa ble>();
    for(VarDef v : paramDefs) {
      this.paramTypes.add(((VarLable)v.getLabel()).getType());
    }
  }

  
}
