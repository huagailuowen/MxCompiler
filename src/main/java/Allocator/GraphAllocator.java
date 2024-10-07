//package Allocator;
//
//import Ir.Item.RegItem;
//import Ir.Node.IRRoot;
//import Ir.Node.def.IRFuncDef;
//import Ir.Node.ins.IRCallIns;
//import Ir.Node.ins.IRIns;
//import Ir.Node.ins.IRPhiIns;
//import Ir.Node.stmt.IRBlockStmt;
//import Ir.Utility.RegAddr;
//import Utility.error.ErrorBasic;
//
//import java.util.*;
//class ComparablePair implements Comparable<ComparablePair>{
//  public float a;
//  public RegItem b;
//  public ComparablePair(float a, RegItem b){
//    this.a = a;
//    this.b = b;
//  }
//  @Override
//  public int compareTo(ComparablePair o) {
//    if(this.a < o.a){
//      return -1;
//    }else if(this.a > o.a){
//      return 1;
//    }else{
//      return 0;
//    }
//  }
//}
//
//public class GraphAllocator{
//  public static final int K = 24;
//  boolean priorCallee;
//  LifeTimeMonitor lifeTimeMonitor;
//  //largest number of registers
//  ArrayList<HashSet<Integer>>edge;
//  //by index
//  ArrayList<IRIns> insList;
//  ArrayList<IRIns> blockInsList;
//  HashMap<RegItem, Integer> like;
//  HashSet<RegItem> needShare ;
//
//  public GraphAllocator() {
//    lifeTimeMonitor = new LifeTimeMonitor();
//  }
//  public void visit(IRRoot node) {
//    visit(node.getInitFunc());
//    for (var func : node.getFuncList()) {
//      visit(func);
//    }
//  }
//
//  public void buildGraph() {
//    //TO DO
//    edge = new ArrayList<>();
//    for (int i = 0; i < lifeTimeMonitor.var2index.size(); i++) {
//      edge.add(new HashSet<>());
//    }
//    for (var ins : insList) {
//      var live = new ArrayList<RegItem>(ins.getLiveIn());
//      ArrayList<Integer> liveIndex = new ArrayList<>();
//      for (var reg : live) {
//        liveIndex.add(lifeTimeMonitor.var2index.get(reg));
//      }
//      for (int i = 0; i < live.size(); i++) {
//        for (int j = 0; j < live.size(); j++) {
//          if (i != j) {
//            edge.get(liveIndex.get(i)).add(liveIndex.get(j));
//          }
//        }
//      }
//      live = new ArrayList<RegItem>(ins.getLiveOut());
//      liveIndex = new ArrayList<>();
//      for (var reg : live) {
//        liveIndex.add(lifeTimeMonitor.var2index.get(reg));
//      }
//      for (int i = 0; i < live.size(); i++) {
//        for (int j = 0; j < live.size(); j++) {
//          if (i != j) {
//            edge.get(liveIndex.get(i)).add(liveIndex.get(j));
//          }
//        }
//      }
//    }
//  }
//  public void handleIns(IRIns ins, BitSet used, LinkedList<Integer> available) {
//    var liveOutList = ins.getLiveOut();
//    var def = ins.getDefRegs();
//    var use = ins.getUseRegs();
//
//    for (var var : use) {
//      if(liveOutList.contains(var)){
//        continue;
//      }
//      var index = -1;
//      if(!var.getRegAddr().isSpilled()){
//        index = var.getRegAddr().getRegIndex();
//      }
//      if(index != -1){
//        used.set(index,false);
//        boolean isCallee = index >= 10;
//        if(isCallee == priorCallee && !needShare.contains(var)){
//          available.addLast(index);
//        }else {
//          available.addFirst(index);
//        }
//      }
//    }
//    for (var var : def) {
//      if(!lifeTimeMonitor.use.containsKey(var)){
//        var.getRegAddr().setRegIndex(K + 1);
//        //dead variable
//      }
//      if(var.getRegAddr().isSpilled()){
//        continue;
//      }
//      if(like.containsKey(var)){
//        int index = like.get(var);
//        assert index != -1;
//        if(!used.get(index)){
//          var.getRegAddr().setRegIndex(index);
//        }
//      }
//      if(var.getRegAddr().getRegIndex() == -1){
//        int index = available.isEmpty() ? K : available.getLast();
//        if(index >= K){
//          throw new ErrorBasic("No enough registers");
//        }
//        var.setRegAddr(new RegAddr(index));
////        var.setRegAddr(new RegAddr(-1));
//        used.set(index);
//        available.removeLast();
//      }else{
//        int index = var.getRegAddr().getRegIndex();
//        used.set(index);
//        for(var item : available){
//          if(item == index){
//            available.remove(item);
//            break;
//          }
//        }
//      }
//    }
//  }
//  public void handlePhiIns(IRIns ins, BitSet used ,LinkedList<Integer> available) {
//    var liveOutList = ins.getLiveOut();
//    var def = ins.getDefRegs();
//    var use = ins.getUseRegs();
//    RegItem prefer = null;
//    int preferIndex = -1;
//    if(like.containsKey(((IRPhiIns)ins).getDest())){
//      int tmp = like.get(((IRPhiIns)ins).getDest());
//      assert !((IRPhiIns)ins).getDest().getRegAddr().isSpilled();
//      assert ((IRPhiIns)ins).getDest().getRegAddr().getRegIndex() ==-1;
//
//      if(!used.get(tmp)){
//        preferIndex = tmp;
//      }
//    }
//    if(!((IRPhiIns)ins).getDest().getRegAddr().isSpilled()){
//      for (var var : use) {
//        if(liveOutList.contains(var)){
//          continue;
//        }
//        var index = -1;
//        if(var.getRegAddr().isSpilled()){
//          continue;
//        }
//        index = var.getRegAddr().getRegIndex();
//        if(index >= K || (0<=index && index<K && used.get(index))){
//          continue;
//        }
//        if(like.containsKey(var)){
//          continue;
//        }
//        if(index == -1){
//          prefer = var;
//        }else{
//          if(preferIndex != -1) {
//            preferIndex = index;
//          }
//        }
//        break;
//      }
//    }
//
//    for (var var : def) {
//      if(!lifeTimeMonitor.use.containsKey(var)){
//        var.getRegAddr().setRegIndex(K + 1);
//        //dead variable
//      }
//      if(var.getRegAddr().isSpilled()){
//        continue;
//      }
//
//      if(preferIndex != -1){
//        assert !used.get(preferIndex);
//        var.getRegAddr().setRegIndex(preferIndex);
//      }
//      if(var.getRegAddr().getRegIndex() == -1){
//        int index = available.isEmpty() ? K : available.getLast();
//        if(index >= K){
//          throw new ErrorBasic("No enough registers");
//        }
//        var.setRegAddr(new RegAddr(index));
////        var.setRegAddr(new RegAddr(-1));
//        used.set(index);
//        available.removeLast();
//      }else{
//        int index = var.getRegAddr().getRegIndex();
//        used.set(index);
//        for(var item : available){
//          if(item == index){
//            available.remove(item);
//            break;
//          }
//        }
//      }
//      if(prefer != null && var.getRegAddr().getRegIndex()!=-1){
//        like.put(prefer,var.getRegAddr().getRegIndex());
//        needShare.add(var);
//      }
//    }
//  }
//  public void dfsGraph(IRBlockStmt block) {
//
//    var liveInList = lifeTimeMonitor.firstIns.get(block).getLiveIn();
//    var used = new BitSet(K);
//    var available = new LinkedList<Integer>();
//    for (var var : liveInList) {
//      var index = -1;
//      if(!var.getRegAddr().isSpilled()){
//        index = var.getRegAddr().getRegIndex();
//      }
//      if(index != -1 && index < K){
//        used.set(index);
//      }
//    }
//    if(priorCallee){
//      for(int i = 0;i < K; i++) {
//        if (!used.get(i)) {
//          available.add(i);
//          //the s0 -> s11 is in the last, has the priority to be used
//        }
//      }
//    }else{
//      for(int i = K - 1;i >= 0; i--) {
//        if (!used.get(i)) {
//          available.add(i);
//          //the s0 -> s11 is in the last, has the priority to be used
//        }
//      }
//    }
//    //do we need to add the phi ins?
//    for(var entry : block.getPhi().entrySet()){
//      var ins = entry.getValue();
//      handlePhiIns(ins,used,available);
//    }
//
//    for (var ins : block.getInsList()) {
//      handleIns(ins,used,available);
//    }
//    handleIns(block.getExitIns(),used,available);
//    for(var child : block.getDomChild()){
//      dfsGraph(child);
//    }
//    //a tree structure, no need to worry about the loop
//  }
//  public void colorGraph(IRFuncDef node) {
//    like = new HashMap<>();
//    needShare = new HashSet<>();
//    dfsGraph(node.getBlockList().get(0));
//  }
//  public void spillVar(IRFuncDef node) {
//    int cnt = 0;
//    for(var param : node.getParamList()){
//      if(cnt<8){
//        param.setRegAddr(new RegAddr(cnt+1));
//      }else{
//        param.setRegAddr(new RegAddr(-1));
//      }
//      cnt++;
//    }
//    lifeTimeMonitor.loopFinder(node);
//    lifeTimeMonitor.calcCost(node);
//    for(var ins : insList){
//      spillVar(ins.getLiveIn());
//      spillVar(ins.getLiveOut());
//    }
//  }
//  public void spillVar(HashSet<RegItem> set)
//  {
//    var queue = new PriorityQueue<ComparablePair>();
//    int tmp = -1;
//    HashSet<Integer> haveUsed = new HashSet<>();
//    for(var var : set){
//      if(var.getRegAddr() !=null && (var.getRegAddr().isSpilled() || (tmp = var.getRegAddr().getRegIndex()) != -1)){
//        if(tmp!=-1){
//          haveUsed.add(tmp);
//        }
//        continue;
//      }
//      var index = lifeTimeMonitor.var2index.get(var);
//      queue.add(new ComparablePair(lifeTimeMonitor.cost.get(index),var));
//    }
//    int capcity = K - haveUsed.size();
//    while (queue.size() > capcity){
//      var var = Objects.requireNonNull(queue.poll()).b;
//      var.setRegAddr(new RegAddr());
//      var.getRegAddr().setSpilled(true);
//    }
//  }
////  public void coalition(IRFuncDef node)
////  {
////    originName = new HashMap<>();
////    for(var block : node.getBlockList()){
////      for(var phi : block.getPhi().values()){
////
////      }
////    }
////  }
//
//  public void insCollect(IRFuncDef node){
//    priorCallee = false;
//    insList = new ArrayList<>();
//    for (var block : node.getBlockList()) {
//      for(var entry : block.getPhi().entrySet()){
//        var ins = entry.getValue();
//        insList.add(ins);
//      }
//      for(var ins : block.getInsList()){
//        if(ins instanceof IRCallIns){
//          priorCallee = true;
//        }
//      }
//      insList.addAll(block.getInsList());
//      insList.add(block.getExitIns());
//    }
//  }
//
//  public void visit(IRFuncDef node) {
//    insCollect(node);
//    lifeTimeMonitor.visit(node);
//    spillVar(node);
//    buildGraph();
//    colorGraph(node);
////    for(var var : lifeTimeMonitor.var2index.keySet()){
////      System.err.println(var);
////    }
//  }
//  /*TO DO List:
//   * 1. Implement the Graph Builder
//   * 2. Implement the Graph Colored
//   * 3. Implement the Graph Spiller
//   * 4. Fix the PhiRemover
//   */
//
//}
package Allocator;

import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRCallIns;
import Ir.Node.ins.IRIns;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;

import java.util.*;
class ComparablePair implements Comparable<ComparablePair>{
  public float a;
  public RegItem b;
  public ComparablePair(float a, RegItem b){
    this.a = a;
    this.b = b;
  }
  @Override
  public int compareTo(ComparablePair o) {
    if(this.a < o.a){
      return -1;
    }else if(this.a > o.a){
      return 1;
    }else{
      return 0;
    }
  }
}

public class GraphAllocator{
  public static final int K = 24;
  boolean priorCallee;
  LifeTimeMonitor lifeTimeMonitor;
  //largest number of registers
  ArrayList<HashSet<Integer>>edge;
  //by index
  ArrayList<IRIns> insList;
  ArrayList<IRIns> blockInsList;
  HashMap<RegItem, String> originName;

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
  public void handleIns(IRIns ins, BitSet used, LinkedList<Integer> available) {
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
        boolean isCallee = index >= 10;
        if(isCallee == priorCallee){
          available.addLast(index);
        }else {
          available.addFirst(index);
        }
      }
    }
    for (var var : def) {
      if(!lifeTimeMonitor.use.containsKey(var)){
        var.getRegAddr().setRegIndex(K + 1);
        //dead variable
      }
      if(var.getRegAddr().isSpilled()){
        continue;
      }
      if(var.getRegAddr().getRegIndex() == -1){
        int index = available.isEmpty() ? K : available.getLast();
        if(index >= K){
          throw new ErrorBasic("No enough registers");
        }
        var.setRegAddr(new RegAddr(index));
//        var.setRegAddr(new RegAddr(-1));
        used.set(index);
        available.removeLast();
      }else{
        int index = var.getRegAddr().getRegIndex();
        used.set(index);
        boolean flag=false;
        for(var item : available){
          if(item == index){
            available.remove(item);
            flag = true;
            break;
          }
        }
        assert flag;
      }
    }
  }
  public void handlePhiIns(IRIns ins, BitSet used ,LinkedList<Integer> available) {
    var liveOutList = ins.getLiveOut();
    var def = ins.getDefRegs();
    var use = ins.getUseRegs();
    int preferIndex = -1;
    for (var var : use) {
      if(liveOutList.contains(var)){
        continue;
      }
      var index = -1;
      if(var.getRegAddr().isSpilled()){
        continue;
      }
      index = var.getRegAddr().getRegIndex();
      if(index == -1 || index >= K || used.get(index)){
        continue;
      }
      preferIndex = index;
      break;

    }
    for (var var : def) {
      if(!lifeTimeMonitor.use.containsKey(var)){
        var.getRegAddr().setRegIndex(K + 1);
        //dead variable
      }
      if(var.getRegAddr().isSpilled()){
        continue;
      }
      if(preferIndex != -1){
        var.getRegAddr().setRegIndex(preferIndex);
      }
      if(var.getRegAddr().getRegIndex() == -1){
        int index = available.isEmpty() ? K : available.getLast();
        if(index >= K){
          throw new ErrorBasic("No enough registers");
        }
        var.setRegAddr(new RegAddr(index));
//        var.setRegAddr(new RegAddr(-1));
        used.set(index);
        available.removeLast();
      }else{
        int index = var.getRegAddr().getRegIndex();
        used.set(index);
        for(var item : available){
          if(item == index){
            available.remove(item);
            break;
          }
        }
      }
    }
  }
  public void dfsGraph(IRBlockStmt block) {

    var liveInList = lifeTimeMonitor.firstIns.get(block).getLiveIn();
    var used = new BitSet(K);
    var available = new LinkedList<Integer>();
    for (var var : liveInList) {
      var index = -1;
      if(!var.getRegAddr().isSpilled()){
        index = var.getRegAddr().getRegIndex();
      }
      if(index != -1 && index < K){
        used.set(index);
      }
    }
    if(priorCallee){
      for(int i = 0;i < K; i++) {
        if (!used.get(i)) {
          available.add(i);
          //the s0 -> s11 is in the last, has the priority to be used
        }
      }
    }else{
      for(int i = K - 1;i >= 0; i--) {
        if (!used.get(i)) {
          available.add(i);
          //the s0 -> s11 is in the last, has the priority to be used
        }
      }
    }
    //do we need to add the phi ins?
    for(var entry : block.getPhi().entrySet()){
      var ins = entry.getValue();
      handlePhiIns(ins,used,available);
    }

    for (var ins : block.getInsList()) {
      handleIns(ins,used,available);
    }
    handleIns(block.getExitIns(),used,available);
    for(var child : block.getDomChild()){
      dfsGraph(child);
    }
    //a tree structure, no need to worry about the loop
  }
  public void colorGraph(IRFuncDef node) {

    dfsGraph(node.getBlockList().get(0));
  }
  public void spillVar(IRFuncDef node) {
    int cnt = 0;
    for(var param : node.getParamList()){
      if(cnt<8){
        param.setRegAddr(new RegAddr(cnt+1));
      }else{
        param.setRegAddr(new RegAddr(-1));
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
    var queue = new PriorityQueue<ComparablePair>();
    int tmp = -1;
    HashSet<Integer> haveUsed = new HashSet<>();
    for(var var : set){
      if(var.getRegAddr() !=null && (var.getRegAddr().isSpilled() || (tmp = var.getRegAddr().getRegIndex()) != -1)){
        if(tmp!=-1){
          haveUsed.add(tmp);
        }
        continue;
      }
      var index = lifeTimeMonitor.var2index.get(var);
      queue.add(new ComparablePair(lifeTimeMonitor.cost.get(index),var));
    }
    int capcity = K - haveUsed.size();
    while (queue.size() > capcity){
      var var = Objects.requireNonNull(queue.poll()).b;
      var.setRegAddr(new RegAddr());
      var.getRegAddr().setSpilled(true);
    }
  }
//  public void coalition(IRFuncDef node)
//  {
//    originName = new HashMap<>();
//    for(var block : node.getBlockList()){
//      for(var phi : block.getPhi().values()){
//
//      }
//    }
//  }

  public void insCollect(IRFuncDef node){
    priorCallee = false;
    insList = new ArrayList<>();
    for (var block : node.getBlockList()) {
      for(var entry : block.getPhi().entrySet()){
        var ins = entry.getValue();
        insList.add(ins);
      }
      for(var ins : block.getInsList()){
        if(ins instanceof IRCallIns){
          priorCallee = true;
        }
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
//    for(var var : lifeTimeMonitor.var2index.keySet()){
//      System.err.println(var);
//    }
  }
  /*TO DO List:
   * 1. Implement the Graph Builder
   * 2. Implement the Graph Colored
   * 3. Implement the Graph Spiller
   * 4. Fix the PhiRemover
   */

}