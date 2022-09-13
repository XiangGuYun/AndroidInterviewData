package com.yxd.knowledge.thread._01_线程.code;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示Lock的使用
 *
 * 如果使用了Lock，输出结果是：
 * 线程A添加了Hello到数组
 * 线程B添加了World到数组
 * 数组：[A, B, Hello, World, ]
 *
 * 如果不用Lock，输出结果是：
 * 线程B添加了World到数组
 * 线程A添加了Hello到数组
 * 数组：[A, B, World, , ]
 * 这里的数组结果明显不对
 */
public class Lock {

    public static void main(String[] args) {
        new Lock().start();
    }

    public void start() {
        MyList myList = new MyList();
        Runnable r1 = () -> {
            myList.add("Hello");
        };
        Runnable r2 = () -> {
            myList.add("World");
        };
        Thread t1 = new Thread(r1, "线程A");
        Thread t2 = new Thread(r2, "线程B");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("数组："+Arrays.toString(myList.getArr()));
    }

    class MyList {

        private String[] arr = {"A", "B", "", "", ""};
        private int index = 2;
        private java.util.concurrent.locks.Lock lock = new ReentrantLock();

        public void add(String str) {
            // 获取锁
//            lock.lock();
//            try {
//                testCode(str);
//            } finally {
//                // 释放锁
//                // 一定要在finally中进行，保证锁的释放
//                lock.unlock();
//            }

            testCode(str);
        }


        public void testCode(String str){
            arr[index] = str;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            index++;
            System.out.println(Thread.currentThread().getName() + "添加了" + str+"到数组");
        }

        public String[] getArr() {
            return arr;
        }

    }

}