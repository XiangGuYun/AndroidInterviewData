# into
####RequestBuilder.java
``` java
  public ViewTarget<ImageView, TranscodeType> into(@NonNull ImageView view) {
      // ...
    
      return into(
          // ImageViewTarget
          glideContext.buildImageViewTarget(view, transcodeClass),
          null,// 目标监听器
          requestOptions, // 请求选项
          Executors.mainThreadExecutor() // 线程池
      );
  }
```

``` java
  private <Y extends Target<TranscodeType>> Y into(
      @NonNull Y target,
      @Nullable RequestListener<TranscodeType> targetListener,
      BaseRequestOptions<?> options,
      Executor callbackExecutor) {
      
    //...
    
    // request实际类型是SingleRequest，表示当前请求
    Request request = buildRequest(target, targetListener, options, callbackExecutor);
    
    // 上一个请求
    // 如果上一个请求未执行完，则先执行上一个请求
    Request previous = target.getRequest();
    if (request.isEquivalentTo(previous)
        && !isSkipMemoryCacheWithCompletePreviousRequest(options, previous)) {
      if (!Preconditions.checkNotNull(previous).isRunning()) {
        previous.begin();
      }
      return target;
    }

    requestManager.clear(target);
    target.setRequest(request);
    // 关键代码
    requestManager.track(target, request);

    return target;
  }
```
####RequestManager.java
``` java
  synchronized void track(@NonNull Target<?> target, @NonNull Request request) {
    targetTracker.track(target);
    requestTracker.runRequest(request);
  }
```
####RequestTracker.java
``` java
  public void runRequest(@NonNull Request request) {
    // 正在运行的队列
    // 类似okhhtp中的runningAsyncCalls
    requests.add(request);
    if (!isPaused) {
      // 执行请求
      request.begin();
    } else {   
      request.clear();
      if (Log.isLoggable(TAG, Log.VERBOSE)) {
        Log.v(TAG, "Paused, delaying request");
      }
      // 等待运行的队列
      // 类似okhhtp中的readyAsyncCalls
      pendingRequests.add(request);
    }
  }
```
接着，看看request.begin()是如何开启任务的
#### SingleRequest.java
``` java
  @Override
  public void begin() {
    synchronized (requestLock) {
      // ...

      cookie = GlideTrace.beginSectionAsync(TAG);
      status = Status.WAITING_FOR_SIZE;
      if (Util.isValidDimensions(overrideWidth, overrideHeight)) {
        // 关键代码
        onSizeReady(overrideWidth, overrideHeight);
      } else {
        target.getSize(this);
      }

      if ((status == Status.RUNNING || status == Status.WAITING_FOR_SIZE)
          && canNotifyStatusChanged()) {
        target.onLoadStarted(getPlaceholderDrawable());
      }
    
    }
  }
```

``` java
  @Override
  public void onSizeReady(int width, int height) {
    synchronized (requestLock) {
      // ...
      
      loadStatus = engine.load(...);

      // ...
    }
  }
```
####Engine.java
``` java
  public <R> LoadStatus load(...) {
    // key是图片的唯一标识   
    EngineKey key = keyFactory.buildKey(...);

    EngineResource<?> memoryResource;
    
    synchronized (this) {
      // 从内存缓存中去取资源
      memoryResource = loadFromMemory(key, isMemoryCacheable, startTime);

      if (memoryResource == null) {
        return waitForExistingOrStartNewJob(...);
      }
    }
    
    cb.onResourceReady(
        memoryResource, DataSource.MEMORY_CACHE, /* isLoadedFromAlternateCacheKey= */ false);

    return null;
  }
```
支线流程：[loadFromMemory](doc/loadFromMemory.md)