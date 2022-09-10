package com.yxd.knowledge.component.activity.codes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yxd.R

class BActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bactivity)
        Log.d("Test", " BActivity onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Test", " BActivity onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Test", " BActivity onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Test", " BActivity onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test", " BActivity onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Test", " BActivity onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Test", " BActivity onRestart")
    }


}