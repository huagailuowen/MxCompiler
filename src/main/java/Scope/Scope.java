package Scope;

import java.awt.*;
import java.util.TreeMap;

import Ir.Item.Item;
import Ir.Utility.IRLable;
import Utility.error.ErrorBasic;
import Utility.label.*;
import org.antlr.v4.runtime.misc.Pair;

@lombok.Getter
@lombok.Setter
public class Scope {
  protected Scope parent;
  TreeMap<String, FuncLable> funcMap;
  TreeMap<String, VarLable> varMap;
  TreeMap<String, TypeLable> classMap;

  //for the IR builder
  //store the register of the variable and function lable
  //once created, it can not be changed
  TreeMap<String, String> irLableMap = new TreeMap<String, String>();
  String irThisName = null;
  //the rules
  /*
  1. globalFunc and globalVar: the origin name
  2. classFunc : class_name.func_name
  3. classVar : class_name.var_name, however it actually not exist, it is a class member, with "this+offset"
  4. classConstructor: 'class'.constructor
  4. localVar: var_name.index
  so apparently, an element is global if only it not contain a dot
   */



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
      irLableMap.put(lable.getName(), lable.getName());

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
  public Pair<Lable,String> getDetail(String name,boolean recursive)
  {
    if(funcMap.containsKey(name)){
      return new Pair<>(funcMap.get(name),irLableMap.get(name));
    }

    if(varMap.containsKey(name)) {
      return new Pair<>(varMap.get(name),irLableMap.get(name));
    }
    if(classMap.containsKey(name)) {
      return new Pair<>(classMap.get(name),irLableMap.get(name));
    }
    if(recursive && parent!=null)
      return parent.getDetail(name,recursive);
    return new Pair<>(null,null);
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
  public String getIrLable(String name,boolean recursive)
  {
    if(irLableMap.containsKey(name))
      return irLableMap.get(name);
    if(recursive && parent!=null)
      return parent.getIrLable(name,recursive);
    return null;
  }
  public void declareIrLable(String name, String irName)
  {
    if(irLableMap.containsKey(name))
      throw new ErrorBasic("Variable "+name+" has been declared, can not declare reg");
    irLableMap.put(name, irName);
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
