# 如何理解volatile关键字

## 1. 保证可见性

对于加了volatile的变量，修改时会直接将<font color=#dea32c>**CPU高级缓存**</font>中的数据写回到<font color=#dea32c>**主内存**</font>，直接从主内存读取该变量。

## 2. 保证有序性

底层通过操作系统的<font color=#dea32c>**内存屏障**</font>实现，<font color=#dea32c>**禁止指令重排序**</font>。

