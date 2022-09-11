package com.yxd.knowledge.sourcecode.glide._03_myglide

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.yxd.devlib.utils.MD5Utils
import java.lang.ref.SoftReference

class BitmapRequest(val context: Context) {

    var url: String? = null

    var image: ImageView? = null

    private var urlMd5: String? = null

    var loadingResId: Int = 0

    var requestListener: ((Bitmap) -> Unit)? = null

    fun placeHolder(resId: Int): BitmapRequest {
        this.loadingResId = resId
        return this
    }

    fun listener(requestListener: ((Bitmap) -> Unit)?): BitmapRequest {
        this.requestListener = requestListener
        return this
    }

    fun load(url: String): BitmapRequest {
        this.url = url
        this.urlMd5 = MD5Utils.stringToMD5(url)
        return this
    }

    fun into(iv:ImageView){
        this.image = SoftReference(iv).get()
        this.image?.tag = urlMd5
        if(loadingResId != 0)
            this.image?.setImageResource(loadingResId)
        RequestManager.getInstance().addBimapRequest(this)
    }
}