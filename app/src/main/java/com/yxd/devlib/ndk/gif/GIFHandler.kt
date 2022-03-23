package com.yxd.devlib.ndk.gif

class GIFHandler(gifHandler: Long) {
    companion object {

        external fun nativeGetWidth(gifPoint: Long): Int
        external fun nativeGetHeight(gifPoint: Long): Int
        external fun nativeLoad(path: String): Long

        var gifHandler = 0L

        fun load(path:String): GIFHandler {
            gifHandler = nativeLoad(path)
            val handler = GIFHandler(gifHandler)
            return handler
        }

    }

    fun getWidth(): Int {
        return nativeGetWidth(gifHandler)
    }

    fun getHeight(): Int {
        return nativeGetHeight(gifHandler)
    }

}