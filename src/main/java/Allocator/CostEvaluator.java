package Allocator;

import Ir.Item.RegItem;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRIns;
import Ir.Node.stmt.IRBlockStmt;
import Utility.error.ErrorBasic;

import java.util.*;

import static java.lang.Math.pow;

public class CostEvaluator {
  ArrayList<Float> cost;
  public HashMap<RegItem, Integer> var2index;
  public HashMap<Integer, RegItem> index2var;
  public HashMap<RegItem, IRIns> def;
  public HashMap<RegItem, ArrayList<IRIns>> use;
  public HashMap<IRIns, IRBlockStmt> ins2Block;
  BitSet visited;
  HashSet<IRBlockStmt>ancestors;
  int size;
  public void dfsCalcDepth(IRBlockStmt node)
  {
    //assume that the dom tree has been built
    //then we can easily find all the loop entry block
    //to calculate the loop depth of a block,
    //just first get the farthest distance it can reach back on the tree,
    //and then sum the entry block's number up.
    ancestors.add(node);
    calcDepth(node);
    for(var child : node.getDomChild())
    {
      dfsCalcDepth(child);
    }
    ancestors.remove(node);
  }
  public void calcDepth(IRBlockStmt block)
  {
    if(block.getTreeDepth() == -1)
    {
      throw new ErrorBasic("block tree depth not set");
    }
    visited = new BitSet(size);
    var farthest = searchFarthest(block, true);
    if(farthest == null)
    {
      //this block is not in a loop
      return;
    }
    var cur = block;
    int loopDepth = 1;
    //farthest must be a loop entry
    while(cur != farthest)
    {
      if(cur.isLoopEntry())
      {
        loopDepth++;
      }
      cur = cur.getIDom();
    }
    block.setLoopDepth(loopDepth);
  }
  public void calcCost(IRFuncDef node)
  {
    loopFinder(node);
    ancestors = new HashSet<>();
    dfsCalcDepth(node.getBlockList().get(0));
    cost = new ArrayList<>();
    size = var2index.size();
    for(int i = 0; i < size; i++)
    {
      cost.add(0f);
    }
    for(var entry : var2index.entrySet()){
      var reg = entry.getKey();
      var index = entry.getValue();
      float c = 0;
      if(! use.containsKey(reg))
      {
        continue;
      }
      for(var ins : use.get(reg))
      {
        c += calcCost(ins);
      }
      c += calcCost(def.get(reg));
      c/=(1+reg.getDegree());
      cost.set(index, c);
    }
  }
  public int calcCost(IRIns ins)
  {
    int c = 0;
    if(ins == null ){
      return c;
    }
    var block = ins2Block.get(ins);
    c= (int)pow(10,block.getLoopDepth());
    return c;
  }

  public void dfsLoopFinder(IRBlockStmt node, int depth)
  {
    //assume that the dom tree has been built
    //then we can easily find all the loop entry block
    //to calculate the loop depth of a block,
    //just first get the farthest distance it can reach back on the tree,
    //and then sum the entry block's number up.
    node.setTreeDepth(depth);
    for(var succ : node.getSucc())
    {
      if(succ.getTreeDepth() != -1 && succ.getTreeDepth() < depth)
      {
        succ.setLoopEntry(true);
      }
    }
    for(var child : node.getDomChild())
    {
      dfsLoopFinder(child, depth + 1);
    }
  }

  public IRBlockStmt searchFarthest(IRBlockStmt node, boolean isFirst)
  {
    IRBlockStmt res = null;
    if(visited.get(node.getIndex()))
    {
      if(ancestors.contains(node))
      {
        res = node;
      }
      return res;
    }

    if(!isFirst)
    {
      if(ancestors.contains(node))
      {
        res = node;
      }
    }
    visited.set(node.getIndex());
    for(var succ : node.getSucc())
    {
      var tmp = searchFarthest(succ, false);
      if(res == null){
        res = tmp;
      }else if(tmp != null && tmp.getTreeDepth() < res.getTreeDepth())
      {
        res = tmp;
      }
    }
    return res;
  }

  public void loopFinder(IRFuncDef node)
  {
   //assume that the dom tree has been built
    for(var block : node.getBlockList())
    {
      block.setLoopEntry(false);
    }
    dfsLoopFinder(node.getBlockList().get(0),0);
  }
}
