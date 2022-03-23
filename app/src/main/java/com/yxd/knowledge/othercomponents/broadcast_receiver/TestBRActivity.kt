package com.yxd.knowledge.othercomponents.broadcast_receiver

import android.content.ComponentName
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.yxd.R

class TestBRActivity : AppCompatActivity() {

    lateinit var broadcastReceiver: DynamicBroadcastR

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ceshiguangbo)

        broadcastReceiver = DynamicBroadcastR()
        val filter = IntentFilter()
        filter.addAction("动态注册")
        registerReceiver(broadcastReceiver, filter)

        findViewById<View>(R.id.btnTest).setOnClickListener {
           sendBroadcast(Intent().apply {
               action = "动态注册"
               putExtra("data", "xxxxxx数据")
           })
        }

        findViewById<View>(R.id.btnTest1).setOnClickListener {
            // 注意android8.0以上必须指定全类名才能发送广播给静态广播接收器
            sendBroadcast(Intent().apply {
                action = "static"
                component = ComponentName("com.yxd.devlib.knowledge.broadcast_receiver","StaticBroadcastR")
                putExtra("data", "xxxxxx数据")
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

}