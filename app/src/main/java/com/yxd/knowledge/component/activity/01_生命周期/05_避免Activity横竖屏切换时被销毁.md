#### 问：如何避免Activity横竖屏切换时Activity被销毁？

<details>
<summary>查看答案</summary>
<pre>
可以通过在AndroidManifest文件的Activity中指定如下属性：

``` xml
android:configChanges = "orientation| screenSize"
```

此时横竖屏切换时会回调如下方法：
``` java
@Override
public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
}
```
</pre>
</details>