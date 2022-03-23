package com.yxd.devlib.ndk

import android.os.Bundle
import android.view.View
import com.yxd.devlib.base.TestFragment

class NativeFragment: TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        logt(stringFromJNI())

    }

    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'anative' library on application startup.
        init {
            System.loadLibrary("anative")
        }
    }
}