package com.yxd.knowledge.component.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class TestService : Service(){

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("Test", "onBind")
        return null
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("Test", "onUnbind")
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        Log.d("Test", "onRebind")
        super.onRebind(intent)
    }

    override fun onCreate() {
        Log.d("Test", "onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("Test", "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("Test", "onDestroy")
        super.onDestroy()
    }

}