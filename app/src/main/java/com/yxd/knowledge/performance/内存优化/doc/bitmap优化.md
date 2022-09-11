### 如何计算Bitmap占用的内存大小？

<details>
<summary>查看答案</summary>
<pre>
Bitmap占用内存 = 分辨率 * 单个像素点的内存
</pre>
</details>

### 优化Bitmap分辨率
通常APP加载一张图片时候，ImageView的大小是确定的，比如一个ImageView<br> 
的大小设置为100 * 100 ，但是被加载的Bitmap的分辨率是 200 * 200，那么<br>
就可以通过采样压缩将该'Bitmap' 的分辨率压缩到 '100 * 100'。通过这一压缩<br>
操作可以直接减少4倍的内存大小。

### 将图片放入到合适的mipmap文件夹下
mipmap文件夹所标志的dpi越大，它的分辨率和内存占用就越小，内部是通过BitmapFactory.
Options控制的。如果希望图片分辨率在不同的设备是始终保持一致，就应该放到assets文件夹或nodpi文件夹下。 

    例如，如果大分辨率的手机用了低dpi文件夹的图片，图片就会被放大并显得模糊。