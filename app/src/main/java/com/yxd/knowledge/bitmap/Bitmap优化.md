# Bitmap优化
思路1：减少单个像素的大小。

思路2：减少像素的总数。

思路3：放入合适的mipmap文件夹下面。

思路4：Bitmap内存复用。

思路5：三级缓存。

***
### BitmapFactory.Options
<font color=#dea32c>**inPreferredConfig**</font>：图片解码后的<font color=#dea32c>**像素格式**</font>。
> ARGB_8888(8x4=32字节)，ARGB_4444(4x4=16字节)，RGB_565（5+6+6=16字节），ALPHA_8。

<font color=#dea32c>**inSampleSize**</font>：设置图片的采样率进行缩放展示。

![](img/9298d0dd.png)