# ArrayList

1. 线程不安全
2. 可以放null值。


### ArrayList的父类和父接口
1. AbstractList：大部分List的共同父类，提供了一些基本的 ____ ，以及通用的 ____ 实现。
2. List：列表标准接口，列表是一个 ____ 集合。
3. RandomAccess：一个 ____ 的随机访问接口，没有提供任何方法，表示该类
   使用 ____ 要比 ____ 要更快。
4. Cloneable：用于标记 ____ ，只要有实现接口接口才能调用clone方法。
5. Serializable：____ 标记接口，实现该接口的类可以实现 ____ 和 ____ 。

<details>
<summary>查看答案</summary>
<pre>
1. 方法封装，迭代器
2. 有序
3. 标记性质 索引遍历 迭代器
4. 可克隆对象 
5. 序列化 序列化 反序列化
</pre>
</details>