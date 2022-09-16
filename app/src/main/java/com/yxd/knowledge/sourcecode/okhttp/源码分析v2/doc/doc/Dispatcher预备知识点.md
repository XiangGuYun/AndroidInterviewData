# 分发器预备知识点

### 1. 分发器的线程池和双向队列

在分析流程之前，首先需要了解Dispatcher的线程池和队列

``` java
// 线程池
private @Nullable ExecutorService executorService;

// ArrayDeque 双端队列
// 如果作为栈使用，会比Stack更快；如果作为队列使用，会比Queue更快。
// 等待执行的异步请求队列
private final Deque<AsyncCall> readyAsyncCalls = new ArrayDeque<>();

// 正在执行的异步请求队列
private final Deque<AsyncCall> runningAsyncCalls = new ArrayDeque<>();

// 正在执行的同步请求队列
private final Deque<RealCall> runningSyncCalls = new ArrayDeque<>();
```

### 2. 分发器的异步工作流程图

![](../img/f35043d3.png)

### 3. 思考
1. 分发器是如何决定将请求放入哪个队列的？
2. 等待队列中的请求，如何移动到执行队列中？
3. 线程池是如何定义的？