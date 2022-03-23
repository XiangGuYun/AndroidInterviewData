### OkHttpClient.newCall
```kotlin
  /** 准备在将来某个时间点执行的[请求]。 */
  override fun newCall(request: Request): Call = 
      RealCall(this, request, forWebSocket = false)
```

### 什么是RealCall
>  OkHttp应用和网络层之间的桥梁。该类公开高级应用层原语:连接、请求、响应和流。 这个类支持异步取消。这是为了使爆炸半径尽可能小。如果一个HTTP/2流是活动的，取消将取消该流，但不会取消其他共享其连接的流。但如果TLS握手仍在进行中，取消可能会中断整个连接。
> 

### ReadCall.enqueue
```kotlin
override fun enqueue(responseCallback: Callback) {
    check(executed.compareAndSet(false, true)) { "Already Executed" }

    callStart()
    client.dispatcher.enqueue(AsyncCall(responseCallback))
  }
```

### Dispatcher.enqueue
```kotlin
internal fun enqueue(call: AsyncCall) {
    synchronized(this) {
      readyAsyncCalls.add(call)

      // Mutate the AsyncCall so that it shares the AtomicInteger of an existing running call to
      // the same host.
      if (!call.call.forWebSocket) {
        val existingCall = findExistingCallWithHost(call.host)
        if (existingCall != null) call.reuseCallsPerHostFrom(existingCall)
      }
    }
    promoteAndExecute()
  }
```