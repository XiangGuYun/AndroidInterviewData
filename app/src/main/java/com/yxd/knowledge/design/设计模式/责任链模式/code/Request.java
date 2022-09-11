package com.yxd.knowledge.design.设计模式.责任链模式.code;

public class Request {
    /**
     * 是否以登录
     */
    private boolean loggedOn;
    /**
     * 是否没频繁请求
     */
    private boolean frequentOK;
    /**
     * 是否有权访问
     */
    private boolean isPermits;
    /**
     * 是否包含敏感词
     */
    private boolean containsSensitiveWords;
    private String requestBody;

    public Request(boolean loggedOn, boolean frequentOK, boolean isPermits, boolean containsSensitiveWords) {
        this.loggedOn = loggedOn;
        this.frequentOK = frequentOK;
        this.isPermits = isPermits;
        this.containsSensitiveWords = containsSensitiveWords;
    }

    public boolean isLoggedOn() {
        return loggedOn;
    }


    public boolean isFrequentOK() {
        return frequentOK;
    }

    public boolean isPermits() {
        return isPermits;
    }

    public boolean isContainsSensitiveWords() {
        return containsSensitiveWords;
    }

    static class RequestBuilder{
        private boolean loggedOn;
        private boolean frequentOK;
        private boolean isPermits;
        private boolean containsSensitiveWords;

        RequestBuilder loggedOn(boolean loggedOn){
            this.loggedOn = loggedOn;
            return this;
        }

        RequestBuilder frequentOK(boolean frequentOK){
            this.frequentOK = frequentOK;
            return this;
        }

        RequestBuilder isPermits(boolean isPermits){
            this.isPermits = isPermits;
            return this;
        }

        RequestBuilder containsSensitiveWords(boolean containsSensitiveWords){
            this.containsSensitiveWords = containsSensitiveWords;
            return this;
        }

        public Request build() {
            return new Request(loggedOn, frequentOK, isPermits, containsSensitiveWords);
        }
    }

}

/**
 * 处理者基类
 */
abstract class Handler{
    Handler next;

    public Handler(Handler next){
        this.next = next;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    /**
     * 处理请求
     * @param request
     * @return 只有返回true才会继续执行
     */
    abstract boolean process(Request request);
}

/**
 * 请求频率处理者
 */
class RequestFrequentHandler extends Handler{

    public RequestFrequentHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("访问频率控制");
        if(request.isFrequentOK()){
            Handler next = getNext();
            if(null == next){
                // 如果没有下个节点了则返回true
                return true;
            }
            // 根据下个节点的返回值来进行返回
            return next.process(request);
        }
        return false;
    }
}

/**
 * 登录处理器
 */
class LoggingHandler extends Handler{

    public LoggingHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("登录验证");
        if(request.isLoggedOn()){
            Handler next = getNext();
            if(null == next){
                return true;
            }
            return next.process(request);
        }
        return false;
    }
}

class IsPermitsHandler extends Handler{

    public IsPermitsHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("权限验证");
        if(request.isPermits()){
            Handler next = getNext();
            if(null == next){
                return true;
            }
            return next.process(request);
        }
        return false;
    }
}

class ContainsSensitiveWordsHandler extends Handler{

    public ContainsSensitiveWordsHandler(Handler next) {
        super(next);
    }

    @Override
    boolean process(Request request) {
        System.out.println("敏感词验证");
        if(request.isContainsSensitiveWords()){
            Handler next = getNext();
            if(null == next){
                return true;
            }
            return next.process(request);
        }
        return false;
    }
}