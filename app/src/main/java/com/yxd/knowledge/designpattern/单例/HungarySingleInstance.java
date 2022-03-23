package com.yxd.knowledge.designpattern.单例;

public class HungarySingleInstance {

    private static HungarySingleInstance instance = new HungarySingleInstance();

    private HungarySingleInstance(){}

    public static HungarySingleInstance getInstance(){
        return instance;
    }

}
