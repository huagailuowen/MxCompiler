package Scope;

import Utility.label.FuncLable;
import Utility.label.TypeLable;

public class BasicClassFunc {
  public static TypeLable int_ = new TypeLable("int");
  public static TypeLable string_ = new TypeLable("string");
  public static TypeLable bool_ = new TypeLable("bool");
  public static TypeLable void_ = new TypeLable("void");
  public static TypeLable null_ = new TypeLable("null");
  public static TypeLable this_ = new TypeLable("this");

  public static TypeLable[] BuildInClass= {int_,string_,bool_,void_,null_,this_};

  public static FuncLable BuildInArraySize_ = new FuncLable("@buildInArraySize", int_);

  public static FuncLable print_ = new FuncLable("print", void_, string_);
  public static FuncLable println_ = new FuncLable("println", void_, string_);
  public static FuncLable PrintInt_ = new FuncLable("printInt", void_, int_);
  public static FuncLable PrintlnInt_ = new FuncLable("printlnInt", void_, int_);
  public static FuncLable getString_ = new FuncLable("getString", string_);
  public static FuncLable getInt_ = new FuncLable("getInt", int_);
  public static FuncLable toString_ = new FuncLable("toString", string_, int_);

  public static FuncLable length_ = new FuncLable("string.length", int_);
  public static FuncLable substring_ = new FuncLable("string.substring", string_, int_, int_);
  public static FuncLable parseInt_ = new FuncLable("string.parseInt", int_);
  public static FuncLable ord_ = new FuncLable("string.ord", int_, int_);
  public static FuncLable[] BuildInFunc = {BuildInArraySize_, print_, println_, PrintInt_, PrintlnInt_, getString_, getInt_, toString_, length_, substring_, parseInt_, ord_};

}


