#include "co.h"
#include <bits/pthreadtypes.h>
#include <pthread.h>
#include <setjmp.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <signal.h>
#include <unistd.h>
#include <errno.h>

#define getmin(a, b) ((a) < (b) ? (a) : (b))


#include <sys/syscall.h>


// #define DEBUG
#ifdef DEBUG
// 获取线程ID（用于调试输出）
static inline pid_t get_tid() {
    return syscall(SYS_gettid);
}
// 每个线程保存自己持有的锁地址集合
#define MAX_HELD_LOCKS 1024

__thread const pthread_mutex_t* held_locks[MAX_HELD_LOCKS];
__thread int held_lock_count = 0;
__thread struct co *last_co;

int is_lock_held(const pthread_mutex_t* m) {
    for (int i = 0; i < held_lock_count; ++i) {
        if (held_locks[i] == m) return i+1;
    }
    return 0;
}
void debug_abort() {
    fprintf(stderr, "[ERROR] Aborting thread %d\n", get_tid());
    abort();
}
void add_lock(const pthread_mutex_t* m) {
    if (held_lock_count < MAX_HELD_LOCKS) {
        held_locks[held_lock_count++] = m;
    } else {
        fprintf(stderr, "[FATAL] held_lock_count overflow\n");
        debug_abort();
    }
}

void remove_lock(const pthread_mutex_t* m) {
    for (int i = 0; i < held_lock_count; ++i) {
        if (held_locks[i] == m) {
            held_locks[i] = held_locks[--held_lock_count];
            return;
        }
    }
    fprintf(stderr, "[WARN] Unlocking untracked lock %p\n", m);
    debug_abort();
}

void debug_mutex_lock(pthread_mutex_t* m) {
    if (is_lock_held(m)) {
        fprintf(stderr, "[ERROR] Double lock detected by TID %d on mutex %p\n", get_tid(), (void*)m);
        debug_abort();
    }
    pthread_mutex_lock(m);
    add_lock(m);
}
__thread int ttt;
void debug_mutex_unlock(pthread_mutex_t* m) {
    ttt = held_lock_count;
    remove_lock(m);
    if(ttt != held_lock_count+1){
        fprintf(stderr, "[ERROR] Unlocking untracked lock %p by TID %d\n", (void*)m, get_tid());
        debug_abort();
    }
    ttt=  held_lock_count;
    pthread_mutex_unlock(m);
    if(ttt != held_lock_count){
        fprintf(stderr, "[ERROR] Unlocking untracked lock %p by TID %d\n", (void*)m, get_tid());
        debug_abort();
    }
}
#define pthread_mutex_lock debug_mutex_lock
#define pthread_mutex_unlock debug_mutex_unlock
#endif

// 原子读取状态
__thread struct co* volatile current;
__attribute__((noinline))
struct co* get_current()
{
    return current;
}
__attribute__((noinline))
void set_current(struct co* co)
{
    current = co;
}
__thread struct co_regedit *local_co_regedit;  // 当前M对应的P
__thread bool is_main_thread; // 是否为主线程
struct global_co_regedit global_co_regedit;

void queue_check(struct queue_wrapper *queue) {
    return;
    int cnt = 0;
    struct queue_node *node = queue->head;
    while (node != NULL) {
        cnt++;
        node = node->next;
    }
    if(cnt != queue->num) {
        fprintf(stderr, "Fatal Error : queue num is %d, but count is %d\n", queue->num, cnt);
        abort();
    }
    if(queue->head != NULL && queue->tail == NULL) {
        fprintf(stderr, "Fatal Error : queue head is not NULL, but tail is NULL\n");
        abort();
    }
    cnt =0;
    node = queue->tail;
    while (node != NULL) {
        cnt++;
        node = node->prev;
    }
    if(cnt != queue->num) {
        fprintf(stderr, "Fatal Error : queue num is %d, but count is %d\n", queue->num, cnt);
        abort();
    }
}
struct queue_wrapper *get_queue_wrapper(struct queue_node *node) {
    return atomic_load(&node->queue_wrapper);
}
void set_queue_wrapper(struct queue_node *node, struct queue_wrapper *queue) {
    atomic_store(&node->queue_wrapper, queue);
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
    set_queue_wrapper(node, NULL); // 初始化队列指针为NULL
    return node;
}
struct queue_node * pop_queue_node(struct queue_wrapper * queue) {
    volatile struct queue_node * head = queue->head;
    if(head == NULL) {
        return NULL;
    }
    queue->head = head->next;
    if(queue->head == NULL) {
        queue->tail = NULL;
    }
    if(queue->head != NULL) {
        queue->head->prev = NULL; // 设置新的头节点的前驱为NULL
    }
    queue->num--;
    set_queue_wrapper(head, NULL); // 清除队列指针
    queue_check(queue);
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
    if(queue->tail != NULL) {
        queue->tail->next = NULL;
    }
    queue->num--;
    set_queue_wrapper(tail, NULL); // 清除队列指针
    queue_check(queue);

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
        node->prev = queue->tail;
        queue->tail = node;
        node->next = NULL;
    }
    set_queue_wrapper(node, queue); // 设置队列指针
    
    queue->num++;
    queue_check(queue);
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
    set_queue_wrapper(node, queue); // 设置队列指针
    queue->num++;
    queue_check(queue);
}
struct queue_node * fetch_queue_node(struct queue_wrapper * queue, struct queue_node * node) {
    if(queue == NULL || node == NULL || get_queue_wrapper(node) != queue) {
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
    set_queue_wrapper(node, NULL); // 清除队列指针
    queue->num--;
    queue_check(queue);

    return node;
}
void free_queue(struct queue_wrapper * queue) {
    abort(); // 这里不应该被调用，除非是测试代码
    struct queue_node * node = queue->head;
    while(node != NULL) {
        struct queue_node * temp = node;
        node = node->next;
        free(temp);
    }
    queue->head = NULL;
    queue->tail = NULL;
}


void co_signal_init(struct co_signal *signal, unsigned int initial_count)
{
    signal->waiter_list_head = NULL; // 初始化等待队列为空
    signal->count = initial_count; // 设置初始信号计数
    pthread_mutex_init(&signal->mutex, NULL); // 初始化互斥锁
}

void co_signal_destroy(struct co_signal *signal)
{
    pthread_mutex_destroy(&signal->mutex);
    struct linked_list_node *node = signal->waiter_list_head;
    while (node != NULL) {
        struct linked_list_node *temp = node;
        node = node->next;
        free(temp);
    }
    signal->waiter_list_head = NULL;
}

void co_signal_wait(struct co_signal *signal)
{
    pthread_mutex_lock(&signal->mutex);
    if (signal->count == 0) {
        struct linked_list_node *node = malloc(sizeof(struct linked_list_node));
        node->next = signal->waiter_list_head;
        signal->waiter_list_head = node;
        node->waiter = get_current(); // 获取当前协程
        pthread_mutex_lock(&node->waiter->wait_mutex); // 锁定当前协程的互斥锁
        node->waiter->status = CO_WAITING; // 设置当前协程状态为等待
        
        pthread_mutex_lock(&local_co_regedit->mutex);
        local_co_regedit->is_kick = true;
        
        pop_queue_node(&local_co_regedit->runable_queue);
        pthread_mutex_unlock(&signal->mutex);
        co_yield();
    }else{
        signal->count--;
        pthread_mutex_unlock(&signal->mutex);
    }
    
}
void assign_co_task(struct queue_node *node);
void co_signal_notify(struct co_signal *signal)
{
    pthread_mutex_lock(&signal->mutex);
    if (signal->waiter_list_head != NULL) {
        struct linked_list_node *node = signal->waiter_list_head;
        signal->waiter_list_head = node->next;
        
        pthread_mutex_lock(&node->waiter->wait_mutex); // 锁定当前协程的互斥锁
        node->waiter->status = CO_RUNNING; 
        pthread_mutex_unlock(&node->waiter->wait_mutex); // 解锁当前协程的互斥锁
        
        pthread_mutex_unlock(&signal->mutex);
        pthread_mutex_lock(&local_co_regedit->mutex);
        assign_co_task(node->waiter->queue_node_ptr); // 将等待的协程任务分配到运行队列
        pthread_mutex_unlock(&local_co_regedit->mutex);

        
        free(node); 
    } else {
        signal->count++;
        pthread_mutex_unlock(&signal->mutex);
    }
}





enum global_co_schedule_status get_schedule_status() {
    return atomic_load(&global_co_regedit.status);
}

// 原子设置状态
void set_schedule_status(enum global_co_schedule_status new_status) {
    atomic_store(&global_co_regedit.status, new_status);
}

// 原子比较并交换
// bool compare_and_swap_status(enum global_co_schedule_status expected, 
//                             enum global_co_schedule_status desired) {
//     return atomic_compare_exchange_strong(&global_co_regedit.status, &expected, desired);
// }


void transmit_queue(struct queue_wrapper * src, struct queue_wrapper * dest, int transmit_num) {
    struct queue_node * main_node = NULL;
    while(transmit_num-- > 0 && (src->num > 1 || src == &global_co_regedit.runable_queue&& src->num > 0 )) {
        struct queue_node * node = pop_queue_node_back(src);
        queue_check(src);
        // if(src->head && src->head->co == global_co_regedit.co_main) {
        //     // 如果是主协程，直接放回源队列
        //     break;
        // }
        if(node == NULL) {
            break; // 如果源队列为空，退出循环
        }
        // fprintf(stdout,"steal co name %s, id %d\n", node->co->name ? node->co->name : "unknown", node->co->c_id);
        if(node->co->c_id == 0){
            
            // 如果是主协程，直接放回源队列
            // break;
            main_node = node; // 保存主协程节点
            continue; // 跳过主协程
        }
        push_queue_node(dest, node); // 将节点添加到目标队列最低优先级 
        queue_check(dest); // 检查目标队列状态
    }
    if(main_node != NULL) {
        // 如果有主协程节点，放回源队列
        push_queue_node(src, main_node);
        queue_check(src); // 检查源队列状态
    }
    
}


// 负载均衡器
void steal_co_task()
{
    
    if(get_schedule_status() != GLOBAL_CO_SCHEDULE_RUNNING) {
        // 如果全局协程调度已停止，直接返回
        return;
    }
    // return;
    int i = 0, P_num;
    // pthread_mutex_lock(&global_co_regedit.mutex);
    P_num = global_co_regedit.P_num;
    // pthread_mutex_unlock(&global_co_regedit.mutex);
    
    int start = rand() % P_num;
    // fprintf(stdout,"Stealing tasks from P threads, start index: %d\n", start);
    for (i = 0; i < (P_num + 1 )>>1; i++) {
        int idx = (start + i) % P_num;
        struct co_regedit *target = global_co_regedit.local_co_regedit[idx];
        if(target == local_co_regedit)
            continue; // 跳过当前线程
        if (target && pthread_mutex_trylock(&target->mutex) == 0) {
            // pthread_mutex_unlock(&target->mutex);
            // if(idx == 0) {
            //     pthread_mutex_unlock(&target->mutex);
            //     continue;
            // }
            // continue;
            if (target->runable_queue.num > MAX_FETCH_NUM>>1) {
                int fetch_num = getmin(MAX_FETCH_NUM, target->runable_queue.num>>1);
                transmit_queue(&target->runable_queue, &local_co_regedit->runable_queue, fetch_num);
                queue_check(&local_co_regedit->runable_queue); // 检查当前线程的运行队列状态
                queue_check(&target->runable_queue); // 检查目标线程的运行队列状态
                pthread_mutex_unlock(&target->mutex);
                break;
            }
            pthread_mutex_unlock(&target->mutex);
        }
    }
}
__attribute__((noinline))
void fetch_co_task()
{
    // if (pthread_mutex_trylock(&local_co_regedit->mutex) == 0) {
    //     fprintf(stderr, "Fatal Error: fetch_co_task must be called with local_co_regedit->mutex held\n");
    //     abort();
    // } 
    int cnt = 0;
    
    pthread_mutex_lock(&global_co_regedit.mutex);
    do{
        if(is_main_thread){
            if(global_co_regedit.is_main_available) {
                // 如果主协程已经准备好，直接从主协程队列中获取任务
                struct queue_node * node = global_co_regedit.co_main_queue_node;
                // global_co_regedit.co_main_queue_node = NULL; // 清空主协程队列节点
                global_co_regedit.is_main_available = false; // 标记主协程未准备好
                push_queue_node(&local_co_regedit->runable_queue, node);
                queue_check(&local_co_regedit->runable_queue); // 检查当前线程的运行队列状态
                cnt++;
            }
        }
        int origin_num = local_co_regedit->runable_queue.num;
        transmit_queue(&global_co_regedit.runable_queue, &local_co_regedit->runable_queue, MAX_FETCH_NUM);
        queue_check(&local_co_regedit->runable_queue); // 检查队列状态
        queue_check(&global_co_regedit.runable_queue); // 检查全局队列状态
        cnt += local_co_regedit->runable_queue.num - origin_num; // 计算获取的任务数
        // pthread_mutex_unlock(&global_co_regedit.mutex);
        if (cnt == 0) {
            steal_co_task(); // 尝试从其他线程窃取任务
        }
        if (local_co_regedit->runable_queue.num == 0) {
            // 如果运行队列还为空，等待其他线程添加任务
            // pthread_mutex_lock(&global_co_regedit.mutex);
            if(is_main_thread) {
                global_co_regedit.running_num--;
                if(global_co_regedit.running_num == 0) {
                    fprintf(stderr,"11ddfadfadfad6\n");
                    // abort();
                }
                pthread_cond_wait(&global_co_regedit.main_available, &global_co_regedit.mutex);
            } else {
                global_co_regedit.running_num--;
                if(global_co_regedit.running_num == 0 ) {
                    if(global_co_regedit.is_main_available){
                        pthread_cond_signal(&global_co_regedit.main_available); // 唤醒主线程
                    }
                    else{
                        fprintf(stderr,("START\n"));
                        // abort();
                    }
                }
                pthread_cond_wait(&global_co_regedit.work_available, &global_co_regedit.mutex);
            }
            global_co_regedit.running_num++;
            // pthread_mutex_unlock(&global_co_regedit.mutex);
        }
    }while(local_co_regedit->runable_queue.num == 0 && get_schedule_status() != GLOBAL_CO_SCHEDULE_STOPPED);
    if(get_schedule_status() == GLOBAL_CO_SCHEDULE_STOPPED) {
        pthread_mutex_unlock(&global_co_regedit.mutex);
        pthread_mutex_unlock(&local_co_regedit->mutex);
        pthread_exit(NULL); // 如果全局协程调度已停止，退出当前线程
        
    }
    // pthread_mutex_lock(&global_co_regedit.mutex);
    bool is_available_work = global_co_regedit.runable_queue.num > MAX_FETCH_NUM  || get_schedule_status() == GLOBAL_CO_SCHEDULE_STOPPED;
    is_available_work = is_available_work || global_co_regedit.runable_queue.num>0 && global_co_regedit.running_num < (MAX_CO_PROCESS_NUM>>2|1);
    if(is_available_work) {
        // 唤醒其他等待的线程
        pthread_cond_signal(&global_co_regedit.work_available);
    }
    if(is_available_work || global_co_regedit.is_main_available) {
        pthread_cond_signal(&global_co_regedit.main_available);
    }
    pthread_mutex_unlock(&global_co_regedit.mutex);
    if((local_co_regedit->runable_queue.head == NULL) !=( local_co_regedit->runable_queue.num==0)){
        fprintf(stderr, "Fatal Error : local_co_regedit->runable_queue.head is NULL but num is %d\n", local_co_regedit->runable_queue.num);
        abort();
    }
    // printf("[%d,%d]", local_co_regedit->runable_queue.head == NULL, local_co_regedit->runable_queue.num);
    
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
        abort();
    }
    if(node->co == global_co_regedit.co_main) {
        // printf("%d",is_main_thread);
        if(is_main_thread) {
            // 如果是main线程，直接加入主线程
            
            push_queue_node(&local_co_regedit->runable_queue, node);
            queue_check(&local_co_regedit->runable_queue);
        }
        else{
            // 加入主线程队列
            pthread_mutex_lock(&global_co_regedit.mutex);
            // if (!global_co_regedit.is_main_available) {
            // } 
            global_co_regedit.co_main_queue_node = node; // 保存主协程的队列节点
            global_co_regedit.is_main_available = true; // 标记主协程已准备好
            // printf("Main coroutine %s is now available\n", node->co->name ? node->co->name : "unknown");
            pthread_cond_signal(&global_co_regedit.main_available); // 唤醒主线程
            pthread_mutex_unlock(&global_co_regedit.mutex);
            // printf("send signal to main thread\n");
        }
        return;
    }
    // if(false) {
    if (local_co_regedit->runable_queue.num >= MAX_FETCH_NUM<<1 || (local_co_regedit->runable_queue.num >= MAX_FETCH_NUM && node->priority > 64)) {
        // 如果当前P线程的可运行队列已满，则尝试将协程任务分配给全局可运行队列
        pthread_mutex_lock(&global_co_regedit.mutex);
        push_queue_node_front(&global_co_regedit.runable_queue, node);
        queue_check(&global_co_regedit.runable_queue); // 检查全局队列状态
        node->priority = 0; // 设置优先级为0，表示全局队列
        bool is_available_work = global_co_regedit.runable_queue.num >MAX_FETCH_NUM || global_co_regedit.running_num < MAX_CO_PROCESS_NUM>>1 || get_schedule_status() == GLOBAL_CO_SCHEDULE_STOPPED; // 检查全局队列是否有可运行的协程
        // 唤醒其他等待的线程
        is_available_work = is_available_work || global_co_regedit.runable_queue.num>0 && global_co_regedit.running_num < (MAX_CO_PROCESS_NUM>>2|1);
        if(is_available_work){
            pthread_cond_signal(&global_co_regedit.work_available); 
        }
        if(is_available_work || global_co_regedit.is_main_available){
            pthread_cond_signal(&global_co_regedit.main_available); // 唤醒主线程
        }
        pthread_mutex_unlock(&global_co_regedit.mutex);
        

    } else {
        // 否则，将协程任务分配给当前P线程
        push_queue_node(&local_co_regedit->runable_queue, node);
        queue_check(&local_co_regedit->runable_queue); // 检查当前线程的运行队列状态
    }
    
}

struct co * co_add_task(const char *name, void (*func)(void *), void *arg, bool is_main) {
    struct co * handler = (struct co *)malloc(sizeof(struct co));
    if (!handler) {
        fprintf(stderr, "Failed to allocate memory for coroutine\n");
        abort();
    }
    // printf("Creating coroutine %s with ID %d\n", name ? name : "unknown", global_co_regedit.co_num);
    struct queue_node * queue_node = create_queue_node(handler);
    pthread_mutex_lock(&global_co_regedit.mutex);
    if(global_co_regedit.co_num == MAX_CO_NUM) {
        fprintf(stderr, "Too many coroutines\n");
        abort();
    }
    global_co_regedit.co_pool[global_co_regedit.co_num] = handler;
    handler->c_id = global_co_regedit.co_num++;
    global_co_regedit.alive_num++; // 增加全局存活协程数
    if(is_main){
        global_co_regedit.co_main = handler; // 设置全局main协程
    }
    pthread_mutex_unlock(&global_co_regedit.mutex);

    
    
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
        abort();
    }
    pthread_mutex_lock(&local_co_regedit->mutex);
    assign_co_task(queue_node); // 分配协程任务到当前P线程的可运行队列
    pthread_mutex_unlock(&local_co_regedit->mutex);
    return handler;
}


struct co *co_start(const char *name, void (*func)(void *), void *arg)
{
    struct co *co = co_add_task(name, func, arg, false);
    return co;
}

__attribute__((noinline))
void start_new_wrapper() {
    
    pthread_mutex_lock(&get_current()->wait_mutex);
    current->status = CO_RUNNING;
    pthread_mutex_unlock(&current->wait_mutex);
    
    current->func(current->arg);
    // printf("Coroutine %s finished\n", current->name ? current->name : "unknown");
    // printf("Local queue :%d\n", local_co_regedit->runable_queue.num);
    pthread_mutex_lock(&local_co_regedit->mutex);
    pop_queue_node(&local_co_regedit->runable_queue);

    pthread_mutex_lock(&get_current()->wait_mutex);
    current->status = CO_DEAD;
    local_co_regedit->is_kick = true;
    #ifdef DEBUG
    last_co = current; // 保存最后运行的协程
    #endif
    struct linked_list_node * node = current->waiter_list_head;
    while(node != NULL) {
        struct co * waiter = node->waiter;
        node = node->next;
        pthread_mutex_lock(&waiter->wait_mutex);
        waiter->status = CO_RUNNING;
        if(waiter == global_co_regedit.co_main) {
            // printf("Main coroutine %s is now running\n", waiter->name ? waiter->name : "unknown");
            // printf("%d",waiter->queue_node_ptr==NULL);
        }
        pthread_mutex_unlock(&waiter->wait_mutex);
        assign_co_task(waiter->queue_node_ptr); // 将等待的协程重新加入可运行队列
    }
    pthread_mutex_lock(&global_co_regedit.mutex);
    global_co_regedit.alive_num--; // 减少全局存活协程数
    pthread_mutex_unlock(&global_co_regedit.mutex);
    
    // pthread_mutex_unlock(&local_co_regedit->mutex);
    //返回最初调用他的schedule, 并设置状态为 CO_DEAD
}
struct co_regedit * co_P_init() { //init for P
    struct co_regedit *co_regedit = malloc(sizeof(struct co_regedit));
    if (!co_regedit) {
        fprintf(stderr, "Failed to allocate memory for coroutine registry\n");
        abort();
    }
    pthread_mutex_lock(&global_co_regedit.mutex);
    co_regedit->regedit_id = global_co_regedit.P_num++;
    global_co_regedit.running_num++; // 增加全局正在运行的P线程数

    // printf(stdout,"Creating P thread registry with ID %d\n", co_regedit->regedit_id);
    global_co_regedit.local_co_regedit[co_regedit->regedit_id] = co_regedit;
    pthread_mutex_unlock(&global_co_regedit.mutex);
    co_regedit->runable_queue.num = 0;
    co_regedit->runable_queue.head = NULL;
    co_regedit->runable_queue.tail = NULL;
    co_regedit->schedule_count = 0; // 初始化调度计数
    co_regedit->is_resume = false;
    co_regedit->is_kick = false;
    co_regedit->resume_co = NULL;
    // pthread_mutexattr_t attr;
    // pthread_mutexattr_init(&attr);
    // pthread_mutexattr_settype(&attr, PTHREAD_MUTEX_RECURSIVE);
    if(pthread_mutex_init(&co_regedit->mutex, NULL) != 0) {
        fprintf(stderr, "Failed to initialize mutex\n");
        free(co_regedit);
        abort();
    }
    return co_regedit;
}

__attribute__((noinline))
void start_new_context(void *sp, void (*entry)(void)) {
    
    asm volatile (
        "mov %0, %%rsp\n"
        "jmp *%1\n"
        :
        : "r"(sp - 8), "r"(entry)
        : "rsp", "memory"
    );
    __builtin_unreachable();
}
void schedule(); // 前向声明
void ret_origin_context() {
    // 返回到原始的P线程上下文

    if(get_current() != global_co_regedit.co_main) {
        longjmp(local_co_regedit->origin_context, 1);
    }else{
        schedule();
    }
}

__attribute__((noreturn)) void trampoline_wrapper() {
    __asm__ __volatile__("" ::: "memory");
    void (*entry)(void) = start_new_wrapper;  // 直接赋值，不从栈读
    // printf("trampoline_wrapper: entry = %p\n", entry);
    entry();    
    __atomic_thread_fence(__ATOMIC_SEQ_CST);
    __asm__ __volatile__("" ::: "memory");
    ret_origin_context(); // 返回到原始的P线程上下文
    __builtin_unreachable();
}

void schedule() {
    while(true){
        struct queue_node * volatile node = NULL;
        struct queue_node * volatile resume_node = NULL;
        volatile int is_kick = local_co_regedit->is_kick;
        if(!is_kick){
            pthread_mutex_lock(&local_co_regedit->mutex);
            node = pop_queue_node(&local_co_regedit->runable_queue);
        }else {
            pthread_mutex_unlock(&current->wait_mutex);

            #ifdef DEBUG
            if(!is_lock_held(&local_co_regedit->mutex)){
                fprintf(stderr, "Fatal Error : local_co_regedit->mutex must be held before calling schedule\n");
                abort();
            }
            #endif
        }
        local_co_regedit->is_kick = false; // 重置kick状态
        queue_check(&local_co_regedit->runable_queue);
        if(get_schedule_status() == GLOBAL_CO_SCHEDULE_STOPPED) {

            // 如果不是主线程且全局协程调度已停止，直接返回
            if(is_main_thread) {
                fprintf(stderr, "Fatal Error : schedule must not be called in main thread after global_co_regedit.status is GLOBAL_CO_SCHEDULE_STOPPED\n");
                abort();
            }
            pthread_mutex_unlock(&local_co_regedit->mutex);
            return;// 返回到P线程的初始状态
        }
        
        bool is_resume = false;
        // there is now bug in resume, for may be the resume_co is not in the local queue
        if(node != NULL){
            if(node->co != current){
                fprintf(stderr, "Warning: Coroutine %d is not the current coroutine\n", node->co->c_id);
                abort();
            }
        }

        if(local_co_regedit->is_resume) {
            is_resume = true;
            local_co_regedit->is_resume = false;
            // pthread_mutex_lock(&local_co_regedit->resume_co->wait_mutex);
            pthread_mutex_lock(&global_co_regedit.mutex);
            struct queue_wrapper *queue = get_queue_wrapper(local_co_regedit->resume_co->queue_node_ptr);
            // 锁住全局调度器才保证resume_co队列位置判断的原子性
            if(local_co_regedit->resume_co == global_co_regedit.co_main && global_co_regedit.is_main_available){
                global_co_regedit.is_main_available = false; // 标记主协程未准备好
                push_queue_node_front(&local_co_regedit->runable_queue, local_co_regedit->resume_co->queue_node_ptr);
            }else if(queue == &local_co_regedit->runable_queue) {
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
            // pthread_mutex_unlock(&local_co_regedit->resume_co->wait_mutex);

            if(resume_node != local_co_regedit->runable_queue.head) {
                push_queue_node_front(&local_co_regedit->runable_queue, resume_node);
            }
        }else{
            resume_node = local_co_regedit->runable_queue.head; // 取出队列头部的协程
        }
        // node 为空 resume必为空
        if(!node && !is_kick && resume_node){
            fprintf(stderr, "Warning: Coroutine %d is not the current coroutine, but resume_node is not NULL\n", resume_node->co->c_id);
            abort();
        }
        if((node == NULL && !is_kick) || (resume_node == NULL && is_kick)) {
            // 真的全空了向全局索要
            fetch_co_task();
            resume_node = local_co_regedit->runable_queue.head;
            if(local_co_regedit->runable_queue.head == NULL) {
                // 如果本地可运行队列还是空的，尝试从其他线程窃取任务
                printf("Local runnable queue is empty, Error\n");
            }
            pthread_mutex_lock(&global_co_regedit.mutex);
            if(get_schedule_status() == GLOBAL_CO_SCHEDULE_STOPPED) {
                pthread_mutex_unlock(&global_co_regedit.mutex);
                pthread_mutex_unlock(&local_co_regedit->mutex);
                return;// 返回到P线程的初始状态
            }
            pthread_mutex_unlock(&global_co_regedit.mutex);

            // 此时node是NULL，之前没有运行过的协程
        }
        if(resume_node == NULL) {
            if(!node || is_kick) {
                // 如果没有可运行的协程，且当前协程已经死亡，不正常
                printf("%d",local_co_regedit->runable_queue.head==NULL);
                printf("No runnable coroutines available, exiting...\n");
                abort();
            }
            resume_node = node; // 如果没有resume_node，使用node
        }
        
        if(node && !is_kick) {
            if(node == resume_node){
                push_queue_node(&local_co_regedit->runable_queue, node);
            }
            else{
                assign_co_task(node); // 将当前协程重新加入可运行队列
            }
            // 保证加入自身
        }
        if(local_co_regedit->runable_queue.head == NULL) {
            printf("All coroutines are dead\n");
            exit(-1);
        }
        if(local_co_regedit->schedule_count % 64 == 0 && local_co_regedit->runable_queue.num < MAX_FETCH_NUM ) {
            fetch_co_task(); 
        }
        resume_node->priority++; // 每运行一次优先级下降
        local_co_regedit->schedule_count++;
        struct co *volatile  co = resume_node->co;
        // current = co;
        set_current(co);
        if(current != local_co_regedit->runable_queue.head->co) {
            fprintf(stderr, "Fatal Error : current is not the head of local_co_regedit->runable_queue\n");
            abort();
            
        }
        queue_check(&local_co_regedit->runable_queue); // 检查当前线程的运行队列状态
        local_co_regedit->is_kick = false;
        pthread_mutex_unlock(&local_co_regedit->mutex);
        // printf("Switching to coroutine %s with ID %d  %d\n", co->name ? co->name : "unknown", co->c_id,current->status);
        if(get_current() == global_co_regedit.co_main) {
            // 如果是主协程，设置全局状态为正在运行
            return;
        }
        __asm__ __volatile__("" ::: "memory");
        if(setjmp(local_co_regedit->origin_context) == 0){
            __asm__ __volatile__("" ::: "memory");
            if(get_current()->status == CO_NEW) {
                //if co being spill to the stack by the compiler, the error may occur
                // asm volatile("mov %0, %%rsp" : : "g"(current->stack + STACK_SIZE) : "rsp", "memory");
                
                uint8_t *sp = (uint8_t *)current->stack + STACK_SIZE;
                sp = (uint8_t *)((uintptr_t)sp & ~0xF); // 16字节对齐
        
                // sp -= sizeof(void *);
                // *(void **)sp = (void *)start_new_wrapper; // 将被 trampoline_wrapper 取出
                __asm__ __volatile__("" ::: "memory");

                start_new_context(sp, trampoline_wrapper);
        
        
                // start_new_wrapper();
                // goto Start;
            } else {
                //must be CO_RUNNING
                __asm__ __volatile__("" ::: "memory");
                longjmp(get_current()->context, 1);
            }
        }
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
    __asm__ __volatile__("" ::: "memory");
    if (setjmp(current->context) == 0) {
        // asm volatile("mov %0, %%rsp" : : "g"(global_co_regedit.co_pool[0]->stack + STACK_SIZE));
        __asm__ __volatile__("" ::: "memory");
        ret_origin_context();
    }
    __asm__ __volatile__("" ::: "memory");
}

void co_resume(struct co *co)
{
    pthread_mutex_lock(&co->wait_mutex);
    if (co->status == CO_DEAD) {
        pthread_mutex_unlock(&co->wait_mutex);
        return;
    }
    if(co->status == CO_WAITING) {
        printf("Fatal Error : co is waiting\n");
        pthread_mutex_unlock(&co->wait_mutex);

        abort();
    }
    if(co == current) {
        pthread_mutex_unlock(&co->wait_mutex);
        return;
    }
    pthread_mutex_unlock(&co->wait_mutex);
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
    pthread_mutex_lock(&local_co_regedit->mutex);
    pthread_mutex_lock(&co->wait_mutex);
    if (co->status == CO_DEAD) {
        pthread_mutex_unlock(&co->wait_mutex);
        pthread_mutex_unlock(&local_co_regedit->mutex);
        return;
    }
    pop_queue_node(&local_co_regedit->runable_queue);
    pthread_mutex_lock(&current->wait_mutex);
    add_waiter(current, co);
    current->status = CO_WAITING;
    set_queue_wrapper(current->queue_node_ptr, NULL);
    #ifdef DEBUG
    if(!is_lock_held(&local_co_regedit->mutex)) {
        fprintf(stderr, "Fatal Error : local_co_regedit->mutex must be held before calling co_wait\n");
        abort();
    }
    int ps = is_lock_held(&local_co_regedit->mutex);
    
    int uu = held_lock_count;
    const pthread_mutex_t* tt = held_locks[0];
    const pthread_mutex_t* tt1 = held_locks[1];
    #endif
    pthread_mutex_unlock(&co->wait_mutex);
    #ifdef DEBUG
    int uu_ = held_lock_count;
    const pthread_mutex_t* tt_ = held_locks[0];
    
    int ps_ = is_lock_held(&local_co_regedit->mutex);
    if(!is_lock_held(&local_co_regedit->mutex)) {
        fprintf(stderr, "Fatal Error : local_co_regedit->mutex must be held before calling co_wait\n");
        abort();
    }
    #endif
    local_co_regedit->is_kick = true; // 标记当前协程被挂起
    #ifdef DEBUG
    last_co = current; // 保存最后运行的协程
    #endif
    // if(current == global_co_regedit.co_main){
    //     global_co_regedit.co_main_queue_node = current->queue_node_ptr; // 如果当前协程是主协程，更新主协程队列节点
    //     global_co_regedit.is_main_available = false; // 标记主协程未准备好
    // }
    // printf("Coroutine %s is waiting for coroutine %s\n", current->name ? current->name : "unknown", co->name ? co->name : "unknown");
    // printf("%d",local_co_regedit->is_resume);
    co_yield();
}
void co_free(struct co *co)
{
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
}
__attribute__((constructor))
static void co_global_init() {
    // 使用 PTHREAD_MUTEX_INITIALIZER 静态初始化
    // static pthread_mutex_t temp_mutex = PTHREAD_MUTEX_INITIALIZER;
    // global_co_regedit.mutex = temp_mutex;
    if (pthread_mutex_init(&global_co_regedit.mutex, NULL) != 0) {
        fprintf(stderr, "Failed to initialize mutex\n");
        abort();
    }
    if (pthread_cond_init(&global_co_regedit.work_available, NULL) != 0) {
        fprintf(stderr, "Failed to initialize work_available condition variable\n");
        pthread_mutex_destroy(&global_co_regedit.mutex);
        abort();
    }
    if(pthread_cond_init(&global_co_regedit.main_available, NULL) != 0) {
        fprintf(stderr, "Failed to initialize main_available condition variable\n");
        pthread_mutex_destroy(&global_co_regedit.mutex);
        pthread_cond_destroy(&global_co_regedit.work_available);
        abort();
    }
    if(MAX_CO_PROCESS_NUM == 0){
        printf("Warning: MAX_CO_PROCESS_NUM is set to 0, destructor will fail for the global variable will first be destroyed.\n");
    }

    global_co_regedit.co_num = 0;
    global_co_regedit.runable_queue.num = 0;
    global_co_regedit.runable_queue.head = NULL;
    global_co_regedit.runable_queue.tail = NULL;
    set_schedule_status(GLOBAL_CO_SCHEDULE_INIT); // have not started yet
    global_co_regedit.P_num = 0;
    global_co_regedit.running_num = 0;
    global_co_regedit.alive_num = 0;
    global_co_regedit.is_main_available = false; 
    
    // 初始化main函数
    local_co_regedit = co_P_init();
    is_main_thread = true; // 设置为主线程
    struct co *main_co = co_add_task("main", NULL, NULL, true);
    main_co->status = CO_RUNNING; // 设置main协程为运行状态
    current = main_co; // 设置当前协程为main协程
    global_co_regedit.co_main_queue_node = main_co->queue_node_ptr; // 设置全局main协程
    pthread_mutex_lock(&local_co_regedit->mutex);
    for(int i = 0; i < MAX_CO_PROCESS_NUM; i++) {
        if (pthread_create(&global_co_regedit.threads[i], NULL, co_M_create, NULL) != 0) {
            fprintf(stderr, "Failed to create thread %d\n", i);
            abort();
        }
    }
    global_co_regedit.M_num = MAX_CO_PROCESS_NUM; // 设置当前线程数为最大线程数
    set_schedule_status(GLOBAL_CO_SCHEDULE_RUNNING); // 全局协程调度状态设置为运行中
    printf("Coroutine system initialized\n");  // 可选的调试信息
    pthread_mutex_unlock(&local_co_regedit->mutex);
}

__attribute__((destructor))
static void co_cleanup() {
    printf("Some M destructor");
    if(!is_main_thread){
        // printf("Fatal Error : co_cleanup must be called in main thread\n");
        // abort();
        return; // 如果不是主线程，直接返回 
    }
    printf("Cleaning up coroutine system resources...\n");

    // 设置停止标志
    // for (int i = 0; i < global_co_regedit.M_num; i++) {
    //     // pthread_join(global_co_regedit.threads[i],NULL);
    //     pthread_cancel(global_co_regedit.threads[i]); // 取消所有线程
    // }
    // exit(0);
    pthread_mutex_lock(&global_co_regedit.mutex);
    set_schedule_status(GLOBAL_CO_SCHEDULE_STOPPED);
    pthread_mutex_unlock(&global_co_regedit.mutex);
    pthread_cond_broadcast(&global_co_regedit.work_available); // 唤醒所有等待的线程
    // printf("Waiting for all threads to finish...\n");
    struct timespec wait_time = {.tv_sec = 0, .tv_nsec = 100000000}; // 100ms
    nanosleep(&wait_time, NULL);
    for (int i = 0; i < global_co_regedit.M_num; i++) {
        pthread_join(global_co_regedit.threads[i],NULL);
        // pthread_cancel(global_co_regedit.threads[i]); // 取消所有线程
    }
    printf("All threads have finished\n");
    for (int i = 0; i < global_co_regedit.co_num; i++) {
        struct co *co = global_co_regedit.co_pool[i];
        if (co) {

            co_free(co);

            
            pthread_mutex_destroy(&co->wait_mutex); // 销毁协程的互斥锁
            // 释放协程结构体
            free(co);
        }
    }
    printf("All coroutines have been cleaned up\n");
    // 释放可运行队列中的节点
    // free_queue(&global_co_regedit.runable_queue);
    printf("%d",global_co_regedit.P_num);
    for (int i = 0; i < global_co_regedit.P_num; i++) {
        struct co_regedit *regedit = global_co_regedit.local_co_regedit[i];
        if (regedit) {
            pthread_mutex_destroy(&regedit->mutex); // 销毁P线程的互斥锁
            // free_queue(&regedit->runable_queue);
            free(regedit);
        }
    }
    printf("All P thread registries have been cleaned up\n");
    pthread_mutex_destroy(&global_co_regedit.mutex);
    pthread_cond_destroy(&global_co_regedit.work_available);
    pthread_cond_destroy(&global_co_regedit.main_available);
    printf("Coroutine system resources cleaned up\n");
    exit(0); // 退出程序
}
struct co *co_self() {
    return current; 
}
#ifdef DEBUG
#undef pthread_mutex_lock
#undef pthread_mutex_unlock
#endif