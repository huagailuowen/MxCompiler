package Optim;

import Ir.Item.Item;
import Ir.Item.LiteralItem;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

public class Inline {
  HashSet<IRFuncDef> inlined;
  HashMap<String, IRFuncDef> name2func;
  HashMap<String, Integer> inlineCnt;

  boolean judge(IRFuncDef node){
    return false;
  }
  public void visit(IRRoot root) {
    inlined = new HashSet<>();
    name2func = new HashMap<>();
    inlineCnt = new HashMap<>();
    name2func.put(root.getInitFunc().getName().getName(), root.getInitFunc());
    inlineCnt.put(root.getInitFunc().getName().getName(), 0);
    if(judge(root.getInitFunc())){
      inlined.add(root.getInitFunc());
    }
    for(var func : root.getFuncList()){
      name2func.put(func.getName().getName(), func);
      inlineCnt.put(func.getName().getName(), 0);
      if(judge(func)){
        inlined.add(func);
      }
    }
  }
  void inlineFunc(IRFuncDef caller, HashSet<IRFuncDef> inlined){
    assert !inlined.contains(caller);
    HashMap<String, String>newLables = new HashMap<>();
    ArrayList<IRBlockStmt> newBlockList = new ArrayList<>();
    for(var block : caller.getBlockList()){
      for(var ins : block.getInsList()){
        if(ins instanceof IRCallIns callIns){
          if(inlined.contains(name2func.get(callIns.getFuncName().getName()))){
            ArrayList<IRBlockStmt> inlinedBlock = inlineFunc(callIns, name2func.get(callIns.getFuncName().getName()));
            int index = block.getInsList().indexOf(ins);
            block.getInsList().remove(index);
            block.getInsList().addAll(index, inlinedBlock);
          }
        }
      }
    }
  }
  ArrayList<IRBlockStmt> inlineFunc(IRCallIns caller, IRFuncDef inlined){
    int cnt = inlineCnt.get(inlined.getName().getName());
    ArrayList<IRBlockStmt> ret = new ArrayList<>();
    inlineCnt.put(inlined.getName().getName(), cnt+1);
    HashMap<RegItem, Item> replaceMap = new HashMap<>();
    HashMap<String, String> newLables = new HashMap<>();
    for(int i=0;i<caller.getArgs().size();i++){
      replaceMap.put(inlined.getParamList().get(i), caller.getArgs().get(i));
    }
    for(var block : inlined.getBlockList()){
      IRBlockStmt newBlock = new IRBlockStmt(block.getLableName()+"._"+cnt);
      TreeMap<String, IRPhiIns> newPhi = new TreeMap<>();
      for(var phi : block.getPhi().values()){
        IRPhiIns newPhiIns = phi.copy();
        RegItem newReg = new RegItem(phi.getDest().getType(), phi.getDest().getNameReg()+"._"+cnt, phi.getDest().getRealType());
        newPhiIns.replaceDef(newReg);
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
        replaceMap.put(caller.getDest(), (RegItem) retIns.getValue());
      }else{
        endBlock.addIns(new IRArithIns(retIns.getValue(), new LiteralItem(retIns.getValue().getType(), 0),caller.getDest(),"+"));
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
