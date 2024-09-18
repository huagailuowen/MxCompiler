package Optim;

import Ir.Item.Item;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRBranchIns;
import Ir.Node.ins.IRJmpIns;
import Ir.Node.ins.IRRetIns;
import Ir.Node.stmt.IRBlockStmt;
import Utility.error.ErrorBasic;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.TreeMap;

public class CFGBuilder {

  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for (var func : node.getFuncList()) {
      visit(func);
    }
  }
  void searchBlock(IRBlockStmt block, BitSet visitBlockFlag)
  {
    if(visitBlockFlag.get(block.getIndex())){
      return;
    }
    visitBlockFlag.set(block.getIndex(), true);
    for(var succ : block.getSucc()){
      searchBlock(succ, visitBlockFlag);
    }
  }
  public void removeUnreachable(IRFuncDef node)
  {
    BitSet visitFlag = new BitSet(node.getBlockList().size());
    for(int i=0;i<node.getBlockList().size();i++){
      node.getBlockList().get(i).setIndex(i);
    }
    searchBlock(node.getBlockList().get(0), visitFlag);
    var newBlockList = new ArrayList<IRBlockStmt>();
    for(var block : node.getBlockList()){
      if(visitFlag.get(block.getIndex())){
        newBlockList.add(block);
      }
    }
    node.setBlockList(newBlockList);
  }
  public void tryBuild(IRFuncDef node)
  {
    TreeMap<String, IRBlockStmt> lable2block = new TreeMap<>();
    for(var block : node.getBlockList()){
      if(block.getLableName() != null){
        lable2block.put(block.getLableName(), block);
      }else{
        throw new ErrorBasic("block without lable");
      }
      block.setPred(new ArrayList<>());
      block.setSucc(new ArrayList<>());
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
//        block.getSucc().add(null);
        continue;
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
    for(var block : node.getBlockList()){
      for(var phi : block.getPhi().values()){
        ArrayList<Pair<Item,String>>newValueList = new ArrayList<>();
        for(var pair : phi.getValueList()){
          IRBlockStmt preblock = lable2block.get(pair.b);
          if(block.getPred().contains(preblock)){
            newValueList.add(pair);
          }
        }
        phi.setValueList(newValueList);
      }
    }
  }
  public void visit(IRFuncDef node) {
    tryBuild(node);
    removeUnreachable(node);
    tryBuild(node);

  }

}
