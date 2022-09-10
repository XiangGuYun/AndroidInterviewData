# ANR

## 什么是ANR？
ANR(Application Not responding)，是指应用程序未响应。

Android系统对于主线程中的事件需要在短时间内完成，防止长时间阻塞主线程。
***

## ANR情形
### KeyDispatchTimeout(5 seconds) 
主要类型按键或触摸事件在特定时间内无响应。
### BroadcastTimeout(10 seconds) 
BroadcastReceiver在特定时间内无法处理完成。
### ServiceTimeout(20 seconds) 
小概率类型 Service在特定的时间内无法处理完成。
***

## 避免ANR

避免在主线程中处理复杂的逻辑和计算，交给子线程操作。

***

## 分析ANR
1. logcat日志
2. /data/anr/traces.tex文件

***

## 监控线上ANR

### android6.0之前
使用FileObserveable对/data/anr/目录进行监听。
### android6.0之后
1. ANR Watch Dog思路（微信开源性能检测工具Marchs）。
2. 信号机制。