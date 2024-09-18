package Ir.Node.ins;

import Ir.IRVisitor;
import Ir.Item.Item;
import Ir.Item.RegItem;
import Utility.error.ErrorBasic;

import java.util.ArrayList;
import java.util.HashMap;

@lombok.Getter
@lombok.Setter
public class IRArithIns extends IRIns {
  Item lhs, rhs;
  RegItem dest;
  String op;
  public IRArithIns()
  {
    this.lhs = null;
    this.rhs = null;
    this.dest = null;
    this.op = null;
  }
  public IRArithIns(Item lhs, Item rhs, RegItem dest, String op) {
    this.lhs = lhs;
    this.rhs = rhs;
    this.dest = dest;
    this.op = null;
    if(op.equals("+")){
      this.op = "add";
    }else if(op.equals("-")){
      this.op = "sub";
    }else if(op.equals("*")){
      this.op = "mul";
    }else if(op.equals("/")){
      this.op = "sdiv";
    }else if(op.equals("%")){
      this.op = "srem";
    }else if(op.equals("<<")){
      this.op = "shl";
    }else if(op.equals(">>")){
      this.op = "ashr";
    }else if(op.equals("&")){
      this.op = "and";
    }else if(op.equals("|")){
      this.op = "or";
    }else if(op.equals("^")){
      this.op = "xor";
    }else if(op.equals("==")){
      this.op = "eq";
    }else if(op.equals("!=")){
      this.op = "ne";
    }else if(op.equals("<")){
      this.op = "slt";
    }else if(op.equals(">")){
      this.op = "sgt";
    }else if(op.equals("<=")){
      this.op = "sle";
    }else if(op.equals(">=")){
      this.op = "sge";
    } else{
      this.op = op;
//      throw new ErrorBasic("Invalid op in IRArithIns");
    }
  }
  public static int calc(String op, int lhs, int rhs){
    if(op.equals("add")){
      return lhs + rhs;
    }else if(op.equals("sub")){
      return lhs - rhs;
    }else if(op.equals("mul")){
      return lhs * rhs;
    }else if(op.equals("sdiv")){
      return lhs / rhs;
    }else if(op.equals("srem")){
      return lhs % rhs;
    }else if(op.equals("shl")){
      return lhs << rhs;
    }else if(op.equals("ashr")){
      return lhs >> rhs;
    }else if(op.equals("and")){
      return lhs & rhs;
    }else if(op.equals("or")){
      return lhs | rhs;
    }else if(op.equals("xor")){
      return lhs ^ rhs;
    } else if (op.equals("eq")) {
      return lhs == rhs ? 1 : 0;
    } else if (op.equals("ne")) {
      return lhs != rhs ? 1 : 0;
    } else if (op.equals("slt")) {
      return lhs < rhs ? 1 : 0;
    } else if (op.equals("sgt")) {
      return lhs > rhs ? 1 : 0;
    } else if (op.equals("sle")) {
      return lhs <= rhs ? 1 : 0;
    } else if (op.equals("sge")) {
      return lhs >= rhs ? 1 : 0;
    } else{
      throw new ErrorBasic("Invalid op in IRArithIns");
    }
  }
  @Override
  public <T> T accept(IRVisitor<T> visitor) throws ErrorBasic {
    return visitor.visit(this);
  }
  @Override
  public String toString() {
    if(!lhs.getType().getName().equals(rhs.getType().getName())){
      throw new ErrorBasic("lhs and rhs type mismatch in IRArithIns");
    }
    if(op.equals("eq") || op.equals("ne") || op.equals("slt") || op.equals("sgt") || op.equals("sle") || op.equals("sge")){

      return dest.getNameReg() + " = icmp " + op + " " + lhs.getType().toString() + " " + lhs.getName() + ", " + rhs.getName();
    }
    return dest.getNameReg() + " = " + op + " " + lhs.getType().toString() + " " + lhs.getName() + ", " + rhs.getName();
  }
  @Override
  public void replaceUse(HashMap<RegItem, Item> map) {
    if(map.containsKey(lhs)){
      lhs = map.get(lhs);
    }
    if(map.containsKey(rhs)){
      rhs = map.get(rhs);
    }
  }
  @Override
  public void replaceDef(RegItem newReg) {
    dest = newReg;
  }
  @Override
  public ArrayList<RegItem> getUseRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    if(lhs instanceof RegItem){
      ret.add((RegItem)lhs);
    }
    if(rhs instanceof RegItem){
      ret.add((RegItem)rhs);
    }
    return ret;
  }
  @Override
  public ArrayList<RegItem> getDefRegs() {
    ArrayList<RegItem> ret = new ArrayList<>();
    ret.add(dest);
    return ret;
  }
  @Override
  public IRArithIns copy() {
    return new IRArithIns(lhs, rhs, dest, op);
  }
}
