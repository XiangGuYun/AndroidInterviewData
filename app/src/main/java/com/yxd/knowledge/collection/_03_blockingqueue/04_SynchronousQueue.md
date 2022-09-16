# SynchronousQueue

同步队列

## 构造方法
``` java
// fair：是否采用公平模式
public SynchronousQueue(boolean fair) {
    // 如果是，则返回一个队列，否则返回栈
    transferer = fair ? new TransferQueue<E>() : new TransferStack<E>();
}
```

> ###队列和栈的公平性
> 1. 队列具备先进先出(FIFO)的特性，因此是公平的。
> > 包子店前排满了长队，先排队的人先买到包子，然后出队。
> 2. 栈具备后进先出(LIFO)的特性，因此是不公平的。
> > 包子店前排满了长队，排在最后的人最先买到了包子，然后离开(出栈)。


## 不存储元素的队列
没有enqueue和dequeue方法。
``` java
// 请求入队
public void put(E e) throws InterruptedException {
    if (e == null) throw new NullPointerException();
    if (transferer.transfer(e, false, 0) == null) {
        Thread.interrupted();
        throw new InterruptedException();
    }
}

// 请求出队
public E take() throws InterruptedException {
    E e = transferer.transfer(null, false, 0);
    if (e != null)
        return e;
    Thread.interrupted();
    throw new InterruptedException();
}
```

🤔：一个不能存储元素的队列，它存在的意义是什么？

答：当核心线程池已满，又有任务进来时，可以立即创建子线程来执行任务，不用等待。

