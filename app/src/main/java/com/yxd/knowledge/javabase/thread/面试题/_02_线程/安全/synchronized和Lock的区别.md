# synchronized和Lock（ReentrantLock）的区别

## 性质
1. Lock是<font color=#dea32c>**接口**</font>，属于API层面的锁，通过state来标识锁的状态。
2. synchronized是<font color=#dea32c>**关键字**</font>，属于JVM层面的锁，锁信息保存在对象头中。

## 灵活性
1. Lock可以<font color=#dea32c>**手动**</font>控制锁的获取和释放，
2. synchronized是只需指定锁，锁的获取和释放是<font color=#dea32c>**自动**</font>的。

## 性能
1. 线程较多时Lock性能好
2. 线程较少时synchronized性能好

## 公平 or 非公平
1. synchronized是非公平锁，当一个线程释放掉锁后，其它等待的线程均有可能获得这把锁。
2. ReentrantLock可以选择公平锁或非公平锁。
