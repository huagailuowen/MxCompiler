package AST;

import org.antlr.v4.runtime.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

import AST.Node.*;
import AST.Node.def.ASTClassDef;
import AST.Node.def.ASTDef;
import AST.Node.def.ASTFuncDef;
import AST.Node.def.ASTVarDef;
import AST.Node.expr.ASTExpr;
import AST.Node.stmt.ASTBlockStmt;
import AST.Node.stmt.ASTBreakStmt;
import AST.Node.stmt.ASTContStmt;
import AST.Node.stmt.ASTForStmt;
import AST.Node.stmt.ASTIfStmt;
import AST.Node.stmt.ASTStmt;
import AST.Node.stmt.ASTVarDefStmt;
import AST.Node.stmt.ASTWhileStmt;
import AST.Node.typ.ASTType;
import Grammar.*;
import Utility.error.ErrorBasic;
import Utility.label.ClassLable;
import Utility.label.FuncLable;
import Utility.label.TypeLable;
import Utility.label.VarLable;
import Utility.position.Position;
public class ASTBuilder extends MxparserBaseVisitor<ASTNode> {
  

	@Override public ASTNode visitProgram(MxparserParser.ProgramContext ctx) {
    var defList = new ArrayList<>();
    for (var def : ctx.children) {
      if(def instanceof MxparserParser.ClassDeclarationContext) {
        defList.add(visitClassDeclaration((MxparserParser.ClassDeclarationContext)def));
      }
      else if(def instanceof MxparserParser.FunctionDeclarationContext) {
        defList.add(visitFunctionDeclaration((MxparserParser.FunctionDeclarationContext)def));
      }
      else if (def instanceof MxparserParser.VariableDeclarationContext) {
        ASTVarDefStmt varList = (ASTVarDefStmt) visit(def);
        for(var v : varList.getVarList()){
          defList.add(v);
        }
      }
    }
    return ASTRoot.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .defList(defList)
      .build();
      
  }
	

	@Override public ASTNode visitClassDeclaration(MxparserParser.ClassDeclarationContext ctx) {
    ArrayList<ASTFuncDef> funcDefs = new ArrayList<>();
    ArrayList<ASTDef> varDefs = new ArrayList<>();
    ASTFuncDef constructor = null;
    if(ctx.classConstructor().size()>1){
      throw ErrorBasic("Multiple constructors are not allowed", new Position(ctx.getStart()));
    }else if(ctx.classConstructor().size()==1){
      constructor = (ASTFuncDef) visit(ctx.classConstructor(0));
      constructor.setLable(new FuncLable(ctx.Identifier().getText(), new TypeLable(ctx.Identifier().getText()), new ArrayList<>()));
    }else{
      //defalut constructor
      //the position may not right
      constructor = ASTFuncDef.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .lable(new FuncLable(ctx.Identifier().getText(), new TypeLable(ctx.Identifier().getText()), new ArrayList<>()))
      .paraList(new ArrayList<>())
      .stmtList(new ArrayList<>())
      .build();
    }
    for(var def : ctx.functionDeclaration()){
      funcDefs.add((ASTFuncDef) visit(def));
    }
    for(var def : ctx.variableDeclaration()){
      ASTVarDefStmt varList = (ASTVarDefStmt) visit(def);
      for(var v : varList.getVarList()){
        varDefs.add(v);
      }
    }
    ClassLable label = new ClassLable(ctx.Identifier().getText(), funcDefs, varDefs);
    ASTClassDef node = ASTClassDef.builder()
    .position(new Position(ctx.getStart()))
    .father(null)
    .funcDefs(funcDefs)
    .varDefs(varDefs)
    .constructor(constructor)
    .label(label)
    .build();
    for(var def : node.getFuncDefs()){
      def.setFather(node);
    }
    for(var def : node.getVarDefs()){
      def.setFather(node);
    }
    constructor.setFather(node);
    return node;

  }
	

	@Override public ASTNode visitFunctionDeclaration(MxparserParser.FunctionDeclarationContext ctx) {
    ArrayList<ASTVarDef> paraList = new ArrayList<>();
    ArrayList<ASTStmt> stmtList = new ArrayList<>();
    for(var para : ctx.parameterDeclarationList().parameterDeclaration()){
      paraList.add((ASTVarDef) visit(para));
    }
    stmtList.add((ASTStmt) visit(ctx.block()));

    FuncLable label = new FuncLable(ctx.Identifier().getText(), (ASTType)visit(ctx.type()), paraList);

    ASTFuncDef node = ASTFuncDef.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .label(label)
      .paraList(paraList)
      .stmtList(stmtList)
      .build();
    for(var def : node.getParaList()){
      def.setFather(node);
    }
    for(var def : node.getStmtList()){
      def.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitParameterDeclarationList(MxparserParser.ParameterDeclarationListContext ctx) {
    throw ErrorBasic("This should not be called", new Position(ctx.getStart()));
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitParameterDeclaration(MxparserParser.ParameterDeclarationContext ctx) {
    ASTType type = (ASTType) visit(ctx.type());
    ASTVarDef node = (ASTVarDef) visit(ctx.atomVariableDeclaration());
    node.getLabel().setType(type.getLabel());
    return node;
  }
	

	@Override public ASTNode visitParameterList(MxparserParser.ParameterListContext ctx) {
    throw ErrorBasic("This should not be called", new Position(ctx.getStart()));
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitVariableDeclaration(MxparserParser.VariableDeclarationContext ctx) {
    ArrayList<ASTVarDef> varList = new ArrayList<>();
    ASTType type = (ASTType) visit(ctx.type());
    for(var def : ctx.atomVariableDeclaration()){
      varList.add((ASTVarDef) visit(def));
    }
    ASTVarDefStmt node = ASTVarDefStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .varList(varList)
      .build();
    for(ASTVarDef def : node.getVarList()){
      def.setFather(node);
      def.getLabel().setType(type.getLabel());
    }
    return node;
  }
	

	@Override public ASTNode visitAtomVariableDeclaration(MxparserParser.AtomVariableDeclarationContext ctx) {
    ASTType type = null;
    //this will be set in the visitVariableDeclaration
    ASTExpr init = null;
    if(ctx.expression()!=null){
      init = (ASTExpr) visit(ctx.expression());
    }
    if(!(ctx.atom() instanceof MxparserParser.IdAtomContext)){
      throw ErrorBasic("Expect the Identifier", new Position(ctx.getStart()));
    }
    ASTVarDef node = ASTVarDef.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .label(new VarLable(ctx.atom().getText(), type))
      .init(init)
      .build();
    return node;
  }
	

	@Override public ASTNode visitClassConstructor(MxparserParser.ClassConstructorContext ctx) {
    // should return a FuncDef, with the return type being null
    ArrayList<ASTVarDef> paraList = new ArrayList<>();
    ArrayList<ASTStmt> stmtList = new ArrayList<>();
    //there is no parameter in the constructor
    stmtList.add((ASTStmt) visit(ctx.block()));

    FuncLable label = new FuncLable(ctx.Identifier().getText(), null, paraList);

    ASTFuncDef node = ASTFuncDef.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .label(label)
      .paraList(paraList)
      .stmtList(stmtList)
      .build();
    for(var def : node.getParaList()){
      def.setFather(node);
    }
    for(var def : node.getStmtList()){
      def.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitIfStmt(MxparserParser.IfStmtContext ctx) {
    return visit(ctx.ifStatement());
  }
	

	@Override public ASTNode visitBlockStmt(MxparserParser.BlockStmtCointext ctx) {
    return visit(ctx.block());
  }
	

	@Override public ASTNode visitForStmt(MxparserParser.ForStmtContext ctx) {
    return visit(ctx.forStatement());
  }
	

	@Override public ASTNode visitWhileStmt(MxparserParser.WhileStmtContext ctx) {
    return visit(ctx.whileStatement());
  }
	

	@Override public ASTNode visitReturnStmt(MxparserParser.ReturnStmtContext ctx) {
    return visit(ctx.returnStatement());
  }
	

	@Override public ASTNode visitBreakStmt(MxparserParser.BreakStmtContext ctx) {
    return visit(ctx.breakStatement());
  }
	

	@Override public ASTNode visitContinueStmt(MxparserParser.ContinueStmtContext ctx) {
    return visit(ctx.continueStatement());
  }
	

	@Override public ASTNode visitExpressionStmt(MxparserParser.ExpressionStmtContext ctx) {
    return visit(ctx.expressionStatement());
  }
	

	@Override public ASTNode visitVariableDeclarationStmt(MxparserParser.VariableDeclarationStmtContext ctx) {
    return visit(ctx.variableDeclaration());
  }
	

	@Override public ASTNode visitEmptyStmt(MxparserParser.EmptyStmtContext ctx) {
    //empty statement
    return ASTBlockStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .stmtList(new ArrayList<>())
      .build();
  }
	

	@Override public ASTNode visitBlock(MxparserParser.BlockContext ctx) {
    ArrayList<ASTStmt> stmtList = new ArrayList<ASTStmt>();
    for(var stmt : ctx.statement()){
      stmtList.add((ASTStmt) visit(stmt));
    }
    ASTBlockStmt node = ASTBlockStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .stmtList(stmtList)
      .build();
    for(var def : node.getStmtList()){
      def.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitIfStatement(MxparserParser.IfStatementContext ctx) {
    ASTExpr cond = (ASTExpr) visit(ctx.cond);

    ASTStmt thenStmt = (ASTStmt) visit(ctx.then);
    ASTStmt elseStmt = null;
    if(ctx.els!=null){
      elseStmt = (ASTStmt) visit(ctx.els);
    }
    
    ASTIfStmt node= ASTIfStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .cond(cond)
      .thenStmt(thenStmt)
      .elseStmt(elseStmt)
      .build();
    cond.setFather(node);
    thenStmt.setFather(node);
    if(elseStmt!=null){
      elseStmt.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitForStatement(MxparserParser.ForStatementContext ctx) {
    ASTStmt init = null;
    ASTExpr cond = null;
    ASTExpr update = null;
    ASTStmt content = null;
    if(ctx.init!=null){
      init = (ASTStmt) visit(ctx.init);
    }else{
      throw ErrorBasic("there must be a ';' in For Init!", new Position(ctx.getStart()));
    }
    if(ctx.cond!=null){
      cond = (ASTExpr) visit(ctx.cond);
    }
    if(ctx.update!=null){
      update = (ASTExpr) visit(ctx.update);
    }
    content = (ASTStmt) visit(ctx.content);
    ASTForStmt node = ASTForStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .init(init)
      .cond(cond)
      .update(update)
      .content(content)
      .build();
    init.setFather(node);
    
    if(cond!=null){
      cond.setFather(node);
    }
    if(update!=null){
      update.setFather(node);
    }
    content.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitWhileStatement(MxparserParser.WhileStatementContext ctx) {
    ASTExpr condition = (ASTExpr) visit(ctx.cond);
    ASTStmt content = (ASTStmt) visit(ctx.content);
    ASTWhileStmt node = ASTWhileStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .condition(condition)
      .content(content)
      .build();
    condition.setFather(node);
    content.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitReturnStatement(MxparserParser.ReturnStatementContext ctx) {
    ASTExpr expr = null;
    if(ctx.expr!=null){
      expr = (ASTExpr) visit(ctx.expr);
    }
    ASTReturnStmt node = ASTReturnStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .expr(expr)
      .build();
    if(expr!=null){
      expr.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitBreakStatement(MxparserParser.BreakStatementContext ctx) {
    return ASTBreakStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .build();
  }
	

	@Override public ASTNode visitContinueStatement(MxparserParser.ContinueStatementContext ctx) {
    return ASTContStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .build();
  }
	

	@Override public ASTNode visitExpressionStatement(MxparserParser.ExpressionStatementContext ctx) {
    ASTExpr expr = (ASTExpr) visit(ctx.expressionStatement());
    ASTExprStmt node = ASTExprStmt.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .expr(expr)
      .build();
    expr.setFather(node);
    return node;
  }
	

	
	

	@Override public ASTNode visitType(MxparserParser.TypeContext ctx) {
    String name = ctx.typ.getText();
    for(var array : ctx.arrayLable()){
      if(array.expression()!=null){
        throw ErrorBasic("the definition of array should have no expression", new Position(ctx.getStart()));
      }
    }
    ASTType node = ASTType.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .label(new TypeLable(name,ctx.arrayLable().size()))
      .build();
    return node;
  }
	

	@Override public ASTNode visitArrayLable(MxparserParser.ArrayLableContext ctx) {
    throw ErrorBasic("This should not be called", new Position(ctx.getStart()));
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitFormatStringElement(MxparserParser.FormatStringElementContext ctx) {
    ArrayList<ASTExpr> exprList = new ArrayList<>();
    ArrayList<String> strList = new ArrayList<>();
    for(var ele : ctx.children){
      if(ele instanceof MxparserParser.ExpressionContext){
        exprList.add((ASTExpr) visit(ele));
      }else if(ele instanceof TerminalNode){
        strList.add(ele.getText());
        throw ErrorBasic("We will cut the useless f\", TO DO", new Position(ctx.getStart()));
      }
    }
    ASTFormatString node = ASTFormatString.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .exprList(exprList)
      .strList(strList)
      .build();
    for(var def : node.getExprList()){
      def.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitNewExpr(MxparserParser.NewExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitSelfOpExpr(MxparserParser.SelfOpExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitTernaryExpr(MxparserParser.TernaryExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitArrayExpr(MxparserParser.ArrayExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitMemberExpr(MxparserParser.MemberExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitAtomExpr(MxparserParser.AtomExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitBinaryExpr(MxparserParser.BinaryExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitFormatStringExpr(MxparserParser.FormatStringExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitChildExpr(MxparserParser.ChildExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitCallExpr(MxparserParser.CallExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitAssignExpr(MxparserParser.AssignExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitPreOpExpr(MxparserParser.PreOpExprContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitIdAtom(MxparserParser.IdAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitIntAtom(MxparserParser.IntAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitStringAtom(MxparserParser.StringAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitTrueAtom(MxparserParser.TrueAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitFalseAtom(MxparserParser.FalseAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitThisAtom(MxparserParser.ThisAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitNullAtom(MxparserParser.NullAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitArrayAtom(MxparserParser.ArrayAtomContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitArray(MxparserParser.ArrayContext ctx) {
    return visitChildren(ctx); 
  }
	

	@Override public ASTNode visitConstArray(MxparserParser.ConstArrayContext ctx) {
    return visitChildren(ctx); 
  }
  
}
