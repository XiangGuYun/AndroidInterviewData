# 空白Fragment的创建和绑定

#### RequestManagerRetriever.java
``` java
@NonNull
public RequestManager get(@NonNull FragmentActivity activity) {
    if (Util.isOnBackgroundThread()) {
      // 如果在子线程，则使用全局context，同时会将失去生命周期管理
      return get(activity.getApplicationContext());
    } else {
      // ...
      return supportFragmentGet(activity, fm, /*parentHint=*/ null, isActivityVisible(activity));
    }
}
```

``` java
  @NonNull
private RequestManager supportFragmentGet(
    @NonNull Context context,
    @NonNull FragmentManager fm,
    @Nullable Fragment parentHint,
    boolean isParentVisible) {
      
    // current是一个空白的fragment 
    SupportRequestManagerFragment current = getSupportRequestManagerFragment(fm, parentHint);
    RequestManager requestManager = current.getRequestManager();
    // requestManager不会重复创建
    if (requestManager == null) {
】      Glide glide = Glide.get(context);
      requestManager =
          factory.build(
              glide, current.getGlideLifecycle(), current.getRequestManagerTreeNode(), context);
      if (isParentVisible) {
        requestManager.onStart();
      }
      current.setRequestManager(requestManager);
    }
    return requestManager;
}
```

#### RequestManagerRetriever.java
``` java
@NonNull
private SupportRequestManagerFragment getSupportRequestManagerFragment(
  @NonNull final FragmentManager fm, @Nullable Fragment parentHint) {
// If we have a pending Fragment, we need to continue to use the pending Fragment. Otherwise
// there's a race where an old Fragment could be added and retrieved here before our logic to
// add our pending Fragment notices. That can then result in both the pending Fragmeng and the
// old Fragment having requests running for them, which is impossible to safely unwind.
SupportRequestManagerFragment current = pendingSupportRequestManagerFragments.get(fm);
if (current == null) {
  current = (SupportRequestManagerFragment) fm.findFragmentByTag(FRAGMENT_TAG);
  if (current == null) {
    // 使用构造器创建
    current = new SupportRequestManagerFragment();
    current.setParentFragmentHint(parentHint);
    pendingSupportRequestManagerFragments.put(fm, current);
    // 开启事务并绑定Activity
    fm.beginTransaction().add(current, FRAGMENT_TAG).commitAllowingStateLoss();
    handler.obtainMessage(ID_REMOVE_SUPPORT_FRAGMENT_MANAGER, fm).sendToTarget();
  }
}
return current;
}
```