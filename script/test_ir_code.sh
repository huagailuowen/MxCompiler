cd ./src/test/mx
#clang --target=riscv32-unknown-elf -S builtin.ll -o builtin.s
#clang  -falign-functions=1   --target=riscv32-unknown-elf -S output.ll -o test.s
##llc -align-all-functions=1 -align-all-blocks=1 output.ll -o test.s
#reimu

#sed 's/string_/string./g;s/array_/array./g' output_imm.ll >output.ll
clang -m32 builtin.ll output.ll -o test
./test
echo $?