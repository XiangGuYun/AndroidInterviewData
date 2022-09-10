### Java8之前的HashMap
1. 在Java8之前，HashMap的底层实现是 ____ 和 ____ 。
2. HashMap存储数据的核心成员变量叫 ____ ，类型是 ____ 数组，每个数组元素本质是一个
   ____ 。
3. 为什么HashMap的容量必须是2的幂次方?
4. hashmap的put流程
    1. 计算 ____ 。
    2. 根据 ____ 和 ____ 长度来确定下标。
    3. 存入数组。
    4. 根据 ____ 和 ____ 比对，来确定是替换还是增加链表节点。
    5. 根据增加后的size来判断是否需要 ____ 。

<details>
<summary>查看答案</summary>
<pre>
1. 数组 链表 
2. table Entry 链表
3. 使Key的hashcode分散，避免hash冲突，提高查询效率
4. key的hash值 hash值 table长度 key值 hash值 扩容
</pre>
</details>