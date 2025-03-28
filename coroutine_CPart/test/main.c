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
    for (int i = 0; i < 100; ) {
        if (!q_is_full(queue)) {
            // co_yield();
            do_produce(queue);
            i += 1;
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
    struct co *thd3 = co_start("consumer-1", consumer, queue);
    struct co *thd4 = co_start("consumer-2", consumer, queue);

    co_wait(thd1);
    co_wait(thd2);

    g_running = 0;

    co_wait(thd3);
    co_wait(thd4);

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
int main() {
    setbuf(stdout, NULL);

    printf("Test #1. Expect: (X|Y){0, 1, 2, ..., 199}\n");
    test_1();

    printf("\n\nTest #2. Expect: (libco-){200, 201, 202, ..., 399}\n");
    test_2();


    test_3();
    printf("\n\n");

    return 0;
}