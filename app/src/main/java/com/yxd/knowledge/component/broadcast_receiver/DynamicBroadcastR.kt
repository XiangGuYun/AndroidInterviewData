package com.yxd.knowledge.component.broadcast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * 动态注册的广播
 */
class DynamicBroadcastR : BroadcastReceiver(){
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?:return
        // 获取到收到的广播的名称
        val action = intent.action
        Log.e("Test", "DynamicBroadcastR：收到的广播的Action是：$action，" +
                      "收到的数据是${intent.getStringExtra("data")}");
    }
}