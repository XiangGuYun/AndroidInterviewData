# LinkedBlockingQueue

链表阻塞队列

1. 链表容量默认不限制(Integer.MAX_VALUE)。
    > 链表不像数组那样需要预先申请一块连续内存，所以限制容量没啥意义。
2. 新元素插入在队尾。
    > 为了符合先进先出的特性。
3. 至少会保留一个为null的head节点。
4. 有两把锁，一把用于入队，一把用于出队。
    > 入入互斥，出入不互斥。
    > 
    > 在多线程频繁执行出队入队的场景下效率较高。
    > 
    > >🤔 为什么线程池用的最多的是LinkedBlockingQueue？
    > 
    > >答：LinkedBlockingQueue允许两个线程同时执行任务的出队和入队，特别适用于执行大量短周期任务的场景下。 
5. 使用AtomicInterger类型的变量表示元素个数，保证线程操作安全。

## remove和take的区别
remove：删除队列中的任意元素。

take：让队列中的第一个元素出队。

🤔：为什么remove()方法同时需要两把锁?


