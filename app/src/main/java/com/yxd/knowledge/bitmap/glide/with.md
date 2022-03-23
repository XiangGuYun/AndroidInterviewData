# with
### 监听Activity/Fragment生命周期
当with的Fragment或Activity不可见的时候暂停请求，当with的Fragment或Activity可见的时候恢复请求。
``` java
public RequestManager get(@NonNull Context context)

public RequestManager get(@NonNull FragmentActivity activity)

public RequestManager get(@NonNull Fragment fragment)

public RequestManager get(@NonNull Activity activity)

public RequestManager get(@NonNull View view)
```

|with 参数	|作用域	|代码中线程|
|----|----|----|
|-|Application|	子线程使用with|
|View|	Fragment/Activity|	主线程|
Fragment|	Fragment|	主线程
Activity|	Activity|	主线程
FragmentActivity|	Activity|	主线程
ServiceContext/ApplicationContext|	Application|	主线程
