### ThreadLocalMap
每个线程都存储着一份ThreadLocalMap
![](../images/8cb39a95.png)

***
#### ThreadLocal的set方法流程
![](../images/5d2d4ab6.png)

***
#### ThreadLocal的get方法流程
![](../images/0505e2a7.png)

***
#### ThreadLocal的remove方法
``` java
public void remove() {
    //获取当前线程绑定的threadLocals
     ThreadLocalMap m = getMap(Thread.currentThread());
     //如果map不为null，就移除当前线程中指定ThreadLocal实例的本地变量
     if (m != null)
         m.remove(this);
 }
```