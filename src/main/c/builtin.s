	.text
	.attribute	4, 16
	.attribute	5, "rv32i2p1_m2p0_a2p1"
	.file	"builtin.c"
	.option	push
	.option	arch, +c
	.globl	print                           # -- Begin function print
	.p2align	1
	.type	print,@function
print:                                  # @print
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	printf
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end0:
	.size	print, .Lfunc_end0-print
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	println                         # -- Begin function println
	.p2align	1
	.type	println,@function
println:                                # @println
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.1)
	addi	a0, a0, %lo(.L.str.1)
	call	printf
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end1:
	.size	println, .Lfunc_end1-println
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	printInt                        # -- Begin function printInt
	.p2align	1
	.type	printInt,@function
printInt:                               # @printInt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	call	printf
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end2:
	.size	printInt, .Lfunc_end2-printInt
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	printlnInt                      # -- Begin function printlnInt
	.p2align	1
	.type	printlnInt,@function
printlnInt:                             # @printlnInt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str.3)
	addi	a0, a0, %lo(.L.str.3)
	call	printf
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end3:
	.size	printlnInt, .Lfunc_end3-printlnInt
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	getString                       # -- Begin function getString
	.p2align	1
	.type	getString,@function
getString:                              # @getString
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	lui	a0, 1
	call	malloc
	sw	a0, -12(s0)
	lw	a1, -12(s0)
	lui	a0, %hi(.L.str)
	addi	a0, a0, %lo(.L.str)
	call	scanf
	lw	a0, -12(s0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end4:
	.size	getString, .Lfunc_end4-getString
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	getInt                          # -- Begin function getInt
	.p2align	1
	.type	getInt,@function
getInt:                                 # @getInt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	lui	a0, %hi(.L.str.2)
	addi	a0, a0, %lo(.L.str.2)
	addi	a1, s0, -12
	call	scanf
	lw	a0, -12(s0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end5:
	.size	getInt, .Lfunc_end5-getInt
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	toString                        # -- Begin function toString
	.p2align	1
	.type	toString,@function
toString:                               # @toString
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	li	a0, 10
	call	malloc
	sw	a0, -16(s0)
	lw	a0, -16(s0)
	lw	a2, -12(s0)
	lui	a1, %hi(.L.str.2)
	addi	a1, a1, %lo(.L.str.2)
	call	sprintf
	lw	a0, -16(s0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end6:
	.size	toString, .Lfunc_end6-toString
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	toString_bool                   # -- Begin function toString_bool
	.p2align	1
	.type	toString_bool,@function
toString_bool:                          # @toString_bool
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sb	a0, -13(s0)
	lbu	a0, -13(s0)
	andi	a0, a0, 1
	beqz	a0, .LBB7_2
	j	.LBB7_1
.LBB7_1:
	lui	a0, %hi(.L.str.4)
	addi	a0, a0, %lo(.L.str.4)
	sw	a0, -12(s0)
	j	.LBB7_3
.LBB7_2:
	lui	a0, %hi(.L.str.5)
	addi	a0, a0, %lo(.L.str.5)
	sw	a0, -12(s0)
	j	.LBB7_3
.LBB7_3:
	lw	a0, -12(s0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end7:
	.size	toString_bool, .Lfunc_end7-toString_bool
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__malloc_array                  # -- Begin function __malloc_array
	.p2align	1
	.type	__malloc_array,@function
__malloc_array:                         # @__malloc_array
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	addi	s0, sp, 32
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -16(s0)
	lw	a1, -12(s0)
	mul	a0, a0, a1
	addi	a0, a0, 4
	call	malloc
	sw	a0, -20(s0)
	lw	a0, -12(s0)
	lw	a1, -20(s0)
	sw	a0, 0(a1)
	lw	a0, -20(s0)
	addi	a0, a0, 4
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end8:
	.size	__malloc_array, .Lfunc_end8-__malloc_array
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__malloc                        # -- Begin function __malloc
	.p2align	1
	.type	__malloc,@function
__malloc:                               # @__malloc
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -16(s0)
	lw	a1, -12(s0)
	mul	a0, a0, a1
	call	malloc
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end9:
	.size	__malloc, .Lfunc_end9-__malloc
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	buildInArraySize                # -- Begin function buildInArraySize
	.p2align	1
	.type	buildInArraySize,@function
buildInArraySize:                       # @buildInArraySize
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	lw	a0, -12(s0)
	lw	a0, -4(a0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end10:
	.size	buildInArraySize, .Lfunc_end10-buildInArraySize
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_length                 # -- Begin function __string_length
	.p2align	1
	.type	__string_length,@function
__string_length:                        # @__string_length
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	zero, -16(s0)
	j	.LBB11_1
.LBB11_1:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	beqz	a0, .LBB11_3
	j	.LBB11_2
.LBB11_2:                               #   in Loop: Header=BB11_1 Depth=1
	lw	a0, -16(s0)
	addi	a0, a0, 1
	sw	a0, -16(s0)
	j	.LBB11_1
.LBB11_3:
	lw	a0, -16(s0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end11:
	.size	__string_length, .Lfunc_end11-__string_length
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_substring              # -- Begin function __string_substring
	.p2align	1
	.type	__string_substring,@function
__string_substring:                     # @__string_substring
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	addi	s0, sp, 32
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	sw	a2, -20(s0)
	lw	a0, -20(s0)
	lw	a1, -16(s0)
	sub	a0, a0, a1
	sw	a0, -24(s0)
	lw	a0, -24(s0)
	addi	a0, a0, 1
	call	malloc
	sw	a0, -28(s0)
	sw	zero, -32(s0)
	j	.LBB12_1
.LBB12_1:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -32(s0)
	lw	a1, -24(s0)
	bge	a0, a1, .LBB12_4
	j	.LBB12_2
.LBB12_2:                               #   in Loop: Header=BB12_1 Depth=1
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	lw	a2, -32(s0)
	add	a1, a1, a2
	add	a0, a0, a1
	lbu	a0, 0(a0)
	lw	a1, -28(s0)
	add	a1, a1, a2
	sb	a0, 0(a1)
	j	.LBB12_3
.LBB12_3:                               #   in Loop: Header=BB12_1 Depth=1
	lw	a0, -32(s0)
	addi	a0, a0, 1
	sw	a0, -32(s0)
	j	.LBB12_1
.LBB12_4:
	lw	a0, -28(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	sb	zero, 0(a0)
	lw	a0, -28(s0)
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end12:
	.size	__string_substring, .Lfunc_end12-__string_substring
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_parseInt               # -- Begin function __string_parseInt
	.p2align	1
	.type	__string_parseInt,@function
__string_parseInt:                      # @__string_parseInt
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	addi	s0, sp, 32
	sw	a0, -12(s0)
	sw	zero, -16(s0)
	sw	zero, -20(s0)
	j	.LBB13_1
.LBB13_1:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -12(s0)
	lw	a1, -20(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	beqz	a0, .LBB13_3
	j	.LBB13_2
.LBB13_2:                               #   in Loop: Header=BB13_1 Depth=1
	lw	a0, -16(s0)
	li	a1, 10
	mul	a0, a0, a1
	lw	a1, -12(s0)
	lw	a2, -20(s0)
	add	a1, a1, a2
	lbu	a1, 0(a1)
	add	a0, a0, a1
	addi	a0, a0, -48
	sw	a0, -16(s0)
	lw	a0, -20(s0)
	addi	a0, a0, 1
	sw	a0, -20(s0)
	j	.LBB13_1
.LBB13_3:
	lw	a0, -16(s0)
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end13:
	.size	__string_parseInt, .Lfunc_end13-__string_parseInt
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_ord                    # -- Begin function __string_ord
	.p2align	1
	.type	__string_ord,@function
__string_ord:                           # @__string_ord
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end14:
	.size	__string_ord, .Lfunc_end14-__string_ord
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_eq                     # -- Begin function __string_eq
	.p2align	1
	.type	__string_eq,@function
__string_eq:                            # @__string_eq
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	addi	s0, sp, 32
	sw	a0, -16(s0)
	sw	a1, -20(s0)
	sw	zero, -24(s0)
	j	.LBB15_1
.LBB15_1:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a1, 0(a0)
	li	a0, 0
	beqz	a1, .LBB15_3
	j	.LBB15_2
.LBB15_2:                               #   in Loop: Header=BB15_1 Depth=1
	lw	a0, -20(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	snez	a0, a0
	j	.LBB15_3
.LBB15_3:                               #   in Loop: Header=BB15_1 Depth=1
	andi	a0, a0, 1
	beqz	a0, .LBB15_7
	j	.LBB15_4
.LBB15_4:                               #   in Loop: Header=BB15_1 Depth=1
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	lw	a2, -20(s0)
	add	a1, a1, a2
	lbu	a1, 0(a1)
	beq	a0, a1, .LBB15_6
	j	.LBB15_5
.LBB15_5:
	sw	zero, -12(s0)
	j	.LBB15_11
.LBB15_6:                               #   in Loop: Header=BB15_1 Depth=1
	lw	a0, -24(s0)
	addi	a0, a0, 1
	sw	a0, -24(s0)
	j	.LBB15_1
.LBB15_7:
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	bnez	a0, .LBB15_10
	j	.LBB15_8
.LBB15_8:
	lw	a0, -20(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	bnez	a0, .LBB15_10
	j	.LBB15_9
.LBB15_9:
	li	a0, 1
	sw	a0, -12(s0)
	j	.LBB15_11
.LBB15_10:
	sw	zero, -12(s0)
	j	.LBB15_11
.LBB15_11:
	lw	a0, -12(s0)
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end15:
	.size	__string_eq, .Lfunc_end15-__string_eq
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_ne                     # -- Begin function __string_ne
	.p2align	1
	.type	__string_ne,@function
__string_ne:                            # @__string_ne
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	__string_eq
	seqz	a0, a0
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end16:
	.size	__string_ne, .Lfunc_end16-__string_ne
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_lt                     # -- Begin function __string_lt
	.p2align	1
	.type	__string_lt,@function
__string_lt:                            # @__string_lt
# %bb.0:
	addi	sp, sp, -32
	sw	ra, 28(sp)                      # 4-byte Folded Spill
	sw	s0, 24(sp)                      # 4-byte Folded Spill
	addi	s0, sp, 32
	sw	a0, -16(s0)
	sw	a1, -20(s0)
	sw	zero, -24(s0)
	j	.LBB17_1
.LBB17_1:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a1, 0(a0)
	li	a0, 0
	beqz	a1, .LBB17_3
	j	.LBB17_2
.LBB17_2:                               #   in Loop: Header=BB17_1 Depth=1
	lw	a0, -20(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	snez	a0, a0
	j	.LBB17_3
.LBB17_3:                               #   in Loop: Header=BB17_1 Depth=1
	andi	a0, a0, 1
	beqz	a0, .LBB17_7
	j	.LBB17_4
.LBB17_4:                               #   in Loop: Header=BB17_1 Depth=1
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	lw	a2, -20(s0)
	add	a1, a1, a2
	lbu	a1, 0(a1)
	blt	a0, a1, .LBB17_6
	j	.LBB17_5
.LBB17_5:
	sw	zero, -12(s0)
	j	.LBB17_11
.LBB17_6:                               #   in Loop: Header=BB17_1 Depth=1
	lw	a0, -24(s0)
	addi	a0, a0, 1
	sw	a0, -24(s0)
	j	.LBB17_1
.LBB17_7:
	lw	a0, -16(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	bnez	a0, .LBB17_10
	j	.LBB17_8
.LBB17_8:
	lw	a0, -20(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	bnez	a0, .LBB17_10
	j	.LBB17_9
.LBB17_9:
	sw	zero, -12(s0)
	j	.LBB17_11
.LBB17_10:
	li	a0, 1
	sw	a0, -12(s0)
	j	.LBB17_11
.LBB17_11:
	lw	a0, -12(s0)
	lw	ra, 28(sp)                      # 4-byte Folded Reload
	lw	s0, 24(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 32
	ret
.Lfunc_end17:
	.size	__string_lt, .Lfunc_end17-__string_lt
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_le                     # -- Begin function __string_le
	.p2align	1
	.type	__string_le,@function
__string_le:                            # @__string_le
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -16(s0)
	lw	a1, -12(s0)
	call	__string_lt
	seqz	a0, a0
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end18:
	.size	__string_le, .Lfunc_end18-__string_le
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_gt                     # -- Begin function __string_gt
	.p2align	1
	.type	__string_gt,@function
__string_gt:                            # @__string_gt
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -16(s0)
	lw	a1, -12(s0)
	call	__string_lt
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end19:
	.size	__string_gt, .Lfunc_end19-__string_gt
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_ge                     # -- Begin function __string_ge
	.p2align	1
	.type	__string_ge,@function
__string_ge:                            # @__string_ge
# %bb.0:
	addi	sp, sp, -16
	sw	ra, 12(sp)                      # 4-byte Folded Spill
	sw	s0, 8(sp)                       # 4-byte Folded Spill
	addi	s0, sp, 16
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	lw	a1, -16(s0)
	call	__string_lt
	seqz	a0, a0
	lw	ra, 12(sp)                      # 4-byte Folded Reload
	lw	s0, 8(sp)                       # 4-byte Folded Reload
	addi	sp, sp, 16
	ret
.Lfunc_end20:
	.size	__string_ge, .Lfunc_end20-__string_ge
                                        # -- End function
	.option	pop
	.option	push
	.option	arch, +c
	.globl	__string_concat                 # -- Begin function __string_concat
	.p2align	1
	.type	__string_concat,@function
__string_concat:                        # @__string_concat
# %bb.0:
	addi	sp, sp, -48
	sw	ra, 44(sp)                      # 4-byte Folded Spill
	sw	s0, 40(sp)                      # 4-byte Folded Spill
	addi	s0, sp, 48
	sw	a0, -12(s0)
	sw	a1, -16(s0)
	lw	a0, -12(s0)
	call	__string_length
	sw	a0, -20(s0)
	lw	a0, -16(s0)
	call	__string_length
	sw	a0, -24(s0)
	lw	a0, -20(s0)
	lw	a1, -24(s0)
	add	a0, a0, a1
	addi	a0, a0, 1
	call	malloc
	sw	a0, -28(s0)
	sw	zero, -32(s0)
	j	.LBB21_1
.LBB21_1:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -32(s0)
	lw	a1, -20(s0)
	bge	a0, a1, .LBB21_4
	j	.LBB21_2
.LBB21_2:                               #   in Loop: Header=BB21_1 Depth=1
	lw	a0, -12(s0)
	lw	a1, -32(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	lw	a2, -28(s0)
	add	a1, a1, a2
	sb	a0, 0(a1)
	j	.LBB21_3
.LBB21_3:                               #   in Loop: Header=BB21_1 Depth=1
	lw	a0, -32(s0)
	addi	a0, a0, 1
	sw	a0, -32(s0)
	j	.LBB21_1
.LBB21_4:
	sw	zero, -36(s0)
	j	.LBB21_5
.LBB21_5:                               # =>This Inner Loop Header: Depth=1
	lw	a0, -36(s0)
	lw	a1, -24(s0)
	bge	a0, a1, .LBB21_8
	j	.LBB21_6
.LBB21_6:                               #   in Loop: Header=BB21_5 Depth=1
	lw	a0, -16(s0)
	lw	a1, -36(s0)
	add	a0, a0, a1
	lbu	a0, 0(a0)
	lw	a2, -28(s0)
	lw	a3, -20(s0)
	add	a1, a1, a3
	add	a1, a1, a2
	sb	a0, 0(a1)
	j	.LBB21_7
.LBB21_7:                               #   in Loop: Header=BB21_5 Depth=1
	lw	a0, -36(s0)
	addi	a0, a0, 1
	sw	a0, -36(s0)
	j	.LBB21_5
.LBB21_8:
	lw	a0, -28(s0)
	lw	a1, -20(s0)
	lw	a2, -24(s0)
	add	a1, a1, a2
	add	a0, a0, a1
	sb	zero, 0(a0)
	lw	a0, -28(s0)
	lw	ra, 44(sp)                      # 4-byte Folded Reload
	lw	s0, 40(sp)                      # 4-byte Folded Reload
	addi	sp, sp, 48
	ret
.Lfunc_end21:
	.size	__string_concat, .Lfunc_end21-__string_concat
                                        # -- End function
	.option	pop
	.type	.L.str,@object                  # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"%s"
	.size	.L.str, 3

	.type	.L.str.1,@object                # @.str.1
.L.str.1:
	.asciz	"%s\n"
	.size	.L.str.1, 4

	.type	.L.str.2,@object                # @.str.2
.L.str.2:
	.asciz	"%d"
	.size	.L.str.2, 3

	.type	.L.str.3,@object                # @.str.3
.L.str.3:
	.asciz	"%d\n"
	.size	.L.str.3, 4

	.type	.L.str.4,@object                # @.str.4
.L.str.4:
	.asciz	"true"
	.size	.L.str.4, 5

	.type	.L.str.5,@object                # @.str.5
.L.str.5:
	.asciz	"false"
	.size	.L.str.5, 6

	.ident	"Ubuntu clang version 18.1.3 (1ubuntu1)"
	.section	".note.GNU-stack","",@progbits
