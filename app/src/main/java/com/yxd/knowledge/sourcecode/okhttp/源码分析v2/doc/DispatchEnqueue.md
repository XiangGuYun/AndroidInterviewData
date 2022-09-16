# Dispatcher的enqueue流程

1. 预备知识点
2. 流程分析
3. 等待队列中的任务何时尝试再次提交到执行队列中
4. 分发器的线程池

## 预备知识点


[点击查看](doc/Dispatcher预备知识点.md)



## 流程分析

> #### 角色
> 1. executorService - ES公司
> 2. readyAsyncCalls - 储备员工队列
> 3. runningAsyncCalls - 正式员工队列
> 4. AsyncCall - 员工
> 
> #### ES公司规章
> 
> 1. 员工进公司，默认都先进入储备队列中。 
> 2. 当正式员工数量已经超出64个时，不再晋升任何储备员工。
> 3. 当同个地域(域名)的正式员工数量超出5个时，不再晋升该地域的储备员工。
> 4. 当某个正式员工离职(run()运行结束)后，会再执行一次储备员工晋升。
> 

``` java
// AsyncCall本质是一个Runnable，用于提交给线程池处理
void enqueue(AsyncCall call) {
    synchronized (this) {
      // 首先将Call放入等待队列中
      readyAsyncCalls.add(call);
    
      // ...
    }
    // promote:晋升，将Call从等待队列晋升到执行队列中
    // Execute：由线程池执行执行队列中的Call
    promoteAndExecute();
}
```

``` java
/**
 * 将符合条件的Call从readyAsyncCalls晋升到runningAsyncCalls中，
 * 并在线程池中运行它们。
 */
private boolean promoteAndExecute() {
    assert (!Thread.holdsLock(this));
    
    List<AsyncCall> executableCalls = new ArrayList<>();
    boolean isRunning;
    synchronized (this) {
      // 遍历等待队列
      for (Iterator<AsyncCall> i = readyAsyncCalls.iterator(); i.hasNext(); ) {
        AsyncCall asyncCall = i.next();
        
        // 当执行队列的容量已满（64）时，退出循环
        if (runningAsyncCalls.size() >= maxRequests) break; // Max capacity.
        // 同域名请求数超出5个，则取消本次循环
        if (asyncCall.callsPerHost().get() >= maxRequestsPerHost) continue; // Host max capacity.
    
        i.remove();
        asyncCall.callsPerHost().incrementAndGet();
        // 添加
        executableCalls.add(asyncCall);
        runningAsyncCalls.add(asyncCall);
      }
      isRunning = runningCallsCount() > 0;
    }
    
    for (int i = 0, size = executableCalls.size(); i < size; i++) {
      AsyncCall asyncCall = executableCalls.get(i);
      // 执行请求
      asyncCall.executeOn(executorService());
    }
    
    return isRunning;
}
```
***
## 等待队列中的任务何时尝试再次提交到执行队列中

将AsyncCall提交给线程池后，它的execute方法会被执行。
#### AsyncCall.java
``` java
@Override 
protected void execute() {
    // ...
    try {
        // 通过拦截器完成请求，获得Response
        Response response = getResponseWithInterceptorChain();
        // 回调成功
        responseCallback.onResponse(RealCall.this, response);
    } catch (IOException e) {
        // ...
        // 回调失败
        responseCallback.onFailure(RealCall.this, e);
    } catch (Throwable t) {
        // ...
        // 回调失败
        responseCallback.onFailure(RealCall.this, canceledException);
        throw t;
    } finally {
        // 分发器的finished方法一定会被调用
        client.dispatcher().finished(this);
    }
}
```
#### Dispatcher.java
``` java
void finished(AsyncCall call) {
    finished(runningAsyncCalls, call);
}
```

``` java
private <T> void finished(Deque<T> calls, T call) {
    Runnable idleCallback;
    synchronized (this) {
      if (!calls.remove(call)) throw new AssertionError("Call wasn't in-flight!");
      idleCallback = this.idleCallback;
    }
    // 再次调用promoteAndExecute方法
    boolean isRunning = promoteAndExecute();
    
    if (!isRunning && idleCallback != null) {
      idleCallback.run();
    }
}
```

***

## 分发器的线程池
#### Dispatcher.java
``` java
public synchronized ExecutorService executorService() {
    if (executorService == null) {
      executorService = new ThreadPoolExecutor(
            0, 
            Integer.MAX_VALUE, 
            60, TimeUnit.SECONDS,
            new SynchronousQueue<>(), 
            Util.threadFactory("OkHttp Dispatcher", false));
    }
    return executorService;
}
```

🤔 为什么OKHTTP的线程池参数要这样设置？
1. 核心线程数：0，网络请求都是短周期任务，没必要使用核心线程。
    > 一个不用迭代维护的短期项目，用临时工来做肯定更为划算。
2. 非核心线程数：Integer.MAX_VALUE，支持高并发。
3. 非核心线程空闲存活时间：60秒对于网络请求已经够久了。
4. 阻塞队列：没有容量的队列，有新的请求进来立即创建子线程来执行。

