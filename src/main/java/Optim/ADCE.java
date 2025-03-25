package Optim;

import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;

import java.util.*;

public class ADCE {
  HashSet<IRIns> liveIns;
  LinkedList<IRIns> workList;
  HashMap<IRBlockStmt, ArrayList<IRIns>>allIns;
  HashMap<IRIns, HashSet<IRIns>> defUse;
  HashMap<RegItem, IRIns> reg2Ins;
  public void visit(IRRoot node)
  {
    visit(node.getInitFunc());
    for(IRFuncDef func : node.getFuncList())
    {
      visit(func);
    }
  }
  void visit(IRFuncDef node)
  {
    init(node);
    markLive(node);
    removeDead(node);
  }
  void init(IRFuncDef node)
  {
    liveIns = new HashSet<>();
    workList = new LinkedList<>();
    allIns = new HashMap<>();
    defUse = new HashMap<>();
    reg2Ins = new HashMap<>();
    for(var block : node.getBlockList())
    {
      var insList = new ArrayList<IRIns>();
      insList.addAll(block.getPhi().values());
      insList.addAll(block.getInsList());
      insList.add(block.getExitIns());
      allIns.put(block, insList);
      for(var ins : insList)
      {
        if(ins instanceof IRStoreIns
          || ins instanceof IRCallIns
          || ins instanceof IRRetIns
          || ins instanceof IRBranchIns){
          liveIns.add(ins);
          workList.add(ins);
        }
        RegItem dest = IRIns.getAllocaReg(ins);
        if(dest != null){
          reg2Ins.put(dest, ins);
        }

      }
    }

  }
  void markLive(IRFuncDef node)
  {
    while(!workList.isEmpty())
    {
      IRIns ins = workList.poll();
      for(var use : ins.getUseRegs())
      {
        var useIns = reg2Ins.get(use);
        if(useIns != null && !liveIns.contains(useIns))
        {
          liveIns.add(useIns);
          workList.add(useIns);
        }
      }
    }
  }
  void removeDead(IRFuncDef node)
  {
    for(var block : node.getBlockList())
    {
      var newPhi = new TreeMap<String, IRPhiIns>();
      for(var entry : block.getPhi().entrySet())
      {
        if(liveIns.contains(entry.getValue()))
        {
          newPhi.put(entry.getKey(), entry.getValue());
        }
      }
      block.setPhi(newPhi);
      var newInsList = new ArrayList<IRIns>();
      var insList = allIns.get(block);
      insList.remove(insList.size()-1);
      for(var ins : insList)
      {
        if(ins instanceof IRPhiIns){
          continue;
        }
        if(liveIns.contains(ins))
        {
          newInsList.add(ins);
        }
      }
      block.setInsList(newInsList);
    }
  }
}
