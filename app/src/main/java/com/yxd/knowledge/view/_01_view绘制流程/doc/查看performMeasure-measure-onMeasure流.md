### 查看performMeasure-measure-onMeasure流

ViewRootImpl.java

``` java
private void performMeasure(int childWidthMeasureSpec, int childHeightMeasureSpec) {
    if (mView == null) {
        return;
    }
    Trace.traceBegin(Trace.TRACE_TAG_VIEW, "measure");
    try {
        mView.measure(childWidthMeasureSpec, childHeightMeasureSpec);
    } finally {
        Trace.traceEnd(Trace.TRACE_TAG_VIEW);
    }
}
```

View.java

``` java
public final void measure(int widthMeasureSpec, int heightMeasureSpec) {
    // ...

    if (forceLayout || needsLayout) {
        // ...
        
        if (cacheIndex < 0 || sIgnoreMeasureCache) {
            // 关键
            onMeasure(widthMeasureSpec, heightMeasureSpec);
            mPrivateFlags3 &= ~PFLAG3_MEASURE_NEEDED_BEFORE_LAYOUT;
        }
        // ...
    }

    // ...
}
```

假设当前的父容器的LinearLayout

``` java
@Override
protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    if (mOrientation == VERTICAL) {
        measureVertical(widthMeasureSpec, heightMeasureSpec);
    } else {
        measureHorizontal(widthMeasureSpec, heightMeasureSpec);
    }
}
```

``` java
void measureVertical(int widthMeasureSpec, int heightMeasureSpec) {
    // ...
    
    for (int i = 0; i < count; ++i) {
        final View child = getVirtualChildAt(i);
        
        if (child == null || child.getVisibility() == View.GONE) {
            continue;
        }

        final LayoutParams lp = (LayoutParams) child.getLayoutParams();
        final float childWeight = lp.weight;
        if (childWeight > 0) {
            // ...
            
            // 对每个子View都测量一遍
            child.measure(childWidthMeasureSpec, childHeightMeasureSpec);

            // ...
        }

        // ...
    }
    
    // ...
}
```


