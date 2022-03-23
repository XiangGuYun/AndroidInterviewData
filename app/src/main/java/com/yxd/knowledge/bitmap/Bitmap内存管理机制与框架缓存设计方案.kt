package com.yxd.knowledge.bitmap

import android.app.ActivityManager
import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import android.view.View
import com.jakewharton.disklrucache.DiskLruCache
import com.yxd.R
import com.yxd.devlib.base.TestFragment
import com.yxd.knowledge.bitmap.ImageResize.resizeBitmap
import java.io.File
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference
import java.util.*
import kotlin.collections.HashSet

/**
 * Bitmap内存管理机制与框架缓存设计方案
 * 讲解了Bitmap优化的各种技巧
 * 课程来源：bilibili.com/video/BV1bk4y1m7Ca?from=search&seid=10641523087589986477&spm_id_from=333.337.0.0
 */
class BitmapCaseFragment : TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        // =============================
        // 学习利用inSampleSize和inJustDecodeBounds来压缩Bitmap
        // =============================
        优化点1_像素压缩()
        // =============================
        // 学习利用inMutable和inBitmap来复用Bitmap内存
        // 在按下这两个按钮后的内存变化见imgs/img.png
        // =============================
        优化点2_内存复用()
        // =============================
        // LRU算法：最近最少使用算法
        // =============================
        优化点3_三级缓存()
    }

    private fun 优化点3_三级缓存() {

    }

    private fun 优化点2_内存复用() {
        addButton("生成500个相同的Bitmap") {
            for (i in 0 until 500) {
                BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong)
            }
        }
        addButton("生成500个相同的Bitmap(复用内存)") {
            val options = BitmapFactory.Options()
            // 当设置为true时，解析出来的bitmap将是可变的，即可以再次赋值
            options.inMutable = true
            var bitmapFirst = BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong, options)
            // 接下来所有的bitmap将复用bitmapFirst的内存块
            for (i in 0 until 500) {
                // 复用bitmapFirst内存
                options.inBitmap = bitmapFirst
                // 在API19之前，只能相同的图片才能复用，之后只要新图片内存小于或等于被复用的内存，就可以复用
                bitmapFirst = BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong, options)
            }
        }
    }

    private fun 优化点1_像素压缩() {
        val bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong)
        printBmpInfo(bitmap1)
        val bitmap2 = resizeBitmap(resources, R.mipmap.lvmaochong, 35, 35, false)
        printBmpInfo(bitmap2)
    }

    /**
     * 打印Bitmap的分辨率和内存占用
     * @param bitmap Bitmap
     *
     */
    private fun printBmpInfo(bitmap: Bitmap?) {
        bitmap ?: return
        // 图片所处的dpi文件越大，它的分辨率和内存占用就越小，内部是通过BitmapFactory.Options控制的
        logt(
            "bitmap的分辨率是${bitmap.width}x${bitmap.height}，" +
            "占用内存大小是${bitmap.allocationByteCount / 1024f}kb"
        )
    }

}

object ImageResize {
    /**
     * 重新调整Bitmap的大小
     * @param id Int
     * @param targetWidth Int
     * @param targetHeight Int
     * @param useAlpha Boolean
     */
    fun resizeBitmap(
        resources: Resources,
        id: Int,
        targetWidth: Int,
        targetHeight: Int,
        useAlpha: Boolean
    ): Bitmap? {
        val options = BitmapFactory.Options()
        // 无需分配内存，只获取Bitmap的宽高等信息
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, id, options)
        // ourWidth和outHeight，返回图片最终的宽高
        val w = options.outWidth
        val h = options.outHeight
        // 设置采样大小，值越大，压缩就越大，只能取2的N次方，即跟1024挂钩
        options.inSampleSize = calcInSampleSize(w, h, targetWidth, targetHeight)
        options.inJustDecodeBounds = false
        if (!useAlpha) {
            options.inPreferredConfig = Bitmap.Config.RGB_565
        }
        return BitmapFactory.decodeResource(resources, id, options)
    }

    fun calcInSampleSize(w: Int, h: Int, targetW: Int, targetH: Int): Int {
        var inSampleSize = 1
        if (w > targetW && h > targetH) {
            inSampleSize = 2
            while (w / inSampleSize > targetW && h / inSampleSize > targetH) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }
}

class ImageCache {
    companion object {
        @Volatile
        private var instance: ImageCache? = null
    }

    inner class ImageLruCache(maxSize: Int) : LruCache<String, Bitmap>(maxSize) {

        private val gcListener = GCListener<Bitmap>().apply {
            setGarbageCollectedListener {
                if (!it.isRecycled) {
                    it.recycle()
                }
            }
        }

        // 以用户定义的单位返回键和值的条目(在此处表示Bitmap)的大小。
        override fun sizeOf(key: String?, value: Bitmap?): Int {
            //返回bitmap的内存大小
            return value!!.allocationByteCount
        }

        // 当将oldValue逐出以腾出空间、由remove调用删除或由put调用替换时，将调用此方法。
        override fun entryRemoved(
            evicted: Boolean,
            key: String?,
            oldValue: Bitmap?,
            newValue: Bitmap?
        ) {
            oldValue ?: return
            if (oldValue.isMutable) {
                // 如果能复用
                reusePool.plus(WeakReference(oldValue, gcListener.getReferenceQueue()))
            } else {
                oldValue.recycle()
            }
        }
    }

    private lateinit var context: Context
    private lateinit var memoryCache: ImageLruCache
    private lateinit var diskCache: DiskLruCache

    private val options = BitmapFactory.Options()

    private lateinit var reusePool: MutableSet<WeakReference<Bitmap>>

    private constructor()

    fun init(context: Context, dir: String) {
        this.context = context.applicationContext
        reusePool = Collections.synchronizedSet(HashSet<WeakReference<Bitmap>>())
        val am = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        // 返回当前设备下每个应用程序获取到的内存近似值，单位是MB
        val memoryClass = am.memoryClass
        memoryCache = ImageLruCache(memoryClass / 8 * 1024 * 1024)
        diskCache = DiskLruCache.open(File(dir), 1, 1, 10 * 1024 * 1024)

    }

    fun putBitmapToMemory(key: String, bitmap: Bitmap) {
        memoryCache.put(key, bitmap)
    }

    fun getBitmapFromMemory(key: String): Bitmap? {
        return memoryCache.get(key)
    }

    fun clearMemoryCache() {
        memoryCache.evictAll()
    }

    fun getReuse(w: Int, h: Int, inSampleSize: Int): Bitmap? {
        val iterator = reusePool.iterator()
        var reuseBitmap:Bitmap?=null
        if(iterator.hasNext()){
            val bitmap = iterator.next().get()
            if(bitmap != null){
                if(checkInBitmap(bitmap, w, h, inSampleSize)){
                    reuseBitmap = bitmap
                    iterator.remove()
                    Log.d("Test", "复用池中找到了")
                } else {
                    iterator.remove()
                }
            }
        }
        return reuseBitmap
    }

    private fun checkInBitmap(bitmap: Bitmap, w: Int, h: Int, inSampleSize: Int): Boolean {
        return true
    }

    fun getInstance(): ImageCache {
        if (instance == null) {
            synchronized(ImageResize::class.java) {
                if (instance == null) {
                    instance = ImageCache()
                }
            }
        }
        return instance ?: ImageCache()
    }


}

/**
 * GC监听器
 * @param T
 */
class GCListener<T> {
    private var referenceQueue: ReferenceQueue<T>? = null
    private lateinit var thread: Thread
    private var shutDown = false

    private var garbageCollectedListener: ((T) -> Unit)? = null

    fun setGarbageCollectedListener(garbageCollectedListener: ((T) -> Unit)?) {
        this.garbageCollectedListener = garbageCollectedListener
    }

    // 用于主动监听GC的API，加快回收
    fun getReferenceQueue(): ReferenceQueue<T> {
        if (referenceQueue == null) {
            referenceQueue = ReferenceQueue<T>()
            thread = Thread {
                if (!shutDown) {
                    val reference = referenceQueue?.remove()
                    val t = reference?.get()
                    if (null != t) {
                        garbageCollectedListener?.invoke(t)
                    }
                }
            }
        }
        return referenceQueue ?: ReferenceQueue<T>()
    }
}