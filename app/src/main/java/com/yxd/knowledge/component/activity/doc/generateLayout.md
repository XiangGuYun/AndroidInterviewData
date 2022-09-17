# generateLayout

这个方法的作用就是，通过我们设置的style或者requestWindowFuture等来选出一个系统自带的布局文件，默认的是R.layout.screen_simple，

选出布局文件后，通过调用mDecor.onResourcesLoaded(mLayoutInflater, layoutResource);

方法inflate出来后，add到DecorView上

``` java
protected ViewGroup generateLayout(DecorView decor) {
    
    // ...
    
    // Inflate the window decor.

    int layoutResource;
    int features = getLocalFeatures();
    if ((features & ((1 << FEATURE_LEFT_ICON) | (1 << FEATURE_RIGHT_ICON))) != 0) {
        if (mIsFloating) {
            TypedValue res = new TypedValue();
            getContext().getTheme().resolveAttribute(
                    R.attr.dialogTitleIconsDecorLayout, res, true);
            layoutResource = res.resourceId;
        } else {
            layoutResource = R.layout.screen_title_icons;
        }
        removeFeature(FEATURE_ACTION_BAR);
    } else if ((features & ((1 << FEATURE_PROGRESS) | (1 << FEATURE_INDETERMINATE_PROGRESS))) != 0
            && (features & (1 << FEATURE_ACTION_BAR)) == 0) {
        layoutResource = R.layout.screen_progress;
    } else if ((features & (1 << FEATURE_CUSTOM_TITLE)) != 0) {
        if (mIsFloating) {
            TypedValue res = new TypedValue();
            getContext().getTheme().resolveAttribute(
                    R.attr.dialogCustomTitleDecorLayout, res, true);
            layoutResource = res.resourceId;
        } else {
            layoutResource = R.layout.screen_custom_title;
        }
        removeFeature(FEATURE_ACTION_BAR);
    } else if ((features & (1 << FEATURE_NO_TITLE)) == 0) {
        if (mIsFloating) {
            TypedValue res = new TypedValue();
            getContext().getTheme().resolveAttribute(
                    R.attr.dialogTitleDecorLayout, res, true);
            layoutResource = res.resourceId;
        } else if ((features & (1 << FEATURE_ACTION_BAR)) != 0) {
            layoutResource = a.getResourceId(
                    R.styleable.Window_windowActionBarFullscreenDecorLayout,
                    R.layout.screen_action_bar);
        } else {
            layoutResource = R.layout.screen_title;
        }
    } else if ((features & (1 << FEATURE_ACTION_MODE_OVERLAY)) != 0) {
        layoutResource = R.layout.screen_simple_overlay_action_mode;
    } else {
        layoutResource = R.layout.screen_simple;
    }

    mDecor.startChanging();
    mDecor.onResourcesLoaded(mLayoutInflater, layoutResource);
    
    // 初始化contentParent
    ViewGroup contentParent = (ViewGroup)findViewById(ID_ANDROID_CONTENT);
   
    // ...

    if (getContainer() == null) {
        mDecor.setWindowBackground(mBackgroundDrawable);

        final Drawable frame;
        if (mFrameResource != 0) {
            frame = getContext().getDrawable(mFrameResource);
        } else {
            frame = null;
        }
        mDecor.setWindowFrame(frame);

        mDecor.setElevation(mElevation);
        mDecor.setClipToOutline(mClipToOutline);

        if (mTitle != null) {
            setTitle(mTitle);
        }

        if (mTitleColor == 0) {
            mTitleColor = mTextColor;
        }
        setTitleColor(mTitleColor);
    }

    mDecor.finishChanging();
    // 返回
    return contentParent;
}
```

#### screen_simple.xml
``` java
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:fitsSystemWindows="true"
    android:orientation="vertical">
    <ViewStub android:id="@+id/action_mode_bar_stub"
              android:inflatedId="@+id/action_mode_bar"
              android:layout="@layout/action_mode_bar"
              android:layout_width="match_parent"
              android:layout_height="wrap_content"
              android:theme="?attr/actionBarTheme" />
    <FrameLayout
         android:id="@android:id/content"
         android:layout_width="match_parent"
         android:layout_height="match_parent"
         android:foregroundInsidePadding="false"
         android:foregroundGravity="fill_horizontal|top"
         android:foreground="?android:attr/windowContentOverlay" />
</LinearLayout>
```