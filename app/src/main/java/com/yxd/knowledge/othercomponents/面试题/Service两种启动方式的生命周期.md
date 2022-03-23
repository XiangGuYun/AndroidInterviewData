### Service两种启动方式的生命周期
1. start方式: onCreate -> ____ -> onDestroy
2. bind方式: onCreate -> ____ -> ____ -> onDestroy

<details>
<summary>查看答案</summary>
<pre>
onStartCommand onBind onUnbind
</pre>
</details>