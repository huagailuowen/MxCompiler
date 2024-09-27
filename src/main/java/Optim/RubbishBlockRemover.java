package Optim;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRBranchIns;
import Ir.Node.ins.IRJmpIns;
import Ir.Node.stmt.IRBlockStmt;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class RubbishBlockRemover {
  HashSet<IRBlockStmt> visited;
  public void visit(IRRoot node) {
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
    if(visited.contains(block)){
      newLables.put(block.getLableName(),block.getLableName());
      return block.getLableName();
    }
    visited.add(block);
    var exitIns = block.getExitIns();
    if(exitIns instanceof IRJmpIns jmpIns) {
      if (block.getInsList().isEmpty()) {
        String tmp = dfsDest(lable2Block.get(jmpIns.getLabel()));
        if(!newLables.containsKey(block.getLableName())){
          newLables.put(block.getLableName(), tmp);
        }
        return newLables.get(block.getLableName());
      }
    }
    newLables.put(block.getLableName(),block.getLableName());
    return block.getLableName();
  }
  void visit(IRFuncDef node) {
    newLables = new HashMap<>();
    visited = new HashSet<>();
    lable2Block = new HashMap<>();
    for(var block : node.getBlockList()){
      lable2Block.put(block.getLableName(),block);
    }
    for(var block : node.getBlockList()){
      dfsDest(block);
    }
    for(var block : node.getBlockList()){
      for(var phiIns : block.getPhi().values()){
        for(int i=0;i<phiIns.getValueList().size();i++){
          var pair = phiIns.getValueList().get(i);
          if(newLables.containsKey(pair.b)){
            phiIns.getValueList().set(i,new Pair<>(pair.a,newLables.get(pair.b)));
          }
        }
      }
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
    new CFGBuilder().visit(node);
    new Mem2Reg().calcDom(node);
  }
}
