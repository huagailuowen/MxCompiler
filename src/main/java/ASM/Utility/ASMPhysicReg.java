package ASM.Utility;

import ASM.Item.ASMReg;

import java.util.HashMap;

public interface ASMPhysicReg {


  //these register can not be allocated
  public static ASMReg zero= new ASMReg("zero", 0,-1);
  public static ASMReg sp= new ASMReg("sp", 2,-1);
  public static ASMReg gp= new ASMReg("gp", 3,-1);
  public static ASMReg tp= new ASMReg("tp", 4,-1);
  public static ASMReg t0= new ASMReg("t0", 5,-1);
  public static ASMReg t1= new ASMReg("t1", 6,-1);
  public static ASMReg t2= new ASMReg("t2", 7,-1);
  public static ASMReg t5= new ASMReg("t5", 30,-1);//for addi and sw's big number
  //24 available register
  public static ASMReg t6= new ASMReg("t6", 31,23);
  public static ASMReg ra= new ASMReg("ra", 1,24);
  public static ASMReg s0= new ASMReg("s0", 8,11);
  public static ASMReg s1= new ASMReg("s1", 9,12);
  public static ASMReg a0= new ASMReg("a0", 10,1);
  public static ASMReg a1= new ASMReg("a1", 11,2);
  public static ASMReg a2= new ASMReg("a2", 12,3);
  public static ASMReg a3= new ASMReg("a3", 13,4);
  public static ASMReg a4= new ASMReg("a4", 14,5);
  public static ASMReg a5= new ASMReg("a5", 15,6);
  public static ASMReg a6= new ASMReg("a6", 16,7);
  public static ASMReg a7= new ASMReg("a7", 17,8);
  public static ASMReg s2= new ASMReg("s2", 18,13);
  public static ASMReg s3= new ASMReg("s3", 19,14);
  public static ASMReg s4= new ASMReg("s4", 20,15);
  public static ASMReg s5= new ASMReg("s5", 21,16);
  public static ASMReg s6= new ASMReg("s6", 22,17);
  public static ASMReg s7= new ASMReg("s7", 23,18);
  public static ASMReg s8= new ASMReg("s8", 24,19);
  public static ASMReg s9= new ASMReg("s9", 25,20);
  public static ASMReg s10= new ASMReg("s10", 26,21);
  public static ASMReg s11= new ASMReg("s11", 27,22);
  public static ASMReg t3= new ASMReg("t3", 28,9);
  public static ASMReg t4= new ASMReg("t4", 29,10);

  public static ASMReg[] availableReg = {ra, s0, s1, a0, a1, a2, a3, a4, a5, a6, a7, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, t3, t4, t6, t0, zero};

  public static ASMReg[] calleeReg ={s0, s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11};
  public static ASMReg[] callerReg ={a0, a1, a2, a3, a4, a5, a6, a7, ra, t3, t4, t6};

}
