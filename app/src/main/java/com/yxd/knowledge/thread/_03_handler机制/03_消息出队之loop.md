# loop消息出队解析

### Looper.java

``` java
public static void loop() {
    final Looper me = myLooper();
    
    // ...

    for (;;) {
        // 循环取消息
        if (!loopOnce(me, ident, thresholdOverride)) {
            return;
        }
    }
}
```

``` java
private static boolean loopOnce(final Looper me,
        final long ident, final int thresholdOverride) {
    
    // 获取下一个消息
    Message msg = me.mQueue.next(); // might block
    
    // ...
    
    try {
        // 分发消息
        // 这里的target就是Handler
        msg.target.dispatchMessage(msg);   
    }
    
    // ...

    return true;
}
```

### Handler.java

``` java
public void dispatchMessage(@NonNull Message msg) {
    if (msg.callback != null) {
        handleCallback(msg);
    } else {
        if (mCallback != null) {
            if (mCallback.handleMessage(msg)) {
                return;
            }
        }
        // 处理消息
        handleMessage(msg);
    }
}
```