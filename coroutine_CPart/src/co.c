#include "co.h"
#include <setjmp.h>


struct queue_node * create_queue_node(struct co * co) {
    struct queue_node * node = malloc(sizeof(struct queue_node));
    node->co = co;
    node->next = NULL;
    return node;
}
struct queue_node * pop_queue_node() {
    struct queue_node * head = global_co_regedit.runable_queue_head;
    if(head == NULL) {
        return NULL;
    }
    global_co_regedit.runable_queue_head = head->next;
    if(global_co_regedit.runable_queue_head == NULL) {
        global_co_regedit.runable_queue_tail = NULL;
    }
    return head;
}
void push_queue_node(struct queue_node * node) {
    if(global_co_regedit.runable_queue_head == NULL) {
        global_co_regedit.runable_queue_head = node;
        global_co_regedit.runable_queue_tail = node;
    } else {
        global_co_regedit.runable_queue_tail->next = node;
        global_co_regedit.runable_queue_tail = node;
    }
}
struct co* current;
__attribute__((constructor))
struct co * co_add_task(const char *name, void (*func)(void *), void *arg) {
    if(global_co_regedit.co_num == MAX_CO_NUM) {
        printf("Too many coroutines\n");
        exit(1);
    }
    struct co * handler = (struct co *)malloc(sizeof(struct co));
    global_co_regedit.co_pool[global_co_regedit.co_num] = handler;
    struct queue_node * queue_node = create_queue_node(handler);
    push_queue_node(queue_node);

    handler->c_id = global_co_regedit.co_num;
    handler->name = malloc(strlen(name) + 1);
    handler->func = func;
    handler->arg = arg;
    strcpy(handler->name, name);

    handler->status = CO_NEW;
    handler->waiter_list_head = NULL;
    handler->stack = malloc(STACK_SIZE);
    global_co_regedit.co_num++;
    return handler;
}
static void co_init() {
    // 在这里添加你的初始化代码
    
    struct co * main_handler = co_add_task("main", NULL, NULL);
    main_handler -> status = CO_RUNNING;
    current = main_handler;
    printf("Coroutine system initialized\n");  // 可选的调试信息
}

struct co *co_start(const char *name, void (*func)(void *), void *arg)
{
    struct co *co = co_add_task(name, func, arg);
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
        push_queue_node(create_queue_node(waiter));
    }
}
void schedule() {
    Start:;
    struct queue_node * node = pop_queue_node();
    if(node == NULL) {
        printf("Fatal Error : do not contain current\n");
        exit(1);
    }
    if(node->co->status != CO_DEAD && node->co->status != CO_WAITING) {
        push_queue_node(node);
    }else{
        free(node);
    }
    if(global_co_regedit.runable_queue_head == NULL) {
        printf("All coroutines are dead\n");
        exit(-1);
    }
    struct co * co = global_co_regedit.runable_queue_head->co;
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
}

void co_yield()
{
    if (setjmp(current->context) == 0) {
        schedule();
    }
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


__attribute__((destructor))
static void co_cleanup() {
    printf("Cleaning up coroutine system resources...\n");
    
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
    struct queue_node *node = global_co_regedit.runable_queue_head;
    while (node) {
        struct queue_node *temp = node;
        node = node->next;
        free(temp);
    }
    
    printf("Coroutine system resources cleaned up\n");
}