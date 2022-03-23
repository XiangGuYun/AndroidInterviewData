### Activity是否能直接与start方式启动的Service产生交互？如果Activity
   退出了，Service是否也会退出？

<details>
<summary>查看答案</summary>
<pre>
不能，不会。
</pre>
</details>

### bind方式启动的服务是否会随着Activity的销毁而销毁？Activity是否可以  
   直接访问bind方式启动的Service？

<details>
<summary>查看答案</summary>
<pre>
会，可以，通过binder对象进行访问。
</pre>
</details>