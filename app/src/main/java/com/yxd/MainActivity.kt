package com.yxd

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.yxd.knowledge.net.okhttp.baseuse.OkHttpCustomInterceptor

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .add(R.id.flContainer, getFragment(), "test")
            .commit()
    }

    private fun getFragment(): Fragment {
        return OkHttpCustomInterceptor()
    }
}