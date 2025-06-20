#include <co.h>
#include <stdlib.h>
#include <stdio.h>
#include <pthread.h>

//===============================================================
// COPY FROM: https://isis.poly.edu/kulesh/stuff/src/klist/list.h
//===============================================================

/* This file is from Linux Kernel (include/linux/list.h)
 * and modified by simply removing hardware prefetching of list items.
 * Here by copyright, credits attributed to wherever they belong.
 * Kulesh Shanmugasundaram (kulesh [squiggly] isis.poly.edu)
 */

/*
 * Simple doubly linked list implementation.
 *
 * Some of the internal func_ts ("__xxx") are useful when
 * manipulating whole lists rather than single entries, as
 * sometimes we already know the next/prev entries and we can
 * generate better code by using them directly rather than
 * using the generic single-entry routines.
 */

struct list_head {
	struct list_head *next, *prev;
};

#define LIST_HEAD_INIT(name) { &(name), &(name) }

#define LIST_HEAD(name) \
	struct list_head name = LIST_HEAD_INIT(name)

#define INIT_LIST_HEAD(ptr) do { \
	(ptr)->next = (ptr); (ptr)->prev = (ptr); \
} while (0)

/*
 * Insert a new entry between two known consecutive entries.
 *
 * This is only for internal list manipulation where we know
 * the prev/next entries already!
 */
static inline void __list_add(struct list_head *new,
			      struct list_head *prev,
			      struct list_head *next)
{
	next->prev = new;
	new->next = next;
	new->prev = prev;
	prev->next = new;
}

/**
 * list_add - add a new entry
 * @new: new entry to be added
 * @head: list head to add it after
 *
 * Insert a new entry after the specified head.
 * This is good for implementing stacks.
 */
static inline void list_add(struct list_head *new, struct list_head *head)
{
	__list_add(new, head, head->next);
}

/**
 * list_add_tail - add a new entry
 * @new: new entry to be added
 * @head: list head to add it before
 *
 * Insert a new entry before the specified head.
 * This is useful for implementing queues.
 */
static inline void list_add_tail(struct list_head *new, struct list_head *head)
{
	__list_add(new, head->prev, head);
}

/*
 * Delete a list entry by making the prev/next entries
 * point to each other.
 *
 * This is only for internal list manipulation where we know
 * the prev/next entries already!
 */
static inline void __list_del(struct list_head *prev, struct list_head *next)
{
	next->prev = prev;
	prev->next = next;
}

/**
 * list_del - deletes entry from list.
 * @entry: the element to delete from the list.
 * Note: list_empty on entry does not return true after this, the entry is in an undefined state.
 */
static inline void list_del(struct list_head *entry)
{
	__list_del(entry->prev, entry->next);
	entry->next = (void *) 0;
	entry->prev = (void *) 0;
}

/**
 * list_del_init - deletes entry from list and reinitialize it.
 * @entry: the element to delete from the list.
 */
static inline void list_del_init(struct list_head *entry)
{
	__list_del(entry->prev, entry->next);
	INIT_LIST_HEAD(entry);
}

/**
 * list_move - delete from one list and add as another's head
 * @list: the entry to move
 * @head: the head that will precede our entry
 */
static inline void list_move(struct list_head *list, struct list_head *head)
{
        __list_del(list->prev, list->next);
        list_add(list, head);
}

/**
 * list_move_tail - delete from one list and add as another's tail
 * @list: the entry to move
 * @head: the head that will follow our entry
 */
static inline void list_move_tail(struct list_head *list,
				  struct list_head *head)
{
        __list_del(list->prev, list->next);
        list_add_tail(list, head);
}

/**
 * list_empty - tests whether a list is empty
 * @head: the list to test.
 */
static inline int list_empty(struct list_head *head)
{
	return head->next == head;
}

static inline void __list_splice(struct list_head *list,
				 struct list_head *head)
{
	struct list_head *first = list->next;
	struct list_head *last = list->prev;
	struct list_head *at = head->next;

	first->prev = head;
	head->next = first;

	last->next = at;
	at->prev = last;
}

/**
 * list_splice - join two lists
 * @list: the new list to add.
 * @head: the place to add it in the first list.
 */
static inline void list_splice(struct list_head *list, struct list_head *head)
{
	if (!list_empty(list))
		__list_splice(list, head);
}

/**
 * list_splice_init - join two lists and reinitialise the emptied list.
 * @list: the new list to add.
 * @head: the place to add it in the first list.
 *
 * The list at @list is reinitialised
 */
static inline void list_splice_init(struct list_head *list,
				    struct list_head *head)
{
	if (!list_empty(list)) {
		__list_splice(list, head);
		INIT_LIST_HEAD(list);
	}
}

/**
 * list_entry - get the struct for this entry
 * @ptr:	the &struct list_head pointer.
 * @type:	the type of the struct this is embedded in.
 * @member:	the name of the list_struct within the struct.
 */
#define list_entry(ptr, type, member) \
	((type *)((char *)(ptr)-(unsigned long)(&((type *)0)->member)))

/**
 * list_for_each	-	iterate over a list
 * @pos:	the &struct list_head to use as a loop counter.
 * @head:	the head for your list.
 */
#define list_for_each(pos, head) \
	for (pos = (head)->next; pos != (head); \
        	pos = pos->next)
/**
 * list_for_each_prev	-	iterate over a list backwards
 * @pos:	the &struct list_head to use as a loop counter.
 * @head:	the head for your list.
 */
#define list_for_each_prev(pos, head) \
	for (pos = (head)->prev; pos != (head); \
        	pos = pos->prev)

/**
 * list_for_each_safe	-	iterate over a list safe against removal of list entry
 * @pos:	the &struct list_head to use as a loop counter.
 * @n:		another &struct list_head to use as temporary storage
 * @head:	the head for your list.
 */
#define list_for_each_safe(pos, n, head) \
	for (pos = (head)->next, n = pos->next; pos != (head); \
		pos = n, n = pos->next)

/**
 * list_for_each_entry	-	iterate over list of given type
 * @pos:	the type * to use as a loop counter.
 * @head:	the head for your list.
 * @member:	the name of the list_struct within the struct.
 */
#define list_for_each_entry(pos, head, member)				\
	for (pos = list_entry((head)->next, typeof(*pos), member);	\
	     &pos->member != (head); 					\
	     pos = list_entry(pos->member.next, typeof(*pos), member))

/**
 * list_for_each_entry_safe - iterate over list of given type safe against removal of list entry
 * @pos:	the type * to use as a loop counter.
 * @n:		another type * to use as temporary storage
 * @head:	the head for your list.
 * @member:	the name of the list_struct within the struct.
 */
#define list_for_each_entry_safe(pos, n, head, member)			\
	for (pos = list_entry((head)->next, typeof(*pos), member),	\
		n = list_entry(pos->member.next, typeof(*pos), member);	\
	     &pos->member != (head); 					\
	     pos = n, n = list_entry(n->member.next, typeof(*n), member))


typedef struct Queue_t {
    struct list_head list;
    int sz;
    int cap;
    pthread_mutex_t mutex;  // Add mutex for thread safety
} Queue;

typedef struct Item_t {
    char *data;
    struct list_head link;
} Item;

static inline Queue* q_new() {
    Queue* queue = (Queue*)malloc(sizeof(Queue));
    if (!queue) {
        fprintf(stderr, "New queue failure\n");
        exit(1);
    }
    queue->cap = 2000;
    queue->sz = 0;
    INIT_LIST_HEAD(&queue->list);
    
    // Initialize mutex
    if (pthread_mutex_init(&queue->mutex, NULL) != 0) {
        fprintf(stderr, "Mutex initialization failed\n");
        free(queue);
        exit(1);
    }
    
    return queue;
}

static inline void q_free(Queue *queue) {
    if (!queue) return;
    
    // Lock before cleanup
    pthread_mutex_lock(&queue->mutex);
    
    // Manually iterate through the list to free items
    struct list_head *pos = queue->list.next;
    while (pos != &queue->list) {
        struct list_head *next = pos->next;
        Item *item = list_entry(pos, Item, link);
        list_del(pos);
        free(item);
        pos = next;
    }
    
    // Unlock before destroying mutex
    pthread_mutex_unlock(&queue->mutex);
    
    // Destroy mutex
    pthread_mutex_destroy(&queue->mutex);
    
    free(queue);
}

static inline int q_is_full(Queue *queue) {
    if (!queue) return 1;
    
    pthread_mutex_lock(&queue->mutex);
    int result = (queue->sz == queue->cap);
    pthread_mutex_unlock(&queue->mutex);
    
    return result;
}

static inline int q_is_empty(Queue *queue) {
    if (!queue) return 1;
    
    pthread_mutex_lock(&queue->mutex);
    int result = list_empty(&queue->list);
    pthread_mutex_unlock(&queue->mutex);
    
    return result;
}

static inline void q_push(Queue *queue, Item *item) {
    if (!queue || !item) return;
    
    pthread_mutex_lock(&queue->mutex);
    
    if (queue->sz >= queue->cap) {
        pthread_mutex_unlock(&queue->mutex);
        fprintf(stderr, "Push queue failure\n");
        return;
    }
    
    list_add_tail(&item->link, &queue->list);
    queue->sz += 1;
    
    pthread_mutex_unlock(&queue->mutex);
}

static inline Item* q_pop(Queue *queue) {
    if (!queue) return NULL;
    
    pthread_mutex_lock(&queue->mutex);
    
    if (list_empty(&queue->list)) {
        pthread_mutex_unlock(&queue->mutex);
        return NULL;
    }

    Item *item = list_entry(queue->list.next, Item, link);
    list_del(&item->link);
    queue->sz -= 1;
    
    pthread_mutex_unlock(&queue->mutex);
    
    return item;
}

// Additional thread-safe helper functions for queue operations
static inline int q_size(Queue *queue) {
    if (!queue) return 0;
    
    pthread_mutex_lock(&queue->mutex);
    int size = queue->sz;
    pthread_mutex_unlock(&queue->mutex);
    
    return size;
}

static inline int q_capacity(Queue *queue) {
    if (!queue) return 0;
    
    pthread_mutex_lock(&queue->mutex);
    int capacity = queue->cap;
    pthread_mutex_unlock(&queue->mutex);
    
    return capacity;
}

// Thread-safe queue clear function
static inline void q_clear(Queue *queue) {
    if (!queue) return;
    
    pthread_mutex_lock(&queue->mutex);
    
    // Manually iterate through the list to free items
    struct list_head *pos = queue->list.next;
    while (pos != &queue->list) {
        struct list_head *next = pos->next;
        Item *item = list_entry(pos, Item, link);
        list_del(pos);
        free(item);
        pos = next;
    }
    
    queue->sz = 0;
    INIT_LIST_HEAD(&queue->list);
    
    pthread_mutex_unlock(&queue->mutex);
}

// Thread-safe function to create a new item
static inline Item* q_item_new(void *data) {
    Item *item = (Item*)malloc(sizeof(Item));
    if (!item) {
        fprintf(stderr, "Failed to allocate memory for queue item\n");
        return NULL;
    }
    item->data = data;
    INIT_LIST_HEAD(&item->link);
    return item;
}

// Thread-safe function to free an item
static inline void q_item_free(Item *item) {
    if (item) {
        free(item);
    }
}