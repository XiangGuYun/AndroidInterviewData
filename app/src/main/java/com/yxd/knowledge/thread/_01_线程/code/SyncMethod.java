package com.yxd.knowledge.thread._01_线程.code;

public class SyncMethod {

    public static void main(String[] args) {
        new SyncMethod().start();
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
            sell();
        }

        /**
         * 同步方法的锁是从调用方法的对象获取的，在这里就是TicketOffice的对象
         * synchronized关键字也可以修饰静态方法，这是锁是从类本身获取的，
         * 在这里就是TicketOffice.class
         */
        public synchronized void sell(){
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
    }
}
