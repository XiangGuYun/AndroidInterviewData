# RecyclerView局部刷新实现原理

## RV和LV添加移除Item效果对比
![](img/ec9031e0.png)

## 分析
以RecyclerView中notifyItemRemoved(1)为例，最终会调用requestLayout()，使整个RecyclerView重新绘制，过程为：onMeasure()-->onLayout()-->onDraw()