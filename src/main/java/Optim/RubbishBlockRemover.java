package Optim;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRBranchIns;
import Ir.Node.ins.IRJmpIns;
import Ir.Node.stmt.IRBlockStmt;

import java.util.ArrayList;
import java.util.HashMap;

public class RubbishBlockRemover {
  void visit(IRRoot node) {
    visit(node.getInitFunc());
    for (var func : node.getFuncList()) {
      visit(func);
    }
  }
  HashMap<String, String> newLables ;
  HashMap<String, IRBlockStmt> lable2Block ;
  String dfsDest(IRBlockStmt block)
  {
    if(newLables.containsKey(block.getLableName())){
      return newLables.get(block.getLableName());
    }
    var exitIns = block.getExitIns();
    if(exitIns instanceof IRJmpIns jmpIns) {
      if (block.getInsList().isEmpty()) {
        String tmp = dfsDest(lable2Block.get(jmpIns.getLabel()));
        newLables.put(block.getLableName(), tmp);
        return tmp;
      }
    }
    newLables.put(block.getLableName(),block.getLableName());
    return block.getLableName();
  }
  void visit(IRFuncDef node) {
    newLables = new HashMap<>();
    lable2Block = new HashMap<>();
    for(var block : node.getBlockList()){
      lable2Block.put(block.getLableName(),block);
    }
    for(var block : node.getBlockList()){
      dfsDest(block);
    }
    for(var block : node.getBlockList()){
      var exitIns = block.getExitIns();
      if(exitIns instanceof IRJmpIns jmpIns){
        if(newLables.containsKey(jmpIns.getLabel())){
          jmpIns.setLabel(newLables.get(jmpIns.getLabel()));
        }
      }else if(exitIns instanceof IRBranchIns branchIns){
        if(newLables.containsKey(branchIns.getTrueLabel())){
          branchIns.setTrueLabel(newLables.get(branchIns.getTrueLabel()));
        }
        if(newLables.containsKey(branchIns.getFalseLabel())){
          branchIns.setFalseLabel(newLables.get(branchIns.getFalseLabel()));
        }
      }
    }
    var newBlocks = new ArrayList<IRBlockStmt>();
    for(var block : node.getBlockList()){
      if(!newLables.get(block.getLableName()).equals(block.getLableName())){
        continue;
      }
      newBlocks.add(block);
    }
    node.setBlockList(newBlocks);
  }
}
