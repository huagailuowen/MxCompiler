package Optim;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRBranchIns;
import Ir.Node.ins.IRJmpIns;
import Ir.Node.ins.IRRetIns;
import Ir.Node.stmt.IRBlockStmt;
import Utility.error.ErrorBasic;

import java.util.TreeMap;

public class CFGBuilder {
  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for (var func : node.getFuncList()) {
      visit(func);
    }
  }
  public void visit(IRFuncDef node) {
    TreeMap<String, IRBlockStmt> lable2block = new TreeMap<>();
    for(var block : node.getBlockList()){
      if(block.getLableName() != null){
        lable2block.put(block.getLableName(), block);
      }else{
        throw new ErrorBasic("block without lable");
      }
    }
    for(var block : node.getBlockList()){
      if(block.getExitIns() == null){
        throw new ErrorBasic("block without exit");
      }
      var exitins = block.getExitIns();
      if(exitins instanceof IRJmpIns) {
        var jmpins = (IRJmpIns) exitins;
        if (lable2block.containsKey(jmpins.getLabel())) {
          block.getSucc().add(lable2block.get(jmpins.getLabel()));
        } else {
          throw new ErrorBasic("lable not found");
        }
      }else if(exitins instanceof IRBranchIns) {
        var branchins = (IRBranchIns) exitins;
        if (lable2block.containsKey(branchins.getTrueLabel())) {
          block.getSucc().add(lable2block.get(branchins.getTrueLabel()));
        } else {
          throw new ErrorBasic("lable not found");
        }
        if (lable2block.containsKey(branchins.getFalseLabel())) {
          block.getSucc().add(lable2block.get(branchins.getFalseLabel()));
        } else {
          throw new ErrorBasic("lable not found");
        }
      }else if(exitins instanceof IRRetIns) {
        block.getSucc().add(null);
        //the return block
      }else{
        throw new ErrorBasic("exitins error");
      }
    }
    for(var block : node.getBlockList()){
      for(var succ : block.getSucc()){
        if(succ != null){
          succ.getPred().add(block);
        }
      }
    }
  }

}
