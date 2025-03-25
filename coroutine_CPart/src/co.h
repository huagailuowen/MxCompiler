#ifndef CO_H
#define CO_H
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <setjmp.h>
#define STACK_SIZE 32*1024
enum co_status {
    CO_NEW = 1, // 新创建，还未执行过
    CO_RUNNING, // 已经执行过
    CO_WAITING, // 在 co_wait 上等待
    CO_DEAD,    // 已经结束，但还未释放资源
};

struct co {
    unsigned int c_id; // 协程的id, 默认为0(main)
    char *name;
    void (*func)(void *); // co_start 指定的入口地址和参数
    void *arg;
    
    enum co_status status;  // 协程的状态
    struct linked_list_node{
        struct co *waiter;
        struct linked_list_node *next;
    };
    struct linked_list_node * waiter_list_head; // 等待当前协程的协程链表
    jmp_buf        context; // 寄存器现场
    uint8_t *      stack; // 协程的堆栈
};

#define MAX_CO_NUM 1024 // 最大协程数
struct co_regedit {
    struct co *co_pool[MAX_CO_NUM]; // 协程池
    unsigned int co_num; // 当前协程数
    struct queue_node{
        struct co *co;
        struct queue_node *next;
    };
    struct queue_node * runable_queue_tail;
    struct queue_node * runable_queue_head;
}global_co_regedit;

struct co *co_start(const char *name, void (*func)(void *), void *arg);
void       co_yield();
void       co_wait(struct co *co);

#endif // CO_H
