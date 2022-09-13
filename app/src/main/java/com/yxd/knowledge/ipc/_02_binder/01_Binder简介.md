# Binder简介

## Android Binder


### 是什么?
1. Android平台上一种跨进程通信机制，由OpenBinder演化而来。
2. 从应用层角度来说，Binder是客户端和服务端通信的媒介。
3. 从FrameWork角度，Binder是ServiceManager连接各种Manager（如am，wm）等的桥梁。
4. 从IPC角度，Binder是跨进程通信方式。

### 相比于其它IPC方案的优势
1. 性能：只需要<font color=#dea32c>**一次数据拷贝**</font>，性能上仅次于共享内存。
2. 稳定性：基于<font color=#dea32c>**C/S架构**</font>，职责明确，架构清晰，因此稳定性好。
3. 安全性：为每个App分配<font color=#dea32c>**UID**</font>（鉴别进程身份的重要标志）。

***

## IPC原理

![](img/81a6560f.png)

1. 每个Android进程，