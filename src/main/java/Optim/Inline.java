package Optim;

import Ir.Item.Item;
import Ir.Item.LiteralItem;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;

import java.util.*;

public class Inline {
  HashSet<IRFuncDef> inlined;
  HashMap<String, IRFuncDef> name2func;
  HashMap<String, Integer> inlineCnt;


  boolean judge(IRFuncDef caller, HashSet<IRFuncDef> callees){
    // the naive version
    int callerSize = 0;
    int addSize = 0;
    HashMap<IRFuncDef, Integer>calleeSize = new HashMap<>();
    for(var callee : callees){
      int calleesize = 0;
      for(int i=0;i<callee.getBlockList().size();i++){
        calleesize += callee.getBlockList().get(i).getInsList().size();
        calleesize += callee.getBlockList().get(i).getPhi().size();
      }
      calleeSize.put(callee, calleesize);
    }
    for(int i=0;i<caller.getBlockList().size();i++){
      callerSize += caller.getBlockList().get(i).getInsList().size();
      callerSize += caller.getBlockList().get(i).getPhi().size();
      for(var ins : caller.getBlockList().get(i).getInsList()){
        if(ins instanceof IRCallIns callIns && callees.contains(name2func.get(callIns.getFuncName()))){
          addSize += calleeSize.get(name2func.get(callIns.getFuncName()));
        }
      }
    }

    return callerSize < 200 || addSize < 200;

  }
  Tarjan tarjan;
  void buildCallGraph(IRRoot root)
  {
    HashMap<IRFuncDef, HashSet<IRFuncDef>> callGraph;
    callGraph = new HashMap<>();
    callGraph.put(root.getInitFunc(), new HashSet<>());
    for(var func : root.getFuncList()){
      callGraph.put(func, new HashSet<>());
    }
    //init won't call any function
    for(var func : root.getFuncList()){
      for(var block : func.getBlockList()){
        for(var ins : block.getInsList()){
          if(ins instanceof IRCallIns callIns && name2func.containsKey(callIns.getFuncName())){
            callGraph.get(func).add(name2func.get(callIns.getFuncName()));
          }
        }
      }
    }
    tarjan = new Tarjan(callGraph);
    tarjan.computeSCCs();
    tarjan.buildSCCGraph();
  }
  void bfsInline(IRRoot root)
  {
    var sortedSCCs = tarjan.topologicalSort();
    Collections.reverse(sortedSCCs);
    for(var node : sortedSCCs){
      var outEdge = tarjan.sccGraph.get(node);
      for(var func : node){
        var inlineList = new HashSet<IRFuncDef>();
        for(var callees : outEdge){
          for(var callee : callees){
            inlineList.add(callee);
            if(!judge(func, inlineList)){
              inlineList.remove(callee);
            }
          }
        }
        if(!inlineList.isEmpty()){
          inlineFunc(func, inlineList);
        }
      }
    }


  }
  public void visit(IRRoot root) {
    inlined = new HashSet<>();
    name2func = new HashMap<>();
    inlineCnt = new HashMap<>();
    name2func.put(root.getInitFunc().getName().getName(), root.getInitFunc());
    inlineCnt.put(root.getInitFunc().getName().getName(), 0);
    for(var func : root.getFuncList()){
      name2func.put(func.getName().getName(), func);
      inlineCnt.put(func.getName().getName(), 0);
    }
    buildCallGraph(root);
    bfsInline(root);
    new CFGBuilder().visit(root);
    new Mem2Reg().calcDom(root);
  }
  void inlineFunc(IRFuncDef caller, HashSet<IRFuncDef> inlined){
    assert !inlined.contains(caller);
    HashMap<String, String>newLables = new HashMap<>();
    ArrayList<IRBlockStmt> newBlockList = new ArrayList<>();
    for(var block : caller.getBlockList()){
      IRBlockStmt curBlock = new IRBlockStmt(block.getLableName());
      curBlock.setExitIns(null);
      curBlock.setPhi(block.getPhi());
      newBlockList.add(curBlock);
      for(var ins : block.getInsList()){
        if(ins instanceof IRCallIns callIns && inlined.contains(name2func.get(callIns.getFuncName()))){
          ArrayList<IRBlockStmt> inlinedBlock = inlineFunc(callIns, name2func.get(callIns.getFuncName()));
          curBlock.setExitIns(new IRJmpIns(inlinedBlock.get(0).getLableName()));
          newBlockList.addAll(inlinedBlock);
          curBlock = inlinedBlock.get(inlinedBlock.size()-1);
          assert curBlock.getExitIns() == null;
        }else{
          curBlock.addIns(ins);
        }
      }
      curBlock.setExitIns(block.getExitIns());

      newLables.put(block.getLableName(), curBlock.getLableName());

    }
    for(var block : newBlockList){
      for(var phi : block.getPhi().values()){
        IRIns.replaceLable(phi, newLables);
      }
      //we only change the phi's lableName because the jmp dest is not changed
    }
    caller.setBlockList(newBlockList);
  }
  ArrayList<IRBlockStmt> inlineFunc(IRCallIns caller, IRFuncDef inlined){
    int cnt = inlineCnt.get(inlined.getName().getName());
    ArrayList<IRBlockStmt> ret = new ArrayList<>();
    inlineCnt.put(inlined.getName().getName(), cnt+1);
    HashMap<RegItem, Item> replaceMap = new HashMap<>();
    HashMap<String, String> newLables = new HashMap<>();
    HashMap<String, RegItem> newRegs = new HashMap<>();
    for(int i=0;i<caller.getArgs().size();i++){
      replaceMap.put(inlined.getParamList().get(i), caller.getArgs().get(i));
    }
    for(var block : inlined.getBlockList()){
      IRBlockStmt newBlock = new IRBlockStmt(block.getLableName()+"._"+cnt);
      newLables.put(block.getLableName(), newBlock.getLableName());
      TreeMap<String, IRPhiIns> newPhi = new TreeMap<>();
      for(var phi : block.getPhi().values()){
        IRPhiIns newPhiIns = phi.copy();
        RegItem newReg = new RegItem(phi.getDest().getType(), phi.getDest().getNameReg()+"._"+cnt, phi.getDest().getRealType());
        newPhiIns.replaceDef(newReg);
        newRegs.put(phi.getDest().getName(), newReg);
        replaceMap.put(phi.getDest(), newReg);
        newPhi.put(newPhiIns.getDest().getNameReg(), newPhiIns);
      }
      newBlock.setPhi(newPhi);
      for(var ins : block.getInsList()){
        newBlock.addIns(ins.copy());
        RegItem dest = IRIns.getAllocaReg(ins);
        if(dest != null){
          RegItem newReg = new RegItem(dest.getType(), dest.getNameReg()+"._"+cnt, dest.getRealType());
          replaceMap.put(dest, newReg);
          newRegs.put(dest.getName(), newReg);
        }
      }

      newBlock.setExitIns(block.getExitIns().copy());
      ret.add(newBlock);
    }
    IRBlockStmt endBlock = ret.get(ret.size()-1);
    assert endBlock.getExitIns() instanceof IRRetIns ;
    IRRetIns retIns = (IRRetIns) endBlock.getExitIns();
    if(caller.getDest() != null){
      if(retIns.getValue() instanceof RegItem){
        replaceMap.put(newRegs.get(retIns.getValue().getName()),caller.getDest());
      }else{
        endBlock.addIns(new IRArithIns(caller.getDest(), new LiteralItem(caller.getDest().getType(), 0),caller.getDest(),"+"));
      }
    }
    endBlock.setExitIns(null);
    for(var block : ret){
      for(var phi : block.getPhi().values()){
        phi.replaceUse(replaceMap);
        var dest = IRIns.getAllocaReg(phi);
        if(dest != null && replaceMap.containsKey(dest)){
          phi.replaceDef((RegItem) replaceMap.get(dest));
        }
        IRIns.replaceLable(phi, newLables);
      }
      for(var ins : block.getInsList()){
        ins.replaceUse(replaceMap);
        var dest = IRIns.getAllocaReg(ins);
        if(dest != null && replaceMap.containsKey(dest)){
          ins.replaceDef((RegItem) replaceMap.get(dest));
        }
      }
      if(block.getExitIns() == null){
        continue;
      }
      block.getExitIns().replaceUse(replaceMap);
      var dest = IRIns.getAllocaReg(block.getExitIns());
      if(dest != null && replaceMap.containsKey(dest)){
        block.getExitIns().replaceDef((RegItem) replaceMap.get(dest));
      }
      IRIns.replaceLable(block.getExitIns(), newLables);
    }

    return ret;

  }

}

class Tarjan {
  Map<IRFuncDef, HashSet<IRFuncDef>> callGraph;
  Map<IRFuncDef, Integer> index;
  Map<IRFuncDef, Integer> lowlink;
  Stack<IRFuncDef> stack;
  HashSet<IRFuncDef> onStack;
  List<HashSet<IRFuncDef>> stronglyConnectedComponents;
  Map<HashSet<IRFuncDef>, HashSet<HashSet<IRFuncDef>> > sccGraph;
  int currentIndex;

  public Tarjan(Map<IRFuncDef, HashSet<IRFuncDef>> callGraph) {
    this.callGraph = callGraph;
    this.index = new HashMap<>();
    this.lowlink = new HashMap<>();
    this.stack = new Stack<>();
    this.onStack = new HashSet<>();
    this.stronglyConnectedComponents = new ArrayList<>();
    this.sccGraph = new HashMap<>();
    this.currentIndex = 0;
  }

  public List<HashSet<IRFuncDef>> computeSCCs() {
    for (IRFuncDef func : callGraph.keySet()) {
      if (!index.containsKey(func)) {
        strongConnect(func);
      }
    }
    return stronglyConnectedComponents;
  }

  private void strongConnect(IRFuncDef func) {
    index.put(func, currentIndex);
    lowlink.put(func, currentIndex);
    currentIndex++;
    stack.push(func);
    onStack.add(func);

    for (IRFuncDef callee : callGraph.get(func)) {
      if (!index.containsKey(callee)) {
        strongConnect(callee);
        lowlink.put(func, Math.min(lowlink.get(func), lowlink.get(callee)));
      } else if (onStack.contains(callee)) {
        lowlink.put(func, Math.min(lowlink.get(func), index.get(callee)));
      }
    }

    if (lowlink.get(func).equals(index.get(func))) {
      HashSet<IRFuncDef> scc = new HashSet<>();
      IRFuncDef w;
      do {
        w = stack.pop();
        onStack.remove(w);
        scc.add(w);
      } while (!w.equals(func));
      stronglyConnectedComponents.add(scc);
      sccGraph.put(scc, new HashSet<>()); // 初始化缩点图
    }
  }

  public Map<HashSet<IRFuncDef>, HashSet<HashSet<IRFuncDef>>> buildSCCGraph() {
    for (HashSet<IRFuncDef> scc : stronglyConnectedComponents) {
      for (IRFuncDef func : scc) {
        for (IRFuncDef callee : callGraph.get(func)) {
          HashSet<IRFuncDef> calleeSCC = findSCC(callee);
          if (calleeSCC != scc) {
            sccGraph.get(scc).add(calleeSCC);
          }
        }
      }
    }
    return sccGraph;
  }

  private HashSet<IRFuncDef> findSCC(IRFuncDef func) {
    for (HashSet<IRFuncDef> scc : stronglyConnectedComponents) {
      if (scc.contains(func)) {
        return scc;
      }
    }
    return null;
  }

  public List<HashSet<IRFuncDef>> topologicalSort() {
    Map<HashSet<IRFuncDef>, Integer> inDegree = new HashMap<>();
    for (HashSet<IRFuncDef> scc : sccGraph.keySet()) {
      inDegree.put(scc, 0);
    }

    for (HashSet<IRFuncDef> scc : sccGraph.keySet()) {
      for (HashSet<IRFuncDef> neighbor : sccGraph.get(scc)) {
        inDegree.put(neighbor, inDegree.get(neighbor) + 1);
      }
    }

    Queue<HashSet<IRFuncDef>> queue = new LinkedList<>();
    for (HashSet<IRFuncDef> scc : inDegree.keySet()) {
      if (inDegree.get(scc) == 0) {
        queue.add(scc);
      }
    }

    List<HashSet<IRFuncDef>> sortedSCCs = new ArrayList<>();
    while (!queue.isEmpty()) {
      HashSet<IRFuncDef> current = queue.poll();
      sortedSCCs.add(current);
      for (HashSet<IRFuncDef> neighbor : sccGraph.get(current)) {
        inDegree.put(neighbor, inDegree.get(neighbor) - 1);
        if (inDegree.get(neighbor) == 0) {
          queue.add(neighbor);
        }
      }
    }

    return sortedSCCs;
  }
}