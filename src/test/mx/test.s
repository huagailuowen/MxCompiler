  .data
  .rodata
@string.2:
  .string "\nabbdbdd\\\\\"d\"c\\adc\"c\00"
@string.0:
  .string "\"\nbda\\\"\ndbd\\c\"\\\"c\\c\"\00"
@string.1:
  .string "\\ab\nac\"\"add\n\"\nac\ndb\"\00"
  .text
  .globl __init__
__init__:
  addi   sp, sp, -112
  sw    ra, 0(sp)

__init___recursive:
  lw    ra, 0(sp)
  addi   sp, sp, 112
  ret   


  .globl main
main:
defaultStart.__init__._0:
  addi   sp, sp, -112
  sw    ra, 0(sp)
  sw    s11, 88(sp)

main_recursive:
  la    a0, @string.2
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.2
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.1
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.1
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.1
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.0
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.1
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.0
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.2
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.0
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print
  la    a0, @string.0
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.1
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  la    a1, @string.2
  call  __string_concat
  mv    s11, a0
  mv    a0, s11
  call  print

func.main.end:
  li    a0, 0
  lw    ra, 0(sp)
  lw    s11, 88(sp)
  addi   sp, sp, 112
  ret   



