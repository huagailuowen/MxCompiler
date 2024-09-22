package Optim;

import Allocator.CostEvaluator;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRIns;
import Ir.Node.stmt.IRBlockStmt;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static java.util.Collections.swap;

public class GCM {
  HashMap<RegItem, IRIns> reg2Ins;
  HashMap<IRIns, HashSet<IRIns>> defUse;
  HashMap<IRBlockStmt, ArrayList<IRIns>>allIns;
  HashMap<IRBlockStmt, ArrayList<IRIns>>phiflowIns;
  HashMap<String, IRBlockStmt> label2Block;
  HashSet<IRIns> visited;
  HashMap<IRIns, IRBlockStmt> earlyBlock;
  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for(var func : node.getFuncList()){
      visit(func);
    }
  }
  void visit(IRFuncDef node)
  {//assume that the dominator tree has been built
    new CostEvaluator().loopFinder(node);
    //calculate the cost of each loop
    scheduleEarly(node);
    scheduleLate(node);
  }
  void scheduleEarly(IRIns node)
  {

  }
  void scheduleEarly(IRFuncDef node)
  {
    visited = new HashSet<>();
    earlyBlock = new HashMap<>();
    for(var block : node.getBlockList())
    {
      var allIns = this.allIns.get(block);
      for(var ins : allIns)
      {
        earlyBlock.put(ins,block);
        if(IRIns.isPinned(ins))
        {
          visited.add(ins);
          for(var use : defUse.get(ins)) {
            if (!visited.contains(use)) {
              scheduleEarly(use);
            }
          }
        }
      }
    }
  }
  void scheduleLate(IRFuncDef node)
  {
    //TO DO
  }
  IRBlockStmt LCA(IRBlockStmt a, IRBlockStmt b)
  {
    if(a == null || b == null)
    {
      throw new NullPointerException("LCA input is null");
    }
    while(a.getTreeDepth() > b.getTreeDepth())
    {
      a = a.getIDom();
    }
    while(b.getTreeDepth() > a.getTreeDepth())
    {
      b = b.getIDom();
    }
    while (a != b)
    {
      a = a.getIDom();
      b = b.getIDom();
    }
    return a;
  }

  void buildUseDefLink(IRFuncDef node)
  {
    reg2Ins = new HashMap<>();
    label2Block = new HashMap<>();
    defUse = new HashMap<>();
    allIns = new HashMap<>();
    phiflowIns = new HashMap<>();
    for(var block : node.getBlockList()){
      label2Block.put(block.getLableName(),block);
      var all = new ArrayList<IRIns>(block.getPhi().values());
      all.addAll(block.getInsList());
      all.add(block.getExitIns());
      allIns.put(block,all);
      var phiflow = new ArrayList<IRIns>(block.getPhi().values());
      phiflow.add(block.getExitIns());
      phiflowIns.put(block,phiflow);
      for(var ins : all){
        ins.setBlock(block);
        RegItem dest = IRIns.getAllocaReg(ins);
        if(dest != null){
          reg2Ins.put(dest,ins);
        }
      }


    }
    for(int i = 0;i < node.getParamList().size();i++){
      reg2Ins.put(node.getParamList().get(i),null);
    }
    for(var block : node.getBlockList()){
      var allIns = this.allIns.get(block);
      for(var ins : allIns){
        var useList = ins.getUseRegs();
        for(var reg : useList){
          if(reg2Ins.containsKey(reg)){
            var regins = reg2Ins.get(reg);
            defUse.putIfAbsent(regins,new HashSet<>());
            defUse.get(regins).add(ins);
          }
        }

      }
    }
  }
}
