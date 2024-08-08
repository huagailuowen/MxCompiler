package AST;

import java.util.ArrayList;

import org.antlr.v4.runtime.tree.TerminalNode;

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
import AST.Node.expr.ASTExpr;
import AST.Node.expr.ASTFStrExpr;
import AST.Node.expr.ASTNewExpr;
import AST.Node.expr.ASTSuffixExpr;
import AST.Node.expr.ASTTernaryExpr;
import AST.Node.stmt.ASTBlockStmt;
import AST.Node.stmt.ASTBreakStmt;
import AST.Node.stmt.ASTContStmt;
import AST.Node.stmt.ASTExprStmt;
import AST.Node.stmt.ASTForStmt;
import AST.Node.stmt.ASTIfStmt;
import AST.Node.stmt.ASTStmt;
import AST.Node.stmt.ASTVarDefStmt;
import AST.Node.stmt.ASTWhileStmt;
import AST.Node.typ.ASTType;
import Grammar.MxparserBaseVisitor;
import Grammar.MxparserParser;
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
      constructor.setLable(new FuncLable(ctx.Identifier().getText(), null, new ArrayList<>()));
    }else{
      //defalut constructor
      //the position may not right
      constructor = ASTFuncDef.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .lable(new FuncLable(ctx.Identifier().getText(), null, new ArrayList<>()))
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

    // FuncLable label = new FuncLable(ctx.getText(), null, paraList);
    // lable is not set here, but in collector
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
      .exprList(expr)
      .build();
    expr.setFather(node);
    return node;
  }
	

	
	

	@Override public ASTNode visitType(MxparserParser.TypeContext ctx) {
    String name = ctx.typ.getText();
    ArrayList<ASTExpr> dimList = new ArrayList<>();
    for(var array : ctx.arrayLable()){
      dimList.add((ASTExpr) visit(array.expression()));
    }
    ASTType node = ASTType.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .label(new TypeLable(name,ctx.arrayLable().size()))
      .dimList(dimList)
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
    //erase the useless f","
    fString str = "";
    for(var ele : ctx.children){
      if(ele instanceof MxparserParser.ExpressionContext){
        exprList.add((ASTExpr) visit(ele));
      }else if(ele instanceof TerminalNode){
        str = prunString(ele.getText(),true);
        strList.add(str);
        // throw ErrorBasic("We will cut the useless f\", TO DO", new Position(ctx.getStart()));
        
        
      }
    }
    ASTFStrExpr node = ASTFStrExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .args(exprList)
      .strs(strList)
      .build();
    for(var def : node.getExprList()){
      def.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitNewExpr(MxparserParser.NewExprContext ctx) {
    ASTType type = (ASTType) visit(ctx.type());
    ASTExpr expr = null;
    if(ctx.constArray()!=null){
      expr = (ASTAtomExpr) visit(ctx.constArray());
    }
    ASTNewExpr node = ASTNewExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(type)
      .expr(expr)
      .build();
    type.setFather(node);
    if(expr!=null){
      expr.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitSelfOpExpr(MxparserParser.SelfOpExprContext ctx) {
    ASTExpr expr = (ASTExpr) visit(ctx.expression());
    ASTSuffixExpr node = ASTSuffixExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .expr(expr)
      .op(ctx.op.getText())
      .build();
    expr.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitTernaryExpr(MxparserParser.TernaryExprContext ctx) {
    if(ctx.expression().size()!=3){
      throw ErrorBasic("Ternary expression should have 3 parts", new Position(ctx.getStart()));
    }
    ASTExpr cond = (ASTExpr) visit(ctx.expression(0));
    ASTExpr then = (ASTExpr) visit(ctx.expression(1));
    ASTExpr els = (ASTExpr) visit(ctx.expression(2));
    ASTTernaryExpr node = ASTTernaryExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .cond(cond)
      .trueExpr(then)
      .falseExpr(els)
      .build();
    cond.setFather(node);
    then.setFather(node);
    els.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitArrayExpr(MxparserParser.ArrayExprContext ctx) {
    ArrayList<ASTExpr> array = new ArrayList<>();
    ASTExpr expr = (ASTExpr) visit(ctx.expression());
    for(var ele : ctx.array){
      array.add((ASTExpr) visit(ele));
    }
    ASTArrayExpr node = ASTArrayExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .expr(expr)
      .array(array)
      .build();
    expr.setFather(node);
    for(var ele : node.getArray()){
      ele.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitMemberExpr(MxparserParser.MemberExprContext ctx) {
    ASTExpr expr = (ASTExpr) visit(ctx.expression());
    ASTSuffixExpr node = ASTSuffixExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .expr(expr)
      .member(ctx.Member().getText())
      .build();
    expr.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitAtomExpr(MxparserParser.AtomExprContext ctx) {
    return visit(ctx.atom());
  }
	

	@Override public ASTNode visitBinaryExpr(MxparserParser.BinaryExprContext ctx) {
    if(ctx.expression().size()!=2){
      throw ErrorBasic("Binary expression should have 2 parts", new Position(ctx.getStart()));
    }
    ASTExpr lhs = (ASTExpr) visit(ctx.expression(0));
    ASTExpr rhs = (ASTExpr) visit(ctx.expression(1));
    ASTBinaryExpr node = ASTBinaryExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .lhs(lhs)
      .rhs(rhs)
      .op(ctx.op.getText())
      .build();
    lhs.setFather(node);
    rhs.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitFormatStringExpr(MxparserParser.FormatStringExprContext ctx) {
    return visit(ctx.formatStringElement());
  }
	

	@Override public ASTNode visitChildExpr(MxparserParser.ChildExprContext ctx) {
    return visit(ctx.expression());
  }
	

	@Override public ASTNode visitCallExpr(MxparserParser.CallExprContext ctx) {
    ASTExpr expr = (ASTExpr) visit(ctx.expression());
    ArrayList<ASTExpr> paraList = new ArrayList<>();
    for(var para : ctx.expressionList().expression()){
      paraList.add((ASTExpr) visit(para));
    }
    ASTCallExpr node = ASTCallExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .expr(expr)
      .args(paraList)
      .build();
    expr.setFather(node);
    for(var def : node.getArgs()){
      def.setFather(node);
    }
    return node;
  }
	

	@Override public ASTNode visitAssignExpr(MxparserParser.AssignExprContext ctx) {
    if(ctx.expression().size()!=2){
      throw ErrorBasic("Assign expression should have 2 parts", new Position(ctx.getStart()));
    }
    ASTExpr lhs = (ASTExpr) visit(ctx.expression(0));
    ASTExpr rhs = (ASTExpr) visit(ctx.expression(1));
    ASTAssignExpr node = ASTAssignExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .lhs(lhs)
      .rhs(rhs)
      .op(ctx.op.getText())
      .build();
    lhs.setFather(node);
    rhs.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitPreOpExpr(MxparserParser.PreOpExprContext ctx) {
    ASTExpr expr = (ASTExpr) visit(ctx.expression());
    ASTSuffixExpr node = ASTSuffixExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .expr(expr)
      .op(ctx.op.getText())
      .build();
    expr.setFather(node);
    return node;
  }
	

	@Override public ASTNode visitIdAtom(MxparserParser.IdAtomContext ctx) {
    return ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.IDENTIFIER)
      .value(ctx.Identifier().getText())
      .build();
  }
	

	@Override public ASTNode visitIntAtom(MxparserParser.IntAtomContext ctx) {
    return ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.INT)
      .value(ctx.Integer().getText())
      .build();
  }
	public String prunString(String str, boolean isFormate = false){
    String res= new String();
    if(isFormate){
      int p=1;
      if(str.charAt(0)=='f'){
        p=2;
      }
      for(int i=1;i<str.length()-1;i++){
        if(str.charAt(i)=='\\'){
          i++;
          if(str.charAt(i)=='n'){
            res+='\n';
          }else if(str.charAt(i)=='t'){
            res+='\t';
          }else if(str.charAt(i)=='\\'){
            res+='\\';
          }else if(str.charAt(i)=='"'){
            res+='"';
          }else{
            throw ErrorBasic("Unknow escape character", new Position(ctx.getStart()));
          }
        }else if(str.charAt(i)=='$'){
          i++;
          if(i==str.length()-1){
            throw ErrorBasic("Unknow escape character", new Position(ctx.getStart()));
          }
          if(str.charAt(i)=='$'){
            res+="$";
          }else {
            throw ErrorBasic("Unknow escape character", new Position(ctx.getStart()));
          }
        }else{
          res+=str.charAt(i);
        }
      }
    }else{
      for(int i=1;i<str.length()-1;i++){
        if(str.charAt(i)=='\\'){
          i++;
          if(str.charAt(i)=='n'){
            res+='\n';
          }else if(str.charAt(i)=='t'){
            res+='\t';
          }else if(str.charAt(i)=='\\'){
            res+='\\';
          }else if(str.charAt(i)=='"'){
            res+='"';
          }else{
            throw ErrorBasic("Unknow escape character", new Position(ctx.getStart()));
          }
        }else{
          res+=str.charAt(i);
        }
      }
    }
    return res;
  }

	@Override public ASTNode visitStringAtom(MxparserParser.StringAtomContext ctx) {
    return ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.STRING)
      .value(prunString(ctx.String().getText(),false))
      .build();
  }
	

	@Override public ASTNode visitTrueAtom(MxparserParser.TrueAtomContext ctx) {
    return ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.BOOL)
      .value("true")
      .build();
  }
	

	@Override public ASTNode visitFalseAtom(MxparserParser.FalseAtomContext ctx) {
    return ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.BOOL)
      .value("false")
      .build();
  }
	

	@Override public ASTNode visitThisAtom(MxparserParser.ThisAtomContext ctx) {
    return ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.THIS)
      .value("this")
      .build();
  }
	

	@Override public ASTNode visitNullAtom(MxparserParser.NullAtomContext ctx) {
    return ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.NULL)
      .value("null")
      .build();
  }
	

	@Override public ASTNode visitArrayAtom(MxparserParser.ArrayAtomContext ctx) {
    return visit(ctx.constArray());
  }
	

	

	@Override public ASTNode visitConstArray(MxparserParser.ConstArrayContext ctx) {
    ArrayList<ASTAtomExpr> array = new ArrayList<>();
    for(var ele : ctx.atom()){
      array.add((ASTAtomExpr) visit(ele));
    }
    ASTAtomExpr node = ASTAtomExpr.builder()
      .position(new Position(ctx.getStart()))
      .father(null)
      .type(ASTAtomExpr.AtomType.ARRAY)
      .array(array)
      .build();
    for(var def : node.getArray()){
      def.setFather(node);
    }
    return node;
  }
  
}
