typedef enum { false, true } bool;
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
    int *ptr=(int *)malloc(size*length+4);
    ptr[0]=size;
    return ptr+1;
}
void * __malloc(int length,int size)
{
    return malloc(size*length);
}
int buildInArraySize(void * ptr)
{
    return ((int *)ptr)[-1];
}
int __string_length(char *s)
{
    int i=0;
    while(s[i]!='\0')
    {
        i++;
    }
    return i;
}
char * __string_substring(char *s,int left,int right)
{
    int len=right-left+1;
    char *str=(char *)malloc(len+1);
    for(int i=0;i<len;i++)
    {
        str[i]=s[left+i];
    }
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
bool __string_eq(char *s1,char *s2)
{
    int i=0;
    while(s1[i]!='\0' && s2[i]!='\0')
    {
        if(s1[i]!=s2[i])
        {
            return false;
        }
        i++;
    }
    if(s1[i]=='\0' && s2[i]=='\0')
    {
        return true;
    }
    return false;
}
bool __string_ne(char *s1,char *s2)
{
    return !__string_eq(s1,s2);
}
bool __string_lt(char *s1,char *s2)
{
    int i=0;
    while(s1[i]!='\0' && s2[i]!='\0')
    {
        if(s1[i]>=s2[i])
        {
            return false;
        }
        i++;
    }
    if(s1[i]=='\0' && s2[i]=='\0')
    {
        return false;
    }
    return true;
}
bool __string_le(char *s1,char *s2)
{
    return !__string_lt(s2,s1);
}
bool __string_gt(char *s1,char *s2)
{
    return __string_lt(s2,s1);
}
bool __string_ge(char *s1,char *s2)
{
    return !__string_lt(s1,s2);
}
void* __string_concat(char *s1,char *s2)
{
    int len1=__string_length(s1);
    int len2=__string_length(s2);
    char *str=(char *)malloc(len1+len2+1);
    for(int i=0;i<len1;i++)
    {
        str[i]=s1[i];
    }
    for(int i=0;i<len2;i++)
    {
        str[len1+i]=s2[i];
    }
    str[len1+len2]='\0';
    return str;
}

