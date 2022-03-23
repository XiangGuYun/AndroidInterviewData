### 强引用
是指创建一个对象并把这个对象赋给一个引用变量。
``` java
Object object = new Object();
String str = "hello";
```
强引用有引用变量指向时不会被垃圾回收，JVM宁愿抛出OutOfMemory错误也不会回收这种对象。
如果想中断强引用和某个对象之间的关联，可以显示地将引用赋值为null，JVM在合适的时间会回收该对象。

***
### 软引用（SoftReference）
如果一个对象具有软引用，只有当内存不足时，垃圾回收器才会回收它。
软引用可用来实现内存敏感的高速缓存，比如网页缓存、图片缓存等。使用软引用能防止内存泄露，增强程序的健壮性。

***
### 弱引用（WeakReference）
当JVM进行垃圾回收时，无论内存是否充足，都会回收被弱引用关联的对象。
[Java的引用](file:////Users/mac/AndroidStudioProjects/DevLib/app/src/main/java/com/yxd/devlib/知识库/面试题/java基础/Java的引用.kt)

要注意的是，被弱引用关联的对象是指只有弱引用与之关联，如果存在强引用同时与之关联，则进行垃圾回收时也不会回收该对象（软引用也是如此）。
[Java的引用1](file:////Users/mac/AndroidStudioProjects/DevLib/app/src/main/java/com/yxd/devlib/知识库/面试题/java基础/Java的引用.kt)

***
### 虚引用（PhantomReference）
虚引用和前面的软引用、弱引用不同，它并不影响对象的生命周期。在java中用java.lang.ref.PhantomReference类表示。如果一个对象与虚引用关联，则跟没有引用与之关联一样，在任何时候都可能被垃圾回收器回收。

要注意的是，虚引用必须和引用队列关联使用，当垃圾回收器准备回收一个对象时，如果发现它还有虚引用，就会把这个虚引用加入到与之 关联的引用队列中。程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象的内存被回收之前采取必要的行动。
[Java的引用2](file:////Users/mac/AndroidStudioProjects/DevLib/app/src/main/java/com/yxd/devlib/知识库/面试题/java基础/Java的引用.kt)
