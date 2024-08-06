package Scope;

import java.util.Map;
import Utility.label.*;
import Scope.*;

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
    for(FuncLable func : BasicFunc.BuildInFunc)
      funcMap.put(func.getName(), func);
    for(ClassLable cls : BasicClass.BuildInClass)
      classMap.put(cls.getName(), cls);
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
