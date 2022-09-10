# ThreadLocal原理

## ThreadLocal的set()方法

``` java
public void set(T value) {
    // 1、获取当前线程
    Thread t = Thread.currentThread();
    // 2、获取线程中的变量 threadLocalMap
    // 如果threadLocalMap 不为空，则直接更新要保存的变量值，
    // 否则创建threadLocalMap，并赋值
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        // 初始化thradLocalMap 并赋值
        createMap(t, value);
}
```

ThreadLocalMap 

``` java
/**
 * ThreadLocal的静态内部类
 */
static class ThreadLocalMap {
    /**
     * 使用Entry来保存数据
     */
    static class Entry extends WeakReference<ThreadLocal<?>> {
        /** The value associated with this ThreadLocal. */
        Object value;
        
        /**
         * k是threadLocal，v是设置进去的值
         */
        Entry(ThreadLocal<?> k, Object v) {
            super(k);
            value = v;
        }
    }  
}
```

##  ThreadLocal的get方法
``` java
public T get() {
    //1、获取当前线程
    Thread t = Thread.currentThread();
    //2、获取当前线程的ThreadLocalMap
    ThreadLocalMap map = getMap(t);
    //3、如果map数据不为空，
    if (map != null) {
        //3.1、获取threalLocalMap中存储的值
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    //如果是数据为null，则初始化，初始化的结果，TheralLocalMap中存放key值为threadLocal，值为null
    return setInitialValue();
}


private T setInitialValue() {
    T value = initialValue();
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
    return value;
}
```

## ThreadLocal的remove方法
``` java
public void remove() {
     ThreadLocalMap m = getMap(Thread.currentThread());
     if (m != null)
         m.remove(this);
 }
```

## 总结
1. ThreadLocal：类似于“取件码”和“存件码”的作用。
2. ThreadLocalMap：类似于每个Thread用来存放独有数据的“储物柜”，识别码是ThreadLocal，存储物是设置的值;
