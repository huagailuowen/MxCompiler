package ASMNaive.Utility;

import ASMNaive.Item.ASMReg;

public interface ASMPhysicReg {
  public static ASMReg sp= new ASMReg("sp", 2);
  public static ASMReg gp= new ASMReg("gp", 3);
  public static ASMReg tp= new ASMReg("tp", 4);
  public static ASMReg t0= new ASMReg("t0", 5);
  public static ASMReg t1= new ASMReg("t1", 6);
  public static ASMReg t2= new ASMReg("t2", 7);
  public static ASMReg s0= new ASMReg("s0", 8);
  public static ASMReg s1= new ASMReg("s1", 9);
  public static ASMReg t3= new ASMReg("t3", 28);
  public static ASMReg t4= new ASMReg("t4", 29);
  public static ASMReg t5= new ASMReg("t5", 30);
  public static ASMReg t6= new ASMReg("t6", 31);
  public static ASMReg a0= new ASMReg("a0", 10);
  public static ASMReg a1= new ASMReg("a1", 11);
  public static ASMReg a2= new ASMReg("a2", 12);
  public static ASMReg a3= new ASMReg("a3", 13);
  public static ASMReg a4= new ASMReg("a4", 14);
  public static ASMReg a5= new ASMReg("a5", 15);
  public static ASMReg a6= new ASMReg("a6", 16);
  public static ASMReg a7= new ASMReg("a7", 17);

  public static ASMReg zero= new ASMReg("zero", 0);
  public static ASMReg ra= new ASMReg("ra", 1);
}
