# AMS→ActivityThread

1. 双向代理：AMS中也有一个ActivityThread的代理，ApplicationThreadProxy（ATP）。

2. ApplicationThread和ATP都实现了接口IApplicationThread。

>关于Activity是如何被启动的，可以参考：
>
>AMS→ApplicationThread
>
>ApplicationThread→Activity

 