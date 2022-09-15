# ActivityThread→AMS

Activity.startActivity 

→ 

Instrumentation.execStartActivity 

→ 

ActivityManagerProxy.startActivity (ActivityThread中)

> 顾名思义，ActivityManagerProxy就是AMS的代理。
>
> #### 代理设计模式
> 将接口函数定义在一个抽象类中， Server和Client都会以该抽象类为基类实现所有接口函数。
> 1. Server端：负责真正的功能实现。
> 2. Client端：负责包装函数的远程调用。
>
> #### 理解AMS和AMP的关系
> 1. AMS是服务端，AMP是客户端。
> 2. AMS和AMP都实现了IActivityManager接口，这个接口定义了AMS提供给外部调用的方法。
> 3. AMS会对接口方法做具体实现，而AMP只是通过Binder远程调用这些方法。


ActivityManagerProxy.startActivity：利用Binder对象，调用transact(处理)方法，把所有需要的参数封装成Parcel对象，向AMS发送数据进行通信。