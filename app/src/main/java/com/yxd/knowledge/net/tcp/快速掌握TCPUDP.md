## TCP / UDP

本质是在不同的程序间<font color=#dea32c>**通过网络传输数据**</font>的协议。

![](img/ae664871.png)

### 使用场景举例
|![](img/25414fbe.png)|![](img/4f31b2ba.png)|
| :---: | :---: |
|浏览网页|网络聊天|

***

TCP/UDP位于网络分层中的<font color=#dea32c>**传输层**</font>。

![](img/945e04a3.png)

传输的数据类型可以是<font color=#dea32c>**文本、视频、图片**</font>等<font color=#dea32c>**二进制**</font>文件。

![](img/f4664595.png)

***

## TCP vs UDP
![](img/f3e70ecd.png)

***

## TCP的三次握手
建立连接的过程。

1. <font color=#dea32c>**第一次**</font>🤝：客户端发送<font color=#00a6ac>**SYN**</font>包给服务端询问能否建立连接。

![](img/bcb74789.png)

2. <font color=#dea32c>**第二次**</font>🤝：服务端同意连接，回复<font color=#00a6ac>**SYN+ACK**</font>包。

![](img/6c632537.png)

3.<font color=#dea32c>**第三次**</font>🤝：客户端收到后，再回复一个<font color=#00a6ac>**ACK**</font>包，连接建立。

![](img/ee176330.png)

> 🤔 为什么要进行第三次握手？
> > 📒 为了防止已失效的请求报文，突然又传到服务端，引发错误。  
> 本质是为了解决网络信道不可靠的问题。
***
## TCP发送缓冲区
![](img/99971eed.png)

![](img/f141d2ce.png)

🤔 如何解决建立连接后发送数据产生的丢包问题和乱序问题？

![](img/7015a27e.png)
***
## TCP四次👋🏻
![](img/9a9b04b1.png)

客户端和服务端均可通过四次👋🏻来关闭连接。

以客户端发起请求为例：

1. 向服务端发起FIN包，表示要关闭连接，然后进入FIN-WAIT-1(终止)状态。

![](img/e0e92228.png)

2. 服务端收到FIN包后，发送ACK包，表示进入CLOSE-WAIT状态，客户端进入FIN-WAIT-2状态，

![](img/bdffa64b.png)

3. 服务端此时还可以发送数据，客户端也可以接受数据，待数据发送完后，发送FIN包给客户端，进入LAST-ACK(最后确认)状态。

![](img/fccf670c.png)

4. 客户端向服务端回复ACK包，服务端收到后立即关闭连接，客户端在超时等待后再关闭连接。

![](img/1ad401ea.png)

> 🤔 为什么客户端需要超时等待时间？
> > 📒 为了保证服务端已收到ACK包，防止服务端未能收到ACK包而一直处于最后确认状态。
> 
> 客户端在发送ACK后等待时，服务端会因为没有收到ACK包而重发FIN包，客户端也会重发ACK包，并刷新超时时间。
> ![](img/453a473c.png)

***
## UDP
![](img/96c25322.png)

TCP：适用于对网络通信质量要求较高的场景，需要准确无误地传输给对方。

UDP：适用于对实时性要求较高，但对少量丢包没有太高要求的场景。

![](img/729cccee.png)









