.PHONY: test libco clean debug
CC = clang
all: libco-test-64

test: libco all
	@echo "==== TEST 64 bit ===="
	@LD_LIBRARY_PATH=../src ./libco-test-64 2> log

debug: libco all
	@LD_LIBRARY_PATH=../src lldb ./libco-test-64 -x gdb.init

libco:
	cd ../src && make clean && make CFLAGS="-g3 -O0 "

# libco-test-64: main.c
# 	$(CC) -I../src -L../src -m64 main.c -o $@ -g3 -O1 -fno-omit-frame-pointer -lco-64

# libco-test-64: compute_graph.c
# 	$(CC) -I../src -L../src -m64 compute_graph.c -o $@ -g3 -O2 -fno-omit-frame-pointer -lco-64


libco-test-64: level_graph.c
	$(CC) -I../src -L../src -m64 level_graph.c -o $@ -g3 -O0  -lco-64

clean:
	rm -f libco-test-* core* valgrind.log test.out
	cd ../src && make clean