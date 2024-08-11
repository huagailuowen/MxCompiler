package Semantic;

import AST.ASTVisitor;
import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import AST.Node.expr.ASTArrayExpr;
import AST.Node.expr.ASTAssignExpr;
import AST.Node.expr.ASTAtomExpr;
import AST.Node.expr.ASTBinaryExpr;
import AST.Node.expr.ASTCallExpr;
import AST.Node.expr.ASTChildExpr;
import AST.Node.expr.ASTExpr;
import AST.Node.expr.ASTFStrExpr;
import AST.Node.expr.ASTMemExpr;
import AST.Node.expr.ASTNewExpr;
import AST.Node.expr.ASTPreExpr;
import AST.Node.expr.ASTSuffixExpr;
import AST.Node.expr.ASTTernaryExpr;
import AST.Node.stmt.*;
import AST.Node.typ.ASTType;
import Scope.Scope;
import Utility.error.ErrorBasic;
import Utility.label.*;

// a type null, if ABANDON, then there is error, if RVALUE, then it is a constant, if LVALUE
public class Checker implements ASTVisitor<Compileinfo>{
  private Scope globalScope = null;
  private Scope currentScope = null;
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
    else{
      if(tarScope==null)
        throw new ErrorBasic("Checker: stepIn: tarScope is null");
      if(tarScope.getParent()!=currentScope)
        throw new ErrorBasic("Checker: stepIn: tarScope is not the child of currentScope");
      currentScope = tarScope;

    }
  }
  private void stepOut()
  {
    currentScope = currentScope.getParent();
  }
  public Compileinfo visit(ASTRoot node) {
    //we assume that the collector has built the global scope
    init(node.getScope());
    var info = new Compileinfo();

    for(ASTDef def:node.getDefList()){
      if(!(def instanceof ASTClassDef)){
        continue;
      }
      if(def.getScope() == null){
        throw new ErrorBasic("Checker: visit ASTRoot: scope is null, is class defined not in global Scope?");
      }
      stepIn(def.getScope(),false);
      for(ASTVarDef var:((ASTClassDef)def).getVarDefs()){
        var name = new String(((ASTClassDef)def).getLabel().getName()+'.'+((VarLable)var.getLable()).getName());
        info.append(var.accept(this));
        if(globalScope.get(name)!=null){
          info.append(new Compileinfo("variable "+name+" has been defined",var.getPosition()));
        }else{
          globalScope.declareVar(name, (VarLable)var.getLable());
        }
        
      }
      stepOut();
    }

    for (ASTDef def : node.getDefList()) {
      if(def instanceof ASTClassDef ){
        //we assume the collector has checked the class
        info.append(def.accept(this));
      }else if(def instanceof ASTFuncDef){
        //we assume the collector has checked the function
        info.append(def.accept(this));
      }else if(def instanceof ASTVarDef){
        //in here we check the variable, for it not allow former declaration
        info.append(def.accept(this));
      }
      assert(currentScope==globalScope);
    }
    assert(currentScope==globalScope);
    return info;
  }
  public Compileinfo visit(ASTClassDef node) {
    //we assume that the collector has built the global scope
    if(node.getScope() == null){
      throw new ErrorBasic("Checker: visit ASTClassDef: scope is null, is class defined not in global Scope?");
    }
    stepIn(node.getScope(),false);
    var info = new Compileinfo();
    // for (ASTVarDef v : node.getVarList()) {
    //   info.append(v.accept(this));
    // }
    //we will not do this in here, but in the visitprogram

    info.append(node.getConstructor().accept(this));
    for(ASTFuncDef func:node.getFuncDefs()){
      info.append(func.accept(this));
    }
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTFuncDef node) {
    //we assume that the collector has built the parameter scope
    //we do not stepIn, for the function scope is not the child of the parameter scope
    if(node.getScope() == null){
      throw new ErrorBasic("Checker: visit ASTFuncDef: scope is null, is function defined not in global Scope?");
    }
    stepIn(node.getScope(),false);
    var info = new Compileinfo();
    if(globalScope.get(node.getLabel().getReturnType().getName(),Scope.QueryType.CLASS)==null){
      info.append(new Compileinfo("return type undefined",node.getPosition()));
    }else if(node.getLabel().getReturnType().getName().equals("this") || node.getLabel().getReturnType().getName().equals("null")){
      throw new ErrorBasic("this is impossible",node.getPosition());
      // info.append(new Compileinfo("void function should not have return value",node.getPosition()));
    }
    for(ASTVarDef var:node.getParaList()){
      if(globalScope.get(var.getLabel().getType().getName(),Scope.QueryType.CLASS)==null){
        info.append(new Compileinfo("parameter type undefined",var.getPosition()));
      }
    }
    for(ASTStmt stmt:node.getStmtList()){
      stmt.setScope(node.getScope());
      info.append(stmt.accept(this));
    }
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTVarDef node) {
    //we assume that the collector has built the global scope
    node.setScope(currentScope);
    var info = new Compileinfo();
    String name = ((VarLable)node.getLabel()).getName();
    String type = ((VarLable)node.getLabel()).getType().getName();
    if(currentScope.get(name,Scope.QueryType.FUNC)!=null){
      info.append(new Compileinfo("variable "+name+" has been defined as a function",node.getPosition()));
    }else if(currentScope.get(name,Scope.QueryType.VAR)!=null){
      info.append(new Compileinfo("variable "+name+" has been defined as a variable",node.getPosition()));
    }else if(currentScope.get(type,Scope.QueryType.CLASS,true)==null || type.equals("null") || type.equals("this")){
      info.append(new Compileinfo("type undefined",node.getPosition()));
    }else{
      currentScope.declareVar(node.getLabel());
      if(node.getInit()!=null){
        info.append(node.getInit().accept(this));
      }
    }
    return info;
  }
  public Compileinfo visit(ASTDef node) {
    throw new ErrorBasic("visit ASTDef, access denied",node.getPosition());
  }
  public Compileinfo visit(ASTStmt node) {
    throw new ErrorBasic("visit ASTStmt, access denied",node.getPosition());
  }
  public Compileinfo visit(ASTBlockStmt node) {
    boolean isNew = node.getScope()==null;
    if(isNew);{
      stepIn(node.getScope(),true);
      node.setScope(currentScope);
      //the first time to visit the block, create a new scope
    }
    var info = new Compileinfo();
    for(ASTStmt stmt:node.getStmts()){
      info.append(stmt.accept(this));
    }
    if(isNew)
      stepOut();
    return info;
  }
  public Compileinfo visit(ASTBreakStmt node) {
    node.setScope(currentScope);
    Scope Loop = currentScope.findLoop(currentScope);
    if(Loop==null){
      return new Compileinfo("break statement not in loop",node.getPosition());
    }else{
      return new Compileinfo();
    }
  }
  public Compileinfo visit(ASTContStmt node) {
    node.setScope(currentScope);
    Scope Loop = currentScope.findLoop(currentScope);
    if(Loop==null){
      return new Compileinfo("continue statement not in loop",node.getPosition());
    }else{
      return new Compileinfo();
    }
  }
  private Compileinfo checkFinalExpr(ASTExpr expr){
    var info = new Compileinfo();
//    info.append(expr.accept(this));
    //stupid, we have already visited the expr
    if(expr.getLabel().getName()!=null && expr.getLabel().getValueType() == ExprLable.ValueType.ABANDON){
      info.append(new Compileinfo("expression should not be a single indentifier of function"));
    }else if(expr.getLabel().getType().getName().equals("null") && expr.getLabel().getValueType() == ExprLable.ValueType.ABANDON){
      ;
      //there must be some error in the expr
    }
    return info;
  }
  public Compileinfo visit(ASTEmptyStmt node) {
    node.setScope(currentScope);
    return new Compileinfo();
  }
  public Compileinfo visit(ASTExprStmt node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    for(var expr:node.getExprList()){
      info.append(expr.accept(this));
      info.append(checkFinalExpr(expr));
    }
    return info;
  }
  public Compileinfo visit(ASTForStmt node) {
    stepIn(currentScope, true);
    node.setScope(currentScope);
    currentScope.setLoop(true);
    var info = new Compileinfo();
    if(node.getInit()!=null){
      info.append(node.getInit().accept(this));
      if(!(node.getInit() instanceof ASTVarDefStmt) && !(node.getInit() instanceof ASTExprStmt)){
        if(info.empty())
          info.append(new Compileinfo("init should be a variable definition or expr",node.getPosition()));
      }
    }
    if(node.getCond()!=null){
      info.append(node.getCond().accept(this));
      if(!node.getCond().getLabel().getType().getName().equals("bool")){
        info.append(new Compileinfo("condition should be bool",node.getPosition()));
      } else if (node.getCond().getLabel().getValueType() == ExprLable.ValueType.ABANDON){
        info.append(new Compileinfo("condition should not be a single identifier of function",node.getPosition()));
      }
    }
    if(node.getUpdate()!=null){
      info.append(node.getUpdate().accept(this));
      info.append(checkFinalExpr(node.getUpdate()));
    }
    node.getContent().setScope(currentScope);
    info.append(node.getContent().accept(this));
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTIfStmt node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getCond().accept(this));
    if(!node.getCond().getLabel().getType().getName().equals("bool") || node.getCond().getLabel().getValueType() == ExprLable.ValueType.ABANDON){
      info.append(new Compileinfo("condition should be bool",node.getPosition()));
    }
    info.append(node.getThenStmt().accept(this));
    if(node.getElseStmt()!=null){
      info.append(node.getElseStmt().accept(this));
    }
    
    return info;
  }
  public Compileinfo visit(ASTRetStmt node) {
    node.setScope(currentScope);
    Scope Func = currentScope.findFunc(currentScope);
    Scope Class = currentScope.findClass(currentScope);
    var info = new Compileinfo();
    if(Func==null){
      return new Compileinfo("return statement not in Func",node.getPosition());
    }else{
      String typename = Func.getName();
      if(Class != null){
        typename = Class.getName() + '.' + typename;
      }
      info.append(node.getRetExpr().accept(this));
      FuncLable lable = (FuncLable)globalScope.get(typename,Scope.QueryType.FUNC);
      if(lable == null){
        throw new ErrorBasic("Checker: visit ASTRetStmt: function not found",node.getPosition());
      }else {
        if(node.getRetExpr().getLabel().getType().getName().equals("null")){
          if(node.getRetExpr().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
            return info;
            //there must be some error in the expr
          }else if(lable.getReturnType().getName().equals("void")
            || lable.getReturnType().getName().equals("int")
            || lable.getReturnType().getName().equals("bool")){
            info.append(new Compileinfo("return type not match",node.getPosition()));
          }else if(node.getRetExpr().getLabel().getType().getDimension() != lable.getReturnType().getDimension()){
            info.append(new Compileinfo("return type dimension not match",node.getPosition()));
          }

        }else if(node.getRetExpr().getLabel().getType().getName().equals(lable.getReturnType().getName())
          && node.getRetExpr().getLabel().getType().getDimension() == lable.getReturnType().getDimension()){
          return info;
        }else{
          return new Compileinfo("return type not match",node.getPosition());
        }
      }

    }
    return info;
  }
  public Compileinfo visit(ASTVarDefStmt node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    for(ASTVarDef var:node.getVarList()){
      info.append(var.accept(this));
    }
    return info;
  }
  public Compileinfo visit(ASTWhileStmt node) {
    stepIn(currentScope, true);
    node.setScope(currentScope);
    currentScope.setLoop(true);
    var info = new Compileinfo();
  
    info.append(node.getCondition().accept(this));
    if(!node.getCondition().getLabel().getType().getName().equals("bool")){
      info.append(new Compileinfo("condition should be bool",node.getPosition()));
    }else if(node.getCondition().getLabel().getValueType() == ExprLable.ValueType.ABANDON){
      info.append(new Compileinfo("condition should not be a single identifier of function",node.getPosition()));
    }
    node.getContent().setScope(currentScope);
    if(info.empty())
      info.append(node.getContent().accept(this));
    stepOut();
    return info;
  }
  public Compileinfo visit(ASTExpr node) {
    throw new ErrorBasic("visit ASTExpr, access denied",node.getPosition());
  }

  public Compileinfo visit(ASTArrayExpr node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    node.getLabel().setType(node.getExpr().getLabel().getType().clone());
    node.getLabel().setValueType(node.getExpr().getLabel().getValueType());
    if(node.getLabel().getValueType() != ExprLable.ValueType.LVALUE){
      info.append(new Compileinfo("array should be lvalue",node.getPosition()));
    }else{
      node.getLabel().setValueType(ExprLable.ValueType.LVALUE);
    }
    int dim = node.getExpr().getLabel().getType().getDimension();
    if(dim < node.getArray().size()){
      info.append(new Compileinfo("dimension not match",node.getPosition()));
      // return info;
    }
    for(var expr:node.getArray()){
      info.append(expr.accept(this));
      if(!expr.getLabel().getType().getName().equals("int")){
        info.append(new Compileinfo("array index should be int",node.getPosition()));
      }else if(expr.getLabel().getValueType()== ExprLable.ValueType.ABANDON){
        info.append(new Compileinfo("array index should be lvalue or rvalue",node.getPosition()));
      }
    }
    node.getLabel().getType().setDimension(dim-node.getArray().size());
    return info;
  }

  public Compileinfo visit(ASTAssignExpr node) {
    node.setScope(currentScope);
    
    var info = new Compileinfo();
    if(!node.getOp().equals("=")){
      throw new ErrorBasic("Checker: visit ASTAssignExpr: op is not =",node.getPosition());
    }
    info.append(node.getLhs().accept(this));
    info.append(node.getRhs().accept(this));
    node.getLabel().setType(new TypeLable("void",node.getLhs().getLabel().getType().getDimension()));
    node.getLabel().setValueType(ExprLable.ValueType.ABANDON);
    //ban the value of the assign expr
    if(!info.empty()){
      return info;
    }
    if(node.getLhs().getLabel().getValueType()!= ExprLable.ValueType.LVALUE){
      info.append(new Compileinfo("lhs is not lvalue",node.getPosition()));
      return info;
    }if(node.getRhs().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
      info.append(new Compileinfo("rhs is abandon",node.getPosition()));
      return info;
    }


    if(node.getLhs().getLabel().getType().getName() == null || node.getRhs().getLabel().getType().getName() == null){
      throw new ErrorBasic("type has no name? impossible",node.getPosition());
      // info.append(new Compileinfo("type null",node.getPosition()));
    } 

    if(node.getRhs().getLabel().getType().getName().equals("null")) {
      if(node.getLhs().getLabel().getType().getDimension()!=0) {
        if (node.getLhs().getLabel().getType().getDimension() < node.getRhs().getLabel().getType().getDimension()) {
          info.append(new Compileinfo("dimension not match", node.getPosition()));
          return info;
        }

      }else if (node.getLhs().getLabel().getType().getName().equals("int")
              || node.getLhs().getLabel().getType().getName().equals("bool")
              || node.getLhs().getLabel().getType().getName().equals("void")) {
        info.append(new Compileinfo("type not match", node.getPosition()));
        return info;
      }
    }
    if(node.getLhs().getLabel().getType().getDimension() != node.getRhs().getLabel().getType().getDimension()){
      info.append(new Compileinfo("dimension not match",node.getPosition()));
      return info;
    }
    if(!node.getLhs().getLabel().getType().getName().equals(node.getRhs().getLabel().getType().getName())){
      info.append(new Compileinfo("type not match",node.getPosition()));
      return info;
    }


    return info;
  }
  
  public Compileinfo visit(ASTAtomExpr node) {
    node.setScope(currentScope);
    var info = new Compileinfo();
    //atomexpr has no lable until now
    if(node.getType() == ASTAtomExpr.AtomType.IDENTIFIER){
      Lable var = currentScope.get(node.getValue(),true);
      if(var==null){
        info.append(new Compileinfo("Identifier "+node.getValue()+" not defined",node.getPosition()));
        node.setLabel(new ExprLable(null, ((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      }else if(var instanceof VarLable){
        node.setLabel(new ExprLable(null, ((VarLable)var).getType().clone(),ExprLable.ValueType.LVALUE));
      }else if(var instanceof FuncLable){
        node.setLabel(new ExprLable(node.getValue(), ((FuncLable)var).getReturnType().clone(),ExprLable.ValueType.ABANDON));
        //the single func can not be a expression
      }else{
        //class must be recognize as type in .g4
        info.append(new Compileinfo("Identifier "+node.getValue()+" is a class name",node.getPosition()));
      }
    }else if(node.getType() == ASTAtomExpr.AtomType.ARRAY){
      node.getLabel().setType(((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone());
      node.getLabel().setValueType(ExprLable.ValueType.ABANDON);
      //we must new a type for the array
      //or we can not set the dimension
      node.getLabel().setValueType(ExprLable.ValueType.ABANDON);
      int dim = 0;
      for(var expr:node.getArray()){
        info.append(expr.accept(this));
        if(!expr.getLabel().getValueType().equals(ExprLable.ValueType.RVALUE)){
          info.append(new Compileinfo("element should be constant",node.getPosition()));
          continue;
        }
        if(expr.getLabel().getType().getName().equals("null")){
          if(node.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
            dim = Math.max(dim,expr.getLabel().getType().getDimension());
          }else{
            if(dim < expr.getLabel().getType().getDimension()){
              info.append(new Compileinfo("dimension not match",node.getPosition()));
            }
          }
          continue;
        }
        if(node.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
          node.getLabel().setType(expr.getLabel().getType().clone());
          node.getLabel().setValueType(ExprLable.ValueType.RVALUE);
        }else{
          if(node.getLabel().getType().getDimension()!=expr.getLabel().getType().getDimension()){
            info.append(new Compileinfo("dimension not match",node.getPosition()));
          }else{
            
            if(!node.getLabel().getType().getName().equals(expr.getLabel().getType().getName())){
              info.append(new Compileinfo("type not match",node.getPosition()));
            }
            
          }
        }
      }
      if(node.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
        node.getLabel().setValueType(ExprLable.ValueType.RVALUE);
      }
      node.getLabel().getType().setDimension(dim+1);

    }else if(node.getType() == ASTAtomExpr.AtomType.BOOL){
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("bool",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.INT){
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("int",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.NULL){
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.STRING){
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("string",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
    }else if(node.getType() == ASTAtomExpr.AtomType.THIS){
      Scope Class = currentScope.findClass(currentScope);
      if(Class==null){
        info.append(new Compileinfo("this not in class",node.getPosition()));
        node.setLabel(new ExprLable(null, ((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(), ExprLable.ValueType.ABANDON));
      }else{
        node.setLabel(new ExprLable(Class.getName(),((TypeLable) globalScope.get("this",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));

      }
    }else if(node.getType() == ASTAtomExpr.AtomType.VOID){
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("void",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
    }else{
      throw new ErrorBasic("Checker: visit ASTAtomExpr: type not match",node.getPosition());
    }
    return info;
  }

  public Compileinfo visit(ASTBinaryExpr node)
  {
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getLhs().accept(this));
    info.append(node.getRhs().accept(this));
    if(node.getLhs().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)
      ||node.getRhs().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
      info.append(new Compileinfo("can not compute the ABANDON",node.getPosition()));
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      return info;
    }
    if(!node.getLhs().getLabel().getType().getIsBaseType()
      || !node.getRhs().getLabel().getType().getIsBaseType()){
      info.append(new Compileinfo("can not compute the non-base type",node.getPosition()));
    }
    if(node.getOp().equals("+")
    ||node.getOp().equals("-")
    ||node.getOp().equals("*")
    ||node.getOp().equals("/")
    ||node.getOp().equals("%")
    ||node.getOp().equals("<<")
    ||node.getOp().equals(">>")
    ||node.getOp().equals("|")
    ||node.getOp().equals("&")
    ||node.getOp().equals("^")){
      if(node.getLhs().getLabel().getType().getName().equals("int")
        && node.getRhs().getLabel().getType().getName().equals("int")){
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("int",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else if(node.getOp().equals("&&")
    ||node.getOp().equals("||")){
      if(node.getLhs().getLabel().getType().getName().equals("bool")
        && node.getRhs().getLabel().getType().getName().equals("bool")){
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("bool",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else if(node.getOp().equals("==")
      ||node.getOp().equals("<=")
      ||node.getOp().equals(">=")
      ||node.getOp().equals("<")
      ||node.getOp().equals(">")
      ||node.getOp().equals("!=")){
      if(!node.getOp().equals("==") && !node.getOp().equals("!=")){
        if(node.getLhs().getLabel().getType().getName().equals("int")
          && node.getRhs().getLabel().getType().getName().equals("int")){
          node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("bool",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
        }else{
          node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
          info.append(new Compileinfo("type not match",node.getPosition()));
        }
      }else{
        if(node.getLhs().getLabel().getType().getName().equals(node.getRhs().getLabel().getType().getName())
          && node.getLhs().getLabel().getType().getDimension() == 0
          && node.getRhs().getLabel().getType().getDimension() == 0){
          node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("bool",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
        }else{
          node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
          info.append(new Compileinfo("type not match",node.getPosition()));
        }
      }
    }else{
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      info.append(new Compileinfo("type not match",node.getPosition()));
    }
    return info;
  }

  public Compileinfo visit(ASTCallExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    ASTExpr expr = node.getExpr();
    info.append(expr.accept(this));
    if(expr.getLabel().getValueType()!=ExprLable.ValueType.ABANDON){
      info.append(new Compileinfo("call to a non-function",node.getPosition()));
      return info;
    }
    var name = expr.getLabel().getName();
    node.setLabel(new ExprLable(null, ((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
    if(name==null){
      info.append(new Compileinfo("call to a non-function",node.getPosition()));
      return info;
    }else if(globalScope.get(name,Scope.QueryType.FUNC)==null){
      info.append(new Compileinfo("function "+name+" not defined",node.getPosition()));
      return info;
    }else{
      FuncLable func = (FuncLable)globalScope.get(name,Scope.QueryType.FUNC);
      if(func.getParamTypes().size()!=node.getArgs().size()){
        info.append(new Compileinfo("parameter number not match",node.getPosition()));
        return info;
      }
      for(int i=0;i<func.getParamTypes().size();i++){
        info.append(node.getArgs().get(i).accept(this));
        if(!node.getArgs().get(i).getLabel().getType().getName().equals(func.getParamTypes().get(i).getName())
          || node.getArgs().get(i).getLabel().getType().getDimension() != func.getParamTypes().get(i).getDimension()){
          info.append(new Compileinfo("parameter type not match",node.getPosition()));
        }
      }
      node.setLabel(new ExprLable(null,func.getReturnType().clone(),ExprLable.ValueType.RVALUE));
      if(func.getReturnType().getName().equals("void")){
        node.getLabel().setValueType(ExprLable.ValueType.ABANDON);
      }
      return info;
    }
    
  }
  public Compileinfo visit(ASTChildExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    node.getLabel().setType(node.getExpr().getLabel().getType().clone());
    return info;
  }

  public Compileinfo visit(ASTFStrExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    node.getLabel().setType(((TypeLable) globalScope.get("string",Scope.QueryType.CLASS)).clone());
    node.getLabel().setValueType(ExprLable.ValueType.RVALUE);
    for(var expr:node.getArgs()){
      info.append(expr.accept(this));
      if(!expr.getLabel().getType().getIsBaseType() || expr.getLabel().getType().getName().equals("void")){
        info.append(new Compileinfo("format string should not contain non-base type",node.getPosition()));
      }
      if(expr.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
        info.append(new Compileinfo("element should be constant",node.getPosition()));
      }
    }
    return info;
  }

  public Compileinfo visit(ASTMemExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    if(node.getExpr().getLabel().getName() != null){
      return new Compileinfo("member access to a func",node.getPosition());
    }
    String typename = node.getExpr().getLabel().getType().getName();
    if(typename == null){
      info.append(new Compileinfo("null has no member",node.getPosition()));
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      return info;
    }
    if(typename.equals("this")){
      var Class = currentScope.findClass(currentScope);
      if(Class==null){
        info.append(new Compileinfo("this not in class",node.getPosition()));
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        return info;
      }
      typename = Class.getName();
    }else if(node.getExpr().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
      info.append(new Compileinfo("member access to a non-class",node.getPosition()));
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      return info;
    }
    node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),node.getExpr().getLabel().getValueType()));
    
    Lable item = globalScope.get(typename+'.'+node.getMember(),Scope.QueryType.ANY);
    if(item==null){
      info.append(new Compileinfo("member "+node.getMember()+" not defined",node.getPosition()));
      return info;
    }else if(item instanceof VarLable){
      node.setLabel(new ExprLable(null,(((VarLable)item).getType()).clone(),node.getExpr().getLabel().getValueType()));
    }else if(item instanceof FuncLable){
      node.setLabel(new ExprLable(((FuncLable)item).getName(),(((FuncLable)item).getReturnType()).clone(),ExprLable.ValueType.ABANDON));
    }else{
      info.append(new Compileinfo("member "+node.getMember()+" is a class name",node.getPosition()));
    }
    return info;
  }
  public Compileinfo visit(ASTNewExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    var typename = node.getType().getLabel().getName();
    for(var expr:node.getType().getDimList()){
      if(expr==null){
        continue;
      }
      info.append(expr.accept(this));
      if(!info.empty()){
        return info;
      }
      if(expr.getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
        info.append(new Compileinfo("dimension should be an ABANDON",node.getPosition()));
        return info;
      }else if(!expr.getLabel().getType().getName().equals("int")) {
        info.append(new Compileinfo("dimension should be an int", node.getPosition()));
        return info;
      }

    }
    var dim = node.getType().getLabel().getDimension();
    if(globalScope.get(typename,Scope.QueryType.CLASS)==null){
      info.append(new Compileinfo("type "+typename+" not defined",node.getPosition()));
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      return info;
    }
    // the new is LVALUE or RVALUE?
    node.setLabel(new ExprLable(null,((TypeLable) globalScope.get(typename,Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.LVALUE));
    if(node.getExpr()!=null){
      info.append(node.getExpr().accept(this));
      if(node.getExpr().getLabel().getType().getName()!=typename){
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
      if(!node.getExpr().getLabel().getValueType().equals(ExprLable.ValueType.RVALUE)){
        info.append(new Compileinfo("dimension should be constant",node.getPosition()));
      }else if(node.getExpr().getLabel().getType().getName().equals("null")){
        if(node.getExpr().getLabel().getType().getDimension() > dim){
          info.append(new Compileinfo("dimension not match",node.getPosition()));
        }
      }else if(node.getExpr().getLabel().getType().getDimension() != dim){
        info.append(new Compileinfo("dimension should be 0",node.getPosition()));
      }
    }
    
    return info;
  }
  
  public Compileinfo visit(ASTPreExpr node)
  {
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    if(node.getExpr().getLabel().getType().getDimension() != 0)
    {
      info.append(new Compileinfo("array should not be ++ or -- and so on",node.getPosition()));
    }
    if(!node.getExpr().getLabel().getValueType().equals(ExprLable.ValueType.LVALUE)){
      if(node.getExpr().getLabel().getType().getName().equals("null")){
        info.append(new Compileinfo("can not compute the ABANDON",node.getPosition()));

      }
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      return info;
    }
    if(node.getOp().equals("++")||node.getOp().equals("--")){
      if(node.getExpr().getLabel().getType().getName().equals("int")){
        //in mx, it is rvalue
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("int",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.LVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else if(node.getOp().equals("+")||node.getOp().equals("-")){
      if(node.getExpr().getLabel().getType().getName().equals("int")){
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("int",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      } 
    }else if(node.getOp().equals("!")){
      if(node.getExpr().getLabel().getType().getName().equals("bool")){
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("bool",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else if(node.getOp().equals("~")){
      if(node.getExpr().getLabel().getType().getName().equals("int")){
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("int",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else{
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      info.append(new Compileinfo("type not match",node.getPosition()));
    }
    return info;
  }

  public Compileinfo visit(ASTSuffixExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getExpr().accept(this));
    if(node.getExpr().getLabel().getType().getDimension() != 0){
      info.append(new Compileinfo("array should not be ++ or -- and so on",node.getPosition()));
    }
    if(!node.getExpr().getLabel().getValueType().equals(ExprLable.ValueType.LVALUE)){
      if(node.getExpr().getLabel().getType().getName().equals("null")){
        info.append(new Compileinfo("can not compute the ABANDON",node.getPosition()));

      }
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      return info;
    }
    if(node.getOp().equals("++")||node.getOp().equals("--")){
      if(node.getExpr().getLabel().getType().getName().equals("int")){
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("int",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else{
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      info.append(new Compileinfo("type not match",node.getPosition()));
    }
    return info;
  }

  public Compileinfo visit(ASTTernaryExpr node){
    node.setScope(currentScope);
    var info = new Compileinfo();
    info.append(node.getCond().accept(this));
    info.append(node.getTrueExpr().accept(this));
    info.append(node.getFalseExpr().accept(this));
    if(node.getCond().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)
      ||node.getTrueExpr().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)
      ||node.getFalseExpr().getLabel().getValueType().equals(ExprLable.ValueType.ABANDON)){
      info.append(new Compileinfo("can not compute the ABANDON",node.getPosition()));
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      return info;
    }
    if(node.getCond().getLabel().getType().getName().equals("bool")){
      if(node.getTrueExpr().getLabel().getType().getName().equals(node.getTrueExpr().getLabel().getType().getName())
        && node.getFalseExpr().getLabel().getType().getDimension() == node.getFalseExpr().getLabel().getType().getDimension()){
        node.setLabel(new ExprLable(null,node.getTrueExpr().getLabel().getType().clone(),ExprLable.ValueType.RVALUE));
      }else{
        node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
        info.append(new Compileinfo("type not match",node.getPosition()));
      }
    }else{
      node.setLabel(new ExprLable(null,((TypeLable) globalScope.get("null",Scope.QueryType.CLASS)).clone(),ExprLable.ValueType.ABANDON));
      info.append(new Compileinfo("type not match",node.getPosition()));
    }
    return info;
  }

  public Compileinfo visit(ASTType node) {
    throw new ErrorBasic("visit ASTType, access denied, it should not be on the tree",node.getPosition());
  }
  public Compileinfo visit(ASTNode node) {
    throw new ErrorBasic("visit ASTType, access denied, it should not be on the tree",node.getPosition());
  }

}
