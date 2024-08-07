package AST;

import AST.Node.ASTNode;
import AST.Node.ASTRoot;
import AST.Node.def.*;
import AST.Node.expr.*;
import AST.Node.stmt.*;
import AST.Node.typ.*;
import Utility.error.BaseError;

public interface ASTVisitor<T> {
  public T visit(ASTNode node) throws BaseError;
  public T visit(ASTRoot node) throws BaseError;

  public T visit(ASTFuncDef node) throws BaseError;
  public T visit(ASTVarDef node) throws BaseError;
  public T visit(ASTClassDef node) throws BaseError;
  
  public T visit(ASTBlockStmt node) throws BaseError;
  public T visit(ASTBreakStmt node) throws BaseError;
  public T visit(ASTContinueStmt node) throws BaseError;
  public T visit(ASTExprStmt node) throws BaseError;
  public T visit(ASTForStmt node) throws BaseError;
  public T visit(ASTIfStmt node) throws BaseError;
  public T visit(ASTReturnStmt node) throws BaseError;
  public T visit(ASTVarDefStmt node) throws BaseError;
  public T visit(ASTWhileStmt node) throws BaseError;

  public T visit(ASTArrayExpr node) throws BaseError;
  public T visit(ASTAssignExpr node) throws BaseError;
  public T visit(ASTAtomExpr node) throws BaseError;
  public T visit(ASTBinaryExpr node) throws BaseError;
  public T visit(ASTCallExpr node) throws BaseError;
  public T visit(ASTChildExpr node) throws BaseError;
  public T visit(ASTFStrExpr node) throws BaseError;
  public T visit(ASTMemExpr node) throws BaseError;
  public T visit(ASTNewExpr node) throws BaseError;
  public T visit(ASTPreExpr node) throws BaseError;
  public T visit(ASTSuffixExpr node) throws BaseError;
  public T visit(ASTTernaryExpr node) throws BaseError;

  public T visit(ASTType node) throws BaseError;
}
