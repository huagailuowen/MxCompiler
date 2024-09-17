package Optim;

import Ir.Item.Item;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.*;
import Ir.Node.stmt.IRBlockStmt;
import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class SCCP {
  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for (var func : node.getFuncList()) {
      visit(func);
    }
  }
  HashMap<RegItem, Pair<Lattice, Integer>> lattice;
  //0: top, 1: const, 2: bottom
  enum Lattice{
    TOP,
    CONST,
    BOTTOM
  }
  //the second element is the value
  HashMap<RegItem, IRIns> reg2Ins;
  HashMap<IRIns, HashSet<IRIns>> defUse;
  HashMap<IRBlockStmt, ArrayList<IRIns>>allIns;
  HashMap<IRBlockStmt, ArrayList<IRIns>>phiflowIns;

  HashMap<IRBlockStmt, HashSet<IRBlockStmt>> marked;
  HashMap<IRBlockStmt, HashSet<IRBlockStmt>> markedInv;

//  HashMap<IRIns, HashSet<IRBlockStmt>> blockUse;

  LinkedList<Pair<IRBlockStmt, IRBlockStmt>> flowList;
  LinkedList<IRIns> ssaList;
  HashMap<String, IRBlockStmt> label2Block;
  HashSet<IRBlockStmt> visited;
  void visit(IRFuncDef node) {

    buildUseDefLink(node);
    updateLattice(node);
    replaceRegs(node);
  }
  void replaceRegs(IRFuncDef node)
  {
    HashMap<RegItem, Item> replaceMap = new HashMap<>();
    for(var item : lattice.entrySet()){
      if(item.getValue().a == Lattice.CONST){
        replaceMap.put(item.getKey(),new Item(item.getKey().getType(),String.valueOf(item.getValue().b)));
      }
    }
    for(var block : node.getBlockList()){
      var allIns = this.allIns.get(block);
      for(var ins : allIns){
        ins.replaceUse(replaceMap);
      }
      if(block.getExitIns() instanceof IRBranchIns branchIns){
        if(!(branchIns.getCondition() instanceof RegItem)){
          if(branchIns.getCondition().getName().equals("1")){
            block.setExitIns(new IRJmpIns(branchIns.getTrueLabel()));
          }if(branchIns.getCondition().getName().equals("0")){
            block.setExitIns(new IRJmpIns(branchIns.getFalseLabel()));
          }else{
            throw new Error("null pointer");
          }
        }

      }
    }
  }
  void visit(IRIns ins){
    if(ins instanceof IRBranchIns branchIns){
      var State = Lattice.CONST;
      int val = 0;
      if(branchIns.getCondition() instanceof RegItem){
        State = lattice.get(branchIns.getCondition()).a;
        val = lattice.get((RegItem) branchIns.getCondition()).b;
      }else{
        if(branchIns.getCondition().getName().equals("null")){
          throw new Error("null pointer");
        }
        val = Integer.valueOf(branchIns.getCondition().getName());
      }
      if(State == Lattice.BOTTOM){
        flowList.add(new Pair<>(ins.getBlock(), label2Block.get(branchIns.getFalseLabel())));
        flowList.add(new Pair<>(ins.getBlock(), label2Block.get(branchIns.getTrueLabel())));
      }else if(State == Lattice.CONST){
        if(val == 1){
          flowList.add(new Pair<>(ins.getBlock(), label2Block.get(branchIns.getTrueLabel())));
        }else{
          flowList.add(new Pair<>(ins.getBlock(), label2Block.get(branchIns.getFalseLabel())));
        }
      }
      return ;
    } else if(ins instanceof IRJmpIns jmpIns){
      flowList.add(new Pair<>(ins.getBlock(), label2Block.get(jmpIns.getLabel()) ));
      return ;
    }
    var dest = IRIns.getAllocaReg(ins);
    if(dest == null){
      return;
    }
    var preState = lattice.get(dest);
    var curState = preState;
    //there is no moveIns
    if(ins instanceof IRPhiIns phiIns){
      Lattice state = Lattice.CONST;
      int val = 0;
      boolean flag = false;
      for(var item : phiIns.getValueList()){
        var block = label2Block.get(item.b);
        if(!markedInv.get(ins.getBlock()).contains(block)){
          continue;
        }
        Lattice tmpState = Lattice.CONST;
        int tmpVal = 0;
        if(item.a instanceof RegItem){
          tmpState = lattice.get(item.a).a;
          tmpVal = lattice.get(item.a).b;
        }else{
          if(item.a.getName().equals("null")){
            throw new Error("null pointer");
          }
          tmpVal = Integer.valueOf(item.a.getName());
        }
        if(tmpState == Lattice.BOTTOM) {
          state = Lattice.BOTTOM;
          break;
        }else if(tmpState == Lattice.TOP){
          state = Lattice.TOP;
        }else{
          if(flag){
            if(val != tmpVal){
              state = Lattice.BOTTOM;
              break;
            }
          }else{
            flag = true;
            val = tmpVal;
          }
        }
      }
      if(state == Lattice.CONST) {
        curState = new Pair<>(Lattice.CONST, val);
      }else{
        curState = new Pair<>(state,0);
      }
    }else if (ins instanceof IRArithIns arithIns) {
      Lattice lState = Lattice.CONST, rState = Lattice.CONST;
      int lval = 0, rval = 0;
      if(arithIns.getLhs() instanceof RegItem){
        lState = lattice.get(arithIns.getLhs()).a;
        lval = lattice.get((RegItem) arithIns.getLhs()).b;
      }else{
        if (arithIns.getLhs().getName().equals("null")){
          throw new Error("null pointer");
        }
        lval = Integer.valueOf(arithIns.getLhs().getName());
      }
      if(arithIns.getRhs() instanceof RegItem){
        rState = lattice.get(arithIns.getRhs()).a;
        rval = lattice.get((RegItem) arithIns.getRhs()).b;
      }else {
        if(arithIns.getRhs().getName().equals("null")){
          throw new Error("null pointer");
        }
        rval = Integer.valueOf(arithIns.getRhs().getName());
      }
      if(arithIns.getOp().equals("mul") && (lval == 0 && lState == Lattice.CONST || rval == 0 && rState == Lattice.CONST)){
        curState = new Pair<>(Lattice.CONST,0);
      }else if(lState == Lattice.BOTTOM || rState == Lattice.BOTTOM) {
        curState = new Pair<>(Lattice.BOTTOM, 0);
      }else if(lState == Lattice.CONST && rState == Lattice.CONST) {
        curState = new Pair<>(Lattice.CONST, IRArithIns.calc(arithIns.getOp(), lval, rval));
      }else{
        curState = new Pair<>(Lattice.TOP,0);
      }
    } else{
      curState = new Pair<>(Lattice.BOTTOM,0);
    }
    if(curState.a != preState.a || !curState.b.equals(preState.b)){
      lattice.put(dest,curState);
      ssaList.addAll(defUse.get(ins));
    }
  }
  void updateLattice(IRFuncDef node){
    marked = new HashMap<>();
    markedInv = new HashMap<>();
    flowList = new LinkedList<>();
    ssaList = new LinkedList<>();
    flowList.add(new Pair<>(null, node.getBlockList().get(0)));
    visited = new HashSet<>();
    //add the entry block
    while(!flowList.isEmpty() || !ssaList.isEmpty()){
      while(!flowList.isEmpty()){
        var pair = flowList.poll();
        var pre = pair.a;
        var cur = pair.b;
        if(pre != null){
          if(marked.get(pre).contains(cur)){
            continue;
          }
          marked.get(pre).add(cur);
          markedInv.get(cur).add(pre);
        }
        var allIns = this.allIns.get(cur);
        var phiflow = this.phiflowIns.get(cur);
        boolean flag = visited.contains(cur);

        if(flag){
          for(var ins : phiflow){
            visit(ins);
          }
        }else{
          for(var ins : allIns){
            visit(ins);
          }
        }
        if(!flag){
          visited.add(cur);
        }
      }
      while(!ssaList.isEmpty()){
        var ins = ssaList.poll();
        var block = ins.getBlock();
        for(var pred : block.getPred()){
          if(marked.get(pred).contains(block)){
            visit(ins);
            break;
          }
        }
      }
    }
  }

  void buildUseDefLink(IRFuncDef node)
  {
    lattice = new HashMap<>();
    reg2Ins = new HashMap<>();
    label2Block = new HashMap<>();
    defUse = new HashMap<>();
    for(var block : node.getBlockList()){
      label2Block.put(block.getLableName(),block);
      marked.put(block,new HashSet<>());
      markedInv.put(block,new HashSet<>());
      var all = new ArrayList<IRIns>(block.getPhi().values());
      all.addAll(block.getInsList());
      all.add(block.getExitIns());
      allIns.put(block,all);
      var phiflow = new ArrayList<IRIns>(block.getPhi().values());
      phiflow.add(block.getExitIns());
      phiflowIns.put(block,phiflow);
      for(var ins : all){
        ins.setBlock(block);
        RegItem dest = IRIns.getAllocaReg(ins);
        if(dest != null){
          lattice.put(dest,new Pair<>(Lattice.TOP,0));
          reg2Ins.put(dest,ins);
        }
      }


    }
    for(var block : node.getBlockList()){
      var allIns = this.allIns.get(block);
      for(var ins : allIns){
        RegItem dest = IRIns.getAllocaReg(ins);
        if(dest != null){
          var useList = ins.getUseRegs();
          for(var reg : useList){
            if(lattice.containsKey(reg)){
              var regins = reg2Ins.get(reg);
              defUse.putIfAbsent(regins,new HashSet<>());
              defUse.get(regins).add(ins);
            }
          }
        }
      }
    }
  }


}
