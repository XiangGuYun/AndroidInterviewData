package com.yxd.knowledge.design.设计模式.单例;

public class HungarySingleInstance {

    private static HungarySingleInstance instance = new HungarySingleInstance();

    private HungarySingleInstance(){}

    public static HungarySingleInstance getInstance(){
        return instance;
    }

}
