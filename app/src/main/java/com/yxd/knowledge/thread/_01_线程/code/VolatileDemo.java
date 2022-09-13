package com.yxd.knowledge.thread._01_线程.code;

/**
 * 演示volatile修饰符的使用
 *
 * 给found添加了volatile的打印结果
 * 等基友送笔来...
 * 基友找到笔了，送过去...
 * 笔来了，开始写字...
 *
 * 不给found添加了volatile的打印结果
 * 等基友送笔来...
 * 基友找到笔了，送过去...
 *
 * "笔来了，开始写字..."，这句由于found在多线程中的可见性问题，没有被打印出来。
 *
 * -----------------------------------------------------
 *
 * 并发编程的三个基本概念
 * 1.原子性：同一时间段只能有一个线程对具有原子性的量进行操作，且不会被线程调度器打断。
 * 2.可见性：当多个线程同时访问一个变量时，一个线程修改了该变量的值，其它线程能立即看到修改后的值。
 * 3.有序性：程序执行的顺序按照代码的先后顺序拉执行。
 * <p>
 * volatile变量的特性
 * 1.保证可见性，不保证原子性
 * 2.禁止指令重排序
 */
public class VolatileDemo {

    public static void main(String[] args) {
        new VolatileDemo().start();
    }

    public static int found = 0;

    public void start() {
        new Thread(() -> {
            System.out.println("等基友送笔来...");
            while (0 == found) {

            }
            System.out.println("笔来了，开始写字...");
        }, "我线程").start();

        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("基友找到笔了，送过去...");
            change();
        }, "基友线程").start();
    }

    private void change() {
        found = 1;
    }
}