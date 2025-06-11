#include "co.h"
#include <bits/pthreadtypes.h>
#include <pthread.h>
#include <setjmp.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 原子读取状态


__thread struct co_regedit *local_co_regedit;  // 当前M对应的P
__thread bool is_main_thread; // 是否为主线程
struct global_co_regedit global_co_regedit;

enum global_co_schedule_status get_schedule_status() {
    return atomic_load(&global_co_regedit.status);
}

// 原子设置状态
void set_schedule_status(enum global_co_schedule_status new_status) {
    atomic_store(&global_co_regedit.status, new_status);
}

// 原子比较并交换
bool compare_and_swap_status(enum global_co_schedule_status expected, 
                            enum global_co_schedule_status desired) {
    return atomic_compare_exchange_strong(&global_co_regedit.status, &expected, desired);
}

struct queue_node * create_queue_node(struct co * co) {
    struct queue_node * node = malloc(sizeof(struct queue_node));
    node->co = co;
    node->priority = 0; // 默认优先级为0
    node->next = NULL;
    node->prev = NULL;
    if(co != NULL) {
        co->queue_node_ptr = node;
    }
    node->queue_wrapper = NULL; // 初始化队列指针为NULL
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
    queue->num--;
    head->queue_wrapper = NULL; // 清除队列指针
    return head;
}
struct queue_node * pop_queue_node_back(struct queue_wrapper * queue) {
    struct queue_node * tail = queue->tail;
    if(tail == NULL) {
        return NULL;
    }
    queue->tail = tail->prev;
    if(queue->tail == NULL) {
        queue->head = NULL;
    }
    if(tail->prev != NULL) {
        tail->prev->next = NULL;
    }
    queue->num--;
    tail->queue_wrapper = NULL; // 清除队列指针

    return tail;
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
    node->queue_wrapper = queue; // 设置队列指针
    queue->num++;
}
void push_queue_node_front(struct queue_wrapper * queue, struct queue_node * node) {
    if(queue->head == NULL) {
        queue->head = node;
        queue->tail = node;
        node->next = NULL;
        node->prev = NULL;
    } else {
        node->next = queue->head;
        queue->head->prev = node;
        queue->head = node;
        node->prev = NULL;
    }
    node->queue_wrapper = queue; // 设置队列指针
    queue->num++;
}
struct queue_node * fetch_queue_node(struct queue_wrapper * queue, struct queue_node * node) {
    if(queue == NULL) {
        return node; // 如果队列或节点为空，或者节点不属于该队列，返回NULL
    }
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
    node->queue_wrapper = NULL; // 清除队列指针
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
void transmit_queue(struct queue_wrapper * src, struct queue_wrapper * dest, int transmit_num) {
    while(transmit_num-- > 0) {
        struct queue_node * node = pop_queue_node_back(src);
        if(node == NULL) {
            break; // 如果源队列为空，退出循环
        }
        push_queue_node(dest, node); // 将节点添加到目标队列最低优先级 
    }
}

__thread struct co* current;
// 负载均衡器
void fetch_co_task()
{
    pthread_mutex_lock(&global_co_regedit.mutex);
    do{
        if(is_main_thread){
            if(global_co_regedit.is_main_available) {
                // 如果主协程已经准备好，直接从主协程队列中获取任务
                struct queue_node * node = global_co_regedit.co_main_queue_node;
                global_co_regedit.co_main_queue_node = NULL; // 清空主协程队列节点
                global_co_regedit.is_main_available = false; // 标记主协程未准备好
                push_queue_node(&local_co_regedit->runable_queue, node);
            }
        }
        transmit_queue(&global_co_regedit.runable_queue, &local_co_regedit->runable_queue, MAX_FETCH_NUM);
        if (local_co_regedit->runable_queue.num == 0) {
            // 如果运行队列还为空，等待其他线程添加任务
            if(is_main_thread) {
                pthread_cond_wait(&global_co_regedit.main_available, &global_co_regedit.mutex);
            } else {
                pthread_cond_wait(&global_co_regedit.work_available, &global_co_regedit.mutex);
            }
        }
    }while(local_co_regedit->runable_queue.num == 0 && get_schedule_status() == GLOBAL_CO_SCHEDULE_RUNNING);
    bool is_available_work = global_co_regedit.runable_queue.num > 0 || global_co_regedit.is_main_available;
    pthread_mutex_unlock(&global_co_regedit.mutex);
    if(is_available_work) {
        // 唤醒其他等待的线程
        pthread_cond_signal(&global_co_regedit.main_available);
        pthread_cond_signal(&global_co_regedit.work_available);
    }
    return;
}
void push_co_task()
{
    pthread_mutex_lock(&global_co_regedit.mutex);
    // TODO

    pthread_mutex_unlock(&global_co_regedit.mutex);
}
// 分配刚刚running协程任务的归属
void assign_co_task(struct queue_node *node) {
    // TODO 
    // 可根据优先级分析，如果优先级很低（即priority很大）可以扔到全局队列中，优先级置为0
    
    
    if (node == NULL || node->co == NULL) {
        printf("Fatal Error : node or co is NULL\n");
        exit(1);
    }
    if(node->co == global_co_regedit.co_main) {
        if(is_main_thread) {
            // 如果是main线程，直接加入主线程，发生在初始化阶段
            printf("Creating main coroutine\n");
            push_queue_node(&local_co_regedit->runable_queue, node);
        }
        else{
            // 加入主线程队列
            pthread_mutex_lock(&global_co_regedit.mutex);
            if (!global_co_regedit.is_main_available) {
            } 
            global_co_regedit.co_main_queue_node = node; // 保存主协程的队列节点
            global_co_regedit.is_main_available = true; // 标记主协程已准备好
            pthread_cond_signal(&global_co_regedit.main_available); // 唤醒主线程
            pthread_mutex_unlock(&global_co_regedit.mutex);
        }
        return;
    }
    if (local_co_regedit->runable_queue.num >= MAX_FETCH_NUM<<1) {
        // 如果当前P线程的可运行队列已满，则尝试将协程任务分配给全局可运行队列
        pthread_mutex_lock(&global_co_regedit.mutex);
        push_queue_node(&global_co_regedit.runable_queue, node);
        pthread_mutex_unlock(&global_co_regedit.mutex);
        // 唤醒其他等待的线程
        pthread_cond_signal(&global_co_regedit.main_available);
        pthread_cond_signal(&global_co_regedit.work_available); 

    } else {
        // 否则，将协程任务分配给当前P线程
        push_queue_node(&local_co_regedit->runable_queue, node);
    }
    
}

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
    if(is_main){
        global_co_regedit.co_main = handler; // 设置全局main协程
    }
    assign_co_task(queue_node); // 分配协程任务到当前P线程的可运行队列

    
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
    pthread_mutex_init(&handler->wait_mutex, NULL);
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
    pthread_mutex_lock(&current->wait_mutex);
    current->status = CO_DEAD;
    struct linked_list_node * node = current->waiter_list_head;
    while(node != NULL) {
        struct co * waiter = node->waiter;
        node = node->next;
        waiter->status = CO_RUNNING;
        assign_co_task(waiter->queue_node_ptr); // 将等待的协程重新加入可运行队列
    }
    pthread_mutex_unlock(&current->wait_mutex);
}
struct co_regedit * co_P_init() { //init for P
    struct co_regedit *co_regedit = malloc(sizeof(struct co_regedit));
    if (!co_regedit) {
        printf("Failed to allocate memory for coroutine registry\n");
        exit(1);
    }
    pthread_mutex_lock(&global_co_regedit.mutex);
    global_co_regedit.local_co_regedit[co_regedit->regedit_id] = co_regedit;
    co_regedit->regedit_id = global_co_regedit.P_num++;
    pthread_mutex_unlock(&global_co_regedit.mutex);
    co_regedit->runable_queue.num = 0;
    co_regedit->runable_queue.head = NULL;
    co_regedit->runable_queue.tail = NULL;
    co_regedit->is_resume = false;
    co_regedit->resume_co = NULL;
    if(pthread_mutex_init(&co_regedit->mutex, NULL) != 0) {
        fprintf(stderr, "Failed to initialize mutex\n");
        free(co_regedit);
        exit(1);
    }
    return co_regedit;
}

void schedule() {
    Start:;

    if(get_schedule_status() == GLOBAL_CO_SCHEDULE_STOPPED) {

        // 如果不是主线程且全局协程调度已停止，直接返回
        if(is_main_thread) {
            fprintf(stderr, "Fatal Error : schedule must not be called in main thread after global_co_regedit.status is GLOBAL_CO_SCHEDULE_STOPPED\n");
            exit(1);
        }
        return;
    }
    struct queue_node * node;
    struct queue_node * resume_node;
    bool is_resume = false;
    // there is now bug in resume, for may be the resume_co is not in the local queue
    node = pop_queue_node(&local_co_regedit->runable_queue);
    if(node != NULL && node->co != current){
        fprintf(stderr, "Warning: Coroutine %d is not the current coroutine\n", node->co->c_id);
        exit(1);
    }
    if(local_co_regedit->is_resume) {
        is_resume = true;
        local_co_regedit->is_resume = false;
        struct queue_wrapper *queue = local_co_regedit->resume_co->queue_node_ptr->queue_wrapper;
        pthread_mutex_lock(&global_co_regedit.mutex);
        // 锁住全局调度器才保证resume_co队列位置判断的原子性
        // TODO : 也许要让
        if(queue == &local_co_regedit->runable_queue) {
            // 如果resume_co在本地队列中，直接取出
            resume_node = fetch_queue_node(&local_co_regedit->runable_queue, local_co_regedit->resume_co->queue_node_ptr);
        } else if(queue == &global_co_regedit.runable_queue) {
            // 如果resume_co在全局队列中，取出
            resume_node = fetch_queue_node(&global_co_regedit.runable_queue, local_co_regedit->resume_co->queue_node_ptr);
        } else {
            is_resume = false; // 如果resume_co不在本地或全局队列中，设置为false，已在别的线程中恢复
            resume_node = local_co_regedit->runable_queue.head;
        }
        pthread_mutex_unlock(&global_co_regedit.mutex);
        if(resume_node != local_co_regedit->runable_queue.head) {
            push_queue_node_front(&local_co_regedit->runable_queue, resume_node);
        }
    }else{
        resume_node = local_co_regedit->runable_queue.head; // 取出队列头部的协程
    }
    // node 为空 resume必为空
    if(!node && resume_node){
        fprintf(stderr, "Warning: Coroutine %d is not the current coroutine, but resume_node is not NULL\n", resume_node->co->c_id);
        exit(1);
    }
    if(node == NULL) {
        // 真的全空了向全局索要
        fetch_co_task();
        pthread_mutex_lock(&global_co_regedit.mutex);
        if(get_schedule_status() == GLOBAL_CO_SCHEDULE_STOPPED) {
            pthread_mutex_unlock(&global_co_regedit.mutex);
            return; // 如果全局协程调度已停止，直接返回
        }
        pthread_mutex_unlock(&global_co_regedit.mutex);
        resume_node = local_co_regedit->runable_queue.head;
        // 此时node是NULL，之前没有运行过的协程
    }
    if(resume_node == NULL) {
        fprintf(stderr, "Fatal Error : no coroutine to run after fetching!\n");
        exit(-1);
    }
    
    if(node && node->co->status != CO_DEAD && node->co->status != CO_WAITING) {
        // push_queue_node(&local_co_regedit->runable_queue, node);
        assign_co_task(node); // 将当前协程重新加入可运行队列
    }
    if(local_co_regedit->runable_queue.head == NULL) {
        printf("All coroutines are dead\n");
        exit(-1);
    }
    resume_node->priority++; // 每运行一次优先级下降
    struct co * co = resume_node->co;
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
    is_main_thread = false; // 设置为非主线程
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
    pthread_mutex_lock(&co->wait_mutex);
    if (co->status == CO_DEAD) {
        pthread_mutex_unlock(&co->wait_mutex);
        return;
    }
    add_waiter(current, co);
    pthread_mutex_unlock(&co->wait_mutex);
    current->queue_node_ptr->queue_wrapper = NULL; // 清除协程的队列指针
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
    if(pthread_cond_init(&global_co_regedit.main_available, NULL) != 0) {
        fprintf(stderr, "Failed to initialize main_available condition variable\n");
        pthread_mutex_destroy(&global_co_regedit.mutex);
        pthread_cond_destroy(&global_co_regedit.work_available);
        exit(1);
    }
    

    global_co_regedit.co_num = 0;
    global_co_regedit.runable_queue.num = 0;
    global_co_regedit.runable_queue.head = NULL;
    global_co_regedit.runable_queue.tail = NULL;
    set_schedule_status(GLOBAL_CO_SCHEDULE_INIT); // have not started yet
    global_co_regedit.P_num = 0;
    global_co_regedit.is_main_available = false; 
    
    // 初始化main函数
    local_co_regedit = co_P_init();
    is_main_thread = true; // 设置为主线程
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
    set_schedule_status(GLOBAL_CO_SCHEDULE_RUNNING); // 全局协程调度状态设置为运行中
    printf("Coroutine system initialized\n");  // 可选的调试信息
}

__attribute__((destructor))
static void co_cleanup() {
    printf("Cleaning up coroutine system resources...\n");

    // 设置停止标志
    pthread_mutex_lock(&global_co_regedit.mutex);
    set_schedule_status(GLOBAL_CO_SCHEDULE_STOPPED);
    if(!is_main_thread){
        printf("Fatal Error : co_cleanup must be called in main thread\n");
        exit(1);
    }
    pthread_mutex_unlock(&global_co_regedit.mutex);
    pthread_cond_broadcast(&global_co_regedit.work_available); // 唤醒所有等待的线程
    
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
            
            if(co->queue_node_ptr){
                free(co->queue_node_ptr); // 释放协程的队列节点
            }
            
            // 释放等待列表
            struct linked_list_node *node = co->waiter_list_head;
            while (node) {
                struct linked_list_node *temp = node;
                node = node->next;
                free(temp);
            }
            pthread_mutex_destroy(&co->wait_mutex); // 销毁协程的互斥锁
            // 释放协程结构体
            free(co);
        }
    }
    
    // 释放可运行队列中的节点
    // free_queue(&global_co_regedit.runable_queue);
    for (int i = 0; i < global_co_regedit.P_num; i++) {
        struct co_regedit *regedit = global_co_regedit.local_co_regedit[i];
        if (regedit) {
            // free_queue(&regedit->runable_queue);
            free(regedit);
        }
    }
    pthread_mutex_destroy(&global_co_regedit.mutex);
    pthread_cond_destroy(&global_co_regedit.work_available);
    pthread_cond_destroy(&global_co_regedit.main_available);
    printf("Coroutine system resources cleaned up\n");
}
struct co *co_self() {
    return current; 
}