#ifndef MYQUEUE_H
#define MYQUEUE_H

#define NULL 0

typedef struct
{
  char *buffer;
	volatile int head;
	volatile int tail;
	int itemSize;
	int length;
}tQueue;

typedef tQueue* xQueue;

xQueue creatQueue(long length ,long itemSize);

char enqueue_t(xQueue qu,void *item);

char dequeue_t(xQueue qu, void *item);

void deleteQueue(xQueue *qu);

long queueLength(xQueue qu);

#endif

