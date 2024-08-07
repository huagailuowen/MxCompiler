package Semantic;

import AST.ASTVisitor;
import AST.Node.ASTRoot;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import Scope.Scope;

public class Checker implements ASTVisitor<Compileinfo>{
  private final Scope globalScope;
  private final Scope currentScope;
  private void init(Scope rootScope)
  {
    globalScope = rootScope;
    currentScope = globalScope;
  }
  private void stepIn(Scope tarScope,boolean isNew)
  {
    //if tarScope is null, create a new scope
    if(isNew)
      currentScope = new Scope(currentScope);
    else
      currentScope = tarScope;
  }
  private void stepOut()
  {
    currentScope = currentScope.getParent();
  }
  public Compileinfo visit(ASTRoot node) {
    //we assume that the collector has built the global scope
    init(node.getScope());
    var info = new Compileinfo();
    for (ASTDef def : node.getDefList()) {
      if(def instanceof ASTClassDef ){
        //we assume the collector has checked the class
        info.append(def.accept(this));
      }else if(def instanceof ASTFuncDef){
        //we assume the collector has checked the function
        info.append(def.accept(this));
      }else if(def instanceof ASTVarDef){
        //in here we check the variable, for it not allow former declaration
        currentScope.declareVar(((ASTVarDef)def).getLabel());
      }
      assert(currentScope==globalScope);
    }
    assert(currentScope==globalScope);
    return info;
  }
  public Compileinfo visit(ASTClassDef node) {
    //we assume that the collector has built the global scope
    stepIn(node.getScope(),false);
    var info = new Compileinfo();
    info.append(node.getConstructor().accept(this));
    stepOut();
    return info;
  }
}
