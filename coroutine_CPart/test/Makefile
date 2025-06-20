.PHONY: all test debug clean libco

CC = clang
CFLAGS = -g3 -O2 -fno-omit-frame-pointer -m64 -I../src -L../src
LDLIBS = -lco-64
SRC_DIR = .
LIB_DIR = ../src

# 所有 C 源文件（你可以限制为 test_*.c 等）
SOURCES := $(wildcard *.c)
TARGETS := $(patsubst %.c,libco-test-%, $(SOURCES))

# 默认目标
all: libco $(TARGETS)

# 编译规则
libco-test-%: %.c
	$(CC) $(CFLAGS) $< -o $@ $(LDLIBS)

# 构建 libco 库
libco:
	cd $(LIB_DIR) && make clean && make CFLAGS="-g3 -O0 -fno-omit-frame-pointer"

# 测试所有目标
test: libco all
	@for bin in $(TARGETS); do \
		echo "==== Running $$bin ===="; \
		LD_LIBRARY_PATH=$(LIB_DIR) ./$$bin 2> log.$$bin || echo "$$bin failed"; \
	done

# 调试其中一个（默认选择第一个）
debug: libco all
	@LD_LIBRARY_PATH=$(LIB_DIR) lldb ./$(word 1,$(TARGETS)) -x gdb.init

# 清理
clean:
	rm -f libco-test-* core* valgrind.log test.out log.*
	cd $(LIB_DIR) && make clean

run:
ifndef FILE
	$(error FILE variable is not set, usage: make run FILE=yourtest.c)
endif
	@echo "==== Compiling and running $(FILE) ===="
	@TARGET=libco-test-$(basename $(FILE)); \
	$(CC) $(CFLAGS) $(FILE) -o $$TARGET $(LDLIBS) && \
	echo "==== Running $$TARGET ====" && \
	LD_LIBRARY_PATH=$(LIB_DIR) ./$$TARGET 2> log.$$TARGET || echo "$$TARGET failed"

%: %.c libco
	@echo "==== Compiling $< ===="
	$(CC) $(CFLAGS) $< -o $@ $(LDLIBS)
	@echo "==== Running $@ ===="
	LD_LIBRARY_PATH=$(LIB_DIR) ./$@