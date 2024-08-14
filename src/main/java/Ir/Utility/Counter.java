package Ir.Utility;

import AST.Node.def.ASTClassDef;
import Ir.Item.Item;
import Ir.Type.IRBaseType;
import Ir.Type.IRClassType;
import Utility.error.ErrorBasic;
import Utility.label.TypeLable;

import java.util.TreeMap;

@lombok.Getter
@lombok.Setter
public class Counter {
  TreeMap<String, Integer> counterMap;
  //only count var num
  int loopIndex;
  int branchIndex;
  int arithIndex;//$arith.index
  int callIndex;//$call.index
  int geteleIndex;//$getele.index
  int allocIndex;//$alloc.index
  int stringIndex;//$string.index
  int loadIndex;//$load.index

  public void addLoopIndex() {
    loopIndex++;
  }
  public void addBranchIndex() {
    branchIndex++;
  }

  public void addArithIndex() {
    arithIndex++;
  }
  public void addCallIndex() {
    callIndex++;
  }
  public void addGeteleIndex() {
    geteleIndex++;
  }
  public void addAllocIndex() {
    allocIndex++;
  }
  public void addStringIndex() {
    stringIndex++;
  }
  public void addLoadIndex() {
    loadIndex++;
  }

  TreeMap<String, Integer> typeSizeMap;
  TreeMap<String, Integer> classMemMap;

  TreeMap<String, Item>itemTable;

  //the reg name will like   @[index][key]  %[index][key]
  public void addItem(String key, Item item) {
    if(itemTable.containsKey(key)) {
      throw new ErrorBasic("item already exist");
    }
    itemTable.put(key, item);
  }
  public Item queryItem(String key) {
    if(itemTable.containsKey(key)) {
      return itemTable.get(key);
    } else {
      return null;
    }
  }

  public Counter() {
    counterMap = new TreeMap<>();
    typeSizeMap = new TreeMap<>();
  }
  public void addClassMem(ASTClassDef node) {
    int index = 0;
    for (var mem : node.getVarDefs()) {
      var typename = node.getLabel().getName() + "." + mem.getLabel().getName();
      if(classMemMap.containsKey(typename)) {
        throw new ErrorBasic("class member already exist");
      }
      classMemMap.put(typename, ++index);
    }
  }
  public int queryIndex(String key) {
    if (counterMap.containsKey(key)) {
      return counterMap.get(key);
    } else {
      return 0;
    }
  }
  public void addIndex(String key, int index) {
    counterMap.put(key, index);
  }
  public void addIndex(String key) {
    if (counterMap.containsKey(key)) {
      counterMap.put(key, counterMap.get(key) + 1);
    } else {
      counterMap.put(key, 1);
    }
  }
  public int addTypeSize(String key, IRBaseType type) {
    if(typeSizeMap.containsKey(key)) {
      throw new ErrorBasic("type size already exist");
      //      return typeSizeMap.get(key);
    }
    int size = 0;
    if(type instanceof IRClassType) {
      for (IRBaseType member : ((IRClassType) type).getMemberVariables()) {
        if(member.getName().equals("i32")) {
          size += 4;
        }else if(member.getName().equals("i1")) {
          size += 1;
        }else if(member.getName().equals("ptr")) {
          size += 8;
        }else{
          throw new ErrorBasic("unknown type");
        }
      }
    }
    typeSizeMap.put(key, size);
    return size;
  }
}