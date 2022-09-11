package com.yxd.knowledge.bitmap.code

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.yxd.R
import com.yxd.devlib.base.TestFragment

/**
 * 演示通过inJustDecodeBounds和inSampleSize修改Bitmap分辨率
 *
 * inJustDecodeBounds：解码Bitmap时，只获取其分辨率，不用加载到内存中。
 *
 * inSampleSize：值越大，压缩就越大，只能取2的N次方。
 */
class SampleFragment : TestFragment() {

    override fun init(view: View, savedInstanceState: Bundle?) {
        val bitmap1 = BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong)
        // 打印压缩前的bitmap信息
        printBmpInfo(bitmap1)

        val bitmap2 = resizeBitmap(resources, R.mipmap.lvmaochong, 35, 35, false)
        // 打印压缩后的bitmap信息
        printBmpInfo(bitmap2)
    }

    /**
     * 重新调整Bitmap的大小
     */
    private fun resizeBitmap(
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

    /**
     * 根据Bitmap压缩前宽高和目标宽高计算出InSampleSize的值
     */
    private fun calcInSampleSize(w: Int, h: Int, targetW: Int, targetH: Int): Int {
        var inSampleSize = 1
        if (w > targetW && h > targetH) {
            inSampleSize = 2
            while (w / inSampleSize > targetW && h / inSampleSize > targetH) {
                inSampleSize *= 2
            }
        }
        return inSampleSize
    }

    private fun printBmpInfo(bitmap: Bitmap?) {
        bitmap ?: return
        // 图片所处的dpi文件越大，它的分辨率和内存占用就越小，内部是通过BitmapFactory.Options控制的
        logt(
            "bitmap的分辨率是${bitmap.width}x${bitmap.height}，" +
            "占用内存大小是${bitmap.allocationByteCount / 1024f}kb"
        )
    }


}