package Optim;

import Ir.Item.Item;
import Ir.Item.LiteralItem;
import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRArithIns;
import Ir.Node.ins.IRGetEleIns;
import Ir.Node.ins.IRIns;
import Ir.Node.ins.IRRetIns;
import Ir.Node.stmt.IRBlockStmt;
import Utility.error.ErrorBasic;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GVN {
  public void visit(IRRoot node) {
    visit(node.getInitFunc());
    for(var func : node.getFuncList()){
      visit(func);
    }
  }
  HashSet<IRBlockStmt>visitedBlock;
  HashMap<RegItem, Item> replaceMap;
  HashMap<String, RegItem> visitedIns;

  HashMap<RegItem, Pair<RegItem, Integer>> arithOptim;
  void dfs(IRBlockStmt block)
  {
    if(visitedBlock.contains(block))return;
    visitedBlock.add(block);
    for(var pred : block.getPred())
    {
      dfs(pred);
    }
    ArrayList<IRIns> newInsList = new ArrayList<>();
    for(var ins : block.getInsList())
    {
      ins.replaceUse(replaceMap);
      if(ins instanceof IRArithIns arithIns){
        if(arithIns.getOp().equals("sub") && arithIns.getRhs() instanceof LiteralItem literalItem){
          arithIns.setOp("add");
          arithIns.setRhs(new LiteralItem(literalItem.getType(), -literalItem.getValue()));
        }
        if(arithIns.getOp().equals("add") && arithIns.getLhs() instanceof LiteralItem literalItem && arithIns.getRhs() instanceof RegItem regItem){
          arithIns.setLhs(regItem);
          arithIns.setRhs(literalItem);
        }

        String key = "$arith" + arithIns.getOp() + "$" + arithIns.getLhs().toString() + arithIns.getRhs().toString();
        if(visitedIns.containsKey(key)){
          replaceMap.put(arithIns.getDest(), visitedIns.get(key));
        }else{

          if(arithIns.getOp().equals("add") && (arithIns.getLhs() instanceof LiteralItem || arithIns.getRhs() instanceof LiteralItem)){
            if(arithIns.getLhs() instanceof LiteralItem && arithIns.getRhs() instanceof LiteralItem){
              throw new ErrorBasic("it should be sccp's work");
            }
            RegItem regItem;
            LiteralItem literalItem;
            if(arithIns.getLhs() instanceof RegItem){
              regItem = (RegItem)arithIns.getLhs();
              literalItem = (LiteralItem)arithIns.getRhs();
            }else{
              regItem = (RegItem)arithIns.getRhs();
              literalItem = (LiteralItem)arithIns.getLhs();
            }
            if(arithOptim.containsKey(regItem)){
              var res = arithOptim.get(regItem);
              arithIns.setLhs(res.a);
              arithIns.setRhs(new LiteralItem(literalItem.getType(),literalItem.getValue() + res.b));
            }
            if(arithIns.getLhs() instanceof RegItem regItem1 && arithIns.getRhs() instanceof LiteralItem literalItem1){
              arithOptim.put(arithIns.getDest(), new Pair<>(regItem1, literalItem1.getValue()));
            }
            key = "$arith" + arithIns.getOp() + "$" + arithIns.getLhs().toString() + arithIns.getRhs().toString();
          }
          visitedIns.put(key, arithIns.getDest());
          newInsList.add(ins);
        }
      }else if(ins instanceof IRGetEleIns getEleIns) {
        String key = "$getele$" + getEleIns.getSrc();
        if(getEleIns.getIndexList().size() > 2 || getEleIns.getIndexList().isEmpty()){
          throw new RuntimeException("GVM: getele index size > 2");
        }else if(getEleIns.getIndexList().size() == 2){
          key += getEleIns.getIndexList().get(0).toString() + getEleIns.getIndexList().get(1).toString();
        }else {
          key += getEleIns.getIndexList().get(0).toString();
        }
        if (visitedIns.containsKey(key)) {
          replaceMap.put(getEleIns.getDest(), visitedIns.get(key));
        } else {
          visitedIns.put(key, getEleIns.getDest());
          newInsList.add(ins);
        }
      }else{
        newInsList.add(ins);
      }
    }
    block.setInsList(newInsList);
    block.getExitIns().replaceUse(replaceMap);
  }
  void visit(IRFuncDef node)
  {
    visitedBlock = new HashSet<>();
    replaceMap = new HashMap<>();
    visitedIns = new HashMap<>();
    arithOptim = new HashMap<>();
    for(var block : node.getBlockList())
    {
      if(block.getExitIns() instanceof IRRetIns){
        dfs(block);
      }
    }
    for (var block : node.getBlockList()) {
      for (var phi : block.getPhi().values()) {
        phi.replaceUse(replaceMap);
      }
    }
  }
}
