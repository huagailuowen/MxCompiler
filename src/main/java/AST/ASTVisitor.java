package AST;

import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import AST.Node.def.*;
import AST.Node.expr.*;
import AST.Node.stmt.*;
import AST.Node.typ.*;
import Utility.error.ErrorBasic;

public interface ASTVisitor<T> {
  public T visit(ASTNode node) throws ErrorBasic;
  public T visit(ASTRoot node) throws ErrorBasic;

  public T visit(ASTFuncDef node) throws ErrorBasic;
  public T visit(ASTVarDef node) throws ErrorBasic;
  public T visit(ASTClassDef node) throws ErrorBasic;
  
  public T visit(ASTBlockStmt node) throws ErrorBasic;
  public T visit(ASTBreakStmt node) throws ErrorBasic;
  public T visit(ASTContStmt node) throws ErrorBasic;
  public T visit(ASTEmptyStmt node) throws ErrorBasic;
  public T visit(ASTExprStmt node) throws ErrorBasic;
  public T visit(ASTForStmt node) throws ErrorBasic;
  public T visit(ASTIfStmt node) throws ErrorBasic;
  public T visit(ASTRetStmt node) throws ErrorBasic;
  public T visit(ASTVarDefStmt node) throws ErrorBasic;
  public T visit(ASTWhileStmt node) throws ErrorBasic;

  public T visit(ASTArrayExpr node) throws ErrorBasic;
  public T visit(ASTAssignExpr node) throws ErrorBasic;
  public T visit(ASTAtomExpr node) throws ErrorBasic;
  public T visit(ASTBinaryExpr node) throws ErrorBasic;
  public T visit(ASTCallExpr node) throws ErrorBasic;
  public T visit(ASTChildExpr node) throws ErrorBasic;
  public T visit(ASTFStrExpr node) throws ErrorBasic;
  public T visit(ASTMemExpr node) throws ErrorBasic;
  public T visit(ASTNewExpr node) throws ErrorBasic;
  public T visit(ASTPreExpr node) throws ErrorBasic;
  public T visit(ASTSuffixExpr node) throws ErrorBasic;
  public T visit(ASTTernaryExpr node) throws ErrorBasic;

  public T visit(ASTType node) throws ErrorBasic;
}
