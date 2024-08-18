cd ./src/test/mx
clang --target=riscv32-unknown-elf -S builtin.ll -o builtin.s
clang --target=riscv32-unknown-elf -S output.ll -o test.s
reimu
$?