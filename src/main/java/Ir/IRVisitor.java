package Ir;

import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Ir.Node.def.*;
import Ir.Node.ins.*;
import Ir.Node.stmt.*;

import Utility.error.ErrorBasic;

public interface IRVisitor<T> {
  public T visit(IRNode node) throws ErrorBasic;
  public T visit(IRRoot node) throws ErrorBasic;
  public T visit(IRFuncDef node) throws ErrorBasic;
  public T visit(IRGlobalDef node) throws ErrorBasic;

  public T visit(IRAllocIns node) throws ErrorBasic;
  public T visit(IRArithIns node) throws ErrorBasic;
  public T visit(IRBranchIns node) throws ErrorBasic;
  public T visit(IRCallIns node) throws ErrorBasic;
  public T visit(IRGetEleIns node) throws ErrorBasic;
  public T visit(IRJmpIns node) throws ErrorBasic;
  public T visit(IRLoadIns node) throws ErrorBasic;
  public T visit(IRPhiIns node) throws ErrorBasic;
  public T visit(IRRetIns node) throws ErrorBasic;
  public T visit(IRStoreIns node) throws ErrorBasic;
  public T visit(IRMoveIns node) throws ErrorBasic;

  public T visit(IRBlockStmt node) throws ErrorBasic;

}
