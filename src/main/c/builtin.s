	.text
	.attribute	4, 16
	.attribute	5, "rv32i2p1_m2p0_a2p1_c2p0"
	.file	"builtin.c"
	.globl	print                           # -- Begin function print
	.p2align	1
	.type	print,@function
print:                                  # @print
# %bb.0:
	lui	a1, %hi(.L.str)
	addi	a1, a1, %lo(.L.str)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end0:
	.size	print, .Lfunc_end0-print
                                        # -- End function
	.globl	println                         # -- Begin function println
	.p2align	1
	.type	println,@function
println:                                # @println
# %bb.0:
	tail	puts
.Lfunc_end1:
	.size	println, .Lfunc_end1-println
                                        # -- End function
	.globl	printInt                        # -- Begin function printInt
	.p2align	1
	.type	printInt,@function
printInt:                               # @printInt
# %bb.0:
	lui	a1, %hi(.L.str.1)
	addi	a1, a1, %lo(.L.str.1)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt
                                        # -- End function
	.globl	printlnInt                      # -- Begin function printlnInt
	.p2align	1
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
# %bb.0:
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	mv	a2, a0
	mv	a0, a1
	mv	a1, a2
	tail	printf
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt
                                        # -- End function
	.globl	getString                       # -- Begin function getString
	.p2align	1
	.type	getString,@function
getString:                              # @getString
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	lui	a0, 1
	call	malloc
	mv	s0, a0
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	mv	a1, s0
	call	scanf
	mv	a0, s0
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString
                                        # -- End function
	.globl	getInt                          # -- Begin function getInt
	.p2align	1
	.type	getInt,@function
getInt:                                 # @getInt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	addi	a1, sp, 8
	call	scanf
	lw	a0, 8(sp)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt
                                        # -- End function
	.globl	toString                        # -- Begin function toString
	.p2align	1
	.type	toString,@function
toString:                               # @toString
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	sw	s1, 4(sp)                       # 4-byte Folded Spill
	mv	s0, a0
	li	a0, 10
	call	malloc
	mv	s1, a0
	lui	a0, %hi(.L.str.1)
	addi	a1, a0, %lo(.L.str.1)
	mv	a0, s1
	mv	a2, s0
	call	sprintf
	mv	a0, s1
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	lw	s1, 4(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString
                                        # -- End function
	.globl	toString_bool                   # -- Begin function toString_bool
	.p2align	1
	.type	toString_bool,@function
toString_bool:                          # @toString_bool
# %bb.0:
	bnez	a0, .LBB7_2
# %bb.1:
	lui	a0, %hi(.L.str.4)
	addi	a0, a0, %lo(.L.str.4)
	ret
.LBB7_2:
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	ret
.Lfunc_end7:
	.size	toString_bool, .Lfunc_end7-toString_bool
                                        # -- End function
	.globl	__malloc_array                  # -- Begin function __malloc_array
	.p2align	1
	.type	__malloc_array,@function
__malloc_array:                         # @__malloc_array
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	mv	s0, a0
	mul	a0, a1, a0
	addi	a0, a0, 4
	call	malloc
	addi	a1, a0, 4
	sw	s0, 0(a0)
	mv	a0, a1
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end8:
	.size	__malloc_array, .Lfunc_end8-__malloc_array
                                        # -- End function
	.globl	__malloc                        # -- Begin function __malloc
	.p2align	1
	.type	__malloc,@function
__malloc:                               # @__malloc
# %bb.0:
	mul	a0, a1, a0
	tail	malloc
.Lfunc_end9:
	.size	__malloc, .Lfunc_end9-__malloc
                                        # -- End function
	.globl	buildInArraySize                # -- Begin function buildInArraySize
	.p2align	1
	.type	buildInArraySize,@function
buildInArraySize:                       # @buildInArraySize
# %bb.0:
	lw	a0, -4(a0)
	ret
.Lfunc_end10:
	.size	buildInArraySize, .Lfunc_end10-buildInArraySize
                                        # -- End function
	.globl	__string_length                 # -- Begin function __string_length
	.p2align	1
	.type	__string_length,@function
__string_length:                        # @__string_length
# %bb.0:
	li	a1, 0
.LBB11_1:                               # =>This Inner Loop Header: Depth=1
	add	a2, a0, a1
	lbu	a2, 0(a2)
	addi	a1, a1, 1
	bnez	a2, .LBB11_1
# %bb.2:
	addi	a0, a1, -1
	ret
.Lfunc_end11:
	.size	__string_length, .Lfunc_end11-__string_length
                                        # -- End function
	.globl	__string_substring              # -- Begin function __string_substring
	.p2align	1
	.type	__string_substring,@function
__string_substring:                     # @__string_substring
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	sw	s1, 20(sp)                      # 4-byte Folded Spill
	sw	s2, 16(sp)                      # 4-byte Folded Spill
	sw	s3, 12(sp)                      # 4-byte Folded Spill
	mv	s3, a1
	mv	s2, a0
	sub	s1, a2, a1
	addi	a0, s1, 1
	call	malloc
	mv	s0, a0
	add	a1, s2, s3
	mv	a2, s1
	call	memcpy
	add	s1, s1, s0
	sb	zero, 0(s1)
	mv	a0, s0
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	lw	s1, 20(sp)                      # 4-byte Folded Reload
	lw	s2, 16(sp)                      # 4-byte Folded Reload
	lw	s3, 12(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end12:
	.size	__string_substring, .Lfunc_end12-__string_substring
                                        # -- End function
	.globl	__string_parseInt               # -- Begin function __string_parseInt
	.p2align	1
	.type	__string_parseInt,@function
__string_parseInt:                      # @__string_parseInt
# %bb.0:
	lbu	a2, 0(a0)
	beqz	a2, .LBB13_4
# %bb.1:
	mv	a1, a0
	li	a0, 0
	addi	a1, a1, 1
	li	a3, 10
.LBB13_2:                               # =>This Inner Loop Header: Depth=1
	andi	a4, a2, 255
	lbu	a2, 0(a1)
	mul	a0, a0, a3
	add	a0, a0, a4
	addi	a0, a0, -48
	addi	a1, a1, 1
	bnez	a2, .LBB13_2
# %bb.3:
	ret
.LBB13_4:
	li	a0, 0
	ret
.Lfunc_end13:
	.size	__string_parseInt, .Lfunc_end13-__string_parseInt
                                        # -- End function
	.globl	__string_ord                    # -- Begin function __string_ord
	.p2align	1
	.type	__string_ord,@function
__string_ord:                           # @__string_ord
# %bb.0:
	add	a0, a0, a1
	lbu	a0, 0(a0)
	ret
.Lfunc_end14:
	.size	__string_ord, .Lfunc_end14-__string_ord
                                        # -- End function
	.globl	__string_eq                     # -- Begin function __string_eq
	.p2align	1
	.type	__string_eq,@function
__string_eq:                            # @__string_eq
# %bb.0:
	lbu	a2, 0(a0)
	beqz	a2, .LBB15_7
# %bb.1:
	li	a3, 0
	addi	a0, a0, 1
.LBB15_2:                               # =>This Inner Loop Header: Depth=1
	add	a4, a1, a3
	lbu	a4, 0(a4)
	beqz	a4, .LBB15_6
# %bb.3:                                #   in Loop: Header=BB15_2 Depth=1
	andi	a2, a2, 255
	bne	a2, a4, .LBB15_6
# %bb.4:                                #   in Loop: Header=BB15_2 Depth=1
	add	a2, a0, a3
	lbu	a2, 0(a2)
	addi	a4, a3, 1
	mv	a3, a4
	bnez	a2, .LBB15_2
# %bb.5:
	add	a1, a1, a4
	lbu	a0, 0(a1)
	beqz	a0, .LBB15_8
.LBB15_6:
	li	a0, 0
	ret
.LBB15_7:
	li	a4, 0
	mv	a1, a1
	lbu	a0, 0(a1)
	bnez	a0, .LBB15_6
.LBB15_8:
	li	a0, 1
	ret
.Lfunc_end15:
	.size	__string_eq, .Lfunc_end15-__string_eq
                                        # -- End function
	.globl	__string_ne                     # -- Begin function __string_ne
	.p2align	1
	.type	__string_ne,@function
__string_ne:                            # @__string_ne
# %bb.0:
	lbu	a2, 0(a0)
	beqz	a2, .LBB16_8
# %bb.1:
	li	a3, 0
	addi	a0, a0, 1
.LBB16_2:                               # =>This Inner Loop Header: Depth=1
	add	a4, a1, a3
	lbu	a4, 0(a4)
	beqz	a4, .LBB16_6
# %bb.3:                                #   in Loop: Header=BB16_2 Depth=1
	andi	a2, a2, 255
	bne	a2, a4, .LBB16_6
# %bb.4:                                #   in Loop: Header=BB16_2 Depth=1
	add	a2, a0, a3
	lbu	a2, 0(a2)
	addi	a4, a3, 1
	mv	a3, a4
	bnez	a2, .LBB16_2
# %bb.5:
	add	a1, a1, a4
	lbu	a0, 0(a1)
	beqz	a0, .LBB16_7
.LBB16_6:
	li	a0, 1
.LBB16_7:
	ret
.LBB16_8:
	li	a4, 0
	mv	a1, a1
	lbu	a0, 0(a1)
	bnez	a0, .LBB16_6
	j	.LBB16_7
.Lfunc_end16:
	.size	__string_ne, .Lfunc_end16-__string_ne
                                        # -- End function
	.globl	__string_lt                     # -- Begin function __string_lt
	.p2align	1
	.type	__string_lt,@function
__string_lt:                            # @__string_lt
# %bb.0:
	lbu	a2, 0(a0)
	beqz	a2, .LBB17_8
# %bb.1:
	li	a3, 0
	addi	a0, a0, 1
.LBB17_2:                               # =>This Inner Loop Header: Depth=1
	add	a4, a1, a3
	lbu	a4, 0(a4)
	beqz	a4, .LBB17_6
# %bb.3:                                #   in Loop: Header=BB17_2 Depth=1
	andi	a2, a2, 255
	bgeu	a2, a4, .LBB17_9
# %bb.4:                                #   in Loop: Header=BB17_2 Depth=1
	add	a2, a0, a3
	lbu	a2, 0(a2)
	addi	a4, a3, 1
	mv	a3, a4
	bnez	a2, .LBB17_2
# %bb.5:
	add	a1, a1, a4
	lbu	a0, 0(a1)
	beqz	a0, .LBB17_7
.LBB17_6:
	li	a0, 1
.LBB17_7:
	ret
.LBB17_8:
	li	a4, 0
	mv	a1, a1
	lbu	a0, 0(a1)
	bnez	a0, .LBB17_6
	j	.LBB17_7
.LBB17_9:
	li	a0, 0
	ret
.Lfunc_end17:
	.size	__string_lt, .Lfunc_end17-__string_lt
                                        # -- End function
	.globl	__string_le                     # -- Begin function __string_le
	.p2align	1
	.type	__string_le,@function
__string_le:                            # @__string_le
# %bb.0:
	lbu	a2, 0(a1)
	beqz	a2, .LBB18_7
# %bb.1:
	li	a3, 0
	addi	a1, a1, 1
.LBB18_2:                               # =>This Inner Loop Header: Depth=1
	add	a4, a0, a3
	lbu	a4, 0(a4)
	beqz	a4, .LBB18_6
# %bb.3:                                #   in Loop: Header=BB18_2 Depth=1
	andi	a2, a2, 255
	bgeu	a2, a4, .LBB18_8
# %bb.4:                                #   in Loop: Header=BB18_2 Depth=1
	add	a2, a1, a3
	lbu	a2, 0(a2)
	addi	a4, a3, 1
	mv	a3, a4
	bnez	a2, .LBB18_2
# %bb.5:
	add	a0, a0, a4
	lbu	a0, 0(a0)
	beqz	a0, .LBB18_8
.LBB18_6:
	li	a0, 0
	ret
.LBB18_7:
	li	a4, 0
	mv	a0, a0
	lbu	a0, 0(a0)
	bnez	a0, .LBB18_6
.LBB18_8:
	li	a0, 1
	ret
.Lfunc_end18:
	.size	__string_le, .Lfunc_end18-__string_le
                                        # -- End function
	.globl	__string_gt                     # -- Begin function __string_gt
	.p2align	1
	.type	__string_gt,@function
__string_gt:                            # @__string_gt
# %bb.0:
	lbu	a2, 0(a1)
	beqz	a2, .LBB19_8
# %bb.1:
	li	a3, 0
	addi	a1, a1, 1
.LBB19_2:                               # =>This Inner Loop Header: Depth=1
	add	a4, a0, a3
	lbu	a4, 0(a4)
	beqz	a4, .LBB19_6
# %bb.3:                                #   in Loop: Header=BB19_2 Depth=1
	andi	a2, a2, 255
	bgeu	a2, a4, .LBB19_9
# %bb.4:                                #   in Loop: Header=BB19_2 Depth=1
	add	a2, a1, a3
	lbu	a2, 0(a2)
	addi	a4, a3, 1
	mv	a3, a4
	bnez	a2, .LBB19_2
# %bb.5:
	add	a0, a0, a4
	lbu	a0, 0(a0)
	beqz	a0, .LBB19_7
.LBB19_6:
	li	a0, 1
.LBB19_7:
	ret
.LBB19_8:
	li	a4, 0
	mv	a0, a0
	lbu	a0, 0(a0)
	bnez	a0, .LBB19_6
	j	.LBB19_7
.LBB19_9:
	li	a0, 0
	ret
.Lfunc_end19:
	.size	__string_gt, .Lfunc_end19-__string_gt
                                        # -- End function
	.globl	__string_ge                     # -- Begin function __string_ge
	.p2align	1
	.type	__string_ge,@function
__string_ge:                            # @__string_ge
# %bb.0:
	lbu	a2, 0(a0)
	beqz	a2, .LBB20_7
# %bb.1:
	li	a3, 0
	addi	a0, a0, 1
.LBB20_2:                               # =>This Inner Loop Header: Depth=1
	add	a4, a1, a3
	lbu	a4, 0(a4)
	beqz	a4, .LBB20_6
# %bb.3:                                #   in Loop: Header=BB20_2 Depth=1
	andi	a2, a2, 255
	bgeu	a2, a4, .LBB20_8
# %bb.4:                                #   in Loop: Header=BB20_2 Depth=1
	add	a2, a0, a3
	lbu	a2, 0(a2)
	addi	a4, a3, 1
	mv	a3, a4
	bnez	a2, .LBB20_2
# %bb.5:
	add	a1, a1, a4
	lbu	a0, 0(a1)
	beqz	a0, .LBB20_8
.LBB20_6:
	li	a0, 0
	ret
.LBB20_7:
	li	a4, 0
	mv	a1, a1
	lbu	a0, 0(a1)
	bnez	a0, .LBB20_6
.LBB20_8:
	li	a0, 1
	ret
.Lfunc_end20:
	.size	__string_ge, .Lfunc_end20-__string_ge
                                        # -- End function
	.globl	__string_concat                 # -- Begin function __string_concat
	.p2align	1
	.type	__string_concat,@function
__string_concat:                        # @__string_concat
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	sw	s1, 20(sp)                      # 4-byte Folded Spill
	sw	s2, 16(sp)                      # 4-byte Folded Spill
	sw	s3, 12(sp)                      # 4-byte Folded Spill
	sw	s4, 8(sp)                       # 4-byte Folded Spill
	sw	s5, 4(sp)                       # 4-byte Folded Spill
	mv	s2, a1
	mv	s4, a0
	li	s1, -1
.LBB21_1:                               # =>This Inner Loop Header: Depth=1
	add	a0, s4, s1
	lbu	a0, 1(a0)
	addi	s1, s1, 1
	bnez	a0, .LBB21_1
# %bb.2:
	li	s0, 0
	addi	s5, s1, -1
.LBB21_3:                               # =>This Inner Loop Header: Depth=1
	add	a0, s2, s0
	lbu	a0, 0(a0)
	addi	s0, s0, 1
	addi	s5, s5, 1
	bnez	a0, .LBB21_3
# %bb.4:
	addi	a0, s5, 1
	call	malloc
	mv	s3, a0
	mv	a1, s4
	mv	a2, s1
	call	memcpy
	add	a0, s3, s1
	addi	a2, s0, -1
	mv	a1, s2
	call	memcpy
	add	s5, s5, s3
	sb	zero, 0(s5)
	mv	a0, s3
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	lw	s1, 20(sp)                      # 4-byte Folded Reload
	lw	s2, 16(sp)                      # 4-byte Folded Reload
	lw	s3, 12(sp)                      # 4-byte Folded Reload
	lw	s4, 8(sp)                       # 4-byte Folded Reload
	lw	s5, 4(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end21:
	.size	__string_concat, .Lfunc_end21-__string_concat
                                        # -- End function
	.type	.L.str,@object                  # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"%d"
	.size	.L.str.1, 3

	.type	.L.str.2,@object                # @.str.2
.L.str.2:
	.asciz	"%d\n"
	.size	.L.str.2, 4

	.type	.L.str.3,@object                # @.str.3
.L.str.3:
	.asciz	"true"
	.size	.L.str.3, 5

	.type	.L.str.4,@object                # @.str.4
.L.str.4:
	.asciz	"false"
	.size	.L.str.4, 6

	.ident	"Ubuntu clang version 18.1.3 (1ubuntu1)"
	.section	".note.GNU-stack","",@progbits
	.addrsig
