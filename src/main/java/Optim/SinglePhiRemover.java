package Optim;

import Ir.Item.Item;
import Ir.Item.LiteralItem;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRArithIns;
import Ir.Node.ins.IRIns;
import Ir.Node.ins.IRMoveIns;
import Ir.Node.ins.IRPhiIns;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class SinglePhiRemover {
  public void visit(IRRoot node)
  {
    visit(node.getInitFunc());
    for(IRFuncDef func : node.getFuncList())
    {
      visit(func);
    }
  }
  HashMap<RegItem , Item> replaceMap;
  HashMap<RegItem , IRPhiIns> reg2Ins;
  Item dfsFinder(RegItem reg)
  {
    if(replaceMap.containsKey(reg))
    {
      return replaceMap.get(reg);
    }
    var ins = reg2Ins.get(reg);
    if(ins.getValueList().size() != 1){
      throw new RuntimeException("SinglePhiRemover: dfsFinder");
    }
    var item = ins.getValueList().get(0).a;
    if(item instanceof RegItem && reg2Ins.containsKey(item))
    {
      replaceMap.put(reg, dfsFinder((RegItem) item));
    }
    replaceMap.put(reg, item);
    return item;
  }
  void visit(IRFuncDef node) {
    replaceMap = new HashMap<>();
    reg2Ins = new HashMap<>();
//    for (var block : node.getBlockList()) {
//      ArrayList<IRIns> insList = new ArrayList<>();
//      TreeMap<String, IRPhiIns> newPhi = new TreeMap<>();
//      for(var phi : block.getPhi().values())
//      {
//        if(phi.getValueList().size() == 1){
////          insList.add(new IRArithIns(phi.getValueList().get(0).a,new LiteralItem(phi.getValueList().get(0).a.getType(),0), phi.getDest(), "+"));
//          insList.add(new IRMoveIns(phi.getValueList().get(0).a, phi.getDest()));
//        }else{
//          newPhi.put(phi.getDest().getNameReg(), phi);
//        }
//      }
//      block.setPhi(newPhi);
//      insList.addAll(block.getInsList());
//      block.setInsList(insList);
//    }
    for (var block : node.getBlockList()) {
      for(var phi : block.getPhi().values())
      {
        if(phi.getValueList().size() == 1){
          reg2Ins.put(phi.getDest(), phi);
        }
      }
    }
    for (var block : node.getBlockList()) {
      for(var phi : block.getPhi().values())
      {
        if(phi.getValueList().size() == 1){
          dfsFinder(phi.getDest());
        }
      }
    }
    for(var block : node.getBlockList())
    {
      TreeMap<String, IRPhiIns> newPhi = new TreeMap<>();
      for(var entry : block.getPhi().entrySet())
      {
        var phi = entry.getValue();
        if(phi.getValueList().size() == 1){
          continue;
        }
        phi.replaceUse(replaceMap);
        newPhi.put(entry.getKey(), phi);
      }
      block.setPhi(newPhi);
      for(var ins : block.getInsList())
      {
        ins.replaceUse(replaceMap);
      }
      block.getExitIns().replaceUse(replaceMap);
    }
  }
}
