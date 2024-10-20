ulimit -s 1000000

make run < ./tmp/inputcode.in
cd ./src/test/mx
#clang --target=riscv32-unknown-elf -S builtin.ll -o builtin.s
#clang  -falign-functions=1   --target=riscv32-unknown-elf -S output.ll -o test.s
##llc -align-all-functions=1 -align-all-blocks=1 output.ll -o test.s
#reimu

#sed 's/string_/string./g;s/array_/array./g' output_imm.ll >output.ll
#clang -m32 builtin.s output.s -o test
#./test
#cp output.s test.s
export PATH="/usr/local/opt/bin:$PATH"
#ravel test.s builtin.s
reimu --all
echo $?