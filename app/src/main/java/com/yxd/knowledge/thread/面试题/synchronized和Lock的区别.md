# synchronized和Lock的区别

### 性质
1. Lock是接口
2. synchronized是关键字

### 灵活性
1. Lock可以手动控制锁的获取和释放，
2. synchronized是只需指定锁，锁的获取和释放是自动的。

### 性能
1. 线程较多时Lock性能好
2. 线程较少时synchronized性能好