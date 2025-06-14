NAME := libco
CFLAGS += -U_FORTIFY_SOURCE -g -O0 #-fsanitize=address -fsanitize=address
SRCS := co.c
DEPS := $(SRCS)

all: clean $(NAME)-64.so #$(NAME)-32.so

$(NAME)-64.so: $(DEPS) # 64bit shared library
	gcc -fPIC -shared -m64 $(CFLAGS) $(SRCS) -o $@ $(LDFLAGS)

# $(NAME)-32.so: $(DEPS) # 32bit shared library
# 	gcc -fPIC -shared -m32 $(CFLAGS) $(SRCS) -o $@ $(LDFLAGS)

clean:
	rm -f $(NAME)-*.so