package com.yxd.knowledge.thread._01_线程.code;

/**
 * 演示同步代码块的使用
 *
 * 1. 不加同步代码块：多个线程都买到了票，且出现了第0张和第-1张票
 * 2. 添加同步代码块：最先运行的线程（获取锁）买到了所有票。
 */
public class SyncCodeBlock {

    public static void main(String[] args) {
        new SyncCodeBlock().start();
    }

    public void start() {
        TicketOffice ticketOffice = new TicketOffice();
        new Thread(ticketOffice, "刘备").start();
        new Thread(ticketOffice, "关羽").start();
        new Thread(ticketOffice, "张飞").start();
    }

    class TicketOffice implements Runnable {
        private int ticket = 100;

        @Override
        public void run() {
            // 只有拿到当前TicketOffice对象锁的线程才能运行方法块中的代码
            // 注意，必须是同一个TicketOffice对象，才能保证是同一把锁。
           synchronized (this){
               while (ticket > 0) {
                   try {
                       Thread.sleep(10);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   System.out.println(Thread.currentThread().getName() +
                           "买到了第" + ticket-- + "张票");
               }
           }

//            while (ticket > 0) {
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(Thread.currentThread().getName() +
//                        "买到了第" + ticket-- + "张票");
//            }

        }
    }
}