package Scope;

import Ir.Type.IRBaseType;
import Ir.Utility.IRLable;
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

  public static FuncLable BuildInArraySize_ = new FuncLable("$buildInArraySize", int_);

  public static FuncLable print_ = new FuncLable("print", void_, string_);
  public static FuncLable println_ = new FuncLable("println", void_, string_);
  public static FuncLable printInt_ = new FuncLable("printInt", void_, int_);
  public static FuncLable printlnInt_ = new FuncLable("printlnInt", void_, int_);
  public static FuncLable getString_ = new FuncLable("getString", string_);
  public static FuncLable getInt_ = new FuncLable("getInt", int_);
  public static FuncLable toString_ = new FuncLable("toString", string_, int_);

  public static FuncLable length_ = new FuncLable("string.length", int_);
  public static FuncLable substring_ = new FuncLable("string.substring", string_, int_, int_);
  public static FuncLable parseInt_ = new FuncLable("string.parseInt", int_);
  public static FuncLable ord_ = new FuncLable("string.ord", int_, int_);
  public static FuncLable[] BuildInFunc = {BuildInArraySize_, print_, println_, printInt_, printlnInt_, getString_, getInt_, toString_, length_, substring_, parseInt_, ord_};



  public static IRLable print_lable = new IRLable("print");
  public static IRLable println_lable = new IRLable("println");
  public static IRLable printInt_lable = new IRLable("printInt");
  public static IRLable printlnInt_lable = new IRLable("printlnInt");
  public static IRLable getString_lable = new IRLable("getString");
  public static IRLable getInt_lable = new IRLable("getInt");
  public static IRLable toString_lable = new IRLable("toString");

  public static IRLable length_lable = new IRLable("string.length");
  public static IRLable substring_lable = new IRLable("string.substring");
  public static IRLable parseInt_lable = new IRLable("string.parseInt");
  public static IRLable ord_lable = new IRLable("string.ord");
  public static IRLable[] BuildInFuncLable = {print_lable, println_lable, printInt_lable, printlnInt_lable, getString_lable, getInt_lable, toString_lable, length_lable, substring_lable, parseInt_lable, ord_lable};


  public static IRBaseType intType = new IRBaseType(int_);
  public static IRBaseType ptrType = new IRBaseType("ptr");
  public static IRBaseType boolType = new IRBaseType(bool_);
  public static IRBaseType voidType = new IRBaseType(void_);
}


