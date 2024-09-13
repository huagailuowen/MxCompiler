package Optim;

import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRBranchIns;
import Ir.Node.ins.IRJmpIns;
import Ir.Node.ins.IRRetIns;
import Ir.Node.stmt.IRBlockStmt;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

public class LoopTransformer {
  void visit(IRRoot node) {
    visit(node.getInitFunc());
    for (var func : node.getFuncList()) {
      visit(func);
    }
  }
  void visit(IRFuncDef node) {
    var newLables = new HashMap<String,String>();
    var NewBlocks = new ArrayList<IRBlockStmt>();
    for (var block : node.getBlockList()) {
      if(block.getLableName().startsWith("loop")
        &&block.getLableName().endsWith("condition")){
        newLables.put(block.getLableName(),block.getLableName()+"_pre");
      }else{
        continue;
      }
      var outBlock = new IRBlockStmt(block.getLableName()+"_out");
      outBlock.setExitIns(new IRJmpIns(block.getLableName().substring(0,block.getLableName().length()-9)+"body"));
      var preBlock = new IRBlockStmt(block.getLableName()+"_pre");
      preBlock.setExitIns(block.getExitIns().copy());
      ((IRBranchIns)preBlock.getExitIns()).setTrueLabel(outBlock.getLableName());
      ((IRBranchIns)preBlock.getExitIns()).setFalseLabel(block.getLableName().substring(0,block.getLableName().length()-9)+"end");

      var varReplace = new HashMap<RegItem, Item>();
      for(var ins : block.getInsList()){
        var newIns = ins.copy();
        var defs = ins.getDefRegs();
        if(defs.size()>1){
          throw new ErrorBasic("LoopTransformer visit error");
        }
        if(defs.size()==1){
          var def = defs.getFirst();
          var newDef = new RegItem(def.getType(),def.getName()+"._pre",def.getRealType());
          varReplace.put(def,newDef);
          newIns.replaceDef(newDef);
        }
        newIns.replaceUse(varReplace);
        preBlock.addIns(newIns);
      }
      NewBlocks.add(preBlock);
      NewBlocks.add(outBlock);
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
    node.getBlockList().addAll(NewBlocks);
  }
}
