package com.yxd.knowledge.bitmap.myglide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.LinkedBlockingQueue

/**
 *
 * @property requestQueue BlockingDeque<BitmapRequest>
 *  请求队列
 *  BlockingQueue：一种额外支持阻塞操作的Deque，在获取元素时等待Deque变为非空，
 *  在存储元素时等待Deque中的空间变为可用。
 * @constructor
 */
class BitmapDispatcher(private val requestQueue: LinkedBlockingQueue<BitmapRequest>) : Thread() {

    private val mHandler = Handler(Looper.getMainLooper())

    override fun run() {
        while (!interrupted()) {
            // 从队列中获取请求
            val bitmapRequest = requestQueue.take()
            // 设置占位符
            showLoadingImage(bitmapRequest)
            // 去网络上获取具体的图片
            bitmapRequest.url?.let { url->
                // 显示到ImageView上
                show(bitmapRequest, getBitmap(url))
            }
        }
    }

    /**
     * 获取Bitmap
     * @param url String
     * @return Bitmap?
     */
    private fun getBitmap(url: String): Bitmap? {
        // todo 缓存处理
        return url2Bitmap(url)
    }

    private fun show(bitmapRequest: BitmapRequest?, bitmap: Bitmap?) {
        val imageView = bitmapRequest?.image
        imageView ?: return
        bitmap ?: return
        mHandler.post {
            imageView.setImageBitmap(bitmap)
            bitmapRequest.requestListener?.invoke(bitmap)
        }
    }

    private fun url2Bitmap(url: String): Bitmap? {
        val inputStream = (URL(url).openConnection() as HttpURLConnection).inputStream
        val bitmap = BitmapFactory.decodeStream(inputStream)
        inputStream.close()
        return bitmap
    }

    private fun showLoadingImage(bitmapRequest: BitmapRequest?) {
        bitmapRequest ?: return
        if (bitmapRequest.loadingResId > 0) {
            val imageView = bitmapRequest.image ?: return
            mHandler.post {
                // 保证UI操作在主线程中
                imageView.setImageResource(bitmapRequest.loadingResId)
            }
        }
    }
}