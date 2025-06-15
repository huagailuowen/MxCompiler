## Debug

```
ulimit -c unlimited
cd test
zsh test.sh
make libco all
LD_LIBRARY_PATH=../src lldb libco-test-64


LD_LIBRARY_PATH=../src ./libco-test-64  

LD_LIBRARY_PATH=../src coredumpctl debug libco-test-64 --debugger=lldb
No match found.
```
