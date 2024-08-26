package Optim;

import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRIns;
import Ir.Node.ins.IRJmpIns;
import Ir.Node.ins.IRMoveIns;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Type.IRBaseType;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.TreeMap;

public class PhiRemover {
  private static int phiCount = 0;
  RegItem tmpReg ;
  public void visit(IRRoot node)
  {
    tmpReg = new RegItem(IRBaseType.getIntType(), "..TMP_SWAP..");
    tmpReg.setRegAddr(new RegAddr(28));
    //means using the reserved register
    visit(node.getInitFunc());
    for(var func : node.getFuncList()){
      visit(func);
    }
  }
  BitSet visited;
  BitSet moved;
  HashMap<RegItem, Integer> reg2index;
  ArrayList<Integer> from ;
  RegItem loopEnd;
  public void addMove(IRBlockStmt block, int index, ArrayList<RegItem> dests, ArrayList<Item> srcs)
  {
    if(visited.get(index)){
      if(moved.get(index)){
        return;
      }
      //there must be a cycle
      block.addIns(new IRMoveIns(srcs.get(index), tmpReg));
      //save the src value
      loopEnd = dests.get(index);
      return ;
    }
    visited.set(index);
    if(from.get(index) == -1){
      block.addIns(new IRMoveIns(srcs.get(index), dests.get(index)));
      moved.set(index);
    }else{
      addMove(block, from.get(index), dests, srcs);
      moved.set(index);
      if(loopEnd == dests.get(index)){
        block.addIns(new IRMoveIns(tmpReg, dests.get(index)));
      }else{
        block.addIns(new IRMoveIns(srcs.get(index), dests.get(index)));
      }
    }
  }
  public void addMove(IRBlockStmt block, ArrayList<RegItem> dests, ArrayList<Item> srcs)
  {
    visited = new BitSet(dests.size());
    moved = new BitSet(dests.size());
    reg2index = new HashMap<RegItem, Integer>();
    from = new ArrayList<Integer>();
    for(int i = 0; i < dests.size(); i++)
    {
      reg2index.put(dests.get(i), i);
    }
    for (Item src : srcs) {
      int index = -1;
      if (src instanceof RegItem) {
        if (reg2index.containsKey((RegItem) src)) {
          index = reg2index.get((RegItem) src);
        }
      }
      from.add(index);
    }
    for(int i = 0; i < dests.size(); i++)
    {
      loopEnd = null;
      addMove(block, i, dests, srcs);
    }
  }

  public void visit(IRFuncDef node)
  {
    //Warning : do we remove the useless block?
    ArrayList<IRBlockStmt> insertBlock = new ArrayList<>();
    TreeMap<String, IRBlockStmt> lable2block = new TreeMap<>();
    for(var block : node.getBlockList()){
      if(block.getLableName() != null){
        lable2block.put(block.getLableName(), block);
      }
      block.setReplacePred(new HashMap<>());
    }
    for(var block : node.getBlockList()) {
      if (block.getPhi().isEmpty()) {
        continue;
      }
      var phi = block.getPhi().entrySet().iterator().next().getValue();
      var predBlocks = new ArrayList<IRBlockStmt>();
      var srcList = new ArrayList<ArrayList<Item>>();
      var destList = new ArrayList<RegItem>();
      for(var pair : phi.getValueList()){
        var predBlock = lable2block.get(pair.b);
        if(predBlock.getSucc().size()>1 && block.getPred().size() >1){
          var insertblock = new IRBlockStmt(".PHI."+phiCount++);
          insertblock.addPred(predBlock);
          predBlock.getSucc().remove(block);
          predBlock.addSucc(insertblock);
          insertblock.addSucc(block);
          block.getPred().remove(predBlock);
          block.addPred(insertblock);
          predBlock.getExitIns().redirectLable(block.getLableName(), insertblock.getLableName());
          insertblock.setExitIns(new IRJmpIns(block.getLableName()));
          insertBlock.add(insertblock);
          block.getReplacePred().put(predBlock, insertblock);
        }
        predBlocks.add(predBlock);
        srcList.add(new ArrayList<Item>());
      }
      for(var entry : block.getPhi().entrySet()){
        var phiIns = entry.getValue();
        destList.add(phiIns.getDest());
        for(int i = 0; i < srcList.size(); i++){
          srcList.get(i).add(phiIns.getValueList().get(i).a);
        }
      }
      for(int i = 0;i< predBlocks.size();i++){
        var predBlock = predBlocks.get(i);
        if(block.getReplacePred().containsKey(predBlock)){
          predBlock = block.getReplacePred().get(predBlock);
        }
        addMove(predBlock, destList, srcList.get(i));
      }
    }
    node.getBlockList().addAll(insertBlock);
  }
}
