package Optim;

import Allocator.GraphAllocator;
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
import Utility.label.TypeLable;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.TreeMap;

public class PhiRemover {
  private static int phiCount = 0;
  RegItem tmpReg ;
  public void visit(IRRoot node)
  {
    tmpReg = new RegItem(IRBaseType.getIntType(), "%..TMP_SWAP..", new TypeLable("int"));
    tmpReg.setRegAddr(new RegAddr(GraphAllocator.K));
    //means using the reserved register t0
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
      if(!moved.get(index)){
        throw new ErrorBasic("There must be a unhandled cycle in the phi function");
      }
      //there must be a cycle
      return ;
    }
    visited.set(index);
    if(dests.get(index) == srcs.get(index)){
      moved.set(index);
      //do nothing
      return;
    }
    if(from.get(index) == -1){
      block.addMoveIns(new IRMoveIns(srcs.get(index), dests.get(index)));
      moved.set(index);
    }else{
      if(visited.get(from.get(index))&& !moved.get(from.get(index))){
        loopEnd = (RegItem) srcs.get(index);
        block.addMoveIns(new IRMoveIns(tmpReg, dests.get(index)));
      }else{
        addMove(block, from.get(index), dests, srcs);
      }

      moved.set(index);
      block.addMoveIns(new IRMoveIns(srcs.get(index), dests.get(index)));
      if(loopEnd == dests.get(index)){
        block.addMoveIns(new IRMoveIns(dests.get(index), tmpReg));
      }

    }
  }
  public void addMove(IRBlockStmt block, ArrayList<RegItem> dests, ArrayList<Item> srcs)
  {
    visited = new BitSet(dests.size());
    moved = new BitSet(dests.size());
    reg2index = new HashMap<RegItem, Integer>();
    from = new ArrayList<Integer>();
    block.setMoveList(new ArrayList<>());
    var srcReplace = new HashMap<Integer, RegItem>();
    for(int i = 0; i < dests.size(); i++)
    {
      var dest = dests.get(i);
      reg2index.put(dest, i);
      if(dest.getRegAddr().getRegIndex() == -1){
        dest.getRegAddr().setSpilled(true);
      }else {
        srcReplace.put(dest.getRegAddr().getRegIndex(), dest);
      }
    }
    for (int i = 0; i < srcs.size(); i++) {
      var src = srcs.get(i);
      int index = -1;
      if (src instanceof RegItem) {
        if (reg2index.containsKey((RegItem) src)) {
          index = reg2index.get((RegItem) src);
        }else if(((RegItem) src).getRegAddr().getRegIndex() != -1){
          if(srcReplace.containsKey(((RegItem) src).getRegAddr().getRegIndex())){
            src = srcReplace.get(((RegItem) src).getRegAddr().getRegIndex());
            index = reg2index.get(src);
            srcs.set(i, src);
          }
        }
      }
      from.add(index);
    }
    for(int i = 0; i < dests.size(); i++)
    {
      loopEnd = null;
      addMove(block, i, dests, srcs);
    }
    for(int i = block.getMoveList().size()-1; i >= 0; i--){
      block.addIns(block.getMoveList().get(i));
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
        if(predBlock.getSucc().size()>1){//&& block.getPred().size() > 1
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
