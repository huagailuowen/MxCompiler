package Allocator;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRIns;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;

public class GraphAllocator {
  LifeTimeMonitor lifeTimeMonitor;
  static int K = 24;
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
      var live = ins.getLiveIn().toArray(new Ir.Item.RegItem[0]);
      ArrayList<Integer> liveIndex = new ArrayList<>();
      for (var reg : live) {
        liveIndex.add(lifeTimeMonitor.var2index.get(reg));
      }
      for (int i = 0; i < live.length; i++) {
        for (int j = 0; j < live.length; j++) {
          if (i != j) {
            edge.get(liveIndex.get(i)).add(liveIndex.get(j));
          }
        }
      }
      live = ins.getLiveOut().toArray(new Ir.Item.RegItem[0]);
      liveIndex = new ArrayList<>();
      for (var reg : live) {
        liveIndex.add(lifeTimeMonitor.var2index.get(reg));
      }
      for (int i = 0; i < live.length; i++) {
        for (int j = 0; j < live.length; j++) {
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
      if(var.getRegAddr() == null){
        int index = used.nextClearBit(0);
        if(index == K){
          throw new ErrorBasic("No enough registers");
        }
        var.setRegAddr(new RegAddr(index));
      }
      if(var.getRegAddr().isSpilled()){
        continue;
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
  public void spillVar() {

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
    spillVar();
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
