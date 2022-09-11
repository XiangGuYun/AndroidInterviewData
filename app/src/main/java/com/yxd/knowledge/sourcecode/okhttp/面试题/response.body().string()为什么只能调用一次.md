```java
public final String string() throws IOException {
  BufferedSource source = source();
  try {
    Charset charset = Util.bomAwareCharset(source, charset());
    return source.readString(charset);
  } finally {
    // 这里把resource给悄悄close了
    // 因此再次打开自然会报异常
    Util.closeQuietly(source);
  }
}
```

解决方案：
1. 内存缓存一份response.body().string()。
2. 自定义拦截器处理Log。