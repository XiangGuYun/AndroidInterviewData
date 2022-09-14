### 如果重复调用startService，Service的是否会重复执行onCreate和onStartCommand？

不会执行onCreate，而是执行onStartCommand。
