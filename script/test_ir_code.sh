cd ./src/test/mx
clang -15 -m32 builtin.ll output.ll -o code
./code
$?