package Scope;

import Utility.label.FuncLable;
import Scope.BasicClass;

public class BasicFunc {
  public FuncLable print_ = new FuncLable("print", BasicClass.void_, BasicClass.string_);
  public FuncLable println_ = new FuncLable("println", BasicClass.void_, BasicClass.string_);
  public FuncLable PrintInt_ = new FuncLable("PrintInt", BasicClass.void_, BasicClass.int_);
  public FuncLable PrintlnInt_ = new FuncLable("PrintlnInt", BasicClass.void_, BasicClass.int_);
  public FuncLable getString_ = new FuncLable("getString", BasicClass.string_);
  public FuncLable getInt_ = new FuncLable("getInt", BasicClass.int_);
  public FuncLable toString_ = new FuncLable("toString", BasicClass.string_, BasicClass.int_);

  public FuncLable[] BuildInFunc = {print_, println_, PrintInt_, PrintlnInt_, getString_, getInt_, toString_};
}


