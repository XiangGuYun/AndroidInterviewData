package com.yxd.knowledge.sourcecode.glide._03_myglide

import android.content.Context

object MyGlide {

    fun with(context: Context): BitmapRequest {
        return BitmapRequest(context)
    }

}