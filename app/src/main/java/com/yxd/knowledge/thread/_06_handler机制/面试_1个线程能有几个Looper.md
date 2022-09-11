# 1个线程能有几个Looper

一个线程只能有一个Looper。

## 如何保证只有一个Looper

1. Looper的构造方法是私有的，只能通过prepare来创建。
2. 调用prepare后，会将Looper的ThreadLocal和创建的Looper并放入当前线程的ThreadLocalMap中。
3. 如果再次调用prepare，会检查出当前线程已经存在Looper，并抛出异常。

### 理解主线程和Looper的关系

#### ActivityThread.java
``` java
// Android的main方法在ActivityThread类中。
public static void main(Stringp[] args){
    // ...
    
    // 初始化主线程的Looper
    Looper.prepareMainLooper();
    
    // ...
    
    // 开始执行
    Looper.loop();

}
```

#### Looper.java
``` java
public static void prepareMainLooper() {
    prepare(false);
    synchronized (Looper.class) {
        sMainLooper = myLooper();
    }
}
```

``` java
// static final保证了sThreadLocal的全局唯一性
// 同时也代表了key的唯一性
static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<Looper>();

private static void prepare(boolean quitAllowed) {
    if (sThreadLocal.get() != null) {
        // 如果当前线程已经有Looper了，则抛出异常
        throw new RuntimeException("Only one Looper may be created per thread");
    }
    // 创建Looper，存储在当前线程的ThreadLocalMap中
    // 注意Looper的构造方法是私有的
    sThreadLocal.set(new Looper(quitAllowed));
}
```

