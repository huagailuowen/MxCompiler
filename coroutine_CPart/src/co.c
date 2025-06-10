#include "co.h"
#include <setjmp.h>
#include <stdio.h>
#include <string.h>

__thread struct co_regedit *local_co_regedit;  // 当前M对应的P
struct global_co_regedit global_co_regedit;
struct queue_node * create_queue_node(struct co * co) {
    struct queue_node * node = malloc(sizeof(struct queue_node));
    node->co = co;
    node->next = NULL;
    node->prev = NULL;
    if(co != NULL) {
        co->queue_node_ptr = node;
    }
    return node;
}
struct queue_node * pop_queue_node(struct queue_wrapper * queue) {
    struct queue_node * head = queue->head;
    if(head == NULL) {
        return NULL;
    }
    queue->head = head->next;
    if(queue->head == NULL) {
        queue->tail = NULL;
    }
    if(head->next != NULL) {
        head->next->prev = NULL;
    }
    return head;
}
void push_queue_node(struct queue_wrapper * queue, struct queue_node * node) {
    if(queue->head == NULL) {
        queue->head = node;
        queue->tail = node;
        node->next = NULL;
        node->prev = NULL;
    } else {
        queue->tail->next = node;
        queue->tail = node;
        node->prev = queue->tail;
        node->next = NULL;
    }
}
struct queue_node * fetch_queue_node(struct queue_wrapper * queue, struct queue_node * node) {
    if(node->prev != NULL) {
        node->prev->next = node->next;
    } else {
        queue->head = node->next;
    }
    if(node->next != NULL) {
        node->next->prev = node->prev;
    } else {
        queue->tail = node->prev;
    }
    return node;
}
void free_queue(struct queue_wrapper * queue) {
    struct queue_node * node = queue->head;
    while(node != NULL) {
        struct queue_node * temp = node;
        node = node->next;
        free(temp);
    }
    queue->head = NULL;
    queue->tail = NULL;
}

__thread struct co* current;

struct co * co_add_task(const char *name, void (*func)(void *), void *arg, bool is_main) {
    if(global_co_regedit.co_num == MAX_CO_NUM*MAX_CO_PROCESS_NUM) {
        printf("Too many coroutines\n");
        exit(1);
    }
    struct co * handler = (struct co *)malloc(sizeof(struct co));
    if (!handler) {
        printf("Failed to allocate memory for coroutine\n");
        exit(1);
    }
    
    global_co_regedit.co_pool[global_co_regedit.co_num] = handler;
    struct queue_node * queue_node = create_queue_node(handler);
    handler->c_id = global_co_regedit.co_num++;
    if(!is_main){
        push_queue_node(&global_co_regedit.runable_queue, queue_node);
    }
    else{
        local_co_regedit->co_num++; // 主线程含有一个coroutine
        push_queue_node(&local_co_regedit->runable_queue, queue_node);
    }

    
    // 安全处理 name 参数
    if (name != NULL) {
        handler->name = malloc(strlen(name) + 1);
        if (handler->name) {
            strcpy(handler->name, name);
        } else {
            handler->name = NULL;
        }
    } else {
        handler->name = malloc(10); // 为默认名称分配空间
        if (handler->name) {
            sprintf(handler->name, "co-%d", handler->c_id);
        } else {
            handler->name = NULL;
        }
    }
    
    handler->func = func;
    handler->arg = arg;
    handler->status = CO_NEW;
    handler->waiter_list_head = NULL;
    handler->stack = malloc(STACK_SIZE);
    
    if (!handler->stack) {
        printf("Failed to allocate stack for coroutine\n");
        if (handler->name) free(handler->name);
        free(handler);
        exit(1);
    }
    
    
    return handler;
}


struct co *co_start(const char *name, void (*func)(void *), void *arg)
{
    struct co *co = co_add_task(name, func, arg, false);
    return co;
}
void start_new_wrapper() {
    current->status = CO_RUNNING;
    current->func(current->arg);
    current->status = CO_DEAD;
    
    struct linked_list_node * node = current->waiter_list_head;
    while(node != NULL) {
        struct co * waiter = node->waiter;
        node = node->next;
        waiter->status = CO_RUNNING;
        push_queue_node(&global_co_regedit.runable_queue, create_queue_node(waiter));
    }
}
struct co_regedit * co_P_init() { //init for P
    struct co_regedit *co_regedit = malloc(sizeof(struct co_regedit));
    if (!co_regedit) {
        printf("Failed to allocate memory for coroutine registry\n");
        exit(1);
    }
    pthread_mutex_lock(&global_co_regedit.mutex);
    co_regedit->regedit_id = global_co_regedit.P_num++;
    global_co_regedit.local_co_regedit[co_regedit->regedit_id] = co_regedit;
    co_regedit->co_num = 0;
    co_regedit->runable_queue.head = NULL;
    co_regedit->runable_queue.tail = NULL;
    co_regedit->is_resume = false;
    co_regedit->resume_co = NULL;
    pthread_mutex_unlock(&global_co_regedit.mutex);

    return co_regedit;
}

void schedule() {
    Start:;
    struct queue_node * node;
    if(local_co_regedit->is_resume) {
        local_co_regedit->is_resume = false;
        node = fetch_queue_node(&local_co_regedit->runable_queue, local_co_regedit->resume_co->queue_node_ptr);
    }else{
        node = pop_queue_node(&local_co_regedit->runable_queue);
    }
    if(node == NULL) {
        printf("Fatal Error : do not contain current\n");
        exit(1);
    }
    if(node->co->status != CO_DEAD && node->co->status != CO_WAITING) {
        push_queue_node(&local_co_regedit->runable_queue, node);
    }else{
        free(node);
    }
    if(local_co_regedit->runable_queue.head == NULL) {
        printf("All coroutines are dead\n");
        exit(-1);
    }
    struct co * co = local_co_regedit->runable_queue.head->co;
    current = co;
    if(current->status == CO_NEW) {
        //if co being spill to the stack by the compiler, the error may occur
        asm volatile("mov %0, %%rsp" : : "g"(current->stack + STACK_SIZE));
        start_new_wrapper();
        goto Start;
    } else {
        //must be CO_RUNNING
        longjmp(co->context, 1);
    }
    // never reach here
}
void* co_M_create() {
    local_co_regedit = co_P_init();
    current = NULL;
    schedule();
    return NULL;
}

void co_yield()
{
    if (setjmp(current->context) == 0) {
        schedule();
    }
}
void co_resume(struct co *co)
{
    if (co->status == CO_DEAD) {
        return;
    }
    if(co->status == CO_WAITING) {
        printf("Fatal Error : co is waiting\n");
        exit(1);
    }
    if(co == current) {
        return;
    }
    local_co_regedit->is_resume = true;
    local_co_regedit->resume_co = co;
    co_yield();
}
void add_waiter(struct co * waiter, struct co * co) {
    struct linked_list_node * node = malloc(sizeof(struct linked_list_node));
    node->waiter = waiter;
    node->next = co->waiter_list_head;
    co->waiter_list_head = node;
}
void co_wait(struct co *co)
{
    if (co->status == CO_DEAD) {
        return;
    }
    add_waiter(current, co);
    current->status = CO_WAITING;
    co_yield();
}

__attribute__((constructor))
static void co_global_init() {
    // 使用 PTHREAD_MUTEX_INITIALIZER 静态初始化
    // static pthread_mutex_t temp_mutex = PTHREAD_MUTEX_INITIALIZER;
    // global_co_regedit.mutex = temp_mutex;
    if (pthread_mutex_init(&global_co_regedit.mutex, NULL) != 0) {
        fprintf(stderr, "Failed to initialize mutex\n");
        exit(1);
    }
    if (pthread_cond_init(&global_co_regedit.work_available, NULL) != 0) {
        fprintf(stderr, "Failed to initialize work_available condition variable\n");
        pthread_mutex_destroy(&global_co_regedit.mutex);
        exit(1);
    }
    

    global_co_regedit.co_num = 0;
    global_co_regedit.runable_queue.head = NULL;
    global_co_regedit.runable_queue.tail = NULL;
    global_co_regedit.status = GLOBAL_CO_SCHEDULE_INIT; // have not started yet
    global_co_regedit.P_num = 0;
    
    // 初始化main函数
    local_co_regedit = co_P_init();
    struct co *main_co = co_add_task("main", NULL, NULL, true);
    main_co->status = CO_RUNNING; // 设置main协程为运行状态
    current = main_co; // 设置当前协程为main协程

    for(int i = 0; i < MAX_CO_PROCESS_NUM; i++) {
        if (pthread_create(&global_co_regedit.threads[i], NULL, co_M_create, NULL) != 0) {
            fprintf(stderr, "Failed to create thread %d\n", i);
            exit(1);
        }
    }
    global_co_regedit.M_num = MAX_CO_PROCESS_NUM; // 设置当前线程数为最大线程数
    global_co_regedit.status = GLOBAL_CO_SCHEDULE_RUNNING; // 全局协程调度状态设置为运行中
    printf("Coroutine system initialized\n");  // 可选的调试信息
}

__attribute__((destructor))
static void co_cleanup() {
    printf("Cleaning up coroutine system resources...\n");

    // 设置停止标志
    pthread_mutex_lock(&global_co_regedit.mutex);
    global_co_regedit.status = GLOBAL_CO_SCHEDULE_STOPPED;
    pthread_cond_broadcast(&global_co_regedit.work_available); // 唤醒所有等待的线程
    pthread_mutex_unlock(&global_co_regedit.mutex);
    
    for (int i = 0; i < global_co_regedit.M_num; i++) {
        pthread_join(global_co_regedit.threads[i], NULL);
    }
    for (int i = 0; i < global_co_regedit.co_num; i++) {
        struct co *co = global_co_regedit.co_pool[i];
        if (co) {

            if (co->name) {
                free(co->name);
            }
            
            if (co->stack) {
                free(co->stack);
            }
            
            // 释放等待列表
            struct linked_list_node *node = co->waiter_list_head;
            while (node) {
                struct linked_list_node *temp = node;
                node = node->next;
                free(temp);
            }
            
            // 释放协程结构体
            free(co);
        }
    }
    
    // 释放可运行队列中的节点
    free_queue(&global_co_regedit.runable_queue);
    for (int i = 0; i < global_co_regedit.P_num; i++) {
        struct co_regedit *regedit = global_co_regedit.local_co_regedit[i];
        if (regedit) {
            free_queue(&regedit->runable_queue);
            free(regedit);
        }
    }
    pthread_mutex_destroy(&global_co_regedit.mutex);
    printf("Coroutine system resources cleaned up\n");
}
struct co *co_self() {
    return current; 
}