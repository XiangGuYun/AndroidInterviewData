### 使用简单，链式调用比较方便
```
 Glide.with(context)
         .load(uri)
         .into(imageView);
```

### 占用内存较小
默认使用RGB_565格式

### 无代码侵入
可以使用默认的ImageView，无额外配置

### 支持GIF
ImageLoader不支持gif图片加载

### 缓存优化
1. 支持内存分级缓存：正在使用的图片，弱引用缓存；已使用过的图片LruCache缓存
2. Glide会为不同的ImageView尺寸缓存不同尺寸的图片

### 与Activity生命周期绑定，不会出现内存泄露

