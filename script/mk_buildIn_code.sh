cd ./src/main/c
clang -emit-llvm -fno-builtin-printf -fno-builtin-memcpy -fno-builtin-malloc -fno-builtin-strlen -O3 --target=builtin.c -S -o builtin.ll
llc --march=riscv32 -O3 -mattr=+m,+a builtin.ll -o builtin.s
#cp builtin.s ../../test/mx/builtin.s


#sed 's/string_/string./g;s/array_/array./g' builtin_intermediate.ll > builtin.ll
