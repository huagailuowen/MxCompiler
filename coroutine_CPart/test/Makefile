.PHONY: test libco

all: libco-test-64 # libco-test-32

test: libco all
	@echo "==== TEST 64 bit ===="
	@LD_LIBRARY_PATH=../src ./libco-test-64

debug: libco all
	@LD_LIBRARY_PATH=../src gdb ./libco-test-64 -x gdb.init

libco:
	cd ../src && make

libco-test-64: main.c
	gcc -I../src -L../src -m64 main.c -o $@ -g -lco-64

# libco-test-32: main.c
# 	gcc -I.. -L.. -m32 main.c -o $@ -g -lco-32 

clean:
	rm -f libco-test-*