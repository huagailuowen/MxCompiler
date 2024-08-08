package Scope;

import java.util.Map;

import Utility.label.ClassLable;
import Utility.label.FuncLable;
import Utility.label.Label;
import Utility.label.VarLable;
@lombok.Getter
@lombok.Setter
public class Scope {
  private final Scope parent;
  Map<String, FuncLable> funcMap;
  Map<String, VarLable> varMap;
  Map<String, ClassLable> classMap;
  boolean isLoop = false;
  boolean isFunc = false;
  boolean isClass = false;
  String name=null;
  //only func and class has name
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
  enum QueryType
  {
    FUNC,
    VAR,
    CLASS,
    ANY
  }
  public Label get(String name,boolean recursive=false)
  {
    if(funcMap.containsKey(name))
      return funcMap.get(name);
    if(varMap.containsKey(name))
      return varMap.get(name);
    if(classMap.containsKey(name))
      return classMap.get(name);
    if(recursive && parent!=null)
      return parent.get(name,recursive);
    return null;
  }
  public Label get(String name, QueryType qType,boolean recursive=false)
  {
    if(funcMap.containsKey(name) && (QueryType.FUNC == qType || QueryType.ANY == qType))
      return funcMap.get(name);
    if(varMap.containsKey(name) && (QueryType.VAR == qType || QueryType.ANY == qType))
      return varMap.get(name);
    if(classMap.containsKey(name) && (QueryType.CLASS == qType || QueryType.ANY == qType))
      return classMap.get(name);
    if(recursive && parent!=null)
      return parent.get(name,recursive);
    return null;
  }
  public void declareFunc(FuncLable func)
  {
    if(funcMap.containsKey(func.getName()))
      throw new Error("Function "+func.getName()+" has been declared");
    funcMap.put(func.getName(), func);
  }
  public void declareFunc(String name, FuncLable func)
  {
    if(funcMap.containsKey(name))
      throw new Error("Function "+name+" has been declared");
    funcMap.put(name, func);
  }
  public void declareVar(VarLable var)
  {
    if(varMap.containsKey(var.getName()))
      throw new Error("Variable "+var.getName()+" has been declared");
    varMap.put(var.getName(), var);
  }
  public void declareVar(String name, VarLable var)
  {
    if(varMap.containsKey(name))
      throw new Error("Variable "+name+" has been declared");
    varMap.put(name, var);
  }
  public void declareClass(ClassLable cls)
  {
    if(classMap.containsKey(cls.getName()))
      throw new Error("Class "+cls.getName()+" has been declared");
    classMap.put(cls.getName(), cls);
  }
  public Scope findLoop(Scope curScope)
  {
    if(curScope==null)
      return null;
    if(curScope.isLoop)
      return curScope;
    return findLoop(curScope.parent);
  }
  public Scope findFunc(Scope curScope)
  {
    if(curScope==null)
      return null;
    if(curScope.isFunc)
      return curScope;
    return findFunc(curScope.parent);
  }
  public Scope findClass(Scope curScope)
  {
    if(curScope==null)
      return null;
    if(curScope.isClass)
      return curScope;
    return findClass(curScope.parent);
  }
  
}
