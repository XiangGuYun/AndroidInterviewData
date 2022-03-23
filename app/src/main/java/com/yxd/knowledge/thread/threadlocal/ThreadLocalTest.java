package com.yxd.knowledge.thread.threadlocal;

/**
 * 开启两个线程，在每个线程内部设置了本地变量的值，然后调用print方法打印当前本地变量的值。
 * 如果在打印之后调用本地变量的remove方法会删除本地内存中的变量
 */
public class ThreadLocalTest {

    //==========================================================================================
    // 创建一个静态的ThreadLocal对象，指定泛型为String。
    //==========================================================================================
    static ThreadLocal<String> localVar = new ThreadLocal<>();

    static void print(String str) {
        // 打印当前线程中本地内存中本地变量的值
        System.out.println(str + " :" + localVar.get());
        // 清除本地内存中的本地变量
        localVar.remove();
    }

    public static void main(String[] args) {
        Thread t1  = new Thread(() -> {
            //设置线程1中本地变量的值
            localVar.set("localVar1");
            //调用打印方法
            print("thread1");
            //打印本地变量
            System.out.println("after remove : " + localVar.get());
        });

        Thread t2  = new Thread(() -> {
            //设置线程1中本地变量的值
            localVar.set("localVar2");
            //调用打印方法
            print("thread2");
            //打印本地变量
            System.out.println("after remove : " + localVar.get());
        });

        t1.start();
        t2.start();
    }
}