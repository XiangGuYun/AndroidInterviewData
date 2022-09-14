# 线程安全之Lock

1. ReentrantLock重入锁
2. 公平锁和非公平锁
3. 读写锁
4. 死锁

## ReentrantLock

Lock在JDK5加入，结构更灵活，功能更强大，性能更优越。

### 主要方法
1. void lock()：获取锁，如果锁被占有，则等待。
2. void tryLock()：尝试获取锁，成功返回true，失败返回false，不阻塞。
3. void unlock()：释放锁。


### 实现类
ReentrantLock 重入锁，与synchronized一样具备互斥的功能。

[查看代码](code/Lock.java)
