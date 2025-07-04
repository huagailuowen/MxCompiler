#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "co-test.h"

int g_count = 0;

static void add_count() {
    g_count++;
}

static int get_count() {
    return g_count;
}

static void work_loop(void *arg) {
    const char *s = (const char*)arg;
    for (int i = 0; i < 100; ++i) {
        printf("%s%d  ", s, get_count());
        add_count();
        co_yield();
    }
}

static void work(void *arg) {
    work_loop(arg);
}

static void test_1() {

    struct co *thd1 = co_start("thread-1", work, "X");
    struct co *thd2 = co_start("thread-2", work, "Y");

    co_wait(thd1);
    co_wait(thd2);

//    printf("\n");
}

// -----------------------------------------------

static int g_running = 1;

static void do_produce(Queue *queue) {
    assert(!q_is_full(queue));
    Item *item = (Item*)malloc(sizeof(Item));
    if (!item) {
        fprintf(stderr, "New item failure\n");
        return;
    }
    item->data = (char*)malloc(10);
    if (!item->data) {
        fprintf(stderr, "New data failure\n");
        free(item);
        return;
    }
    memset(item->data, 0, 10);
    sprintf(item->data, "libco-%d", g_count++);
    q_push(queue, item);
}

static void producer(void *arg) {
    Queue *queue = (Queue*)arg;
    for (int i = 0; i < 40; ) {
        if (!q_is_full(queue)) {
            // co_yield();
            do_produce(queue);
            i += 1;
            // printf("%d",i);
        }
        co_yield();
    }
}

static void do_consume(Queue *queue) {
    assert(!q_is_empty(queue));

    Item *item = q_pop(queue);
    if (item) {
        printf("%s  ", (char *)item->data);
        free(item->data);
        free(item);
    }
}

static void consumer(void *arg) {
    Queue *queue = (Queue*)arg;
    while (g_running) {
        if (!q_is_empty(queue)) {
            do_consume(queue);
        }
        co_yield();
    }
}

static void test_2() {

    Queue *queue = q_new();

    struct co *thd1 = co_start("producer-1", producer, queue);
    struct co *thd2 = co_start("producer-2", producer, queue);
    struct co *thd3 = co_start("producer-3", producer, queue);
    struct co *thd4 = co_start("producer-4", producer, queue);
    struct co *thd5 = co_start("producer-5", producer, queue);
    struct co *thd6 = co_start("producer-6", producer, queue);
    struct co *thd7 = co_start("producer-7", producer, queue);

    struct co *thd8 = co_start("consumer-1", consumer, queue);
    struct co *thd9 = co_start("consumer-2", consumer, queue);
    struct co *thd10 = co_start("consumer-3", consumer, queue);
    struct co *thd11 = co_start("consumer-4", consumer, queue);
    struct co *thd12 = co_start("consumer-5", consumer, queue);
    struct co *thd13 = co_start("consumer-6", consumer, queue);
    struct co *thd14 = co_start("consumer-7", consumer, queue);

    co_wait(thd1);
    co_wait(thd2);
    co_wait(thd3);
    co_resume(thd4); // 恢复thd4
    co_wait(thd4);
    co_wait(thd5);
    co_wait(thd6);
    co_wait(thd7);


    g_running = 0;

    // fprintf(stdout, "second last consumer wait\n");

    co_wait(thd8);
    co_wait(thd9);
    co_wait(thd10);
    co_wait(thd11);
    co_wait(thd12);
    co_wait(thd13);
    co_wait(thd14);


    while (!q_is_empty(queue)) {
        do_consume(queue);
    }

    q_free(queue);
}
static struct co *suspended_co = NULL;

// 这个协程会在中途暂停
static void worker(void *arg) {
    printf("Worker: 开始执行...\n");
    
    for (int i = 0; i < 5; i++) {
        printf("Worker: 步骤 %d\n", i+1);
        co_yield();
    }
    
    printf("Worker: 暂停，等待被恢复...\n");
    suspended_co = co_self(); // 保存自己的引用
    co_yield(); // 让出控制权
    
    printf("Worker: 被恢复啦！继续执行...\n");
    
    for (int i = 5; i < 10; i++) {
        printf("Worker: 步骤 %d\n", i+1);
        co_yield();
    }
    
    printf("Worker: 完成！\n");
}

// 这个协程会恢复被暂停的协程
static void manager(void *arg) {
    printf("Manager: 开始执行...\n");
    
    // 等待一段时间
    for (int i = 0; i < 15; i++) {
        printf("Manager: 检查中... (%d)\n", i+1);
        co_yield();
        
        // 检查是否有被暂停的协程
        if (suspended_co != NULL && i >= 10) {
            printf("Manager: 发现被暂停的协程，准备恢复它...\n");
            co_resume(suspended_co);
            suspended_co = NULL; // 清除引用
            printf("Manager: 已恢复协程\n");
        }
    }
    
    printf("Manager: 完成！\n");
}

static void test_3() {
    printf("\n\nTest #3. 测试协程挂起和恢复\n");
    
    // 重置状态
    suspended_co = NULL;
    
    // 创建协程
    struct co *worker_co = co_start("worker", worker, NULL);
    struct co *manager_co = co_start("manager", manager, NULL);
    
    // 等待两个协程完成
    co_wait(worker_co);
    co_wait(manager_co);
    
    printf("测试 #3 完成\n");
}


#define ITER 1000
#define MAX_CO 1000

void simple_work(void *arg) {
    int id = (int)(size_t)arg; // 将 void* 转换为 int
    for (int i = 0; i < ITER; i++) {
        // printf("[%d] Working %d\n", id, i);
        co_yield();
    }
    printf("[%d] Done!\n", id);
}

void omain() {
    printf("Starting G-P-M scheduler test\n");
    // fprintf(stderr, "AAAA\n");
    struct co *g[MAX_CO];


    for (int i = 0; i < MAX_CO; i++) {
        char name[20];
        // snprintf(name, sizeof(name), "Worker-%d", i + 1);
        name[0]=name[1] = '3'; name[2] = '0'; name[3] = '0'; name[4] = '\0';
        g[i] = co_start(name, simple_work, (void*)(size_t)i);
    }

    printf("Created %d coroutines\n", MAX_CO);

    co_yield();
    // 等待协程完成
    for (int i = 0; i < MAX_CO; i++) {
        co_wait(g[i]);
    }
    
    printf("AAll coroutines completed\n");
}
void test4()
{
    struct co* g= co_start("_main", omain, NULL);
    co_wait(g);
    printf("All coroutines completed\n");
}
int main() {
    setbuf(stdout, NULL);

    printf("Test #1. Expect: (X|Y){0, 1, 2, ..., 199}\n");
    test_1();

    printf("\n\nTest #2. Expect: (libco-){200, 201, 202, ..., 399}\n");
    // test_2();


    test_3();

    test4();

    fprintf(stdout,"Finished\n\n");

    return 0;
}