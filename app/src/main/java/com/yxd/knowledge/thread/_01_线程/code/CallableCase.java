package com.yxd.knowledge.thread._01_线程.code;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableCase {

    public static void main(String[] args) {
        new CallableCase().start();
    }

    public void start(){
        ExecutorService pool = Executors.newFixedThreadPool(3);
        CallableImpl callable1 = new CallableImpl("线程1");
        CallableImpl callable2 = new CallableImpl("线程2");
        CallableImpl callable3 = new CallableImpl("线程3");

        Future<Integer> result1 = pool.submit(callable1);
        Future<Integer> result2 = pool.submit(callable2);
        Future<Integer> result3 = pool.submit(callable3);

        pool.shutdown();

        try {
            System.out.println("线程1的结果是"+result1.get());
            System.out.println("线程2的结果是"+result2.get());
            System.out.println("线程3的结果是"+result3.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class CallableImpl implements Callable<Integer>{
        private String name;
        public CallableImpl(String name){
            this.name = name;
        }

        @Override
        public Integer call() throws Exception {
            System.out.println(name+"开始计算...");
            Thread.sleep(200);
            int sum = 0;
            int random = (int) (Math.random()*10);
            System.out.println(name+"产生的随机数是"+random);
            for (int i = 0; i < random; i++) {
                sum += random;
            }
            return sum;
        }
    }

}
