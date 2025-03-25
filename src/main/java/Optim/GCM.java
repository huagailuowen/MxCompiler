package Optim;

import Allocator.CostEvaluator;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;
import org.antlr.v4.runtime.misc.Pair;

import javax.imageio.ImageReadParam;
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
  IRBlockStmt entryBlock;
  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for(var func : node.getFuncList()){
      visit(func);
    }
  }
  void visit(IRFuncDef node)
  {//assume that the dominator tree has been built
    entryBlock = node.getBlockList().get(0);
    new CostEvaluator().loopFinder(node);
    //calculate the cost of each loop
    buildUseDefLink(node);
    for(var block : node.getBlockList()){
      block.setMoveList(new ArrayList<>());
    }
    scheduleEarly(node);
    scheduleLate(node);
    for(var block : node.getBlockList()){
      ArrayList<IRIns> newInsList = new ArrayList<>();
      for(var ins : block.getInsList()){
        if(ins.getBlock() == block){
          newInsList.add(ins);
        }
      }
      for(int i = 0; i < block.getMoveList().size();i++){
        var ins = block.getMoveList().get(i);
        HashMap<IRIns, Integer> ins2Index = new HashMap<>();
        for(int j = 0;j < newInsList.size();j++){
          ins2Index.put(newInsList.get(j),j);
        }
        int earlyIndex = -1, lateIndex = newInsList.size();
        for(var use : ins.getUseRegs()){
          var useIns = reg2Ins.get(use);
          if(useIns != null && ins2Index.containsKey(useIns)){
            int index = ins2Index.get(useIns);
            earlyIndex = Math.max(earlyIndex,index);
          }
        }
        if(defUse.containsKey(ins))
          for(var def : defUse.get(ins)){
          if(ins2Index.containsKey(def)){
            int index = ins2Index.get(def);
            lateIndex = Math.min(lateIndex,index);
          }
        }
        if(earlyIndex >= lateIndex) {
          throw new RuntimeException("GCM: earlyIndex >= lateIndex");
        }else{
          newInsList.add(lateIndex,ins);
        }
      }
      block.setMoveList(new ArrayList<>());
      block.setInsList(newInsList);
    }
  }
  void scheduleEarly(IRIns node)
  {
    if(visited.contains(node))return;
    visited.add(node);
    var early = entryBlock;
    if(!earlyBlock.containsKey(node)){
      earlyBlock.put(node,node.getBlock());
    }

    for(var use : node.getUseRegs())
    {
      var useIns = reg2Ins.get(use);
      if(useIns == null){
        continue;
      }
      scheduleEarly(useIns);
      var tmp = earlyBlock.get(useIns);
      if(tmp.getTreeDepth() > early.getTreeDepth())
      {
        early = tmp;
      }
    }
    if(IRIns.isPinned(node)){
      early = node.getBlock();
    }
    earlyBlock.put(node,early);
//    throw new RuntimeException("GCM: the pinned ins 's order had to be maintained");
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
        if(!earlyBlock.containsKey(ins)){
          earlyBlock.put(ins,block);
        }
        if(IRIns.isPinned(ins))
        {
          visited.add(ins);
          for(var use : ins.getUseRegs())
          {
            var useIns = reg2Ins.get(use);
            if(useIns != null )
            {
              scheduleEarly(useIns);
            }
          }
        }
      }
    }

  }
  void scheduleLate(IRFuncDef node)
  {
    visited = new HashSet<>();
    for(var block : node.getBlockList())
    {
      var allIns = this.allIns.get(block);
      for(var ins : allIns)
      {

        if(IRIns.isPinned(ins)) {
          if(visited.contains(ins))continue;
          visited.add(ins);
          if(defUse.containsKey(ins)){
            for(var use : defUse.get(ins)) {
              if (!visited.contains(use)) {
                scheduleLate(use);
              }
            }
          }

//          if(!(ins instanceof IRPhiIns)
//            && !(ins instanceof IRBranchIns)
//            && !(ins instanceof IRJmpIns)
//            && !(ins instanceof IRRetIns)){
//            block.getMoveList().add(ins);
//          }
        }
      }
    }
    for(var block : node.getBlockList())
    {
      var allIns = this.allIns.get(block);
      for(var ins : allIns)
      {
        if(visited.contains(ins))continue;

        scheduleLate(ins);
      }
    }
  }
  void scheduleLate(IRIns ins)
  {
    if(visited.contains(ins))return;
    visited.add(ins);
    IRBlockStmt late = null;
    if(IRIns.isPinned(ins)){
      return;
    }
    if(!defUse.containsKey(ins)){
//     throw new RuntimeException("GCM: ins not pinned and has no use");
      ins.setBlock(null);
      return;
    }
    for(var use : defUse.get(ins))
    {
      scheduleLate(use);
      var tmp = use.getBlock();
      if(tmp == null){
        continue;
      }
      if(use instanceof IRPhiIns phiIns){
        for(var bb : phiIns.findBlock(IRIns.getAllocaReg(ins))){
          var block = label2Block.get(bb);
          if(late == null){
            late = block;
          }else {
            late = LCA(late,block);
          }
        }
      }else{
        if(late == null){
          late = tmp;
        }else{
          late = LCA(late,tmp);
        }
      }

    }
    var cur = late;
    var best = late;
    var early = earlyBlock.get(ins);
    while(cur.getTreeDepth()>early.getTreeDepth()){
      if(cur.getLoopDepth() < best.getLoopDepth()){
        best = cur;
      }
      cur = cur.getIDom();
    }
    assert cur == early;
    if(cur.getLoopDepth() < best.getLoopDepth()){
      best = cur;
    }
    if(IRIns.isPinned(ins)){
      best = ins.getBlock();
    }
    if(best == ins.getBlock()){
      return;
    }
    ins.setBlock(best);
    if(!(ins instanceof IRPhiIns)
      && !(ins instanceof IRBranchIns)
      && !(ins instanceof IRJmpIns)
      && !(ins instanceof IRRetIns)){
      best.getMoveList().add(ins);
    }
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
