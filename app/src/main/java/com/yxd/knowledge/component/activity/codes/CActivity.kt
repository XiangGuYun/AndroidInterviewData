package com.yxd.knowledge.component.activity.codes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.yxd.R

class C : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cactivity)
        Log.d("Test", " C onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Test", " C onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Test", " C onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Test", " C onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Test", " C onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Test", " C onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Test", " C onRestart")
    }


}