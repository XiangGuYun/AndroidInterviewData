package com.yxd.knowledge.bitmap.myglide

import android.content.Context

object MyGlide {

    fun with(context: Context): BitmapRequest {
        return BitmapRequest(context)
    }

}