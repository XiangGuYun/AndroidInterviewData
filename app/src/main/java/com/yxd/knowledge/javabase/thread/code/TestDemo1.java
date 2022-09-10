package com.yxd.knowledge.javabase.thread.code;

/**
 * 需求：线程隔离
 * 在多线程并发的场景下，每个线程中的变量都是相互独立的，
 * 不应该存在线程A获取到了线程B的变量，线程B获取到了线程A的变量。
 */
public class TestDemo1 {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSafeContent(){
        // 代码含义：在当前线程的threadLocalMap中取出一个key为threadLocal的Entry，返回它的value
        return threadLocal.get();
    }

    public void setSafeContent(String content){
        // 代码含义：在当前线程的threadLocalMap中放入一个Entry（key为threadLocal，value为content）
        threadLocal.set(content);
    }

    public static void main(String[] args) {
        TestDemo1 demo = new TestDemo1();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(() -> {
                demo.printConfuseInfo();
//                demo.printCorrectInfo();
            });
            thread.setName("线程"+i);
            thread.start();
        }

    }

    /**
     *  获得了正确的数据
     *                 --------------
     *                 线程0---->线程0的数据
     *                 --------------
     *                 线程3---->线程3的数据
     *                 --------------
     *                 线程2---->线程2的数据
     *                 --------------
     *                 线程1---->线程1的数据
     *                 --------------
     *                 线程4---->线程4的数据
     */
    private void printCorrectInfo() {
        setSafeContent(Thread.currentThread().getName()+"的数据");
        System.out.println("--------------");
        System.out.println(Thread.currentThread().getName()+"---->"+getSafeContent());
    }

    /**
     *  打印出了错乱的数据
     *                 --------------
     *                 --------------
     *                 线程2---->线程2的数据
     *                 --------------
     *                 线程1---->线程3的数据
     *                 --------------
     *                 线程3---->线程3的数据
     *                 线程0---->线程2的数据
     *                 --------------
     *                 线程4---->线程4的数据
     */
    private void printConfuseInfo() {
        setContent(Thread.currentThread().getName()+"的数据");
        System.out.println("--------------");
        System.out.println(Thread.currentThread().getName()+"---->"+getContent());
    }


}
