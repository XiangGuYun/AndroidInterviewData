# Bitmap是如何加载的


#### Bitmap.java
createBitmap的有很多重载方法，但最终都会调用到nativeCreate方法
``` java
public static Bitmap createBitmap(int width, int height,
        @NonNull Config config, boolean hasAlpha) {
    return createBitmap(null, width, height, config, hasAlpha);
}
```

``` java
public static Bitmap createBitmap(@Nullable DisplayMetrics display, int width, int height,
        @NonNull Config config, boolean hasAlpha) {
    return createBitmap(display, width, height, config, hasAlpha,
            ColorSpace.get(ColorSpace.Named.SRGB));
}
```

``` java
 public static Bitmap createBitmap(@Nullable DisplayMetrics display, int width, int height,
        @NonNull Config config, boolean hasAlpha, @NonNull ColorSpace colorSpace) {
    // ...

    Bitmap bm = nativeCreate(null, 0, width, width, height, config.nativeInt, true,
            colorSpace == null ? 0 : colorSpace.getNativeInstance());

    // ...
    return bm;
}
```

``` java
private static native Bitmap nativeCreate(int[] colors, int offset,
                                          int stride, int width, int height,
                                          int nativeConfig, boolean mutable,
                                          long nativeColorSpace);
```

#### Bitmap.cpp

> Bitmap.cpp路径
> 
> ![](img/6cd3f00b.png)

```objectivec
// 动态注册
static const JNINativeMethod gBitmapMethods[] = {
    {   
        "nativeCreate", 
        "([IIIIIIZ[FLandroid/graphics/ColorSpace$Rgb$TransferParameters;)Landroid/graphics/Bitmap;",
        (void*)Bitmap_creator // nativeCreate对应的方法是Bitmap_creator
    },
    // ...
};
```

```objectivec
static jobject Bitmap_creator(JNIEnv* env, jobject, jintArray jColors,
                              jint offset, jint stride, jint width, jint height,
                              jint configHandle, jboolean isMutable,
                              jfloatArray xyzD50, jobject transferParameters) {
    // ...
    
    // Android使用的图像引擎是Skia
    SkBitmap bitmap;

    // ...

    bitmap.setInfo(SkImageInfo::Make(width, height, colorType, kPremul_SkAlphaType, colorSpace));
    
    // 为Bitmap开辟内存
    sk_sp<Bitmap> nativeBitmap = Bitmap::allocateHeapBitmap(&bitmap);
    if (!nativeBitmap) {
        ALOGE("OOM allocating Bitmap with dimensions %i x %i", width, height);
        doThrowOOME(env);
        return NULL;
    }

    if (jColors != NULL) {
        GraphicsJNI::SetPixels(env, jColors, offset, stride, 0, 0, width, height, bitmap);
    }

    return createBitmap(env, nativeBitmap.release(), getPremulBitmapCreateFlags(isMutable));
}

```