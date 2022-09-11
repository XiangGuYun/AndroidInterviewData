package com.yxd.knowledge.design.设计模式.责任链模式.code;

public class Test {
    public static void main(String[] args) {
        Request request = new Request.RequestBuilder()
                .frequentOK(true)
                .loggedOn(false)
                .build();

        RequestFrequentHandler requestFrequentHandler =
                new RequestFrequentHandler(new LoggingHandler(null));

        if(requestFrequentHandler.process(request)){
            System.out.println("正常业务处理");
        } else {
            System.out.println("访问异常");
        }
    }
}
