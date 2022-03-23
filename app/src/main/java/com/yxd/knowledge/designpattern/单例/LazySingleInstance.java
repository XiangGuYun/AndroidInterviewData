package com.yxd.knowledge.designpattern.单例;

public class LazySingleInstance {

    private static LazySingleInstance instance;

    private LazySingleInstance(){}

    public static LazySingleInstance getInstance(){
        if(instance == null){
            instance = new LazySingleInstance();
        }
        return instance;
    }

}
