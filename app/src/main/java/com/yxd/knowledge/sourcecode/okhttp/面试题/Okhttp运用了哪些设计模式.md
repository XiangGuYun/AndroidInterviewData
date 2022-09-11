* 构造者模式（OkhttpClient,Request 等各种对象的创建）
* 工厂模式（在 Call 接口中，有一个内部工厂 Factory 接口。）
* 单例模式（Platform 类，已经使用 Okhttp 时使用单例）
* 策略模式（在 CacheInterceptor 中，在响应数据的选择中使用了策略模式，选择缓存数据还是选择网络访问。）
* 责任链模式（拦截器的链式调用）
* 享元模式（Dispatcher 的线程池中，不限量的线程池实现了对象复用）