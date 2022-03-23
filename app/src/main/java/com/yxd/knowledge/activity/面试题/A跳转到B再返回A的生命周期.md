#### 问：从Activity A跳转到Activity B，然后再点击返回键，A和B的生命周期调用流程是什么？
<details>
<summary>查看答案</summary>
<pre>
从Activity A跳转到Activity B
Activity A --> onPause()
Activity B --> onCreate()
Activity B --> onStart()
Activity B --> onResume()
Activity A --> onStop()

然后在Activity B点击back键
Activity B --> onPause()
// 注意在onStop和onStart之间有个onRestart
<b>Activity A --> onRestart()</b>
Activity A --> onStart()
Activity A --> onResume()
Activity B --> onStop()
Activity B --> onDestroy()
</pre>
</details>

***