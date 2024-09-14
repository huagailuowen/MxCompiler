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
  HashMap<RegItem, Item> varReplace;
  IRBlockStmt createReplaceBlock(IRBlockStmt block)
  {
    var preBlock = new IRBlockStmt(block.getLableName()+"_pre");
    preBlock.setExitIns(block.getExitIns().copy());

    for(var ins : block.getInsList()){
      var newIns = ins.copy();
      var defs = ins.getDefRegs();
      if(defs.size()>1){
        throw new ErrorBasic("LoopTransformer visit error");
      }
      if(defs.size()==1){
        var def = defs.get(0);
        var newDef = new RegItem(def.getType(),def.getName()+"._pre",def.getRealType());
        varReplace.put(def,newDef);
        newIns.replaceDef(newDef);
      }
      newIns.replaceUse(varReplace);
      preBlock.addIns(newIns);
    }
    preBlock.getExitIns().replaceUse(varReplace);
    var exitIns = preBlock.getExitIns();
    if(exitIns instanceof IRJmpIns jmpIns){
      jmpIns.setLabel(jmpIns.getLabel()+"_pre");
    }else if(exitIns instanceof IRBranchIns branchIns){
      branchIns.setTrueLabel(branchIns.getTrueLabel()+"_pre");
      branchIns.setFalseLabel(branchIns.getFalseLabel()+"_pre");
    }
    return preBlock;
  }
  void visit(IRFuncDef node) {
    var newLables = new HashMap<String,String>();
    var NewBlocks = new ArrayList<IRBlockStmt>();
    var newBlocksList = new ArrayList<IRBlockStmt>();
    for (int i=0; i<node.getBlockList().size();i++) {
      var block = node.getBlockList().get(i);
      if(!(block.getLableName().startsWith("loop")
        &&block.getLableName().endsWith("condition"))){
        newBlocksList.add(block);
        continue;
      }
      NewBlocks = new ArrayList<>();
      var outBlock = new IRBlockStmt(block.getLableName()+"_out");
      outBlock.setExitIns(new IRJmpIns(block.getLableName().substring(0,block.getLableName().length()-9)+"body"));
      varReplace = new HashMap<>();
      int index = i;
      do{
        var tmpBlock = node.getBlockList().get(index++);
        NewBlocks.add(createReplaceBlock(tmpBlock));
      }while(index<node.getBlockList().size()&&
              !node.getBlockList().get(index).getLableName().equals(block.getLableName().substring(0,block.getLableName().length()-9)+"body"));
      var exitBlock = NewBlocks.get(NewBlocks.size()-1);
      if(exitBlock.getExitIns() instanceof IRBranchIns branchIns){
        branchIns.setTrueLabel(outBlock.getLableName());
        branchIns.setFalseLabel(block.getLableName().substring(0,block.getLableName().length()-9)+"end");
      }else if(exitBlock.getExitIns() instanceof IRJmpIns jmpIns){
        jmpIns.setLabel(outBlock.getLableName());
      }else{
        throw new ErrorBasic("LoopTransformer visit error");
      }
      newBlocksList.addAll(NewBlocks);
      newBlocksList.add(outBlock);
      while(i<index){
        block = node.getBlockList().get(i++);
        newBlocksList.add(block);
      }
      i--;
    }

    for(int i = node.getBlockList().size()-1;i>=0;i--){
      var block = node.getBlockList().get(i);
      if((block.getLableName().startsWith("loop")
        &&block.getLableName().endsWith("condition"))){
        newLables.put(block.getLableName(),block.getLableName()+"_pre");
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
    node.setBlockList(newBlocksList);
  }
}
