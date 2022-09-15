# 线程安全之volatile

[查看代码](code/VolatileDemo.java)

## 1. 保证可见性

对于加了volatile的变量，修改时会立即将<font color=#dea32c>**CPU高速缓存(工作内存)**</font>中的数据写回到<font color=#dea32c>**主内存**</font>，
其它线程会监听到变化，将自己的副本失效，重新从主内存读取该变量。

## 2. 保证有序性

底层通过操作系统的<font color=#dea32c>**内存屏障**</font>实现，<font color=#dea32c>**禁止指令重排序**</font>。

## 3. 不保证原子性

