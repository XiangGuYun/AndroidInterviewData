### <font color=#00ae9d>**BitmapFactory.Options重要属性**</font>
1. <font color=#dea32c>**inJustDecodeBounds**</font>   
   如果这个值为true，那么在解析的时候不会返回Bitmap，而是只返回这个Bitmap
   的尺寸。所以，如果只是想知道Bitmap的尺寸，但又不想将其加载到内存中可以使用这个属性。
2. <font color=#dea32c>**outWidth和outHeight**</font>  
   表示Bitmap的宽和高。一般和inJustDecodeBounds
   一起使用来获取Bitmap的宽高，但不加载到内存。
3. <font color=#dea32c>**inSampleSize**</font>  
   压缩图片时采样率的值，会根据inSampleSize按照比例（1
   /inSampleSize）来缩小Bitmap的宽高。如果inSampleSize为2，那么Bitmap的宽为原来的1/2，高为原来的1/2。那么这个Bitmap所占内存会缩小为原来的1/4。
4. <font color=#dea32c>**inDensity**</font>  
   表示的是这个Bitmap的像素密度，对应的是DisplayMetrics
   中的densityDpi，density.
5. <font color=#dea32c>**inTargetDensity**</font><br>表示要被新 
   Bitmap 的目标像素密度，对应的是 
   DisplayMetrics 中的 densityDpi。
6. <font color=#dea32c>**inScreenDensity**</font>  
   表示实际设备的像素密度，对应的是 DisplayMetrics 中的 
   densityDpi。
7. <font color=#dea32c>**inPreferredConfig**</font>  
   这个值是设置色彩模式，默认值是 ARGB_8888,
   这个模式下，一个像素点占用 4Byte 。RGB_565 占用 2Byte，ARGB_4444 占用 4Byte（以废弃）。
8. <font color=#dea32c>**inPremultiplied**</font>  
   这个值和透明度通道有关，默认值是 true，如果设置为 true，则返回的 
   Bitmap 的颜色通道上会预先附加上透明度通道。
9. <font color=#dea32c>**inDither**</font>  
   这个值和抖动解码有关，默认值为 false，表示不采用抖动解码。
10. <font color=#dea32c>**inScaled**</font><br>设置这个Bitmap 
    是否可以被缩放，默认值是 true，表示可以被缩放。
11. <font color=#dea32c>**inPreferQualityOverSpeed**</font><br>这个值表示是否在解码时图片有更高的品质，仅用于 JPEG 
    格式。如果设置为 true，则图片会有更高的品质，但是会解码速度会很慢。
12. <font color=#dea32c>**inBitmap**</font><br>这个参数用来实现 
    Bitmap 内存的复用，但复用存在一些限制，具体体现在：在Android 4.4 之前只能重用相同大小的 Bitmap 的内存，而 Android 4.4 及以后版本则只要后来的 Bitmap 比之前的小即可。使用 inBitmap 参数前，每创建一个 Bitmap 对象都会分配一块内存供其使用，而使用了 inBitmap 参数后，多个 Bitmap 可以复用一块内存，这样可以提高性能。