package com.yxd

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yxd.knowledge.ipc.aidl.MyService
import com.yxd.knowledge.net.okhttp.baseuse.OkHttpCustomInterceptor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, getFragment(), "test")
            .commit()

        startService(Intent(this, MyService::class.java))

    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(Intent(this, MyService::class.java))
    }

    private fun getFragment(): Fragment {
        return OkHttpCustomInterceptor()
    }
}