package com.yxd.knowledge.thread.code;

/**
 * 需求：线程隔离
 * 在多线程并发的场景下，每个线程中的变量都是相互独立的，
 * 不应该存在线程A获取到了线程B的变量，线程B获取到了线程A的变量。
 */
public class TestDemo2 {


    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static void main(String[] args) {
        TestDemo2 testDemo1 = new TestDemo2();

        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                synchronized (TestDemo2.class){
                    testDemo1.setContent(Thread.currentThread().getName()+"的数据");
                    System.out.println("--------------");
                    System.out.println(Thread.currentThread().getName()+"---->"+testDemo1.getContent());
                }
            });
            thread.setName("线程"+i);
            thread.start();
        }
    }

}
