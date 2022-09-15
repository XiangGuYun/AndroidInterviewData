# Put流程

![](../img/44ef4764.png)

### 核心步骤
1. 计算key的hash值。
2. 给hash值取模。

***

### 放入数据

1. 准备数据  
![](../img/9b748e4e.png)

![](../img/fc5a0437.png)

2. 放入Entry"刘一：

![](../img/0e5f01ae.png)

3. 放入Entry"陈二"

![](../img/f6a1f95d.png)

4. 放入Entry"张三"

> 刘一的位置让给了张三，且张三指向了刘一

![](../img/e743ec65.png)

5. 放入Entry"王五"和"李四"

![](../img/9929adc6.png)

6. 放入Entry"猴哥"

![](../img/23513346.png)

***
# Get流程

![](../img/d3251a2e.png)

1. 首先根据key计算出数组下标
2. 比较key的值，如果不相同，则继续往下找