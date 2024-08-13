package Scope;

import java.util.TreeMap;

import Ir.Item.Item;
import Ir.Utility.IRLable;
import Utility.error.ErrorBasic;
import Utility.label.*;

@lombok.Getter
@lombok.Setter
public class Scope {
  protected Scope parent;
  TreeMap<String, FuncLable> funcMap;
  TreeMap<String, VarLable> varMap;
  TreeMap<String, TypeLable> classMap;

  //for the IR builder
  //store the register of the variable
  //once created, it can not be changed
  TreeMap<String, Item> regMap = new TreeMap<String, Item>();
  //only the global scope has the following map
  TreeMap<String, IRLable> funcLableMap = new TreeMap<String, IRLable>();
  boolean isLoop = false;
  boolean isFunc = false;
  boolean isClass = false;
  String name=null;
  //only func and class has name
  public Scope()
  {
    this.parent = null;
    funcMap = new TreeMap<String,FuncLable>() {};
    varMap = new TreeMap<String,VarLable>() {};
    classMap = new TreeMap<String,TypeLable>() {};
  }
  public Scope(boolean is_global)
  {
    this.parent = null;
    funcMap = new TreeMap<String,FuncLable>() {};
    varMap = new TreeMap<String,VarLable>() {};
    classMap = new TreeMap<String,TypeLable>() {};
    if(!is_global)
      return;
    for(FuncLable func : BasicClassFunc.BuildInFunc)
      funcMap.put(func.getName(), func);
    for(TypeLable cls : BasicClassFunc.BuildInClass)
      classMap.put(cls.getName(), cls);

    for(IRLable lable : BasicClassFunc.BuildInFuncLable)
      funcLableMap.put(lable.getName(), lable);

    //add some default functions
  }
  public Scope(Scope parent) //this is definetely not a global scope
  {
    this.parent = parent;
    funcMap = new TreeMap<String,FuncLable>() {};
    varMap = new TreeMap<String,VarLable>() {};
    classMap = new TreeMap<String,TypeLable>() {};
  }
  public enum QueryType
  {
    FUNC,
    VAR,
    CLASS,
    ANY
  }
  public Lable get(String name)
  {
    boolean recursive = false;
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
  public Lable get(String name,boolean recursive)
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
  public Lable get(String name, QueryType qType)
  {
    boolean recursive = false;
    if(funcMap.containsKey(name) && (QueryType.FUNC == qType || QueryType.ANY == qType))
      return funcMap.get(name);
    if(varMap.containsKey(name) && (QueryType.VAR == qType || QueryType.ANY == qType))
      return varMap.get(name);
    if(classMap.containsKey(name) && (QueryType.CLASS == qType || QueryType.ANY == qType))
      return classMap.get(name);
//    if(recursive && parent!=null)
//      return parent.get(name,recursive);
    return null;
  }
  public Lable get(String name, QueryType qType,boolean recursive)
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
  public Item getReg(String name,boolean recursive)
  {
    if(regMap.containsKey(name))
      return regMap.get(name);
    if(recursive && parent!=null)
      return parent.getReg(name,recursive);
    return null;
  }
  public void declareReg(String name, Item reg)
  {
    if(regMap.containsKey(name))
      throw new ErrorBasic("Variable "+name+" has been declared, can not declare reg");
    regMap.put(name, reg);
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
  public void declareClass(TypeLable cls)
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
