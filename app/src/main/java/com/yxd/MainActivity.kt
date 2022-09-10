package com.yxd

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yxd.knowledge.ipc.aidl.MyService
import com.yxd.knowledge.net.okhttp.baseuse.OkHttpCustomInterceptor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val clickListener = object : View.OnClickListener
        {
            override fun onClick(v: View?) {

            }
        }
    }
}