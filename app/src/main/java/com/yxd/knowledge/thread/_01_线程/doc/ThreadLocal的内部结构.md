# ThreadLocal的内部结构

1. 每个Thread内部有一个ThreadLocalMap的变量。

``` java
    ThreadLocal.ThreadLocalMap threadLocals = null;
```
2. ThreadLocalMap的key是ThreadLocal，value是变量副本。
   
``` java
static class ThreadLocalMap {
    static class Entry extends WeakReference<ThreadLocal<?>> {
        /** The value associated with this ThreadLocal. */
        Object value;

        Entry(ThreadLocal<?> k, Object v) {
            super(k);
            value = v;
        }
    }
}
```
3. ThreadLocalMap由ThreadLocal负责维护（设值取值）。
4. 同一时间只有一个线程能获取变量副本值。