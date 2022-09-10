# 栈内复用模式 singleTask
该模式是一种单例模式，即一个栈内只有一个该Activity实例，常用于主页Activity。
``` xml
<activity android:name=".Activity1"
    <-- 
    每个Activity都有taskAffinify属性，
    这个属性指出了它希望进入的Task 
    -->
    android:launchMode="singleTask"
    android:taskAffinity="com.lvr.task"
    android:label="@string/app_name">
</activity>
```
在这种模式下，如果Activity指定的栈不存在，则创建一个栈，并把创建的Activity压入栈内。如果Activity指定的栈存在，如果其中没有该Activity实例，则会创建Activity并压入栈顶，如果其中有该Activity实例，则把该Activity实例之上的Activity杀死清除出战，重用并让该Activity实例处在栈顶，然后调用onNewIntent()方法。