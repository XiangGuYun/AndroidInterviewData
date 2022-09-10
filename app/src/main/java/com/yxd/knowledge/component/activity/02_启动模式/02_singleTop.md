# 栈顶复用模式 singleTop
如果需要新建的Activity位于任务栈栈顶，那么此Activity的实例就不会重建，而是复用栈顶的实例。并回调：
``` java
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
```