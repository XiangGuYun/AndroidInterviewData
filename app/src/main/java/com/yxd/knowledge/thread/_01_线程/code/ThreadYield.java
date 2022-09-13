package com.yxd.knowledge.thread._01_线程.code;

class ThreadYield{

    public static void main(String[]args){

        new Thread(() -> {
            long time1 = System.currentTimeMillis();
            int num = 1;
            for (int i = 0; i < 1000000; i++) {
                num++;
                Thread.yield();
            }
            long time2 = System.currentTimeMillis();
            System.out.println("子线程耗时："+(time2-time1));
        }).start();

        long time1 = System.currentTimeMillis();
        int num = 1;
        for (int i = 0; i < 1000000; i++) {
            num++;
        }
        long time2 = System.currentTimeMillis();
        System.out.println("主线程耗时："+(time2-time1));


    }

}