.PHONY: test libco clean debug

all: libco-test-64

test: libco all
	@echo "==== TEST 64 bit ===="
	@LD_LIBRARY_PATH=../src valgrind ./libco-test-64 2> log

debug: libco all
	@LD_LIBRARY_PATH=../src gdb ./libco-test-64 -x gdb.init

libco:
	cd ../src && make clean && make CFLAGS="-g3 -O0 -fno-omit-frame-pointer"

libco-test-64: main.c
	gcc -I../src -L../src -m64 main.c -o $@ -g3 -O0 -fno-omit-frame-pointer -lco-64

clean:
	rm -f libco-test-* core* valgrind.log test.out
	cd ../src && make clean