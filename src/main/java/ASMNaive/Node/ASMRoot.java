package ASMNaive.Node;

import ASMNaive.Node.def.ASMFuncDef;
import ASMNaive.Node.def.ASMStrDef;
import ASMNaive.Node.def.ASMVarDef;

import java.util.ArrayList;

@lombok.Getter
@lombok.Setter
public class ASMRoot extends ASMNode {
  protected ArrayList<ASMStrDef> strDefs;
  protected ArrayList<ASMFuncDef> funcDefs;
  protected ArrayList<ASMVarDef>  varDefs;

  public ASMRoot()
  {
    varDefs = new ArrayList<>();
    funcDefs = new ArrayList<>();
    strDefs = new ArrayList<>();
  }
  @Override
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append("  .data\n");
    for (ASMVarDef varDef : varDefs)
    {
      sb.append(varDef.toString());
      sb.append("\n");
    }
    sb.append("  .rodata\n");
    for (ASMStrDef strDef : strDefs)
    {
      sb.append(strDef.toString());
      sb.append("\n");
    }
    sb.append("  .text\n");
    for (ASMFuncDef funcDef : funcDefs)
    {
      sb.append(funcDef.toString());
      sb.append("\n");
    }
    return sb.toString();
  }

}
