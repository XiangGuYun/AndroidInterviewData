package com.yxd.knowledge.bitmap.code

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import com.yxd.R
import com.yxd.devlib.base.TestFragment

/**
 * 演示如何复用Bitmap的内存
 *
 * 避免频繁地创建和回收Bitmap，引发内存抖动。
 */
class BitmapReuseFragment : TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {

        // 不复用
        addButton("生成500个相同的Bitmap") {
            for (i in 0 until 500) {
                BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong)
            }
        }

        // 复用
        addButton("生成500个相同的Bitmap(复用内存)") {
            val options = BitmapFactory.Options()

            // 当设置为true时，解析出来的bitmap将是可变的，即可以再次赋值
            // 如果不设置会报错：Unable to reuse an immutable bitmap as an image decoder target.
            options.inMutable = true
            var bitmapFirst = BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong, options)

            // 接下来所有的bitmap将复用bitmapFirst的内存块
            for (i in 0 until 500) {
                // 复用bitmapFirst内存
                options.inBitmap = bitmapFirst
                // 在API19之前，只能相同的图片才能复用
                // 在API19之后，只要新图片内存小于或等于被复用的内存，都可以复用
                bitmapFirst = BitmapFactory.decodeResource(resources, R.mipmap.lvmaochong, options)
            }
        }

    }
}