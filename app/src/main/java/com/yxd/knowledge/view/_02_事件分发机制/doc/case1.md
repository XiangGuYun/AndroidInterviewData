## 点击按钮(设置了点击监听和触摸监听)

![](img/578d4325.png)

#### 问：两条日志都会打印吗？
答：是的。
***
#### 问：如果将onTouch的返回值改为true，两条日志都会打印吗？
答：不会。
> onTouch返回true会拦截掉触摸事件，导致onClick不会被触发。

分析View.java，发现onTouch返回true时，会导致result为true。
![](img/32e7e05f.png)