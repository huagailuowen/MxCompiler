cd ./src/main/c
clang -emit-llvm -fno-builtin-printf -fno-builtin-memcpy -fno-builtin-malloc -fno-builtin-strlen builtin.c --target=riscv32-unknown-elf -S -o builtin.ll
#clang -emit-llvm -fno-builtin-printf -fno-builtin-memcpy -fno-builtin-malloc -fno-builtin-strlen builtin.c --target=riscv32-unknown-elf -S -o builtin_imm.ll && sed 's/string_/string./g;s/array_/array./g' builtin_imm.ll >builtin.ll
cp builtin.ll ../../test/mx/builtin.ll
#sed 's/string_/string./g;s/array_/array./g' builtin_intermediate.ll > builtin.ll
