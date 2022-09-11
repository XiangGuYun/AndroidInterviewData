package com.yxd.knowledge.bitmap.code

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.LruCache
import android.view.View
import android.widget.ImageView
import com.yxd.devlib.base.TestFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * 演示使用LruCache缓存Bitmap
 */
class LruCacheFragment : TestFragment(){

    val imgUrl = "https://dss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/logo_white-d0c9fe2af5.png"

    override fun init(view: View, savedInstanceState: Bundle?) {
        val iv = addImageView(300, 300)
        addButton("测试"){
            SimpleImageLoader.getInstance().displayImage(iv, imgUrl)
        }
    }

}

class SimpleImageLoader{

    private var mLruCache:LruCache<String, Bitmap>;

    init {
        // 获取最大缓存空间
        val maxCacheSize = (Runtime.getRuntime().maxMemory() / 8).toInt();
        mLruCache = object :LruCache<String, Bitmap>(maxCacheSize){
            override fun sizeOf(key: String?, value: Bitmap?): Int {
                return value?.allocationByteCount?:0
            }
        };
    }

    /**
     * 显示图片
     * @param iv ImageView
     * @param url String
     */
    fun displayImage(iv: ImageView, url: String){
        val bitmap = getBitmapFromCache(url)
        if(bitmap != null){
            iv.setImageBitmap(bitmap)
        } else {
            downloadImage(iv, url)
        }
    }

    /**
     * 下载图片到本地
     * @param iv ImageView
     * @param url String
     */
    private fun downloadImage(iv: ImageView, url: String) {
        val req = Request.Builder().url(url).build()
        OkHttpClient.Builder().build().newCall(req).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {
                Log.d("YXD", "下载失败："+e.localizedMessage)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d("YXD", "下载成功")
                response.body?.let{
                    val stream = it.byteStream()
                    val filePath = Environment.getExternalStorageDirectory().toString()+"/test.png"
                    val fos = FileOutputStream(File(filePath))
                    fos.write(stream.readBytes())
                    fos.flush()
                    fos.close()
                    stream.close()

                    iv.setImageBitmap(BitmapFactory.decodeFile(filePath))

                }
            }

        })
    }

    /**
     * 获取图片缓存
     * @param url String
     * @return Bitmap?
     */
    private fun getBitmapFromCache(url: String): Bitmap? {
        return mLruCache.get(url)
    }

    /**
     * 放入缓存
     * @param url String
     * @param bitmap Bitmap?
     */
    private fun putBitmapToCache(url: String, bitmap: Bitmap?){
        if(bitmap != null){
            mLruCache.put(url, bitmap)
        }
    }

    private constructor()

    companion object{

        private var sLoader:SimpleImageLoader? = null

        fun getInstance() : SimpleImageLoader{
            if(sLoader == null){
                sLoader = SimpleImageLoader();
            }
            return sLoader!!
        }
    }



}