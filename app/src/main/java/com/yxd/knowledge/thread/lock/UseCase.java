package com.yxd.knowledge.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseCase {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        new Thread(() -> {
            lock.lock();
            drawMoney();
            lock.unlock();
        }, "线程1").start();

        new Thread(() -> {
            lock.lock();
            drawMoney();
            lock.unlock();
        }, "线程2").start();

    }

    public static void drawMoney(){
        System.out.println(Thread.currentThread().getName()+"正在取钱...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"取完了");
    }

}
