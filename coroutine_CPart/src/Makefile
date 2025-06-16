CC = clang
CFLAGS ?= -g3 -O0 -fno-omit-frame-pointer -Wall -Wextra
LDFLAGS = -shared -fPIC

all: libco-64.so

libco-64.so: co.c
	$(CC) $(CFLAGS) $(LDFLAGS) -o $@ $<

clean:
	rm -f *.so *.o

.PHONY: all clean