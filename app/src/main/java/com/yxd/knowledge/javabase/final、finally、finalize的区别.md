#### final、finally、finalize的区别

> final用于声明属性、方法和类，分别表示属性 ____ ，方法  ____ ，类 ____ 。
>
> finally是异常处理语句结构的一部分，表示 ____ 。
>
> finalize是 ____ 类的一个方法，在 ____ 执行的时候会回调被回收对象的finalize()方法，可以覆盖此方法提供垃圾收集时其他资源的回收，例如关闭文件。

<details>
<summary>查看答案</summary>
<pre>
不可变  不可覆盖  不可继承  总是执行  Object  垃圾收集器
</pre>
</details>