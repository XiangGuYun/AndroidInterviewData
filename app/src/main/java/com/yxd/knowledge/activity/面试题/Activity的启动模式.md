#### Activity启动模式之standard
每启动一次Activity，就会创建一个新的Activity实例并置于栈顶。谁启动了这个Activity，那么这个Activity就运行在启动它的那个Activity所在的栈中。

启动Activity时，Intent构造方法中的context是可以传Service的context或是application的context，但是必须要设置FLAG_ACTIVITY_NEW_TASK，表示创建一个新的栈来存放这个Activity。

***

#### 栈顶复用模式 singleTop
如果需要新建的Activity位于任务栈栈顶，那么此Activity的实例就不会重建，而是复用栈顶的实例。并回调：
``` java
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
```

***

#### 栈内复用模式 singleTask
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

***

#### 单例模式 singleInstance
打开该Activity时，直接创建一个新的任务栈，并创建该Activity实例放入栈中。一旦该模式的Activity实例已经存在于某个栈中，任何应用在激活该Activity时都会重用该栈中的实例。