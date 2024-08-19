package ASMNaive;

import ASMNaive.Node.ASMNode;
import Ir.IRVisitor;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;
import Utility.error.ErrorBasic;

public class ASMBuilder implements IRVisitor<ASMNode> {

  @Override
  public ASMNode visit(IRNode node) throws ErrorBasic {
    throw new ErrorBasic("ASMBuilder visit IRNode");
  }

  @Override
  public ASMNode visit(IRRoot node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRFuncDef node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRAllocIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRArithIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRBranchIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRCallIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRGetEleIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRJmpIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRLoadIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRPhiIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRRetIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRStoreIns node) throws ErrorBasic {
    return null;
  }

  @Override
  public ASMNode visit(IRBlockStmt node) throws ErrorBasic {
    return null;
  }
}
