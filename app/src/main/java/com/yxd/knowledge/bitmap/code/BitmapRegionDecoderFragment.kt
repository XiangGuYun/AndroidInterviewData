package com.yxd.knowledge.bitmap.code

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import com.yxd.devlib.base.TestFragment

/**
 * 演示BitmapRegionDecoder加载世界地图的一部分
 */
class BitmapRegionDecoderFragment:TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        val screenWidth = requireContext().resources.displayMetrics.widthPixels;
        val iv = addImageView(screenWidth, screenWidth)
        iv.setImageBitmap(getWorldMapBitmap(screenWidth))
    }

    private fun getWorldMapBitmap(size:Int): Bitmap? {
        val inputStream = requireContext().assets.open("world_map.jpeg")
        val decoder = BitmapRegionDecoder.newInstance(inputStream, false)
        val options = BitmapFactory.Options();
        return decoder?.decodeRegion(Rect(0, 0, size, size), options)
    }

}