package Optim;

import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;

import java.util.*;

public class GlobalKiller {
  HashMap<IRFuncDef, HashSet<RegItem>> globalUse;
  HashMap<IRFuncDef, HashSet<RegItem>> globalDef;
  HashMap<IRFuncDef, HashSet<IRFuncDef>>callGraph;
  HashMap<HashSet<IRFuncDef>, HashSet<RegItem>> Use;
  HashMap<HashSet<IRFuncDef>, HashSet<RegItem>> Def;
  HashSet<IRFuncDef>recursiveFunc;

  HashMap<String, IRFuncDef> funcMap;
  void collectVar(IRFuncDef func)
  {
    var defSet = globalDef.get(func);
    var useSet = globalUse.get(func);
    var callSet = callGraph.get(func);
    var allIns = new ArrayList<IRIns>();
    for(var block : func.getBlockList())
    {
      allIns.addAll(block.getPhi().values());
      allIns.addAll(block.getInsList());
      allIns.add(block.getExitIns());
    }
    for(var inst : allIns)
    {
      if(inst instanceof IRCallIns callIns){
        if(!funcMap.containsKey(callIns.getFuncName()))continue;
        callSet.add(funcMap.get(callIns.getFuncName()));
        if(funcMap.get(callIns.getFuncName()) == func){
          recursiveFunc.add(func);
        }
      }
      if(inst instanceof IRStoreIns storeIns){
        if(storeIns.getAddr().getName().startsWith("@")){
          defSet.add(storeIns.getAddr());
        }
        if(storeIns.getValue().getName().startsWith("@")){
          useSet.add((RegItem) storeIns.getValue());
        }
      }else{
        for(var use : inst.getUseRegs())
        {
          if(use.getName().startsWith("@"))
          {
            useSet.add(use);
          }
        }
      }

    }

  }
  void collectVar(IRRoot root)
  {
    recursiveFunc = new HashSet<>();
    funcMap = new HashMap<>();
    funcMap.put(root.getInitFunc().getName().getName(), root.getInitFunc());
    for(var func : root.getFuncList())
    {
      funcMap.put(func.getName().getName(), func);
    }
    globalDef = new HashMap<>();
    globalUse = new HashMap<>();
    globalDef.put(root.getInitFunc(), new HashSet<>());
    globalUse.put(root.getInitFunc(), new HashSet<>());
    root.getFuncList().forEach(func -> {
      globalDef.put(func, new HashSet<>());
      globalUse.put(func, new HashSet<>());
    });
    callGraph = new HashMap<>();
    callGraph.put(root.getInitFunc(), new HashSet<>());
    root.getFuncList().forEach(func -> {
      callGraph.put(func, new HashSet<>());
    });
    collectVar(root.getInitFunc());
    for(var func : root.getFuncList())
    {
      collectVar(func);
    }
  }
  void visit (IRFuncDef func,  HashSet<RegItem> def, HashSet<RegItem> use, boolean recursive)
  {
    var entry = func.getBlockList().get(0);
    var ret = func.getBlockList().get(func.getBlockList().size()-1);

    ArrayList<IRIns> tmpEntry = new ArrayList<>();
    ArrayList<IRIns> tmpRet = new ArrayList<>();
    HashSet<RegItem> allVar = new HashSet<>();
    var selfDef = globalDef.get(func);
    var selfUse = globalUse.get(func);
    allVar.addAll(selfUse);
    allVar.addAll(selfDef);
    //add load and store
    HashMap<RegItem, Item> replaceMap = new HashMap<>();
    for(var var : allVar)
    {
      if(use.contains(var) && selfDef.contains(var))continue;
      if(def.contains(var))continue;
      if(var.getName().startsWith("@string."))continue;
      var reg = new RegItem(var.getType(), "%"+func.getName()+ "." +var.getName().substring(1), var.getRealType());
      reg.setValueType(var.getValueType());
      var ins = new IRAllocIns(var.getType(), reg);
      tmpEntry.add(ins);
      replaceMap.put(var, reg);
    }
    for(var var : allVar){
      if(!replaceMap.containsKey(var))continue;
      if(selfUse.contains(var)){
        var reg = (RegItem) replaceMap.get(var);
        var tmp = new RegItem(var.getValueType(), "%L."+func.getName() + "." + var.getName().substring(1), var.getRealType());
        IRIns ins = new IRLoadIns(var, tmp);
        tmpEntry.add(ins);
        ins = new IRStoreIns(reg, tmp);
        tmpEntry.add(ins);
      }
      if(selfDef.contains(var)){
        var reg = (RegItem) replaceMap.get(var);
        var tmp = new RegItem(var.getValueType(), "%S."+func.getName() + "." + var.getName().substring(1), var.getRealType());
        IRIns ins = new IRLoadIns(reg, tmp);
        tmpRet.add(ins);
        ins = new IRStoreIns(var, tmp);
        tmpRet.add(ins);
      }
    }
    for(var block : func.getBlockList())
    {
      //no global in the def
      for(var phi : block.getPhi().values())
      {
        phi.replaceUse(replaceMap);
      }
      for(var ins : block.getInsList())
      {
        ins.replaceUse(replaceMap);
      }
      block.getExitIns().replaceUse(replaceMap);
    }
    tmpEntry.addAll(entry.getInsList());
    entry.setInsList(tmpEntry);
    ret.getInsList().addAll(tmpRet);

  }
  public void visit(IRRoot root) {
    collectVar(root);
    var inline = new Inline();
    inline.buildCallGraph(root);
    var scc = inline.tarjan.topologicalSort();
    var graph = inline.tarjan.sccGraph;
    Use = new HashMap<>();
    Def = new HashMap<>();
    //reverse the scc
    Collections.reverse(scc);
    for(var set : scc)
    {
      var useSet = new HashSet<RegItem>();
      var defSet = new HashSet<RegItem>();
      ArrayList<IRFuncDef> funcList = new ArrayList<>(set);
      boolean flag = set.size()>1 || recursiveFunc.contains(funcList.get(0));
      for(var callee : graph.get(set)) {
        useSet.addAll(Use.get(callee));
        defSet.addAll(Def.get(callee));
      }
      //try to remove the global var
      if(flag){
        for(var func : funcList)
        {
          useSet.addAll(globalUse.get(func));
          defSet.addAll(globalDef.get(func));
        }
      }
      for(var func : funcList)
      {
        visit(func, defSet, useSet, flag);
      }
      if(!flag)
      {
        for(var func : funcList)
        {
          useSet.addAll(globalUse.get(func));
          defSet.addAll(globalDef.get(func));
        }
      }
      Use.put(set, useSet);
      Def.put(set, defSet);
    }

  }
}
