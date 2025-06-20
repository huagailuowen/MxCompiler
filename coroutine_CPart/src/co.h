#ifndef CO_H
#define CO_H
#include <stddef.h>
#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string.h>
#include <setjmp.h>
#include <stdbool.h>
#include <time.h>
#include <pthread.h>
#include <stdatomic.h>
#include <bits/pthreadtypes.h>
#define STACK_SIZE 1024*1024
enum co_status {
    CO_NEW = 1, // 新创建，还未执行过
    CO_RUNNING, // 已经执行过
    CO_WAITING, // 在 co_wait 上等待
    CO_DEAD,    // 已经结束，但还未释放资源
};
enum global_co_schedule_status {
    GLOBAL_CO_SCHEDULE_INIT,
    GLOBAL_CO_SCHEDULE_RUNNING,
    GLOBAL_CO_SCHEDULE_STOPPED,
};

struct queue_node;
struct linked_list_node{
    struct co *waiter;
    struct linked_list_node *next;
};
struct co {
    unsigned int c_id; // 协程的id, 默认为0(main)
    char *name;
    void (*func)(void *); // co_start 指定的入口地址和参数
    void *arg;
    
    struct queue_node * queue_node_ptr;
    volatile enum co_status status;  // 协程的状态
    pthread_mutex_t wait_mutex;
    struct linked_list_node * waiter_list_head; // 等待当前协程的协程链表
    jmp_buf        context; // 寄存器现场
    uint8_t *      stack; // 协程的堆栈
} ;//__attribute__((aligned(16)));              // 确保16字节对齐

#define MAX_CO_NUM 1024<<4 // 最大协程数

#define MAX_CO_PROCESS_NUM 10 // 最大P线程数
#define MAX_FETCH_NUM 30 // 每次从可运行队列中获取的协程数
struct queue_wrapper;
struct queue_node{
    unsigned int priority; // 优先级，数值越小优先级越高
    struct co *co;
    struct queue_node *next, *prev;
    volatile _Atomic(struct queue_wrapper *) queue_wrapper; // 指向所在的队列
};
struct queue_wrapper {
    int num; // 队列中的节点数
    struct queue_node * head;
    struct queue_node *tail;
};
struct co_regedit {
    unsigned int regedit_id; // P的id,对应创建时local_co_regedit_num
    unsigned int schedule_count; // 当前P线程调度的协程数
    struct queue_wrapper runable_queue; // 可运行队列
    bool is_resume;
    bool is_kick;
    struct co * resume_co;
    pthread_mutex_t mutex;
    jmp_buf        origin_context; // 寄存器现场
};
struct global_co_regedit {
    struct co *co_pool[MAX_CO_NUM]; // 全局协程池
    struct co *co_main; // main协程
    struct queue_node *co_main_queue_node; // main协程的队列节点
    bool is_main_available; // 主协程是否已经准备好
    // 全局协程调度相关
    unsigned int running_num; // 当前正在运行的协程数
    unsigned int alive_num; // 当前存活的协程数
    unsigned int co_num; // 当前总创建协程数
    struct queue_wrapper runable_queue; // 可运行队列

    _Atomic(enum global_co_schedule_status) status; // 全局协程调度状态
    
    struct co_regedit *local_co_regedit[MAX_CO_PROCESS_NUM<<1|1]; // 每个P线程的协程注册表
    // main 默认位于0号
    unsigned int P_num; // 当前P线程的协程注册表数
    pthread_t threads[MAX_CO_PROCESS_NUM<<1]; // M(系统)线程数组
    unsigned int M_num; // 当前线程id

    // 用于全局调度的互斥锁
    pthread_mutex_t mutex;
    pthread_cond_t work_available; // 用于通知饥饿线程有可运行的协程
    pthread_cond_t main_available; // 用于唤醒主协程
};
struct co_signal{
    struct linked_list_node * waiter_list_head; // 等待当前协程的队列
    pthread_mutex_t mutex; // 保护等待队列的互斥锁
    unsigned int count; // 信号计数
};

struct co *co_start(const char *name, void (*func)(void *), void *arg);
void       co_yield();
void       co_wait(struct co *co);
void       co_resume(struct co *co);
void       co_free(struct co *co);
struct co *co_self(); 

void co_signal_init(struct co_signal *signal, unsigned int initial_count);
void co_signal_wait(struct co_signal *signal);
void co_signal_notify(struct co_signal *signal);
void co_signal_destroy(struct co_signal *signal);

#endif // CO_H
