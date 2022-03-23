package com.yxd.knowledge.designpattern.单例;

public class DclSingleInstance {

    // 关键点① - 添加volatile关键词，禁止指令重排序
    private static volatile DclSingleInstance instance;

    // 关键点② - 构造方法私有化
    private DclSingleInstance() { }

    public static DclSingleInstance getInstance() {
        if (instance == null) {
            // 关键点③ - 在同步代码块中再判断一次非空，然后初始化
            synchronized (DclSingleInstance.class) {
                if (instance == null) {
                    // 这句代码在字节码层面做了三件事情
                    //    |_1 给instance实例分配内存；
                    //    |_2 初始化instance的构造器；
                    //    |_3 将instance对象指向分配的内存空间
                    //    （注意到这步时instance就非null了）
                    // 如果不禁止指令重排序，那么有可能2和3顺序变换后执行到3，
                    // 其它线程判断了instance为非空，然而此时并没有初始化，结果就返回了null
                    instance = new DclSingleInstance();
                }
            }
        }
        return instance;
    }

}
