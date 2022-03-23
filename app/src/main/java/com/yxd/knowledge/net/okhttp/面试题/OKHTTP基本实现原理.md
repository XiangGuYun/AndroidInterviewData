> OkHttp 主要是通过 5 个 ____ 和 3 个 ____ （2 个异步队列，1 个同步队列）工作。
> 内部实现通过一个 ____ 模式完成，将网络请求的各个阶段封装到各个链条中，实现了各层的解耦。

<details>
<summary>查看答案</summary>
<pre>
拦截器 双端队列 责任链
</pre>
</details>

> OkHttp 的底层是通过 ____ 发送 HTTP 请求与接受响应，OkHttp 实现了 ____ 的概念，
> 即对于同一主机的多个请求，可以共用一个 ____ 连接，而不是每次发送完 HTTP 请求就关闭底层的 ____ ，
> OkHttp 对 ____ 的读写操作使用的 OkIo 库进行了一层封装。

<details>
<summary>查看答案</summary>
<pre>
Socket 连接池 Socket Socket Socket
</pre>
</details>

### 执行流程
* 通过构建者构建出 ____ 对象,再通过newCall方法获得 ____ 请求对象.
  
  <details>
  <summary>查看答案</summary>
  <pre>
  OkHttpClient RealCall
  </pre>
  </details>
* 通过 ____ 发起同步或异步请求,而决定是异步还是同步请求的是由线程分发器 ____ 来决定.
  
  <details>
  <summary>查看答案</summary>
  <pre>
  RealCall dispatcher
  </pre>
  </details>
* 当发起同步请求时会将请求加入到 ____ 依次执行,所以会阻塞UI线程,需要开启子线程执行.
  
  <details>
  <summary>查看答案</summary>
  <pre>
  同步队列中
  </pre>
  </details>
* 当发起异步请求时会创建一个 ____ ,并且判断请求队列是否大于最大请求队列64,请求主机数是否大于5,
  如果大于请求添加到异步等待队列中,否则添加到异步执行队列,并执行任务.
  
  <details>
  <summary>查看答案</summary>
  <pre>
  线程池   
  </pre>
  </details>