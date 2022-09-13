package com.yxd.knowledge.thread._02_线程池.code;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo {

    public static void main(String[] args) {
        new ThreadPoolDemo().start();
    }

    class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("我要一个教练");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("教练来了" + Thread.currentThread().getName());
            System.out.println("教完后，教练回到了泳池");
        }
    }

    public void start() {
        // 创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(3);
        // 创建Runnable对象
        MyRunnable runnable1 = new MyRunnable();
        MyRunnable runnable2 = new MyRunnable();
        MyRunnable runnable3 = new MyRunnable();
        MyRunnable runnable4 = new MyRunnable();
        MyRunnable runnable5 = new MyRunnable();
        // 提交Runnable对象，从线程池拿出一个线程来执行这个runnable
        pool.submit(runnable1);
        pool.submit(runnable2);
        pool.submit(runnable3);
        pool.submit(runnable4);
        pool.submit(runnable5);
    }

    /**
     * 使用原始的方法创建线程池
     *
     * 关键单词
     * ThreadPoolExecutor   线程池执行器
     * ExecutorService      执行器服务
     * ArrayBlockingQueue   数组阻塞队列
     * AbortPolicy          取消策略
     */
    public void start1() {
        ExecutorService pool = new ThreadPoolExecutor(
                3,// 核心线程数
                5, // 非核心线程的最大线程数
                1L, // 非核心线程空闲时的存活时间
                TimeUnit.MINUTES, // 非核心线程空闲时的存活时间单位
                new ArrayBlockingQueue<>(3),// 等待队列
                Executors.defaultThreadFactory(), // 用于创建线程的线程工厂
                new ThreadPoolExecutor.AbortPolicy() // 拒绝策略，此处为直接抛异常
        );
        for (int i = 0; i < 9; i++) {
            pool.submit(() ->
                    System.out.println(Thread.currentThread().getName() + "==>办理业务"));
        }
        pool.shutdown();
    }

}