package Scope;

import java.util.Map;
import Utility.label.*;

@lombok.Getter
@lombok.Setter
public class Scope {
  private final Scope parent;
  Map<String, FuncLable> funcMap;
  Map<String, VarLable> varMap;
  Map<String, ClassLable> classMap;
  public Scope(boolean is_global=false)
  {
    this.parent = null;
    funcMap = new Map<String,FuncLable>() {};
    varMap = new Map<String,VarLable>() {};
    classMap = new Map<String,ClassLable>() {};
    if(!is_global)
      return;
    //add some default functions
  }
  public Scope(Scope parent) //this is definetely not a global scope
  {
    this.parent = parent;
    funcMap = new Map<String,FuncLable>() {};
    varMap = new Map<String,VarLable>() {};
    classMap = new Map<String,ClassLable>() {};
  }
}
