package Allocator;

import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRIns;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class GraphAllocator{
  LifeTimeMonitor lifeTimeMonitor;
  static int K = 23;
  //largest number of registers
  ArrayList<HashSet<Integer>>edge;
  //by index
  ArrayList<IRIns> insList;
  ArrayList<IRIns> blockInsList;

  public GraphAllocator() {
    lifeTimeMonitor = new LifeTimeMonitor();
  }
  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for (var func : node.getFuncList()) {
      visit(func);
    }
  }

  public void buildGraph() {
    //TO DO
    edge = new ArrayList<>();
    for (int i = 0; i < lifeTimeMonitor.var2index.size(); i++) {
      edge.add(new HashSet<>());
    }
    for (var ins : insList) {
      var live = new ArrayList<RegItem>(ins.getLiveIn());
      ArrayList<Integer> liveIndex = new ArrayList<>();
      for (var reg : live) {
        liveIndex.add(lifeTimeMonitor.var2index.get(reg));
      }
      for (int i = 0; i < live.size(); i++) {
        for (int j = 0; j < live.size(); j++) {
          if (i != j) {
            edge.get(liveIndex.get(i)).add(liveIndex.get(j));
          }
        }
      }
      live = new ArrayList<RegItem>(ins.getLiveOut());
      liveIndex = new ArrayList<>();
      for (var reg : live) {
        liveIndex.add(lifeTimeMonitor.var2index.get(reg));
      }
      for (int i = 0; i < live.size(); i++) {
        for (int j = 0; j < live.size(); j++) {
          if (i != j) {
            edge.get(liveIndex.get(i)).add(liveIndex.get(j));
          }
        }
      }
    }
  }
  public void handleIns(IRIns ins, BitSet used) {
    var liveOutList = ins.getLiveOut();
    var def = ins.getDefRegs();
    var use = ins.getUseRegs();

    for (var var : use) {
      if(liveOutList.contains(var)){
        continue;
      }
      var index = -1;
      if(!var.getRegAddr().isSpilled()){
        index = var.getRegAddr().getRegIndex();
      }
      if(index != -1){
        used.set(index,false);
      }
    }
    for (var var : def) {
      if(var.getRegAddr().getRegIndex() == -1){
        var.getRegAddr().setSpilled(true);
        //dead variable
      }
      if(var.getRegAddr().isSpilled()){
        continue;
      }
      if(var.getRegAddr().getRegIndex() == -1){
        int index = used.nextClearBit(0);
        if(index >= K){
          throw new ErrorBasic("No enough registers");
        }
        var.setRegAddr(new RegAddr(index));
      }

      int index = var.getRegAddr().getRegIndex();
      used.set(index);

    }
  }
  public void dfsGraph(IRBlockStmt block) {
    var liveInList = lifeTimeMonitor.firstIns.get(block).getLiveIn();
    var used = new BitSet(K);
    for (var var : liveInList) {
      var index = -1;
      if(!var.getRegAddr().isSpilled()){
        index = var.getRegAddr().getRegIndex();
      }
      if(index != -1){
        used.set(index);
      }
    }
    //do we need to add the phi ins?
    for(var entry : block.getPhi().entrySet()){
      var ins = entry.getValue();
      handleIns(ins,used);
    }
    for (var ins : block.getInsList()) {
      handleIns(ins,used);
    }
    handleIns(block.getExitIns(),used);
    for(var child : block.getDomChild()){
      dfsGraph(child);
    }
    //a tree structure, no need to worry about the loop
  }
  public void colorGraph(IRFuncDef node) {

    dfsGraph(node.getBlockList().getFirst());
  }
  public void spillVar(IRFuncDef node) {
    int cnt = 0;
    for(var param : node.getParamList()){
      if(cnt<8){
        param.setRegAddr(new RegAddr(cnt+10));
      }
      cnt++;
    }
    lifeTimeMonitor.loopFinder(node);
    lifeTimeMonitor.calcCost(node);
    for(var ins : insList){
      spillVar(ins.getLiveIn());
      spillVar(ins.getLiveOut());
    }
  }
  public void spillVar(HashSet<RegItem> set)
  {
    var queue = new PriorityQueue<Pair<Float,RegItem>>();
    for(var var : set){
      if(var.getRegAddr() !=null && (var.getRegAddr().isSpilled() || var.getRegAddr().getRegIndex() != -1)){
        continue;
      }
      var index = lifeTimeMonitor.var2index.get(var);
      queue.add(new Pair<>(lifeTimeMonitor.cost.get(index),var));
    }
    while (queue.size()>K){
      var var = Objects.requireNonNull(queue.poll()).b;
      var.setRegAddr(new RegAddr());
      var.getRegAddr().setSpilled(true);
    }
  }
  public void insCollect(IRFuncDef node){
    for (var block : node.getBlockList()) {
      for(var entry : block.getPhi().entrySet()){
        var ins = entry.getValue();
        insList.add(ins);
      }
      insList.addAll(block.getInsList());
      insList.add(block.getExitIns());
    }
  }
  public void visit(IRFuncDef node) {
    insCollect(node);
    lifeTimeMonitor.visit(node);
    spillVar(node);
    buildGraph();
    colorGraph(node);
  }
  /*TO DO List:
   * 1. Implement the Graph Builder
   * 2. Implement the Graph Colored
   * 3. Implement the Graph Spiller
   * 4. Fix the PhiRemover
   */

}
