package Ir.Node.ins;

import ASM.ASMBuilder;
import ASM.Utility.ASMPhysicReg;
import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Node.IRNode;
import Ir.Node.def.IRClassDef;
import Ir.Node.stmt.IRBlockStmt;
import Utility.error.ErrorBasic;

import java.security.PublicKey;
import java.util.*;

@lombok.Getter
@lombok.Setter
public class IRIns extends IRNode {
  HashSet<RegItem> liveIn;
  HashSet<RegItem> liveOut;
  IRBlockStmt block;
  public void addLiveIn(RegItem reg)
  {
    liveIn.add(reg);
  }
  public void addLiveOut(RegItem reg)
  {
    liveOut.add(reg);
  }
  public IRIns()
  {
    liveIn = new HashSet<>();
    liveOut = new HashSet<>();
  }
  public static boolean needAlloca(IRIns ins, BitSet usedCallee)
  {
    if(ins instanceof IRJmpIns
      || ins instanceof IRBranchIns
      || ins instanceof IRRetIns
      || ins instanceof IRStoreIns
      ){
      return false;
    }else{

      if(ins instanceof IRMoveIns){
        int id=((IRMoveIns) ins).getDest().getRegAddr().getRegIndex();
        if(id != -1){
          id = ASMPhysicReg.availableReg[id].getIndex();
        }
        if(ASMBuilder.calleeMap.containsKey(id)){
          usedCallee.set(ASMBuilder.calleeMap.get(id),true);
        }

        return ((IRMoveIns) ins).getDest().getRegAddr().isSpilled();
      }
      if(ins instanceof IRCallIns){
        int id=((IRCallIns) ins).getDest() == null ? -1 : ((IRCallIns) ins).getDest().getRegAddr().getRegIndex();
        if(id != -1){
          id = ASMPhysicReg.availableReg[id].getIndex();
        }
        if(ASMBuilder.calleeMap.containsKey(id)){
          usedCallee.set(ASMBuilder.calleeMap.get(id),true);
        }
        return ((IRCallIns) ins).getDest() != null && ((IRCallIns) ins).getDest().getRegAddr().isSpilled();
      }
      if(ins instanceof IRAllocIns){
        throw new ErrorBasic("IRAllocIns should not be here");
      }
      if(ins instanceof IRArithIns){
        int id=((IRArithIns) ins).getDest().getRegAddr().getRegIndex();
        if(id != -1){
          id = ASMPhysicReg.availableReg[id].getIndex();
        }
        if(ASMBuilder.calleeMap.containsKey(id)){
          usedCallee.set(ASMBuilder.calleeMap.get(id),true);
        }
        return ((IRArithIns) ins).getDest().getRegAddr().isSpilled();
      }
      if(ins instanceof IRGetEleIns){
        int id=((IRGetEleIns) ins).getDest().getRegAddr().getRegIndex();
        if(id != -1){
          id = ASMPhysicReg.availableReg[id].getIndex();
        }
        if(ASMBuilder.calleeMap.containsKey(id)){
          usedCallee.set(ASMBuilder.calleeMap.get(id),true);
        }
        return ((IRGetEleIns) ins).getDest().getRegAddr().isSpilled();
      }
      if(ins instanceof IRLoadIns){
        int id=((IRLoadIns) ins).getDest().getRegAddr().getRegIndex();
        if(id != -1){
          id = ASMPhysicReg.availableReg[id].getIndex();
        }
        if(ASMBuilder.calleeMap.containsKey(id)){
          usedCallee.set(ASMBuilder.calleeMap.get(id),true);
        }
        return ((IRLoadIns) ins).getDest().getRegAddr().isSpilled();
      }
      throw new ErrorBasic("needAlloca error");
    }
  }
  public static RegItem getAllocaReg(IRIns ins)
  {
    if(ins instanceof IRAllocIns){
      return ((IRAllocIns) ins).getDest();
    }else if(ins instanceof IRArithIns) {
      return ((IRArithIns) ins).getDest();
    }else if(ins instanceof IRGetEleIns){
      return ((IRGetEleIns) ins).getDest();
    }else if(ins instanceof IRCallIns){
      return ((IRCallIns) ins).getDest();
    }else if(ins instanceof IRLoadIns){
      return ((IRLoadIns) ins).getDest();
    }else if(ins instanceof IRMoveIns){
      return ((IRMoveIns) ins).getDest();
    }else if(ins instanceof IRPhiIns){
      return ((IRPhiIns) ins).getDest();
    }else{
      return null;
    }
  }
  public static String getAllocaName(IRIns ins)
  {
    if(ins instanceof IRAllocIns){
      return ((IRAllocIns) ins).getDest().getName();
    }else if(ins instanceof IRArithIns) {
      return ((IRArithIns) ins).getDest().getName();
    }else if(ins instanceof IRGetEleIns){
      return ((IRGetEleIns) ins).getDest().getName();
    }else if(ins instanceof IRCallIns){
      return ((IRCallIns) ins).getDest().getName();
    }else if(ins instanceof IRLoadIns){
      return ((IRLoadIns) ins).getDest().getName();
    }else if(ins instanceof IRMoveIns){
      return ((IRMoveIns) ins).getDest().getName();
    } else if(ins instanceof IRPhiIns){
      return ((IRPhiIns) ins).getDest().getName();
    } else{
      throw new ErrorBasic("getAllocaName error");
    }
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  public void redirectLable(String origin, String target) {
    throw new ErrorBasic("IRIns redirectLable error");
  }
  public void replaceUse(HashMap<RegItem, Item> map) {
    throw new ErrorBasic("IRIns replaceUse error");
  }
  public void replaceDef(RegItem newReg) {
    throw new ErrorBasic("IRIns replaceDef error");
  }
  public ArrayList<RegItem> getUseRegs() {
    throw new ErrorBasic("IRIns getUseRegs error");
  }
  public ArrayList<RegItem> getDefRegs() {
    throw new ErrorBasic("IRIns getDefRegs error");
  }
  public IRIns copy()
  {
    throw new ErrorBasic("IRIns copy error");
  }

}
