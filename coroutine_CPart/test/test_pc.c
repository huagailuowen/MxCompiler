#include <stdio.h>
#include <stdint.h>
#include "co.h"

#define BUF_SIZE 10
#define N_PRODUCE 100
#define N_PRODUCER 400
#define N_CONSUMER 400


int buffer[BUF_SIZE];
int head = 0, tail = 0;
int count = 0;

struct co_signal signal_empty;
struct co_signal signal_full;
struct co_signal signal_mutex;

void put(int val) {
    buffer[tail] = val;
    tail = (tail + 1) % BUF_SIZE;
    count++;
}

int get() {
    int val = buffer[head];
    head = (head + 1) % BUF_SIZE;
    count--;
    return val;
}

void producer(void *arg) {
    int id = (int)(intptr_t)arg;
    for (int i = 0; i < N_PRODUCE; ++i) {
        co_signal_wait(&signal_empty);
        co_signal_wait(&signal_mutex);

        int val = id * 1000 + i;
        put(val);
        #ifdef PRINT
        printf("[P%d] produce %d\n", id, val);
        #endif
        co_signal_notify(&signal_mutex);
        co_signal_notify(&signal_full);
        co_yield();
    }
    // co_exit();
}

void consumer(void *arg) {
    int id = (int)(intptr_t)arg;
    for (int i = 0; i < N_PRODUCE * N_PRODUCER / N_CONSUMER; ++i) {
        co_signal_wait(&signal_full);
        co_signal_wait(&signal_mutex);

        int val = get();
        #ifdef PRINT
        printf("        [C%d] consume %d\n", id, val);
        #endif
        co_signal_notify(&signal_mutex);
        co_signal_notify(&signal_empty);
        co_yield();
    }
    // co_exit();
}

int main() {
    struct timespec start, end;
    clock_gettime(CLOCK_MONOTONIC, &start);
    co_signal_init(&signal_empty, BUF_SIZE);
    co_signal_init(&signal_full, 0);
    co_signal_init(&signal_mutex, 1);

    struct co *producers[N_PRODUCER];
    struct co *consumers[N_CONSUMER];

    for (int i = 0; i < N_PRODUCER; ++i) {
        producers[i] = co_start("producer", producer, (void*)(intptr_t)i);
    }

    for (int i = 0; i < N_CONSUMER; ++i) {
        consumers[i] = co_start("consumer", consumer, (void*)(intptr_t)i);
    }

    for (int i = 0; i < N_PRODUCER; ++i) {
        co_wait(producers[i]);
    }

    for (int i = 0; i < N_CONSUMER; ++i) {
        co_wait(consumers[i]);
    }

    printf("Finished. Final buffer count = %d\n", count);
    // time cost
    clock_gettime(CLOCK_MONOTONIC, &end);
    double sec = (end.tv_sec - start.tv_sec) + (end.tv_nsec - start.tv_nsec) / 1e9;
    printf("Total time: %.3f s\n", sec);
    return 0;
}
