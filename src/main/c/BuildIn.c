int printf(const char *format, ...);
int scanf(const char *format, ...);
int sprintf(char *str, const char *format, ...);
void *malloc(unsigned int size);

void print(char *s) { printf("%s", s); }
void println(char *s) { printf("%s\n", s); }
void printInt(int n) { printf("%d", n); }
void printlnInt(int n) { printf("%d\n", n); }

char *getString() {
  char *str = (char *)malloc(4096);
  scanf("%s", str);
  return s;
}
int getInt() {
  int num;
  scanf("%d", &num);
  return n;
}
char *toString(int i) {
  char *str;
  str = (char *)malloc(sizeof(char) * 10);
  sprintf(str, "%d", i);
  return str;
}
void __malloc_array

