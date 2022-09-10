### 为何会有ConcurrentHashMap？
1. HashMap线程不安全。
2. HashTable和Collections.synchronizedMap()对读写加独占锁，性能太低。

### 什么是CAS？
C：Compare，比较。  
A：And，与。  
S：Swap，转换。  

是一种无锁算法（不通过加锁实现线程安全）。

