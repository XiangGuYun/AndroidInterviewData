### 应用拦截器
拿到的是 ____ 请求，可以添加一些自定义 ____ 、通用 ____ 、参数加密、网关接入等等。

<details>
<summary>查看答案</summary>
<pre>
原始 header 参数
</pre>
</details>

#### RBCC
* RetryAndFollowUpInterceptor 处理 ____ 和 ____ 。
* BridgeInterceptor ____ 和 ____ 的桥接拦截器，主要工作是为请求添加cookie、添加固定的header，
  比如Host、Content-Length、Content-Type、User-Agent等等，然后保存响应结果的cookie，
  如果响应使用gzip压缩过，则还需要进行解压。
* CacheInterceptor 缓存拦截器，如果命中缓存则不会发起网络请求。
* ConnectInterceptor 连接拦截器，内部会维护一个连接池，负责连接复用、创建连接（三次握手等等）、
  释放连接以及创建连接上的socket流。
  

<details>
<summary>查看答案</summary>
<pre>
错误重试 重定向（FollowUp：追踪，重定向）
应用层 网络层
</pre>
</details>

***

### 网络拦截器
用户自定义拦截器，通常用于监控网络层的 ____ 。

CallServerInterceptor 请求拦截器，在前置准备工作完成后，真正发起了网络请求。

<details>
<summary>查看答案</summary>
<pre>
数据传输 
</pre>
</details>