# 分析generateDecor

``` java
protected DecorView generateDecor(int featureId) {
    Context context;
    
    // ... 初始化context
    
    // 返回DecorView，是一个FrameLayout
    return new DecorView(context, featureId, this, getAttributes());
}
```