package Allocator;

import Ir.Item.RegItem;
import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRIns;
import Ir.Node.ins.IRPhiIns;
import Ir.Node.stmt.IRBlockStmt;
import Ir.Utility.RegAddr;
import Utility.error.ErrorBasic;
import org.antlr.v4.runtime.misc.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LifeTimeMonitor extends CostEvaluator{
  public Set<IRBlockStmt> scannedBlock;


  public HashMap<String, IRBlockStmt> lable2Block;
  public HashMap<IRIns,IRIns> preIns;
  public HashMap<IRBlockStmt, IRIns> firstIns;
  public HashMap<IRBlockStmt, IRIns> lastIns;
  public ArrayList<Pair<RegItem,RegItem>> Edge;


  public void addInterference(RegItem a, RegItem b)
  {
    a.addDegree();
    b.addDegree();
    Edge.add(new Pair<>(a,b));
    Edge.add(new Pair<>(b,a));
  }
  public void adduse(IRIns ins)
  {
    var use = ins.getUseRegs();
    for(var reg : use){
      if(!this.use.containsKey(reg)){
        this.use.put(reg,new ArrayList<>());
      }
      this.use.get(reg).add(ins);
    }
  }
  public void adddef(IRIns ins)
  {
    var def = ins.getDefRegs();
    for(var reg : def){
      if(this.def.containsKey(reg)){
        throw new ErrorBasic("LifeTimeMonitor visit IRFuncDef error");
      }
      this.def.put(reg,ins);
    }
  }
  public void visit(IRFuncDef node)
  {
    def = new HashMap<>();
    use = new HashMap<>();
    ins2Block = new HashMap<>();
    lable2Block = new HashMap<>();
    preIns = new HashMap<>();
    firstIns = new HashMap<>();
    lastIns = new HashMap<>();
    Edge = new ArrayList<>();
    var2index = new HashMap<>();
    index2var = new HashMap<>();
    for(var reg : node.getParamList()){
      def.put(reg,null);
    }
    for(var block : node.getBlockList()){
      IRIns lasIns = null;
      block.setPhiDef(new HashSet<>());
      lable2Block.put(block.getLableName(),block);
      for(var entry : block.getPhi().entrySet()){
        var ins = entry.getValue();
        block.getPhiDef().add(ins.getDest());
        ins2Block.put(ins,block);
        adddef(ins);
        adduse(ins);
//        preIns.put(ins,lasIns);
//        if(lasIns == null){
//          firstIns.put(block,ins);
//        }
//        lasIns = ins;
      }
      for(var ins : block.getInsList()){
        ins2Block.put(ins,block);
        adddef(ins);
        adduse(ins);
        preIns.put(ins,lasIns);
        if(lasIns == null){
          firstIns.put(block,ins);
        }
        lasIns = ins;
      }
      var exitIns = block.getExitIns();
      ins2Block.put(exitIns,block);
      adddef(exitIns);
      adduse(exitIns);
      preIns.put(exitIns,lasIns);
      if(lasIns == null){
        firstIns.put(block,exitIns);
      }
      lastIns.put(block,exitIns);
    }
    int cnt = 0;
    for(var entry : def.entrySet()){
      scannedBlock = new java.util.HashSet<>();

      var reg = entry.getKey();
      var2index.put(reg,cnt);
      index2var.put(cnt,reg);
      cnt++;
      var ins = entry.getValue();
      if(!use.containsKey(reg)){
        reg.setRegAddr(new RegAddr(-1));
        continue;
      }
      var useList = use.get(reg);
      for(var useIns : useList){
        if(useIns instanceof IRPhiIns){
          String blockname = ((IRPhiIns) useIns).findBlock(reg);
          if(blockname == null){
            throw new ErrorBasic("LifeTimeMonitor visit IRFuncDef error");
          }
          scanBlock(lable2Block.get(blockname),reg);
        }else{
          scanLiveIn(useIns,reg);
        }
      }

    }
//    return Edge;
  }
  public void scanBlock(IRBlockStmt block, RegItem v){
    if(scannedBlock.contains(block)){
      return;
    }
    scannedBlock.add(block);
    scanLiveOut(lastIns.get(block),v);
  }
  public void scanLiveIn(IRIns ins, RegItem v){
    ins.addLiveIn(v);
    var pre = preIns.get(ins);
    if(pre == null){
      //first handle the PHI
      var block = ins2Block.get(ins);
      if(block.getPhiDef().contains(v)){
        //find the def, then handle and return
        for(var entry : block.getPhi().entrySet()){
          var phi = entry.getValue();
          //the phi assignation happens at the very same time
          phi.addLiveOut(v);
        }
        return;
      }
      for(var pred : block.getPred()) {
        scanBlock(pred, v);
      }
    }else{
      scanLiveOut(pre,v);
    }
  }
  public void scanLiveOut(IRIns ins, RegItem v){
    ins.addLiveOut(v);
    var defs = ins.getDefRegs();
    boolean flag = false;
    for(var def : defs){
      if(def == v){
        flag = true;
        continue;
      }
      addInterference(def,v);
    }
    if(!flag){
      scanLiveIn(ins,v);
    }
  }


}
