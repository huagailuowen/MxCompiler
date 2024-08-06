package Scope;

import Utility.label.ClassLable;

public class BasicClass {
  public ClassLable int_ = new ClassLable("int");
  public ClassLable string_ = new ClassLable("string");
  public ClassLable bool_ = new ClassLable("bool");
  public ClassLable void_ = new ClassLable("void");
  public ClassLable null_ = new ClassLable("null");
  public ClassLable this_ = new ClassLable("this");

  public ClassLable[] BuildInClass= {int_,string_,bool_,void_,null_,this_}; 
}
