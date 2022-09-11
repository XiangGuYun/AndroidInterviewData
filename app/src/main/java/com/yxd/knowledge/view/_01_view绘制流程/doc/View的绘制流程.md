# View的绘制流程

## View.java

``` java
public void invalidate(boolean invalidateCache) {
    invalidateInternal(0, 0, mRight - mLeft, mBottom - mTop, invalidateCache, true);
}
```

``` java
void invalidateInternal(int l, int t, int r, int b, boolean invalidateCache,
            boolean fullInvalidate) {
    
    // ...

    if (...) {
        // ...
        
        final AttachInfo ai = mAttachInfo;
        final ViewParent p = mParent;
        if (p != null && ai != null && l < r && t < b) {
            final Rect damage = ai.mTmpInvalRect;
            damage.set(l, t, r, b);
            p.invalidateChild(this, damage);
        }

        // ...
    }
}
```

## ViewGroup.java
``` java
@Override
public final void invalidateChild(View child, final Rect dirty) {
        // ...
        
        do {

            // ...
            
            // parent其实就是ViewRootImpl
            parent = parent.invalidateChildInParent(location, dirty);
            
            // ...
            
        } while (parent != null);
    }
}
```

## ViewRoomImpl.java
``` java
@Override
public ViewParent invalidateChildInParent(int[] location, Rect dirty) {
    
    if (dirty == null) {
        // 关键
        invalidate();
        return null;
    } else if (dirty.isEmpty() && !mIsAnimating) {
        return null;
    }

    // ...

    invalidateRectOnScreen(dirty);

    return null;
}
```

``` java
void invalidate() {
    mDirty.set(0, 0, mWidth, mHeight);
    if (!mWillDrawSoon) {
        // 调度遍历
        scheduleTraversals();
    }
}
```

``` java
void scheduleTraversals() {
    if (!mTraversalScheduled) {
        mTraversalScheduled = true;
        mTraversalBarrier = mHandler.getLooper().getQueue().postSyncBarrier();
        // mTraversalRunnable是关键
        mChoreographer.postCallback(
                Choreographer.CALLBACK_TRAVERSAL, mTraversalRunnable, null);
        notifyRendererOfFramePending();
        pokeDrawLockIfNeeded();
    }
}
```

``` java
final class TraversalRunnable implements Runnable {
    @Override
    public void run() {
        doTraversal();
    }
}
```

``` java
void doTraversal() {
if (mTraversalScheduled) {
    // ...

    performTraversals();

    // ...
}
}
```

``` java
/**
 * 完成View的测量、布局、绘制
 */
private void performTraversals() {
    // ...

    if (...) {
        // ...

        if (!mStopped || wasReportNextDraw) {
                // 测量
                performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
                
                // ...
                
                if (measureAgain) {   
                    // 测量            
                    performMeasure(childWidthMeasureSpec, childHeightMeasureSpec);
                }
            }
        }
    }

    // ...
    
    if (didLayout) {
        // 布局
        performLayout(lp, mWidth, mHeight);

        // ...
    }
    
    // ...

    if (!cancelDraw) {
        // ...
        
        // 绘制
        performDraw();
    } 

    // ...
}
```

[查看performMeasure-measure-onMeasure流](查看performMeasure-measure-onMeasure流.md)
