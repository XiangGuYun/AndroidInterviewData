# 线程安全之Lock

<br>

Lock在JDK5加入，相比synchronized，结构更灵活，功能更强大，性能更优越。

### 主要的实现类
1. ReentrantLock
2. ReentrantReadWriteLock.ReadLock
3. ReentrantReadWriteLock.WriteLock

### 锁的可重入性
当一个线程获取到一个对象的锁后，再次请求获取该对象的锁，仍然可以获取到。
> 假设锁不可重入，有可能会产生死锁。

<br>

## ReentrantLock

可重入锁，与synchronized一样具备互斥的功能。

可重复获取锁（调用lock方法），但每次调用lock后，必须调用一次unlock()。

[ReentrantLock使用介绍](doc/ReentrantLock.md)

<br>

## 公平锁和非公平锁

### 非公平锁
非公平锁被释放时，会被其它等待获取的线程随机获取。
> synchronized是非公平锁，ReentrantLock默认也是非公平锁。

### 公平锁
公平锁被释放时，最先申请获取锁的线程会得到锁。
> 可以通过 new ReentrantLock(boolean fair) 得到公平锁。

<br>

## ReentrantReadWriteLock读写锁

### 独占锁
同一时间只允许一个线程执行同步代码块。
> synchronized内部锁、ReentrantLock都是独占锁。

### 读写锁
同一时间内，允许多个线程读取数据，但只允许一个线程修改数据。
> 开发者可以同时读取远程仓库的代码，但是不能同时向远程仓库push代码。


1. 读锁：可以被多个线程持有，读取前必须持有读锁，若其它线程持有写锁，则无法获取。
2. 写锁：当其它线程既不持有读锁也不持有写锁时，才能够获取。
> 读读共享、读写互斥、写写互斥。






