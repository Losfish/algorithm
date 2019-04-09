#include "myqueue.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>

char isFullQueue(xQueue qu)
{
    if((qu->tail+1)%(qu->length+1)==qu->head)
    {
        return 1;
    }
		return 0;
}

char isEmptyQueue(xQueue qu)
{
    if(qu->head==qu->tail)
    {
        return 1;
    }
    return 0;
}

xQueue creatQueue(long length ,long itemSize)
{
    tQueue *newQueue=NULL;
    newQueue=(tQueue *)malloc(sizeof(tQueue));
    if(!newQueue)
    {
				printf("can't creat a tQueue\n");
        return NULL;
    }
    newQueue->buffer=NULL;
    newQueue->buffer=(char *)malloc(itemSize*(length+1));
    if(!newQueue->buffer)
    {
        free(newQueue);
				printf("can't creat a buffer\n");
        return NULL;
    }

    newQueue->head=0;
    newQueue->tail=0;
    newQueue->itemSize=itemSize;
    newQueue->length=length;

    return newQueue;

}

char enqueue_t(xQueue qu,void *item)
{
    if(qu&&isFullQueue(qu))
    {
        qu->head++;
        qu->head%=(qu->length+1);
		}
		memcpy(qu->buffer+(qu->tail*qu->itemSize),item,qu->itemSize);
		qu->tail++;
		qu->tail%=(qu->length+1);
		return 1;
}

char dequeue_t(xQueue qu, void *item)
{
    if(qu&&!isEmptyQueue(qu))
    {
        memcpy(item,qu->buffer+(qu->head*qu->itemSize),qu->itemSize);
        qu->head++;
        qu->head%=(qu->length+1);
        return 1;
    }
    return 0;
}

void deleteQueue(xQueue *qu)
{
    if(*qu!=NULL)
    {
        if((*qu)->buffer!=NULL)
        {
            free((*qu)->buffer);
        }
        free(*qu);
        *qu=NULL;
    }
}

long queueLength(tQueue *qu)
{
    if(qu->tail>=qu->head)
    {
        return qu->tail-qu->head;
    }
    else
    {
        return qu->length-(qu->head-qu->tail-1);
    }
}



