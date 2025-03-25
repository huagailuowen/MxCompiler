  .data
 .align 4
@asciiTable.0:
  .word 0
 .align 4
@MAXCHUNK.0:
  .word 0
 .align 4
@MAXLENGTH.0:
  .word 0
 .align 4
@chunks.0:
  .word 0
 .align 4
@inputBuffer.0:
  .word 0
 .align 4
@outputBuffer.0:
  .word 0
  .rodata
@string.1:
  .string "\00"
@string.0:
  .string " !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\00"
@string.3:
  .string "**:\00"
@string.4:
  .string "Invalid input\00"
@string.5:
  .string "Not Found!\00"
@string.2:
  .string "nChunk > MAXCHUNK!\00"
  .text
  .globl __init__
__init__:
  addi   sp, sp, -224
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  addi   t0, sp, 40
  sw    t0, 36(sp)
  addi   t0, sp, 48
  sw    t0, 44(sp)
  addi   t0, sp, 56
  sw    t0, 52(sp)
  addi   t0, sp, 64
  sw    t0, 60(sp)
  

  la    t6, @string.0
  mv    t1, t6
  la    t6, @asciiTable.0
  mv    t0, t6
  sw    t1, 0(t0)
  li    t1, 100
  la    t6, @MAXCHUNK.0
  mv    t0, t6
  sw    t1, 0(t0)
  la    t6, @MAXCHUNK.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 68(sp)
  lw    t1, 68(sp)
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 72(sp)
  lw    t1, 72(sp)
  li    t2, 64
  mul    t0, t1, t2
  sw    t0, 76(sp)
  lw    t1, 76(sp)
  li    t2, 16
  sub    t0, t1, t2
  sw    t0, 80(sp)
  lw    t1, 80(sp)
  la    t6, @MAXLENGTH.0
  mv    t0, t6
  sw    t1, 0(t0)
  la    t6, @MAXCHUNK.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 84(sp)
  addi   sp, sp, -16
  lw    t0, 100(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 4
  mv    a1, t0
  sw    t0, 4(sp)
  call  __malloc_array
  addi   sp, sp, 16
  sw    a0, 88(sp)
  lw    t1, 88(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  li    t1, 0
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  j     loop.20.0.condition

loop.20.0.condition:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 92(sp)
  lw    t1, 92(sp)
  lw    t2, 84(sp)
  slt    t0, t1, t2
  sw    t0, 96(sp)
  lw    t0, 96(sp)
  bnez  t0, bneqzlable.53
  j     loop.20.0.end
bneqzlable.53:
  j     loop.20.0.body

loop.20.0.body:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 100(sp)
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 104(sp)
  lw    t1, 104(sp)
  lw    t2, 100(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 108(sp)
  addi   sp, sp, -16
  li    t0, 80
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 4
  mv    a1, t0
  sw    t0, 4(sp)
  call  __malloc_array
  addi   sp, sp, 16
  sw    a0, 112(sp)
  lw    t1, 112(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t1, 112(sp)
  lw    t0, 108(sp)
  sw    t1, 0(t0)
  li    t1, 0
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.20.1.condition

loop.20.1.condition:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 116(sp)
  lw    t1, 116(sp)
  li    t2, 80
  slt    t0, t1, t2
  sw    t0, 120(sp)
  lw    t0, 120(sp)
  bnez  t0, bneqzlable.54
  j     loop.20.1.end
bneqzlable.54:
  j     loop.20.1.body

loop.20.1.body:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 124(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 128(sp)
  lw    t1, 128(sp)
  lw    t2, 124(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 132(sp)
  li    t1, 0
  lw    t0, 132(sp)
  sw    t1, 0(t0)
  j     loop.20.1.update

loop.20.1.update:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 136(sp)
  lw    t1, 136(sp)
  addi   t0, t1, 1
  sw    t0, 140(sp)
  lw    t1, 140(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.20.1.condition

loop.20.1.end:
  j     loop.20.0.update

loop.20.0.update:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 144(sp)
  lw    t1, 144(sp)
  addi   t0, t1, 1
  sw    t0, 148(sp)
  lw    t1, 148(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  j     loop.20.0.condition

loop.20.0.end:
  lw    t1, 88(sp)
  la    t6, @chunks.0
  mv    t0, t6
  sw    t1, 0(t0)
  la    t6, @MAXLENGTH.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 152(sp)
  addi   sp, sp, -16
  lw    t0, 168(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 4
  mv    a1, t0
  sw    t0, 4(sp)
  call  __malloc_array
  addi   sp, sp, 16
  sw    a0, 156(sp)
  lw    t1, 156(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  li    t1, 0
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.21.0.condition

loop.21.0.condition:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 160(sp)
  lw    t1, 160(sp)
  lw    t2, 152(sp)
  slt    t0, t1, t2
  sw    t0, 164(sp)
  lw    t0, 164(sp)
  bnez  t0, bneqzlable.55
  j     loop.21.0.end
bneqzlable.55:
  j     loop.21.0.body

loop.21.0.body:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 168(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 172(sp)
  lw    t1, 172(sp)
  lw    t2, 168(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 176(sp)
  li    t1, 0
  lw    t0, 176(sp)
  sw    t1, 0(t0)
  j     loop.21.0.update

loop.21.0.update:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 180(sp)
  lw    t1, 180(sp)
  addi   t0, t1, 1
  sw    t0, 184(sp)
  lw    t1, 184(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.21.0.condition

loop.21.0.end:
  lw    t1, 156(sp)
  la    t6, @inputBuffer.0
  mv    t0, t6
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 5
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 4
  mv    a1, t0
  sw    t0, 4(sp)
  call  __malloc_array
  addi   sp, sp, 16
  sw    a0, 188(sp)
  lw    t1, 188(sp)
  lw    t0, 52(sp)
  sw    t1, 0(t0)
  li    t1, 0
  lw    t0, 60(sp)
  sw    t1, 0(t0)
  j     loop.22.0.condition

loop.22.0.condition:
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 192(sp)
  lw    t1, 192(sp)
  li    t2, 5
  slt    t0, t1, t2
  sw    t0, 196(sp)
  lw    t0, 196(sp)
  bnez  t0, bneqzlable.56
  j     loop.22.0.end
bneqzlable.56:
  j     loop.22.0.body

loop.22.0.body:
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 200(sp)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 204(sp)
  lw    t1, 204(sp)
  lw    t2, 200(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 208(sp)
  li    t1, 0
  lw    t0, 208(sp)
  sw    t1, 0(t0)
  j     loop.22.0.update

loop.22.0.update:
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 212(sp)
  lw    t1, 212(sp)
  addi   t0, t1, 1
  sw    t0, 216(sp)
  lw    t1, 216(sp)
  lw    t0, 60(sp)
  sw    t1, 0(t0)
  j     loop.22.0.condition

loop.22.0.end:
  lw    t1, 188(sp)
  la    t6, @outputBuffer.0
  mv    t0, t6
  sw    t1, 0(t0)
  lw    ra, 0(sp)
  addi   sp, sp, 224
  ret   


  .globl hex2int
hex2int:
  addi   sp, sp, -256
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  addi   t0, sp, 40
  sw    t0, 36(sp)
  addi   t0, sp, 48
  sw    t0, 44(sp)
  addi   t0, sp, 56
  sw    t0, 52(sp)
  addi   t0, sp, 64
  sw    t0, 60(sp)
  

  lw    t1, 256(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  li    t1, 0
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 68(sp)
  li    t1, 0
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  j     loop.0.condition

loop.0.condition:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 72(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 76(sp)
  addi   sp, sp, -16
  lw    t0, 92(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  __string_length
  addi   sp, sp, 16
  sw    a0, 80(sp)
  lw    t1, 72(sp)
  lw    t2, 80(sp)
  slt    t0, t1, t2
  sw    t0, 84(sp)
  lw    t0, 84(sp)
  bnez  t0, bneqzlable.57
  j     loop.0.end
bneqzlable.57:
  j     loop.0.body

loop.0.body:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 88(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 92(sp)
  addi   sp, sp, -16
  lw    t0, 104(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 108(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  __string_ord
  addi   sp, sp, 16
  sw    a0, 96(sp)
  lw    t1, 96(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 100(sp)
  lw    t1, 100(sp)
  li    t2, 48
  slt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 104(sp)
  lw    t1, 104(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  lw    t0, 104(sp)
  bnez  t0, bneqzlable.58
  j     arith.2.end
bneqzlable.58:
  j     arith.2.rhs

arith.2.rhs:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 108(sp)
  lw    t1, 108(sp)
  li    t2, 57
  sgt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 112(sp)
  lw    t1, 112(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     arith.2.end

arith.2.end:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 116(sp)
  lw    t0, 116(sp)
  bnez  t0, bneqzlable.59
  j     if.0.else
bneqzlable.59:
  j     if.0.then

if.0.then:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 120(sp)
  lw    t1, 120(sp)
  li    t2, 16
  mul    t0, t1, t2
  sw    t0, 124(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 128(sp)
  lw    t1, 124(sp)
  lw    t2, 128(sp)
  add    t0, t1, t2
  sw    t0, 132(sp)
  lw    t1, 132(sp)
  li    t2, 48
  sub    t0, t1, t2
  sw    t0, 136(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 140(sp)
  lw    t1, 136(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     if.0.end

if.0.else:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 144(sp)
  lw    t1, 144(sp)
  li    t2, 65
  slt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 148(sp)
  lw    t1, 148(sp)
  lw    t0, 52(sp)
  sw    t1, 0(t0)
  lw    t0, 148(sp)
  bnez  t0, bneqzlable.60
  j     arith.9.end
bneqzlable.60:
  j     arith.9.rhs

arith.9.rhs:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 152(sp)
  lw    t1, 152(sp)
  li    t2, 70
  sgt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 156(sp)
  lw    t1, 156(sp)
  lw    t0, 52(sp)
  sw    t1, 0(t0)
  j     arith.9.end

arith.9.end:
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 160(sp)
  lw    t0, 160(sp)
  bnez  t0, bneqzlable.61
  j     if.1.else
bneqzlable.61:
  j     if.1.then

if.1.then:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 164(sp)
  lw    t1, 164(sp)
  li    t2, 16
  mul    t0, t1, t2
  sw    t0, 168(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 172(sp)
  lw    t1, 168(sp)
  lw    t2, 172(sp)
  add    t0, t1, t2
  sw    t0, 176(sp)
  lw    t1, 176(sp)
  li    t2, 65
  sub    t0, t1, t2
  sw    t0, 180(sp)
  lw    t1, 180(sp)
  addi   t0, t1, 10
  sw    t0, 184(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 188(sp)
  lw    t1, 184(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     if.1.end

if.1.else:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 192(sp)
  lw    t1, 192(sp)
  li    t2, 97
  slt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 196(sp)
  lw    t1, 196(sp)
  lw    t0, 60(sp)
  sw    t1, 0(t0)
  lw    t0, 196(sp)
  bnez  t0, bneqzlable.62
  j     arith.17.end
bneqzlable.62:
  j     arith.17.rhs

arith.17.rhs:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 200(sp)
  lw    t1, 200(sp)
  li    t2, 102
  sgt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 204(sp)
  lw    t1, 204(sp)
  lw    t0, 60(sp)
  sw    t1, 0(t0)
  j     arith.17.end

arith.17.end:
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 208(sp)
  lw    t0, 208(sp)
  bnez  t0, bneqzlable.63
  j     if.2.else
bneqzlable.63:
  j     if.2.then

if.2.then:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 212(sp)
  lw    t1, 212(sp)
  li    t2, 16
  mul    t0, t1, t2
  sw    t0, 216(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 220(sp)
  lw    t1, 216(sp)
  lw    t2, 220(sp)
  add    t0, t1, t2
  sw    t0, 224(sp)
  lw    t1, 224(sp)
  li    t2, 97
  sub    t0, t1, t2
  sw    t0, 228(sp)
  lw    t1, 228(sp)
  addi   t0, t1, 10
  sw    t0, 232(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 236(sp)
  lw    t1, 232(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     if.2.end

if.2.else:
  li    t1, 0
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.hex2int.end

if.2.end:
  j     if.1.end

if.1.end:
  j     if.0.end

if.0.end:
  j     loop.0.update

loop.0.update:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 240(sp)
  lw    t1, 240(sp)
  addi   t0, t1, 1
  sw    t0, 244(sp)
  lw    t1, 244(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  j     loop.0.condition

loop.0.end:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 248(sp)
  lw    t1, 248(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.hex2int.end

func.hex2int.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 252(sp)
  lw    a0, 252(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 256
  ret   


  .globl int2chr
int2chr:
  addi   sp, sp, -80
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  

  lw    t1, 80(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 28(sp)
  lw    t1, 28(sp)
  li    t2, 32
  slt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 32(sp)
  lw    t1, 32(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 32(sp)
  bnez  t0, bneqzlable.64
  j     arith.26.end
bneqzlable.64:
  j     arith.26.rhs

arith.26.rhs:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 36(sp)
  lw    t1, 36(sp)
  li    t2, 126
  sgt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 40(sp)
  lw    t1, 40(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  j     arith.26.end

arith.26.end:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 44(sp)
  lw    t0, 44(sp)
  bnez  t0, bneqzlable.65
  j     if.3.else
bneqzlable.65:
  j     if.3.then

if.3.then:
  la    t6, @asciiTable.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 48(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 52(sp)
  lw    t1, 52(sp)
  li    t2, 32
  sub    t0, t1, t2
  sw    t0, 56(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 60(sp)
  lw    t1, 60(sp)
  li    t2, 31
  sub    t0, t1, t2
  sw    t0, 64(sp)
  addi   sp, sp, -16
  lw    t0, 64(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 72(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  lw    t0, 80(sp)
  mv    a2, t0
  sw    t0, 8(sp)
  call  __string_substring
  addi   sp, sp, 16
  sw    a0, 68(sp)
  lw    t1, 68(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.int2chr.end

if.3.else:
  j     if.3.end

if.3.end:
  la    t6, @string.1
  mv    t1, t6
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.int2chr.end

func.int2chr.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 72(sp)
  lw    a0, 72(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 80
  ret   


  .globl toStringHex
toStringHex:
  addi   sp, sp, -160
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  addi   t0, sp, 40
  sw    t0, 36(sp)
  

  lw    t1, 160(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  la    t6, @string.1
  mv    t1, t6
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 44(sp)
  li    t1, 28
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.1.condition

loop.1.condition:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 48(sp)
  lw    t1, 48(sp)
  li    t2, 0
  slt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 52(sp)
  lw    t0, 52(sp)
  bnez  t0, bneqzlable.66
  j     loop.1.end
bneqzlable.66:
  j     loop.1.body

loop.1.body:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 56(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 60(sp)
  lw    t1, 56(sp)
  lw    t2, 60(sp)
  sra    t0, t1, t2
  sw    t0, 64(sp)
  lw    t1, 64(sp)
  li    t2, 15
  and    t0, t1, t2
  sw    t0, 68(sp)
  lw    t1, 68(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 72(sp)
  lw    t1, 72(sp)
  li    t2, 10
  slt    t0, t1, t2
  sw    t0, 76(sp)
  lw    t0, 76(sp)
  bnez  t0, bneqzlable.67
  j     if.4.else
bneqzlable.67:
  j     if.4.then

if.4.then:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 80(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 84(sp)
  lw    t1, 84(sp)
  addi   t0, t1, 48
  sw    t0, 88(sp)
  addi   sp, sp, -16
  lw    t0, 104(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  int2chr
  addi   sp, sp, 16
  sw    a0, 92(sp)
  addi   sp, sp, -16
  lw    t0, 96(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 108(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  __string_concat
  addi   sp, sp, 16
  sw    a0, 96(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 100(sp)
  lw    t1, 96(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  j     if.4.end

if.4.else:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 104(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 108(sp)
  lw    t1, 108(sp)
  addi   t0, t1, 65
  sw    t0, 112(sp)
  lw    t1, 112(sp)
  li    t2, 10
  sub    t0, t1, t2
  sw    t0, 116(sp)
  addi   sp, sp, -16
  lw    t0, 132(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  int2chr
  addi   sp, sp, 16
  sw    a0, 120(sp)
  addi   sp, sp, -16
  lw    t0, 120(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 136(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  __string_concat
  addi   sp, sp, 16
  sw    a0, 124(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 128(sp)
  lw    t1, 124(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  j     if.4.end

if.4.end:
  j     loop.1.update

loop.1.update:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 132(sp)
  lw    t1, 132(sp)
  li    t2, 4
  sub    t0, t1, t2
  sw    t0, 136(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 140(sp)
  lw    t1, 136(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.1.condition

loop.1.end:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 144(sp)
  lw    t1, 144(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.toStringHex.end

func.toStringHex.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 148(sp)
  lw    a0, 148(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 160
  ret   


  .globl rotate_left
rotate_left:
  addi   sp, sp, -176
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  

  lw    t1, 176(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t1, 180(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 28(sp)
  lw    t1, 28(sp)
  li    t2, 1
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 32(sp)
  lw    t0, 32(sp)
  bnez  t0, bneqzlable.68
  j     if.5.else
bneqzlable.68:
  j     if.5.then

if.5.then:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 36(sp)
  lw    t1, 36(sp)
  li    t2, 2147483647
  and    t0, t1, t2
  sw    t0, 40(sp)
  lw    t1, 40(sp)
  li    t2, 1
  sll    t0, t1, t2
  sw    t0, 44(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 48(sp)
  lw    t1, 48(sp)
  li    t2, 31
  sra    t0, t1, t2
  sw    t0, 52(sp)
  lw    t1, 52(sp)
  li    t2, 1
  and    t0, t1, t2
  sw    t0, 56(sp)
  lw    t1, 44(sp)
  lw    t2, 56(sp)
  or     t0, t1, t2
  sw    t0, 60(sp)
  lw    t1, 60(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.rotate_left.end

if.5.else:
  j     if.5.end

if.5.end:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 64(sp)
  lw    t1, 64(sp)
  li    t2, 31
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 68(sp)
  lw    t0, 68(sp)
  bnez  t0, bneqzlable.69
  j     if.6.else
bneqzlable.69:
  j     if.6.then

if.6.then:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 72(sp)
  lw    t1, 72(sp)
  li    t2, 1
  and    t0, t1, t2
  sw    t0, 76(sp)
  lw    t1, 76(sp)
  li    t2, 31
  sll    t0, t1, t2
  sw    t0, 80(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 84(sp)
  lw    t1, 84(sp)
  li    t2, 1
  sra    t0, t1, t2
  sw    t0, 88(sp)
  lw    t1, 88(sp)
  li    t2, 2147483647
  and    t0, t1, t2
  sw    t0, 92(sp)
  lw    t1, 80(sp)
  lw    t2, 92(sp)
  or     t0, t1, t2
  sw    t0, 96(sp)
  lw    t1, 96(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.rotate_left.end

if.6.else:
  j     if.6.end

if.6.end:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 100(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 104(sp)
  li    t1, 32
  lw    t2, 104(sp)
  sub    t0, t1, t2
  sw    t0, 108(sp)
  li    t1, 1
  lw    t2, 108(sp)
  sll    t0, t1, t2
  sw    t0, 112(sp)
  lw    t1, 112(sp)
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 116(sp)
  lw    t1, 100(sp)
  lw    t2, 116(sp)
  and    t0, t1, t2
  sw    t0, 120(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 124(sp)
  lw    t1, 120(sp)
  lw    t2, 124(sp)
  sll    t0, t1, t2
  sw    t0, 128(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 132(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 136(sp)
  li    t1, 32
  lw    t2, 136(sp)
  sub    t0, t1, t2
  sw    t0, 140(sp)
  lw    t1, 132(sp)
  lw    t2, 140(sp)
  sra    t0, t1, t2
  sw    t0, 144(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 148(sp)
  li    t1, 1
  lw    t2, 148(sp)
  sll    t0, t1, t2
  sw    t0, 152(sp)
  lw    t1, 152(sp)
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 156(sp)
  lw    t1, 144(sp)
  lw    t2, 156(sp)
  and    t0, t1, t2
  sw    t0, 160(sp)
  lw    t1, 128(sp)
  lw    t2, 160(sp)
  or     t0, t1, t2
  sw    t0, 164(sp)
  lw    t1, 164(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.rotate_left.end

func.rotate_left.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 168(sp)
  lw    a0, 168(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 176
  ret   


  .globl add
add:
  addi   sp, sp, -144
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  addi   t0, sp, 40
  sw    t0, 36(sp)
  

  lw    t1, 144(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t1, 148(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 44(sp)
  lw    t1, 44(sp)
  li    t2, 65535
  and    t0, t1, t2
  sw    t0, 48(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 52(sp)
  lw    t1, 52(sp)
  li    t2, 65535
  and    t0, t1, t2
  sw    t0, 56(sp)
  lw    t1, 48(sp)
  lw    t2, 56(sp)
  add    t0, t1, t2
  sw    t0, 60(sp)
  lw    t1, 60(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 64(sp)
  lw    t1, 64(sp)
  li    t2, 16
  sra    t0, t1, t2
  sw    t0, 68(sp)
  lw    t1, 68(sp)
  li    t2, 65535
  and    t0, t1, t2
  sw    t0, 72(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 76(sp)
  lw    t1, 76(sp)
  li    t2, 16
  sra    t0, t1, t2
  sw    t0, 80(sp)
  lw    t1, 80(sp)
  li    t2, 65535
  and    t0, t1, t2
  sw    t0, 84(sp)
  lw    t1, 72(sp)
  lw    t2, 84(sp)
  add    t0, t1, t2
  sw    t0, 88(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 92(sp)
  lw    t1, 92(sp)
  li    t2, 16
  sra    t0, t1, t2
  sw    t0, 96(sp)
  lw    t1, 88(sp)
  lw    t2, 96(sp)
  add    t0, t1, t2
  sw    t0, 100(sp)
  lw    t1, 100(sp)
  li    t2, 65535
  and    t0, t1, t2
  sw    t0, 104(sp)
  lw    t1, 104(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 108(sp)
  lw    t1, 108(sp)
  li    t2, 16
  sll    t0, t1, t2
  sw    t0, 112(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 116(sp)
  lw    t1, 116(sp)
  li    t2, 65535
  and    t0, t1, t2
  sw    t0, 120(sp)
  lw    t1, 112(sp)
  lw    t2, 120(sp)
  or     t0, t1, t2
  sw    t0, 124(sp)
  lw    t1, 124(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.add.end

func.add.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 128(sp)
  lw    a0, 128(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 144
  ret   


  .globl lohi
lohi:
  addi   sp, sp, -48
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  

  lw    t1, 48(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t1, 52(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 28(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 32(sp)
  lw    t1, 32(sp)
  li    t2, 16
  sll    t0, t1, t2
  sw    t0, 36(sp)
  lw    t1, 28(sp)
  lw    t2, 36(sp)
  or     t0, t1, t2
  sw    t0, 40(sp)
  lw    t1, 40(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.lohi.end

func.lohi.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 44(sp)
  lw    a0, 44(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 48
  ret   


  .globl sha1
sha1:
  addi   sp, sp, -1424
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  addi   t0, sp, 40
  sw    t0, 36(sp)
  addi   t0, sp, 48
  sw    t0, 44(sp)
  addi   t0, sp, 56
  sw    t0, 52(sp)
  addi   t0, sp, 64
  sw    t0, 60(sp)
  addi   t0, sp, 72
  sw    t0, 68(sp)
  addi   t0, sp, 80
  sw    t0, 76(sp)
  addi   t0, sp, 88
  sw    t0, 84(sp)
  addi   t0, sp, 96
  sw    t0, 92(sp)
  addi   t0, sp, 104
  sw    t0, 100(sp)
  addi   t0, sp, 112
  sw    t0, 108(sp)
  addi   t0, sp, 120
  sw    t0, 116(sp)
  addi   t0, sp, 128
  sw    t0, 124(sp)
  addi   t0, sp, 136
  sw    t0, 132(sp)
  addi   t0, sp, 144
  sw    t0, 140(sp)
  addi   t0, sp, 152
  sw    t0, 148(sp)
  

  lw    t1, 1424(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t1, 1428(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 156(sp)
  lw    t1, 156(sp)
  addi   t0, t1, 64
  sw    t0, 160(sp)
  lw    t1, 160(sp)
  li    t2, 56
  sub    t0, t1, t2
  sw    t0, 164(sp)
  lw    t1, 164(sp)
  li    t2, 64
  div    t0, t1, t2
  sw    t0, 168(sp)
  lw    t1, 168(sp)
  addi   t0, t1, 1
  sw    t0, 172(sp)
  lw    t1, 172(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 176(sp)
  la    t6, @MAXCHUNK.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 180(sp)
  lw    t1, 176(sp)
  lw    t2, 180(sp)
  sgt    t0, t1, t2
  sw    t0, 184(sp)
  lw    t0, 184(sp)
  bnez  t0, bneqzlable.70
  j     if.7.else
bneqzlable.70:
  j     if.7.then

if.7.then:
  addi   sp, sp, -16
  la    t6, @string.2
  mv    t0, t6
  mv    a0, t0
  sw    t0, 0(sp)
  call  println
  addi   sp, sp, 16
  li    t1, 0
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.sha1.end

if.7.else:
  j     if.7.end

if.7.end:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 188(sp)
  li    t1, 0
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.2.condition

loop.2.condition:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 192(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 196(sp)
  lw    t1, 192(sp)
  lw    t2, 196(sp)
  slt    t0, t1, t2
  sw    t0, 200(sp)
  lw    t0, 200(sp)
  bnez  t0, bneqzlable.71
  j     loop.2.end
bneqzlable.71:
  j     loop.2.body

loop.2.body:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 204(sp)
  li    t1, 0
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.3.condition

loop.3.condition:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 208(sp)
  lw    t1, 208(sp)
  li    t2, 80
  slt    t0, t1, t2
  sw    t0, 212(sp)
  lw    t0, 212(sp)
  bnez  t0, bneqzlable.72
  j     loop.3.end
bneqzlable.72:
  j     loop.3.body

loop.3.body:
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 216(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 220(sp)
  lw    t1, 216(sp)
  lw    t2, 220(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 224(sp)
  lw    t0, 224(sp)
  lw    t1, 0(t0)
  sw    t1, 228(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 232(sp)
  lw    t1, 228(sp)
  lw    t2, 232(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 236(sp)
  lw    t0, 236(sp)
  lw    t1, 0(t0)
  sw    t1, 240(sp)
  li    t1, 0
  lw    t0, 236(sp)
  sw    t1, 0(t0)
  j     loop.3.update

loop.3.update:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 244(sp)
  lw    t1, 244(sp)
  addi   t0, t1, 1
  sw    t0, 248(sp)
  lw    t1, 248(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.3.condition

loop.3.end:
  j     loop.2.update

loop.2.update:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 252(sp)
  lw    t1, 252(sp)
  addi   t0, t1, 1
  sw    t0, 256(sp)
  lw    t1, 256(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.2.condition

loop.2.end:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 260(sp)
  li    t1, 0
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.4.condition

loop.4.condition:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 264(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 268(sp)
  lw    t1, 264(sp)
  lw    t2, 268(sp)
  slt    t0, t1, t2
  sw    t0, 272(sp)
  lw    t0, 272(sp)
  bnez  t0, bneqzlable.73
  j     loop.4.end
bneqzlable.73:
  j     loop.4.body

loop.4.body:
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 276(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 280(sp)
  lw    t1, 280(sp)
  li    t2, 64
  div    t0, t1, t2
  sw    t0, 284(sp)
  lw    t1, 276(sp)
  lw    t2, 284(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 288(sp)
  lw    t0, 288(sp)
  lw    t1, 0(t0)
  sw    t1, 292(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 296(sp)
  lw    t1, 296(sp)
  li    t2, 64
  rem    t0, t1, t2
  sw    t0, 300(sp)
  lw    t1, 300(sp)
  li    t2, 4
  div    t0, t1, t2
  sw    t0, 304(sp)
  lw    t1, 292(sp)
  lw    t2, 304(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 308(sp)
  lw    t0, 308(sp)
  lw    t1, 0(t0)
  sw    t1, 312(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 316(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 320(sp)
  lw    t1, 316(sp)
  lw    t2, 320(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 324(sp)
  lw    t0, 324(sp)
  lw    t1, 0(t0)
  sw    t1, 328(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 332(sp)
  lw    t1, 332(sp)
  li    t2, 4
  rem    t0, t1, t2
  sw    t0, 336(sp)
  li    t1, 3
  lw    t2, 336(sp)
  sub    t0, t1, t2
  sw    t0, 340(sp)
  lw    t1, 340(sp)
  li    t2, 8
  mul    t0, t1, t2
  sw    t0, 344(sp)
  lw    t1, 328(sp)
  lw    t2, 344(sp)
  sll    t0, t1, t2
  sw    t0, 348(sp)
  lw    t1, 312(sp)
  lw    t2, 348(sp)
  or     t0, t1, t2
  sw    t0, 352(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 356(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 360(sp)
  lw    t1, 360(sp)
  li    t2, 64
  div    t0, t1, t2
  sw    t0, 364(sp)
  lw    t1, 356(sp)
  lw    t2, 364(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 368(sp)
  lw    t0, 368(sp)
  lw    t1, 0(t0)
  sw    t1, 372(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 376(sp)
  lw    t1, 376(sp)
  li    t2, 64
  rem    t0, t1, t2
  sw    t0, 380(sp)
  lw    t1, 380(sp)
  li    t2, 4
  div    t0, t1, t2
  sw    t0, 384(sp)
  lw    t1, 372(sp)
  lw    t2, 384(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 388(sp)
  lw    t0, 388(sp)
  lw    t1, 0(t0)
  sw    t1, 392(sp)
  lw    t1, 352(sp)
  lw    t0, 388(sp)
  sw    t1, 0(t0)
  j     loop.4.update

loop.4.update:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 396(sp)
  lw    t1, 396(sp)
  addi   t0, t1, 1
  sw    t0, 400(sp)
  lw    t1, 400(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.4.condition

loop.4.end:
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 404(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 408(sp)
  lw    t1, 408(sp)
  li    t2, 64
  div    t0, t1, t2
  sw    t0, 412(sp)
  lw    t1, 404(sp)
  lw    t2, 412(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 416(sp)
  lw    t0, 416(sp)
  lw    t1, 0(t0)
  sw    t1, 420(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 424(sp)
  lw    t1, 424(sp)
  li    t2, 64
  rem    t0, t1, t2
  sw    t0, 428(sp)
  lw    t1, 428(sp)
  li    t2, 4
  div    t0, t1, t2
  sw    t0, 432(sp)
  lw    t1, 420(sp)
  lw    t2, 432(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 436(sp)
  lw    t0, 436(sp)
  lw    t1, 0(t0)
  sw    t1, 440(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 444(sp)
  lw    t1, 444(sp)
  li    t2, 4
  rem    t0, t1, t2
  sw    t0, 448(sp)
  li    t1, 3
  lw    t2, 448(sp)
  sub    t0, t1, t2
  sw    t0, 452(sp)
  lw    t1, 452(sp)
  li    t2, 8
  mul    t0, t1, t2
  sw    t0, 456(sp)
  li    t1, 128
  lw    t2, 456(sp)
  sll    t0, t1, t2
  sw    t0, 460(sp)
  lw    t1, 440(sp)
  lw    t2, 460(sp)
  or     t0, t1, t2
  sw    t0, 464(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 468(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 472(sp)
  lw    t1, 472(sp)
  li    t2, 64
  div    t0, t1, t2
  sw    t0, 476(sp)
  lw    t1, 468(sp)
  lw    t2, 476(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 480(sp)
  lw    t0, 480(sp)
  lw    t1, 0(t0)
  sw    t1, 484(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 488(sp)
  lw    t1, 488(sp)
  li    t2, 64
  rem    t0, t1, t2
  sw    t0, 492(sp)
  lw    t1, 492(sp)
  li    t2, 4
  div    t0, t1, t2
  sw    t0, 496(sp)
  lw    t1, 484(sp)
  lw    t2, 496(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 500(sp)
  lw    t0, 500(sp)
  lw    t1, 0(t0)
  sw    t1, 504(sp)
  lw    t1, 464(sp)
  lw    t0, 500(sp)
  sw    t1, 0(t0)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 508(sp)
  lw    t1, 508(sp)
  li    t2, 3
  sll    t0, t1, t2
  sw    t0, 512(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 516(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 520(sp)
  lw    t1, 520(sp)
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 524(sp)
  lw    t1, 516(sp)
  lw    t2, 524(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 528(sp)
  lw    t0, 528(sp)
  lw    t1, 0(t0)
  sw    t1, 532(sp)
  lw    t1, 532(sp)
  li    t2, 15
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 536(sp)
  lw    t0, 536(sp)
  lw    t1, 0(t0)
  sw    t1, 540(sp)
  lw    t1, 512(sp)
  lw    t0, 536(sp)
  sw    t1, 0(t0)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 544(sp)
  lw    t1, 544(sp)
  li    t2, 29
  sra    t0, t1, t2
  sw    t0, 548(sp)
  lw    t1, 548(sp)
  li    t2, 7
  and    t0, t1, t2
  sw    t0, 552(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 556(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 560(sp)
  lw    t1, 560(sp)
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 564(sp)
  lw    t1, 556(sp)
  lw    t2, 564(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 568(sp)
  lw    t0, 568(sp)
  lw    t1, 0(t0)
  sw    t1, 572(sp)
  lw    t1, 572(sp)
  li    t2, 14
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 576(sp)
  lw    t0, 576(sp)
  lw    t1, 0(t0)
  sw    t1, 580(sp)
  lw    t1, 552(sp)
  lw    t0, 576(sp)
  sw    t1, 0(t0)
  li    t1, 1732584193
  lw    t0, 52(sp)
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 43913
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 61389
  mv    a1, t0
  sw    t0, 4(sp)
  call  lohi
  addi   sp, sp, 16
  sw    a0, 584(sp)
  lw    t1, 584(sp)
  lw    t0, 60(sp)
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 56574
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 39098
  mv    a1, t0
  sw    t0, 4(sp)
  call  lohi
  addi   sp, sp, 16
  sw    a0, 588(sp)
  lw    t1, 588(sp)
  lw    t0, 68(sp)
  sw    t1, 0(t0)
  li    t1, 271733878
  lw    t0, 76(sp)
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 57840
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 50130
  mv    a1, t0
  sw    t0, 4(sp)
  call  lohi
  addi   sp, sp, 16
  sw    a0, 592(sp)
  lw    t1, 592(sp)
  lw    t0, 84(sp)
  sw    t1, 0(t0)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 596(sp)
  addi   sp, sp, -16
  lw    t0, 612(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 600(sp)
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 604(sp)
  lw    t1, 600(sp)
  lw    t2, 604(sp)
  add    t0, t1, t2
  sw    t0, 608(sp)
  lw    t0, 68(sp)
  lw    t1, 0(t0)
  sw    t1, 612(sp)
  lw    t1, 608(sp)
  lw    t2, 612(sp)
  add    t0, t1, t2
  sw    t0, 616(sp)
  lw    t0, 76(sp)
  lw    t1, 0(t0)
  sw    t1, 620(sp)
  lw    t1, 616(sp)
  lw    t2, 620(sp)
  add    t0, t1, t2
  sw    t0, 624(sp)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 628(sp)
  lw    t1, 624(sp)
  lw    t2, 628(sp)
  add    t0, t1, t2
  sw    t0, 632(sp)
  addi   sp, sp, -16
  lw    t0, 648(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 636(sp)
  li    t1, 0
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.5.condition

loop.5.condition:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 640(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 644(sp)
  lw    t1, 640(sp)
  lw    t2, 644(sp)
  slt    t0, t1, t2
  sw    t0, 648(sp)
  lw    t0, 648(sp)
  bnez  t0, bneqzlable.74
  j     loop.5.end
bneqzlable.74:
  j     loop.5.body

loop.5.body:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 652(sp)
  addi   sp, sp, -16
  lw    t0, 668(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 656(sp)
  li    t1, 16
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.6.condition

loop.6.condition:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 660(sp)
  lw    t1, 660(sp)
  li    t2, 80
  slt    t0, t1, t2
  sw    t0, 664(sp)
  lw    t0, 664(sp)
  bnez  t0, bneqzlable.75
  j     loop.6.end
bneqzlable.75:
  j     loop.6.body

loop.6.body:
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 668(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 672(sp)
  lw    t1, 668(sp)
  lw    t2, 672(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 676(sp)
  lw    t0, 676(sp)
  lw    t1, 0(t0)
  sw    t1, 680(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 684(sp)
  lw    t1, 684(sp)
  li    t2, 3
  sub    t0, t1, t2
  sw    t0, 688(sp)
  lw    t1, 680(sp)
  lw    t2, 688(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 692(sp)
  lw    t0, 692(sp)
  lw    t1, 0(t0)
  sw    t1, 696(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 700(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 704(sp)
  lw    t1, 700(sp)
  lw    t2, 704(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 708(sp)
  lw    t0, 708(sp)
  lw    t1, 0(t0)
  sw    t1, 712(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 716(sp)
  lw    t1, 716(sp)
  li    t2, 8
  sub    t0, t1, t2
  sw    t0, 720(sp)
  lw    t1, 712(sp)
  lw    t2, 720(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 724(sp)
  lw    t0, 724(sp)
  lw    t1, 0(t0)
  sw    t1, 728(sp)
  lw    t1, 696(sp)
  lw    t2, 728(sp)
  xor    t0, t1, t2
  sw    t0, 732(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 736(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 740(sp)
  lw    t1, 736(sp)
  lw    t2, 740(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 744(sp)
  lw    t0, 744(sp)
  lw    t1, 0(t0)
  sw    t1, 748(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 752(sp)
  lw    t1, 752(sp)
  li    t2, 14
  sub    t0, t1, t2
  sw    t0, 756(sp)
  lw    t1, 748(sp)
  lw    t2, 756(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 760(sp)
  lw    t0, 760(sp)
  lw    t1, 0(t0)
  sw    t1, 764(sp)
  lw    t1, 732(sp)
  lw    t2, 764(sp)
  xor    t0, t1, t2
  sw    t0, 768(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 772(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 776(sp)
  lw    t1, 772(sp)
  lw    t2, 776(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 780(sp)
  lw    t0, 780(sp)
  lw    t1, 0(t0)
  sw    t1, 784(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 788(sp)
  lw    t1, 788(sp)
  li    t2, 16
  sub    t0, t1, t2
  sw    t0, 792(sp)
  lw    t1, 784(sp)
  lw    t2, 792(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 796(sp)
  lw    t0, 796(sp)
  lw    t1, 0(t0)
  sw    t1, 800(sp)
  lw    t1, 768(sp)
  lw    t2, 800(sp)
  xor    t0, t1, t2
  sw    t0, 804(sp)
  addi   sp, sp, -16
  lw    t0, 820(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 1
  mv    a1, t0
  sw    t0, 4(sp)
  call  rotate_left
  addi   sp, sp, 16
  sw    a0, 808(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 812(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 816(sp)
  lw    t1, 812(sp)
  lw    t2, 816(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 820(sp)
  lw    t0, 820(sp)
  lw    t1, 0(t0)
  sw    t1, 824(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 828(sp)
  lw    t1, 824(sp)
  lw    t2, 828(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 832(sp)
  lw    t0, 832(sp)
  lw    t1, 0(t0)
  sw    t1, 836(sp)
  lw    t1, 808(sp)
  lw    t0, 832(sp)
  sw    t1, 0(t0)
  j     loop.6.update

loop.6.update:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 840(sp)
  lw    t1, 840(sp)
  addi   t0, t1, 1
  sw    t0, 844(sp)
  lw    t1, 844(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.6.condition

loop.6.end:
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 848(sp)
  lw    t1, 848(sp)
  lw    t0, 92(sp)
  sw    t1, 0(t0)
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 852(sp)
  lw    t1, 852(sp)
  lw    t0, 100(sp)
  sw    t1, 0(t0)
  lw    t0, 68(sp)
  lw    t1, 0(t0)
  sw    t1, 856(sp)
  lw    t1, 856(sp)
  lw    t0, 108(sp)
  sw    t1, 0(t0)
  lw    t0, 76(sp)
  lw    t1, 0(t0)
  sw    t1, 860(sp)
  lw    t1, 860(sp)
  lw    t0, 116(sp)
  sw    t1, 0(t0)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 864(sp)
  lw    t1, 864(sp)
  lw    t0, 124(sp)
  sw    t1, 0(t0)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 868(sp)
  li    t1, 0
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.7.condition

loop.7.condition:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 872(sp)
  lw    t1, 872(sp)
  li    t2, 80
  slt    t0, t1, t2
  sw    t0, 876(sp)
  lw    t0, 876(sp)
  bnez  t0, bneqzlable.76
  j     loop.7.end
bneqzlable.76:
  j     loop.7.body

loop.7.body:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 880(sp)
  lw    t1, 880(sp)
  li    t2, 20
  slt    t0, t1, t2
  sw    t0, 884(sp)
  lw    t0, 884(sp)
  bnez  t0, bneqzlable.77
  j     if.8.else
bneqzlable.77:
  j     if.8.then

if.8.then:
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 888(sp)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 892(sp)
  lw    t1, 888(sp)
  lw    t2, 892(sp)
  and    t0, t1, t2
  sw    t0, 896(sp)
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 900(sp)
  li    t1, -1
  lw    t2, 900(sp)
  xor    t0, t1, t2
  sw    t0, 904(sp)
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 908(sp)
  lw    t1, 904(sp)
  lw    t2, 908(sp)
  and    t0, t1, t2
  sw    t0, 912(sp)
  lw    t1, 896(sp)
  lw    t2, 912(sp)
  or     t0, t1, t2
  sw    t0, 916(sp)
  lw    t0, 132(sp)
  lw    t1, 0(t0)
  sw    t1, 920(sp)
  lw    t1, 916(sp)
  lw    t0, 132(sp)
  sw    t1, 0(t0)
  lw    t0, 140(sp)
  lw    t1, 0(t0)
  sw    t1, 924(sp)
  li    t1, 1518500249
  lw    t0, 140(sp)
  sw    t1, 0(t0)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 928(sp)
  lw    t1, 928(sp)
  li    t2, 2
  slt    t0, t1, t2
  sw    t0, 932(sp)
  lw    t0, 932(sp)
  bnez  t0, bneqzlable.78
  j     if.9.else
bneqzlable.78:
  j     if.9.then

if.9.then:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 936(sp)
  addi   sp, sp, -16
  lw    t0, 952(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printInt
  addi   sp, sp, 16
  addi   sp, sp, -16
  la    t6, @string.3
  mv    t0, t6
  mv    a0, t0
  sw    t0, 0(sp)
  call  print
  addi   sp, sp, 16
  lw    t0, 92(sp)
  lw    t1, 0(t0)
  sw    t1, 940(sp)
  addi   sp, sp, -16
  lw    t0, 956(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 944(sp)
  addi   sp, sp, -16
  lw    t0, 960(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 948(sp)
  addi   sp, sp, -16
  lw    t0, 964(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 952(sp)
  addi   sp, sp, -16
  lw    t0, 968(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 124(sp)
  lw    t1, 0(t0)
  sw    t1, 956(sp)
  addi   sp, sp, -16
  lw    t0, 972(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  lw    t0, 132(sp)
  lw    t1, 0(t0)
  sw    t1, 960(sp)
  addi   sp, sp, -16
  lw    t0, 976(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  j     if.9.end

if.9.else:
  j     if.9.end

if.9.end:
  j     if.8.end

if.8.else:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 964(sp)
  lw    t1, 964(sp)
  li    t2, 40
  slt    t0, t1, t2
  sw    t0, 968(sp)
  lw    t0, 968(sp)
  bnez  t0, bneqzlable.79
  j     if.10.else
bneqzlable.79:
  j     if.10.then

if.10.then:
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 972(sp)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 976(sp)
  lw    t1, 972(sp)
  lw    t2, 976(sp)
  xor    t0, t1, t2
  sw    t0, 980(sp)
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 984(sp)
  lw    t1, 980(sp)
  lw    t2, 984(sp)
  xor    t0, t1, t2
  sw    t0, 988(sp)
  lw    t0, 132(sp)
  lw    t1, 0(t0)
  sw    t1, 992(sp)
  lw    t1, 988(sp)
  lw    t0, 132(sp)
  sw    t1, 0(t0)
  lw    t0, 140(sp)
  lw    t1, 0(t0)
  sw    t1, 996(sp)
  li    t1, 1859775393
  lw    t0, 140(sp)
  sw    t1, 0(t0)
  j     if.10.end

if.10.else:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 1000(sp)
  lw    t1, 1000(sp)
  li    t2, 60
  slt    t0, t1, t2
  sw    t0, 1004(sp)
  lw    t0, 1004(sp)
  bnez  t0, bneqzlable.80
  j     if.11.else
bneqzlable.80:
  j     if.11.then

if.11.then:
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 1008(sp)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 1012(sp)
  lw    t1, 1008(sp)
  lw    t2, 1012(sp)
  and    t0, t1, t2
  sw    t0, 1016(sp)
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 1020(sp)
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 1024(sp)
  lw    t1, 1020(sp)
  lw    t2, 1024(sp)
  and    t0, t1, t2
  sw    t0, 1028(sp)
  lw    t1, 1016(sp)
  lw    t2, 1028(sp)
  or     t0, t1, t2
  sw    t0, 1032(sp)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 1036(sp)
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 1040(sp)
  lw    t1, 1036(sp)
  lw    t2, 1040(sp)
  and    t0, t1, t2
  sw    t0, 1044(sp)
  lw    t1, 1032(sp)
  lw    t2, 1044(sp)
  or     t0, t1, t2
  sw    t0, 1048(sp)
  lw    t0, 132(sp)
  lw    t1, 0(t0)
  sw    t1, 1052(sp)
  lw    t1, 1048(sp)
  lw    t0, 132(sp)
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 48348
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 36635
  mv    a1, t0
  sw    t0, 4(sp)
  call  lohi
  addi   sp, sp, 16
  sw    a0, 1056(sp)
  lw    t0, 140(sp)
  lw    t1, 0(t0)
  sw    t1, 1060(sp)
  lw    t1, 1056(sp)
  lw    t0, 140(sp)
  sw    t1, 0(t0)
  j     if.11.end

if.11.else:
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 1064(sp)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 1068(sp)
  lw    t1, 1064(sp)
  lw    t2, 1068(sp)
  xor    t0, t1, t2
  sw    t0, 1072(sp)
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 1076(sp)
  lw    t1, 1072(sp)
  lw    t2, 1076(sp)
  xor    t0, t1, t2
  sw    t0, 1080(sp)
  lw    t0, 132(sp)
  lw    t1, 0(t0)
  sw    t1, 1084(sp)
  lw    t1, 1080(sp)
  lw    t0, 132(sp)
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 49622
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 51810
  mv    a1, t0
  sw    t0, 4(sp)
  call  lohi
  addi   sp, sp, 16
  sw    a0, 1088(sp)
  lw    t0, 140(sp)
  lw    t1, 0(t0)
  sw    t1, 1092(sp)
  lw    t1, 1088(sp)
  lw    t0, 140(sp)
  sw    t1, 0(t0)
  j     if.11.end

if.11.end:
  j     if.10.end

if.10.end:
  j     if.8.end

if.8.end:
  lw    t0, 92(sp)
  lw    t1, 0(t0)
  sw    t1, 1096(sp)
  addi   sp, sp, -16
  lw    t0, 1112(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 5
  mv    a1, t0
  sw    t0, 4(sp)
  call  rotate_left
  addi   sp, sp, 16
  sw    a0, 1100(sp)
  lw    t0, 124(sp)
  lw    t1, 0(t0)
  sw    t1, 1104(sp)
  addi   sp, sp, -16
  lw    t0, 1116(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1120(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1108(sp)
  lw    t0, 132(sp)
  lw    t1, 0(t0)
  sw    t1, 1112(sp)
  lw    t0, 140(sp)
  lw    t1, 0(t0)
  sw    t1, 1116(sp)
  addi   sp, sp, -16
  lw    t0, 1128(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1132(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1120(sp)
  addi   sp, sp, -16
  lw    t0, 1124(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1136(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1124(sp)
  la    t6, @chunks.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 1128(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 1132(sp)
  lw    t1, 1128(sp)
  lw    t2, 1132(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 1136(sp)
  lw    t0, 1136(sp)
  lw    t1, 0(t0)
  sw    t1, 1140(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 1144(sp)
  lw    t1, 1140(sp)
  lw    t2, 1144(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 1148(sp)
  lw    t0, 1148(sp)
  lw    t1, 0(t0)
  sw    t1, 1152(sp)
  addi   sp, sp, -16
  lw    t0, 1140(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1168(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1156(sp)
  lw    t1, 1156(sp)
  lw    t0, 148(sp)
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 114514
  mv    a0, t0
  sw    t0, 0(sp)
  call  printInt
  addi   sp, sp, 16
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 1160(sp)
  lw    t0, 124(sp)
  lw    t1, 0(t0)
  sw    t1, 1164(sp)
  lw    t1, 1160(sp)
  lw    t0, 124(sp)
  sw    t1, 0(t0)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 1168(sp)
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 1172(sp)
  lw    t1, 1168(sp)
  lw    t0, 116(sp)
  sw    t1, 0(t0)
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 1176(sp)
  addi   sp, sp, -16
  lw    t0, 1192(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 30
  mv    a1, t0
  sw    t0, 4(sp)
  call  rotate_left
  addi   sp, sp, 16
  sw    a0, 1180(sp)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 1184(sp)
  lw    t1, 1180(sp)
  lw    t0, 108(sp)
  sw    t1, 0(t0)
  lw    t0, 92(sp)
  lw    t1, 0(t0)
  sw    t1, 1188(sp)
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 1192(sp)
  lw    t1, 1188(sp)
  lw    t0, 100(sp)
  sw    t1, 0(t0)
  lw    t0, 148(sp)
  lw    t1, 0(t0)
  sw    t1, 1196(sp)
  lw    t0, 92(sp)
  lw    t1, 0(t0)
  sw    t1, 1200(sp)
  lw    t1, 1196(sp)
  lw    t0, 92(sp)
  sw    t1, 0(t0)
  j     loop.7.update

loop.7.update:
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 1204(sp)
  lw    t1, 1204(sp)
  addi   t0, t1, 1
  sw    t0, 1208(sp)
  lw    t1, 1208(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  j     loop.7.condition

loop.7.end:
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 1212(sp)
  lw    t0, 92(sp)
  lw    t1, 0(t0)
  sw    t1, 1216(sp)
  addi   sp, sp, -16
  lw    t0, 1228(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1232(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1220(sp)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 1224(sp)
  lw    t1, 1220(sp)
  lw    t0, 52(sp)
  sw    t1, 0(t0)
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 1228(sp)
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 1232(sp)
  addi   sp, sp, -16
  lw    t0, 1244(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1248(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1236(sp)
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 1240(sp)
  lw    t1, 1236(sp)
  lw    t0, 60(sp)
  sw    t1, 0(t0)
  lw    t0, 68(sp)
  lw    t1, 0(t0)
  sw    t1, 1244(sp)
  lw    t0, 108(sp)
  lw    t1, 0(t0)
  sw    t1, 1248(sp)
  addi   sp, sp, -16
  lw    t0, 1260(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1264(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1252(sp)
  lw    t0, 68(sp)
  lw    t1, 0(t0)
  sw    t1, 1256(sp)
  lw    t1, 1252(sp)
  lw    t0, 68(sp)
  sw    t1, 0(t0)
  lw    t0, 76(sp)
  lw    t1, 0(t0)
  sw    t1, 1260(sp)
  lw    t0, 116(sp)
  lw    t1, 0(t0)
  sw    t1, 1264(sp)
  addi   sp, sp, -16
  lw    t0, 1276(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1280(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1268(sp)
  lw    t0, 76(sp)
  lw    t1, 0(t0)
  sw    t1, 1272(sp)
  lw    t1, 1268(sp)
  lw    t0, 76(sp)
  sw    t1, 0(t0)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 1276(sp)
  lw    t0, 124(sp)
  lw    t1, 0(t0)
  sw    t1, 1280(sp)
  addi   sp, sp, -16
  lw    t0, 1292(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 1296(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  add
  addi   sp, sp, 16
  sw    a0, 1284(sp)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 1288(sp)
  lw    t1, 1284(sp)
  lw    t0, 84(sp)
  sw    t1, 0(t0)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 1292(sp)
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 1296(sp)
  lw    t1, 1292(sp)
  lw    t2, 1296(sp)
  add    t0, t1, t2
  sw    t0, 1300(sp)
  lw    t0, 68(sp)
  lw    t1, 0(t0)
  sw    t1, 1304(sp)
  lw    t1, 1300(sp)
  lw    t2, 1304(sp)
  add    t0, t1, t2
  sw    t0, 1308(sp)
  lw    t0, 76(sp)
  lw    t1, 0(t0)
  sw    t1, 1312(sp)
  lw    t1, 1308(sp)
  lw    t2, 1312(sp)
  add    t0, t1, t2
  sw    t0, 1316(sp)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 1320(sp)
  lw    t1, 1316(sp)
  lw    t2, 1320(sp)
  add    t0, t1, t2
  sw    t0, 1324(sp)
  addi   sp, sp, -16
  lw    t0, 1340(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printlnInt
  addi   sp, sp, 16
  j     loop.5.update

loop.5.update:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 1328(sp)
  lw    t1, 1328(sp)
  addi   t0, t1, 1
  sw    t0, 1332(sp)
  lw    t1, 1332(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.5.condition

loop.5.end:
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 1336(sp)
  la    t6, @outputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 1340(sp)
  lw    t1, 1340(sp)
  li    t2, 0
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 1344(sp)
  lw    t0, 1344(sp)
  lw    t1, 0(t0)
  sw    t1, 1348(sp)
  lw    t1, 1336(sp)
  lw    t0, 1344(sp)
  sw    t1, 0(t0)
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 1352(sp)
  la    t6, @outputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 1356(sp)
  lw    t1, 1356(sp)
  li    t2, 1
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 1360(sp)
  lw    t0, 1360(sp)
  lw    t1, 0(t0)
  sw    t1, 1364(sp)
  lw    t1, 1352(sp)
  lw    t0, 1360(sp)
  sw    t1, 0(t0)
  lw    t0, 68(sp)
  lw    t1, 0(t0)
  sw    t1, 1368(sp)
  la    t6, @outputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 1372(sp)
  lw    t1, 1372(sp)
  li    t2, 2
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 1376(sp)
  lw    t0, 1376(sp)
  lw    t1, 0(t0)
  sw    t1, 1380(sp)
  lw    t1, 1368(sp)
  lw    t0, 1376(sp)
  sw    t1, 0(t0)
  lw    t0, 76(sp)
  lw    t1, 0(t0)
  sw    t1, 1384(sp)
  la    t6, @outputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 1388(sp)
  lw    t1, 1388(sp)
  li    t2, 3
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 1392(sp)
  lw    t0, 1392(sp)
  lw    t1, 0(t0)
  sw    t1, 1396(sp)
  lw    t1, 1384(sp)
  lw    t0, 1392(sp)
  sw    t1, 0(t0)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 1400(sp)
  la    t6, @outputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 1404(sp)
  lw    t1, 1404(sp)
  li    t2, 4
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 1408(sp)
  lw    t0, 1408(sp)
  lw    t1, 0(t0)
  sw    t1, 1412(sp)
  lw    t1, 1400(sp)
  lw    t0, 1408(sp)
  sw    t1, 0(t0)
  la    t6, @outputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 1416(sp)
  lw    t1, 1416(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.sha1.end

func.sha1.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 1420(sp)
  lw    a0, 1420(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 1424
  ret   


  .globl computeSHA1
computeSHA1:
  addi   sp, sp, -208
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  

  lw    t1, 208(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 28(sp)
  li    t1, 0
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  j     loop.8.condition

loop.8.condition:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 32(sp)
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 36(sp)
  addi   sp, sp, -16
  lw    t0, 52(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  __string_length
  addi   sp, sp, 16
  sw    a0, 40(sp)
  lw    t1, 32(sp)
  lw    t2, 40(sp)
  slt    t0, t1, t2
  sw    t0, 44(sp)
  lw    t0, 44(sp)
  bnez  t0, bneqzlable.81
  j     loop.8.end
bneqzlable.81:
  j     loop.8.body

loop.8.body:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 48(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 52(sp)
  addi   sp, sp, -16
  lw    t0, 64(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 68(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  __string_ord
  addi   sp, sp, 16
  sw    a0, 56(sp)
  la    t6, @inputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 60(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 64(sp)
  lw    t1, 60(sp)
  lw    t2, 64(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 68(sp)
  lw    t0, 68(sp)
  lw    t1, 0(t0)
  sw    t1, 72(sp)
  lw    t1, 56(sp)
  lw    t0, 68(sp)
  sw    t1, 0(t0)
  j     loop.8.update

loop.8.update:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 76(sp)
  lw    t1, 76(sp)
  addi   t0, t1, 1
  sw    t0, 80(sp)
  lw    t1, 80(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  j     loop.8.condition

loop.8.end:
  la    t6, @inputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 84(sp)
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 88(sp)
  addi   sp, sp, -16
  lw    t0, 104(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  __string_length
  addi   sp, sp, 16
  sw    a0, 92(sp)
  addi   sp, sp, -16
  lw    t0, 100(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 108(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  sha1
  addi   sp, sp, 16
  sw    a0, 96(sp)
  lw    t1, 96(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 100(sp)
  li    t1, 0
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  j     loop.9.condition

loop.9.condition:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 104(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 108(sp)
  addi   sp, sp, -16
  lw    t0, 124(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  buildInArraySize
  addi   sp, sp, 16
  sw    a0, 112(sp)
  lw    t1, 104(sp)
  lw    t2, 112(sp)
  slt    t0, t1, t2
  sw    t0, 116(sp)
  lw    t0, 116(sp)
  bnez  t0, bneqzlable.82
  j     loop.9.end
bneqzlable.82:
  j     loop.9.body

loop.9.body:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 120(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 124(sp)
  lw    t1, 120(sp)
  lw    t2, 124(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 128(sp)
  lw    t0, 128(sp)
  lw    t1, 0(t0)
  sw    t1, 132(sp)
  addi   sp, sp, -16
  lw    t0, 148(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  toString
  addi   sp, sp, 16
  sw    a0, 136(sp)
  addi   sp, sp, -16
  lw    t0, 152(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  print
  addi   sp, sp, 16
  j     loop.9.update

loop.9.update:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 140(sp)
  lw    t1, 140(sp)
  addi   t0, t1, 1
  sw    t0, 144(sp)
  lw    t1, 144(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  j     loop.9.condition

loop.9.end:
  addi   sp, sp, -16
  la    t6, @string.1
  mv    t0, t6
  mv    a0, t0
  sw    t0, 0(sp)
  call  println
  addi   sp, sp, 16
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 148(sp)
  li    t1, 0
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  j     loop.10.condition

loop.10.condition:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 152(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 156(sp)
  addi   sp, sp, -16
  lw    t0, 172(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  buildInArraySize
  addi   sp, sp, 16
  sw    a0, 160(sp)
  lw    t1, 152(sp)
  lw    t2, 160(sp)
  slt    t0, t1, t2
  sw    t0, 164(sp)
  lw    t0, 164(sp)
  bnez  t0, bneqzlable.83
  j     loop.10.end
bneqzlable.83:
  j     loop.10.body

loop.10.body:
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 168(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 172(sp)
  lw    t1, 168(sp)
  lw    t2, 172(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 176(sp)
  lw    t0, 176(sp)
  lw    t1, 0(t0)
  sw    t1, 180(sp)
  addi   sp, sp, -16
  lw    t0, 196(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  toStringHex
  addi   sp, sp, 16
  sw    a0, 184(sp)
  addi   sp, sp, -16
  lw    t0, 200(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  print
  addi   sp, sp, 16
  j     loop.10.update

loop.10.update:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 188(sp)
  lw    t1, 188(sp)
  addi   t0, t1, 1
  sw    t0, 192(sp)
  lw    t1, 192(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  j     loop.10.condition

loop.10.end:
  addi   sp, sp, -16
  la    t6, @string.1
  mv    t0, t6
  mv    a0, t0
  sw    t0, 0(sp)
  call  println
  addi   sp, sp, 16
  j     func.computeSHA1.end

func.computeSHA1.end:
  lw    ra, 0(sp)
  addi   sp, sp, 208
  ret   


  .globl nextLetter
nextLetter:
  addi   sp, sp, -64
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  

  lw    t1, 64(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 20(sp)
  lw    t1, 20(sp)
  li    t2, 122
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 24(sp)
  lw    t0, 24(sp)
  bnez  t0, bneqzlable.84
  j     if.12.else
bneqzlable.84:
  j     if.12.then

if.12.then:
  li    t1, 0
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 28(sp)
  lw    t1, 28(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.nextLetter.end

if.12.else:
  j     if.12.end

if.12.end:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 32(sp)
  lw    t1, 32(sp)
  li    t2, 90
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 36(sp)
  lw    t0, 36(sp)
  bnez  t0, bneqzlable.85
  j     if.13.else
bneqzlable.85:
  j     if.13.then

if.13.then:
  li    t1, 97
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.nextLetter.end

if.13.else:
  j     if.13.end

if.13.end:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 40(sp)
  lw    t1, 40(sp)
  li    t2, 57
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 44(sp)
  lw    t0, 44(sp)
  bnez  t0, bneqzlable.86
  j     if.14.else
bneqzlable.86:
  j     if.14.then

if.14.then:
  li    t1, 65
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.nextLetter.end

if.14.else:
  j     if.14.end

if.14.end:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 48(sp)
  lw    t1, 48(sp)
  addi   t0, t1, 1
  sw    t0, 52(sp)
  lw    t1, 52(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.nextLetter.end

func.nextLetter.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 56(sp)
  lw    a0, 56(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 64
  ret   


  .globl nextText
nextText:
  addi   sp, sp, -144
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  

  lw    t1, 144(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t1, 148(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 36(sp)
  lw    t1, 36(sp)
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 40(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 44(sp)
  lw    t1, 40(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.11.condition

loop.11.condition:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 48(sp)
  lw    t1, 48(sp)
  li    t2, 0
  slt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 52(sp)
  lw    t0, 52(sp)
  bnez  t0, bneqzlable.87
  j     loop.11.end
bneqzlable.87:
  j     loop.11.body

loop.11.body:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 56(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 60(sp)
  lw    t1, 56(sp)
  lw    t2, 60(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 64(sp)
  lw    t0, 64(sp)
  lw    t1, 0(t0)
  sw    t1, 68(sp)
  addi   sp, sp, -16
  lw    t0, 84(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  nextLetter
  addi   sp, sp, 16
  sw    a0, 72(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 76(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 80(sp)
  lw    t1, 76(sp)
  lw    t2, 80(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 84(sp)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 88(sp)
  lw    t1, 72(sp)
  lw    t0, 84(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 92(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 96(sp)
  lw    t1, 92(sp)
  lw    t2, 96(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 100(sp)
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 104(sp)
  li    t1, 0
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 108(sp)
  lw    t1, 104(sp)
  lw    t2, 108(sp)
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 112(sp)
  lw    t0, 112(sp)
  bnez  t0, bneqzlable.88
  j     if.15.else
bneqzlable.88:
  j     if.15.then

if.15.then:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 116(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 120(sp)
  lw    t1, 116(sp)
  lw    t2, 120(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 124(sp)
  lw    t0, 124(sp)
  lw    t1, 0(t0)
  sw    t1, 128(sp)
  li    t1, 48
  lw    t0, 124(sp)
  sw    t1, 0(t0)
  j     if.15.end

if.15.else:
  li    t1, 1
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.nextText.end

if.15.end:
  j     loop.11.update

loop.11.update:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 132(sp)
  lw    t1, 132(sp)
  li    t2, 1
  sub    t0, t1, t2
  sw    t0, 136(sp)
  lw    t1, 136(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.11.condition

loop.11.end:
  li    t1, 0
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.nextText.end

func.nextText.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 140(sp)
  lw    a0, 140(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 144
  ret   


  .globl array_equal
array_equal:
  addi   sp, sp, -128
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  

  lw    t1, 128(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t1, 132(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 36(sp)
  addi   sp, sp, -16
  lw    t0, 52(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  buildInArraySize
  addi   sp, sp, 16
  sw    a0, 40(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 44(sp)
  addi   sp, sp, -16
  lw    t0, 60(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  buildInArraySize
  addi   sp, sp, 16
  sw    a0, 48(sp)
  lw    t1, 40(sp)
  lw    t2, 48(sp)
  sub    t0, t1, t2
  snez   t0, t0
  sw    t0, 52(sp)
  lw    t0, 52(sp)
  bnez  t0, bneqzlable.89
  j     if.16.else
bneqzlable.89:
  j     if.16.then

if.16.then:
  li    t1, 0
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.array_equal.end

if.16.else:
  j     if.16.end

if.16.end:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 56(sp)
  li    t1, 0
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.12.condition

loop.12.condition:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 60(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 64(sp)
  addi   sp, sp, -16
  lw    t0, 80(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  buildInArraySize
  addi   sp, sp, 16
  sw    a0, 68(sp)
  lw    t1, 60(sp)
  lw    t2, 68(sp)
  slt    t0, t1, t2
  sw    t0, 72(sp)
  lw    t0, 72(sp)
  bnez  t0, bneqzlable.90
  j     loop.12.end
bneqzlable.90:
  j     loop.12.body

loop.12.body:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 76(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 80(sp)
  lw    t1, 76(sp)
  lw    t2, 80(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 84(sp)
  lw    t0, 84(sp)
  lw    t1, 0(t0)
  sw    t1, 88(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 92(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 96(sp)
  lw    t1, 92(sp)
  lw    t2, 96(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 100(sp)
  lw    t0, 100(sp)
  lw    t1, 0(t0)
  sw    t1, 104(sp)
  lw    t1, 88(sp)
  lw    t2, 104(sp)
  sub    t0, t1, t2
  snez   t0, t0
  sw    t0, 108(sp)
  lw    t0, 108(sp)
  bnez  t0, bneqzlable.91
  j     if.17.else
bneqzlable.91:
  j     if.17.then

if.17.then:
  li    t1, 0
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.array_equal.end

if.17.else:
  j     if.17.end

if.17.end:
  j     loop.12.update

loop.12.update:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 112(sp)
  lw    t1, 112(sp)
  addi   t0, t1, 1
  sw    t0, 116(sp)
  lw    t1, 116(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.12.condition

loop.12.end:
  li    t1, 1
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.array_equal.end

func.array_equal.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 120(sp)
  lw    a0, 120(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 128
  ret   


  .globl crackSHA1
crackSHA1:
  addi   sp, sp, -416
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  addi   t0, sp, 40
  sw    t0, 36(sp)
  addi   t0, sp, 48
  sw    t0, 44(sp)
  addi   t0, sp, 56
  sw    t0, 52(sp)
  addi   t0, sp, 64
  sw    t0, 60(sp)
  

  lw    t1, 416(sp)
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  addi   sp, sp, -16
  li    t0, 5
  mv    a0, t0
  sw    t0, 0(sp)
  li    t0, 4
  mv    a1, t0
  sw    t0, 4(sp)
  call  __malloc_array
  addi   sp, sp, 16
  sw    a0, 68(sp)
  lw    t1, 68(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  li    t1, 0
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.23.0.condition

loop.23.0.condition:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 72(sp)
  lw    t1, 72(sp)
  li    t2, 5
  slt    t0, t1, t2
  sw    t0, 76(sp)
  lw    t0, 76(sp)
  bnez  t0, bneqzlable.92
  j     loop.23.0.end
bneqzlable.92:
  j     loop.23.0.body

loop.23.0.body:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 80(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 84(sp)
  lw    t1, 84(sp)
  lw    t2, 80(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 88(sp)
  li    t1, 0
  lw    t0, 88(sp)
  sw    t1, 0(t0)
  j     loop.23.0.update

loop.23.0.update:
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 92(sp)
  lw    t1, 92(sp)
  addi   t0, t1, 1
  sw    t0, 96(sp)
  lw    t1, 96(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  j     loop.23.0.condition

loop.23.0.end:
  lw    t1, 68(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 100(sp)
  addi   sp, sp, -16
  lw    t0, 116(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  __string_length
  addi   sp, sp, 16
  sw    a0, 104(sp)
  lw    t1, 104(sp)
  li    t2, 40
  sub    t0, t1, t2
  snez   t0, t0
  sw    t0, 108(sp)
  lw    t0, 108(sp)
  bnez  t0, bneqzlable.93
  j     if.18.else
bneqzlable.93:
  j     if.18.then

if.18.then:
  addi   sp, sp, -16
  la    t6, @string.4
  mv    t0, t6
  mv    a0, t0
  sw    t0, 0(sp)
  call  println
  addi   sp, sp, 16
  j     func.crackSHA1.end

if.18.else:
  j     if.18.end

if.18.end:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 112(sp)
  li    t1, 0
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.13.condition

loop.13.condition:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 116(sp)
  lw    t1, 116(sp)
  li    t2, 5
  slt    t0, t1, t2
  sw    t0, 120(sp)
  lw    t0, 120(sp)
  bnez  t0, bneqzlable.94
  j     loop.13.end
bneqzlable.94:
  j     loop.13.body

loop.13.body:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 124(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 128(sp)
  lw    t1, 124(sp)
  lw    t2, 128(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 132(sp)
  lw    t0, 132(sp)
  lw    t1, 0(t0)
  sw    t1, 136(sp)
  li    t1, 0
  lw    t0, 132(sp)
  sw    t1, 0(t0)
  j     loop.13.update

loop.13.update:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 140(sp)
  lw    t1, 140(sp)
  addi   t0, t1, 1
  sw    t0, 144(sp)
  lw    t1, 144(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.13.condition

loop.13.end:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 148(sp)
  li    t1, 0
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.14.condition

loop.14.condition:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 152(sp)
  lw    t1, 152(sp)
  li    t2, 40
  slt    t0, t1, t2
  sw    t0, 156(sp)
  lw    t0, 156(sp)
  bnez  t0, bneqzlable.95
  j     loop.14.end
bneqzlable.95:
  j     loop.14.body

loop.14.body:
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 160(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 164(sp)
  lw    t1, 164(sp)
  li    t2, 8
  div    t0, t1, t2
  sw    t0, 168(sp)
  lw    t1, 160(sp)
  lw    t2, 168(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 172(sp)
  lw    t0, 172(sp)
  lw    t1, 0(t0)
  sw    t1, 176(sp)
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 180(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 184(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 188(sp)
  lw    t1, 188(sp)
  addi   t0, t1, 4
  sw    t0, 192(sp)
  addi   sp, sp, -16
  lw    t0, 196(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 200(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  lw    t0, 208(sp)
  mv    a2, t0
  sw    t0, 8(sp)
  call  __string_substring
  addi   sp, sp, 16
  sw    a0, 196(sp)
  addi   sp, sp, -16
  lw    t0, 212(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  hex2int
  addi   sp, sp, 16
  sw    a0, 200(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 204(sp)
  lw    t1, 204(sp)
  li    t2, 4
  div    t0, t1, t2
  sw    t0, 208(sp)
  lw    t1, 208(sp)
  li    t2, 2
  rem    t0, t1, t2
  sw    t0, 212(sp)
  li    t1, 1
  lw    t2, 212(sp)
  sub    t0, t1, t2
  sw    t0, 216(sp)
  lw    t1, 216(sp)
  li    t2, 16
  mul    t0, t1, t2
  sw    t0, 220(sp)
  lw    t1, 200(sp)
  lw    t2, 220(sp)
  sll    t0, t1, t2
  sw    t0, 224(sp)
  lw    t1, 176(sp)
  lw    t2, 224(sp)
  or     t0, t1, t2
  sw    t0, 228(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 232(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 236(sp)
  lw    t1, 236(sp)
  li    t2, 8
  div    t0, t1, t2
  sw    t0, 240(sp)
  lw    t1, 232(sp)
  lw    t2, 240(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 244(sp)
  lw    t0, 244(sp)
  lw    t1, 0(t0)
  sw    t1, 248(sp)
  lw    t1, 228(sp)
  lw    t0, 244(sp)
  sw    t1, 0(t0)
  j     loop.14.update

loop.14.update:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 252(sp)
  lw    t1, 252(sp)
  addi   t0, t1, 4
  sw    t0, 256(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 260(sp)
  lw    t1, 256(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.14.condition

loop.14.end:
  li    t1, 4
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 264(sp)
  li    t1, 1
  lw    t0, 52(sp)
  sw    t1, 0(t0)
  j     loop.15.condition

loop.15.condition:
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 268(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 272(sp)
  lw    t1, 268(sp)
  lw    t2, 272(sp)
  sgt    t0, t1, t2
  seqz   t0, t0
  sw    t0, 276(sp)
  lw    t0, 276(sp)
  bnez  t0, bneqzlable.96
  j     loop.15.end
bneqzlable.96:
  j     loop.15.body

loop.15.body:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 280(sp)
  li    t1, 0
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.16.condition

loop.16.condition:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 284(sp)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 288(sp)
  lw    t1, 284(sp)
  lw    t2, 288(sp)
  slt    t0, t1, t2
  sw    t0, 292(sp)
  lw    t0, 292(sp)
  bnez  t0, bneqzlable.97
  j     loop.16.end
bneqzlable.97:
  j     loop.16.body

loop.16.body:
  la    t6, @inputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 296(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 300(sp)
  lw    t1, 296(sp)
  lw    t2, 300(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 304(sp)
  lw    t0, 304(sp)
  lw    t1, 0(t0)
  sw    t1, 308(sp)
  li    t1, 48
  lw    t0, 304(sp)
  sw    t1, 0(t0)
  j     loop.16.update

loop.16.update:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 312(sp)
  lw    t1, 312(sp)
  addi   t0, t1, 1
  sw    t0, 316(sp)
  lw    t1, 316(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.16.condition

loop.16.end:
  j     loop.17.condition

loop.17.condition:
  li    t0, 1
  bnez  t0, bneqzlable.98
  j     loop.17.end
bneqzlable.98:
  j     loop.17.body

loop.17.body:
  la    t6, @inputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 320(sp)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 324(sp)
  addi   sp, sp, -16
  lw    t0, 336(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 340(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  sha1
  addi   sp, sp, 16
  sw    a0, 328(sp)
  lw    t1, 328(sp)
  lw    t0, 60(sp)
  sw    t1, 0(t0)
  lw    t0, 60(sp)
  lw    t1, 0(t0)
  sw    t1, 332(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 336(sp)
  addi   sp, sp, -16
  lw    t0, 348(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 352(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  array_equal
  addi   sp, sp, 16
  sw    a0, 340(sp)
  lw    t0, 340(sp)
  bnez  t0, bneqzlable.99
  j     if.19.else
bneqzlable.99:
  j     if.19.then

if.19.then:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 344(sp)
  li    t1, 0
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.18.condition

loop.18.condition:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 348(sp)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 352(sp)
  lw    t1, 348(sp)
  lw    t2, 352(sp)
  slt    t0, t1, t2
  sw    t0, 356(sp)
  lw    t0, 356(sp)
  bnez  t0, bneqzlable.100
  j     loop.18.end
bneqzlable.100:
  j     loop.18.body

loop.18.body:
  la    t6, @inputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 360(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 364(sp)
  lw    t1, 360(sp)
  lw    t2, 364(sp)
  li    t0, 4
  mul    t2, t2, t0
  add    t0, t1, t2
  sw    t0, 368(sp)
  lw    t0, 368(sp)
  lw    t1, 0(t0)
  sw    t1, 372(sp)
  addi   sp, sp, -16
  lw    t0, 388(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  int2chr
  addi   sp, sp, 16
  sw    a0, 376(sp)
  addi   sp, sp, -16
  lw    t0, 392(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  print
  addi   sp, sp, 16
  j     loop.18.update

loop.18.update:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 380(sp)
  lw    t1, 380(sp)
  addi   t0, t1, 1
  sw    t0, 384(sp)
  lw    t1, 384(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  j     loop.18.condition

loop.18.end:
  addi   sp, sp, -16
  la    t6, @string.1
  mv    t0, t6
  mv    a0, t0
  sw    t0, 0(sp)
  call  println
  addi   sp, sp, 16
  j     func.crackSHA1.end

if.19.else:
  j     if.19.end

if.19.end:
  la    t6, @inputBuffer.0
  mv    t0, t6
  lw    t1, 0(t0)
  sw    t1, 388(sp)
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 392(sp)
  addi   sp, sp, -16
  lw    t0, 404(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  lw    t0, 408(sp)
  mv    a1, t0
  sw    t0, 4(sp)
  call  nextText
  addi   sp, sp, 16
  sw    a0, 396(sp)
  li    t1, 1
  lw    t2, 396(sp)
  xor    t0, t1, t2
  sw    t0, 400(sp)
  lw    t0, 400(sp)
  bnez  t0, bneqzlable.101
  j     if.20.else
bneqzlable.101:
  j     if.20.then

if.20.then:
  j     loop.17.end

if.20.else:
  j     if.20.end

if.20.end:
  j     loop.17.update

loop.17.update:
  j     loop.17.condition

loop.17.end:
  j     loop.15.update

loop.15.update:
  lw    t0, 52(sp)
  lw    t1, 0(t0)
  sw    t1, 404(sp)
  lw    t1, 404(sp)
  addi   t0, t1, 1
  sw    t0, 408(sp)
  lw    t1, 408(sp)
  lw    t0, 52(sp)
  sw    t1, 0(t0)
  j     loop.15.condition

loop.15.end:
  addi   sp, sp, -16
  la    t6, @string.5
  mv    t0, t6
  mv    a0, t0
  sw    t0, 0(sp)
  call  println
  addi   sp, sp, 16
  j     func.crackSHA1.end

func.crackSHA1.end:
  lw    ra, 0(sp)
  addi   sp, sp, 416
  ret   


  .globl main
main:
  addi   sp, sp, -144
  sw    ra, 0(sp)
  addi   t0, sp, 8
  sw    t0, 4(sp)
  addi   t0, sp, 16
  sw    t0, 12(sp)
  addi   t0, sp, 24
  sw    t0, 20(sp)
  addi   t0, sp, 32
  sw    t0, 28(sp)
  addi   t0, sp, 40
  sw    t0, 36(sp)
  addi   t0, sp, 48
  sw    t0, 44(sp)
  

  addi   sp, sp, 0
  call  __init__
  addi   sp, sp, 0
  li    t1, 0
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  li    t1, 1
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 52(sp)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 56(sp)
  lw    t1, 52(sp)
  lw    t0, 28(sp)
  sw    t1, 0(t0)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 60(sp)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 64(sp)
  lw    t1, 60(sp)
  lw    t0, 12(sp)
  sw    t1, 0(t0)
  lw    t0, 28(sp)
  lw    t1, 0(t0)
  sw    t1, 68(sp)
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 72(sp)
  lw    t1, 68(sp)
  lw    t0, 20(sp)
  sw    t1, 0(t0)
  lw    t0, 12(sp)
  lw    t1, 0(t0)
  sw    t1, 76(sp)
  addi   sp, sp, -16
  lw    t0, 92(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printInt
  addi   sp, sp, 16
  lw    t0, 20(sp)
  lw    t1, 0(t0)
  sw    t1, 80(sp)
  addi   sp, sp, -16
  lw    t0, 96(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  printInt
  addi   sp, sp, 16
  j     loop.19.condition

loop.19.condition:
  li    t0, 1
  bnez  t0, bneqzlable.102
  j     loop.19.end
bneqzlable.102:
  j     loop.19.body

loop.19.body:
  addi   sp, sp, 0
  call  getInt
  addi   sp, sp, 0
  sw    a0, 84(sp)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 88(sp)
  lw    t1, 84(sp)
  lw    t0, 36(sp)
  sw    t1, 0(t0)
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 92(sp)
  lw    t1, 92(sp)
  li    t2, 0
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 96(sp)
  lw    t0, 96(sp)
  bnez  t0, bneqzlable.103
  j     if.21.else
bneqzlable.103:
  j     if.21.then

if.21.then:
  j     loop.19.end

if.21.else:
  j     if.21.end

if.21.end:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 100(sp)
  lw    t1, 100(sp)
  li    t2, 1
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 104(sp)
  lw    t0, 104(sp)
  bnez  t0, bneqzlable.104
  j     if.22.else
bneqzlable.104:
  j     if.22.then

if.22.then:
  addi   sp, sp, 0
  call  getString
  addi   sp, sp, 0
  sw    a0, 108(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 112(sp)
  lw    t1, 108(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 116(sp)
  addi   sp, sp, -16
  lw    t0, 132(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  computeSHA1
  addi   sp, sp, 16
  j     if.22.end

if.22.else:
  lw    t0, 36(sp)
  lw    t1, 0(t0)
  sw    t1, 120(sp)
  lw    t1, 120(sp)
  li    t2, 2
  sub    t0, t1, t2
  seqz   t0, t0
  sw    t0, 124(sp)
  lw    t0, 124(sp)
  bnez  t0, bneqzlable.105
  j     if.23.else
bneqzlable.105:
  j     if.23.then

if.23.then:
  addi   sp, sp, 0
  call  getString
  addi   sp, sp, 0
  sw    a0, 128(sp)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 132(sp)
  lw    t1, 128(sp)
  lw    t0, 44(sp)
  sw    t1, 0(t0)
  lw    t0, 44(sp)
  lw    t1, 0(t0)
  sw    t1, 136(sp)
  addi   sp, sp, -16
  lw    t0, 152(sp)
  mv    a0, t0
  sw    t0, 0(sp)
  call  crackSHA1
  addi   sp, sp, 16
  j     if.23.end

if.23.else:
  j     if.23.end

if.23.end:
  j     if.22.end

if.22.end:
  j     loop.19.update

loop.19.update:
  j     loop.19.condition

loop.19.end:
  li    t1, 0
  lw    t0, 4(sp)
  sw    t1, 0(t0)
  j     func.main.end

func.main.end:
  lw    t0, 4(sp)
  lw    t1, 0(t0)
  sw    t1, 140(sp)
  lw    a0, 140(sp)
  lw    ra, 0(sp)
  addi   sp, sp, 144
  ret   



