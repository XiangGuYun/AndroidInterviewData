package com.yxd.knowledge.sourcecode.glide.code

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yxd.R

class LifecycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
    }

    override fun onStart() {
        super.onStart()
        Log.d("YXD", "LifecycleActivity onStart");
    }

    override fun onStop() {
        super.onStop()
        Log.d("YXD", "LifecycleActivity onStop");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("YXD", "LifecycleActivity onDestroy");
    }
}