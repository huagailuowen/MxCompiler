#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <string.h>
#include "../src/co.h"

#define QUEUE_SIZE 10

// 队列中的元素
typedef struct Item {
    char *data;
} Item;

// 队列实现
typedef struct Queue {
    Item* items[QUEUE_SIZE];
    int front;
    int rear;
    int size;
} Queue;

// 创建新队列
Queue* q_new();

// 释放队列
void q_free(Queue* queue);

// 检查队列是否为空
int q_is_empty(Queue* queue);

// 检查队列是否已满
int q_is_full(Queue* queue);

// 将元素加入队列
void q_push(Queue* queue, Item* item);

// 从队列中取出元素
Item* q_pop(Queue* queue);

Queue* q_new() {
    Queue* queue = (Queue*)malloc(sizeof(Queue));
    if (!queue) {
        fprintf(stderr, "Failed to allocate memory for queue\n");
        return NULL;
    }
    
    queue->front = 0;
    queue->rear = -1;
    queue->size = 0;
    
    return queue;
}

void q_free(Queue* queue) {
    if (queue) {
        // 确保队列中所有元素都已被释放
        while (!q_is_empty(queue)) {
            Item* item = q_pop(queue);
            if (item) {
                if (item->data) {
                    free(item->data);
                }
                free(item);
            }
        }
        free(queue);
    }
}

int q_is_empty(Queue* queue) {
    return queue->size == 0;
}

int q_is_full(Queue* queue) {
    return queue->size == QUEUE_SIZE;
}

void q_push(Queue* queue, Item* item) {
    if (q_is_full(queue)) {
        fprintf(stderr, "Queue is full\n");
        return;
    }
    
    queue->rear = (queue->rear + 1) % QUEUE_SIZE;
    queue->items[queue->rear] = item;
    queue->size++;
}

Item* q_pop(Queue* queue) {
    if (q_is_empty(queue)) {
        return NULL;
    }
    
    Item* item = queue->items[queue->front];
    queue->front = (queue->front + 1) % QUEUE_SIZE;
    queue->size--;
    
    return item;
}
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

int main() {
    setbuf(stdout, NULL);

    printf("Test #1. Expect: (X|Y){0, 1, 2, ..., 199}\n");
    test_1();

    printf("\n\nTest #2. Expect: (libco-){200, 201, 202, ..., 399}\n");
    test_2();

    printf("\n\n");

    return 0;
}