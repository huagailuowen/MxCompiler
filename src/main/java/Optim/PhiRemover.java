package Optim;

import Ir.Node.IRRoot;
import Ir.Node.def.IRFuncDef;
import Ir.Node.ins.IRJmpIns;
import Ir.Node.ins.IRMoveIns;
import Ir.Node.ins.IRPhiIns;
import Ir.Node.stmt.IRBlockStmt;

import java.util.ArrayList;
import java.util.TreeMap;

public class PhiRemover {
  private static int phiCount = 0;
  public void visit(IRRoot node)
  {
    visit(node.getInitFunc());
    for(var func : node.getFuncList()){
      visit(func);
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
    }
    for(var block : node.getBlockList()){
      if(block.getPhi().isEmpty()){
        continue;
      }
      for(var entry : block.getPhi().entrySet()){
        var phi = entry.getValue();
        var var = phi.getDest();
        for(var pair : phi.getValueList()){
          var predBlock = lable2block.get(pair.b);
          if(block.getReplacePred().containsKey(predBlock)){
            predBlock = block.getReplacePred().get(predBlock);
          }
          if(predBlock.isAbandoned()){
            throw new Error("phi remove error");
          }
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
            insertblock.addIns(new IRMoveIns(pair.a,var));
            insertBlock.add(insertblock);
            block.getReplacePred().put(predBlock, insertblock);
          }else {
            predBlock.addIns(new IRMoveIns(pair.a, var));
          }
        }

      }
    }
    node.getBlockList().addAll(insertBlock);
  }
}
