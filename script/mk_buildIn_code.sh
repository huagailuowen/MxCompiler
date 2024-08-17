cd ./src/main/c
clang -S -emit-llvm --target=riscv32-unknown-elf -O2 -fno-builtin-printf -fno-builtin-memcpy  Buildin.c -o builtin.ll
cp builtin.ll ../../test/mx/builtin.ll
#sed 's/string_/string./g;s/array_/array./g' builtin_intermediate.ll > builtin.ll
