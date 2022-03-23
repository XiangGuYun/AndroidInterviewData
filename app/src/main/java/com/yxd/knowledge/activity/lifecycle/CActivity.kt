package com.yxd.knowledge.activity.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yxd.R

class CActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cactivity)
        Log.d("Test", " CActivity onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Test", " CActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Test", " CActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Test", " CActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test", " CActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Test", " CActivity onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Test", " CActivity onRestart")
    }


}