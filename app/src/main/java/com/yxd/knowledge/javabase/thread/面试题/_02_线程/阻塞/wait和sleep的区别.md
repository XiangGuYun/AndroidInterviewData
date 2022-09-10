# wait和sleep的区别

|   |  wait  | sleep |
|  ----  | ----  | ----  |
|锁的释放|释放|不释放|
|所属类|Object|Thread|
|场景限制|必须在同步代码块中|不限制|
|唤醒时机|随时可唤醒|睡眠时间结束后自动唤醒|

***

## 回顾 wait / notify 的用法

[点击前往](../wait_notify/WaitNotify机制的运用.kt)