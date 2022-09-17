package com.yxd.knowledge.sourcecode.glide.code

import android.os.Bundle
import android.util.Log
import android.view.View
import com.yxd.devlib.base.TestFragment

class LifeCycleFragment : TestFragment(){
    override fun init(view: View, savedInstanceState: Bundle?) {

    }

    override fun onStart() {
        super.onStart()
        Log.d("YXD", "LifeCycleFragment onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.d("YXD", "LifeCycleFragment onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("YXD", "LifeCycleFragment onDestroy")
    }

}