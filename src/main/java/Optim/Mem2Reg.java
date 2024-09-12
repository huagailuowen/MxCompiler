package Optim;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.LiteralItem;
import Ir.Item.RegItem;
import Ir.Node.IRNode;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Type.IRBaseType;
import org.antlr.v4.runtime.misc.Pair;
import org.antlr.v4.runtime.tree.Tree;

import java.util.*;

public class Mem2Reg {
  TreeMap<Integer, IRBlockStmt>index2block;
  ArrayList<BitSet> domList;
  ArrayList<Integer> parentIndex;
  ArrayList<ArrayList<Integer>> domFrontier;

  ArrayList<RegItem> allocVars;
  HashMap<RegItem,Integer>var2index;
  ArrayList<ArrayList<Integer>>defList;
  BitSet visitBlockFlag;
  HashMap<RegItem, Item>replaceMap;

  public void visit(IRRoot node){
    visit(node.getInitFunc());
    for(var func : node.getFuncList()){
      visit(func);
    }
  }
  public void makeDomList(IRFuncDef node)
  {
//    removeUnreachable(node);
    int blockindex = 0;
    index2block = new TreeMap<>();
    for(var block : node.getBlockList()){
      block.setIndex(blockindex++);
      index2block.put(block.getIndex(), block);
    }
    domList = new ArrayList<>();
    for(int i=0;i<blockindex;i++){
      var dom = new BitSet(blockindex);
      dom.set(0, blockindex, true);
      domList.add(dom);
    }
    boolean changed = true;
    BitSet visitflag = null;
    while(changed){
      changed = false;
      visitflag = new BitSet(node.getBlockList().size());
      var blockQueue = new LinkedList<Integer >();
      blockQueue.add(0);
      //the entry block
      visitflag.set(0, true);
      while(!blockQueue.isEmpty()){
        blockindex = blockQueue.poll();

        var tmpdom = new BitSet(blockindex);
        if(blockindex != 0){
          tmpdom.set(0, blockindex, true);
        }else{
          tmpdom.set(0, blockindex, false);
        }
        for(var pred : index2block.get(blockindex).getPred()){
          tmpdom.and(domList.get(pred.getIndex()));
        }
        tmpdom.set(blockindex, true);
        if(!tmpdom.equals(domList.get(blockindex))){
          changed = true;
          domList.set(blockindex, tmpdom);
        }
        for(var succ : index2block.get(blockindex).getSucc()){
          if(!visitflag.get(succ.getIndex())){
            blockQueue.add(succ.getIndex());
            visitflag.set(succ.getIndex(), true);
          }
        }
      }
    }
    //delete the block that is not visited
    for(int i=0;i<blockindex;i++){
      if(!visitflag.get(i)){
//        node.getBlockList().get(i).setInsList(new ArrayList<>());
        domList.set(i,null);
        node.getBlockList().get(i).setAbandoned(true);
        //mark the useless block
      }
    }
  }
  public void buildDomTree()
  {
    int totalBlock = domList.size();
    parentIndex = new ArrayList<>(totalBlock);
    for(int i=0;i<totalBlock;i++){
      parentIndex.add(-1);
    }
    parentIndex.set(0, -1);
    for(int i=1;i<totalBlock;i++){
      if(domList.get(i) == null){
        continue;
      }
      var dom = domList.get(i);
      int domsize = dom.cardinality();
      int parent = -1;
      for(int j=0;j<totalBlock;j++){
        if(domList.get(j) == null){
          //abandoned block
          continue;
        }
        if(dom.get(j) && domList.get(j).cardinality() == domsize - 1) {
          parent = j;
          break;
        }
      }
      if(parent == -1){
        throw new Error("dom error");
      }
      parentIndex.set(i, parent);
      var parentBlock = index2block.get(parent);
      var childBlock = index2block.get(i);
      parentBlock.addDomChild(childBlock);
      childBlock.setIDom(parentBlock);
    }
//    for(int i=0;i<index2block.size();i++){
//      int parent = -1;
//      for(int j=0;j<index2block.size();j++){
//        if(domList.get(i).get(j)){
//          if(parent == -1){
//            parent = j;
//          }else{
//            while(parent != -1 && !domList.get(parent).get(j)){
//              parent = parentIndex.get(parent);
//            }
//          }
//        }
//      }
//      parentIndex.set(i, parent);
//    }
  }
  void calcDomFrontier()
  {
    int totalBlock = domList.size();
    domFrontier = new ArrayList<>(totalBlock);
    for(int i=0;i<totalBlock;i++){
      domFrontier.add(new ArrayList<>());
    }
    for(int i=0;i<totalBlock;i++){
      if(domList.get(i) == null){
        continue;
      }
      var frontier = new BitSet(totalBlock);
      BitSet tmpdom = (BitSet) domList.get(i).clone();
      tmpdom.set(i, false);
      tmpdom.flip(0, totalBlock);
//      frontier.set(0, totalBlock, false);
      for(var pred : index2block.get(i).getPred()){
        if(pred.isAbandoned()){
          continue;
        }
        BitSet tmp = (BitSet) domList.get(pred.getIndex()).clone();
        tmp.and(tmpdom);
        frontier.or(tmp);
      }
      for(int j=0;j<totalBlock;j++){
        if(frontier.get(j)){
          domFrontier.get(j).add(i);
        }
      }
    }
  }
  public void placePhi()
  {
    //the name rule: xxx._[blockIndex]
    //for every variable, we need to place phi function
    allocVars = new ArrayList<>();
    var2index = new HashMap<>();
    var entryBlock = index2block.get(0);
    for(var ins : entryBlock.getInsList()){
      if(ins instanceof IRAllocIns){
        var2index.put(((IRAllocIns) ins).getDest(), allocVars.size());
        allocVars.add(((IRAllocIns) ins).getDest());
      }else{
//        throw new Error("the first block should only have alloc ins");
      }
    }
    int totalBlock = domList.size();
    defList = new ArrayList<>(allocVars.size());
    for(int i=0;i<allocVars.size();i++){
      defList.add(new ArrayList<>());
    }
    //initialize the block to handle for every variable
    for(int i=0;i<totalBlock;i++){
      if(domList.get(i) == null){
        continue;
      }
      var block = index2block.get(i);
      for(var ins : block.getInsList()){
        if(ins instanceof IRStoreIns){
          RegItem var = ((IRStoreIns) ins).getAddr();
          if(!allocVars.contains(var)){
            continue;
          }
          var index = var2index.get(var);
          defList.get(index).add(i);
        }
      }
    }
    //place phi function
    for(var var : allocVars){
      var varName = var.getName();
      int num = 0;
      var list = defList.get(var2index.get(var));
      var visitFlag = new BitSet(totalBlock);
      for(var def : list){
        visitFlag.set(def);
      }
      while(num < list.size()){
        int blockIndex = list.get(num);
        num++;
        var block = index2block.get(blockIndex);
        for(var frontier : domFrontier.get(blockIndex)){
          if(visitFlag.get(frontier)){
            continue;
          }
          visitFlag.set(frontier);
          list.add(frontier);
          var frontierBlock = index2block.get(frontier);
          if(frontierBlock.getPhi().containsKey(varName)){
            continue;
          }
          var phi = new IRPhiIns(new RegItem(var.getValueType(),varName + "._"+frontier,var.getRealType().getvalType()),var.getValueType());
          phi.setTmpreg(new RegItem(phi.getDest().getType(),phi.getDest().getName()+"_tmp",phi.getDest().getRealType()));
          frontierBlock.getPhi().put(varName, phi);
        }
      }
    }
  }
  public void dfsReplace(IRBlockStmt block, HashMap<String, Pair<Item, String>> curVarName, String fromLable)
  {
    curVarName = new HashMap<>(curVarName);
    //shallow copy
    int cnt = 0;
    for(var entry : block.getPhi().entrySet()){
      //it is for sure that the entryBlock will not have phi function
      var varName = entry.getKey();
      var phi = entry.getValue();
      if(!curVarName.containsKey(varName)){
        throw new Error("phi error");
      }
      //assert that the variable is defined, so the curVarName should contain the variable
      //and its value is not null
      phi.addBranch(curVarName.get(varName).a, fromLable);
      //not the curVarName.get(varName).b
      //item and label
      curVarName.put(varName, new Pair<>(phi.getDest(), block.getLableName()));
      //overwrite the variable name
    }
    if(visitBlockFlag.get(block.getIndex())){
      return;
    }
    ArrayList<IRIns> newInsList = new ArrayList<>();
    visitBlockFlag.set(block.getIndex(), true);
    for(int i = 0; i<block.getInsList().size();i++){
      var ins = block.getInsList().get(i);
      if(ins instanceof IRAllocIns) {
        continue;
      }
      ins.replaceUse(replaceMap);
      if(ins instanceof IRStoreIns){
        var storeIns = (IRStoreIns) ins;
        var addr = storeIns.getAddr();
        if(!curVarName.containsKey(addr.getName())){
          newInsList.add(ins);
          continue;
        }
        curVarName.put(addr.getName(),new Pair<>(storeIns.getValue(), block.getLableName()));
        continue;
      }else if(ins instanceof IRLoadIns){
        var loadIns = (IRLoadIns) ins;
        var addr = loadIns.getAddr();
        if(!curVarName.containsKey(addr.getName())){
          newInsList.add(ins);
          continue;
        }
        var entry = curVarName.get(addr.getName());
        if(entry == null){
          throw new Error("load error");
        }
        replaceMap.put(loadIns.getDest(), entry.a);
        //remove the load instruction by move instruction
        //may be it can be optimized by the register allocation
//        newInsList.add(new IRArithIns(entry.a,new LiteralItem(entry.a.getType(),0),loadIns.getDest(),"+"));
        continue;
      }

      newInsList.add(ins);
    }
    block.setInsList(newInsList);
    var exitIns = block.getExitIns();

    exitIns.replaceUse(replaceMap);

    for(var succ : block.getSucc()){
      dfsReplace(succ, curVarName, block.getLableName());
    }
  }
  public void replaceVar()
  {
    replaceMap = new HashMap<>();
    visitBlockFlag = new BitSet(domList.size());
    var curVarName = new HashMap<String, Pair<Item, String>>();
    for(var var : allocVars){

      curVarName.put(var.getName(), new Pair<>(new LiteralItem(var.getValueType(),0), null));
    }
    dfsReplace(index2block.get(0), curVarName, null);
  }


  public void visit(IRFuncDef node){
    makeDomList(node);
    buildDomTree();
    calcDomFrontier();
    placePhi();
    replaceVar();
    for(var block : node.getBlockList()){
      if(block.isAbandoned()){
        continue;
      }
      int num = 0;
      for(var pred : block.getPred()){
        if(pred.isAbandoned()){
          continue;
        }
        num++;
      }
      for(var phi : block.getPhi().values()){
        if(phi.getValueList().size()!=num){
          throw new Error("phi error");
        }
      }
    }
  }
}
