//typedef enum { false, true } bool;
#define bool _Bool
#define true 1
#define false 0
int printf(const char *format, ...);
int scanf(const char *format, ...);
int puts(const char *s);
void *memcpy(void *str1, const void *str2, int n);
int sprintf(char *str, const char *format, ...);
void *malloc(unsigned int size);
int strlen(const char *s);
int strcmp(const char *s1, const char *s2);

void print(char *s) { printf("%s", s); }
void println(char *s) { puts(s); }
void printInt(int n) { printf("%d", n); }
void printlnInt(int n) { printf("%d\n", n); }

char *getString() {
  char *str = (char *)malloc(4096);
  scanf("%s", str);
  return str;
}
int getInt() {
  int num;
  scanf("%d", &num);
  return num;
}
char *toString(int num) {
  char *str;
  str = (char *)malloc(sizeof(char) * 10);
  sprintf(str, "%d", num);
  return str;
}
char * toString_bool(bool flag)
{
    if(flag)
    {
        return "true";
    }
    else
    {
        return "false";
    }
}
void * __malloc_array(int length,int size)
{
    int *ptr=(int *)malloc((length<<2)+4);
    ptr[0]=length;
    return ptr+1;
}
void * __malloc(int length,int size)
{
    return malloc(length<<2);
}
int buildInArraySize(void * ptr)
{
    return ((int *)ptr)[-1];
}
int __string_length(char *s)
{
    return strlen(s);
}
char * __string_substring(char *s,int left,int right)
{
    int len=right-left;
    char *str=(char *)malloc(len+1);
    memcpy(str,s+left,len);
    str[len]='\0';
    return str;
}
int __string_parseInt(char *s)
{
    int num=0;
    int i=0;
    while(s[i]!='\0')
    {
        num=num*10+(s[i]-'0');
        i++;
    }
    return num;
}
int __string_ord(char *s,int pos)
{
    return s[pos];
}
int __string_eq(char *s1,char *s2)
{
    return strcmp(s1,s2)==0;
}
int __string_ne(char *s1,char *s2)
{
    return strcmp(s1,s2)!=0;
}
int __string_lt(char *s1,char *s2)
{
    return strcmp(s1,s2)<0;
}
int __string_le(char *s1,char *s2)
{
    return strcmp(s1,s2)<=0;
}
int __string_gt(char *s1,char *s2)
{
    return strcmp(s1,s2)>0;
}
int __string_ge(char *s1,char *s2)
{
    return strcmp(s1,s2)>=0;
}
void* __string_concat(char *s1,char *s2)
{
    int len1=__string_length(s1);
    int len2=__string_length(s2);
    char *str=(char *)malloc(len1+len2+1);
    memcpy(str,s1,len1);
    memcpy(str+len1,s2,len2);
    str[len1+len2]='\0';
    return str;
}

