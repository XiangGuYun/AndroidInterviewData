package com.yxd.knowledge.ipc._03_messenger.code

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import java.lang.Exception

/**
 * 服务端
 *
 * 在test进程中
 */
class MessengerService: Service() {

    /**
     * 创建Handler
     */
    val handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                1 -> {
                    // 获取信使
                    val clientSend: Messenger = msg.replyTo
                    try {
                        Log.d("YXD", "服务端收到客户端发送的消息：${msg.data.get("key")}")
                        // 向客户端发送消息
                        clientSend.send(Message.obtain().apply {
                            what = 2
                            data = Bundle().apply {
                                putString("key", "我是服务端，你好啊客户端！")
                            }
                        })
                    } catch (e : Exception){
                        Log.e("YXD", "err info is ${e.localizedMessage}")
                    }
                }
            }
        }
    }

    /**
     * 创建Messenger
     */
    private val messenger = Messenger(handler)

    override fun onBind(intent: Intent?): IBinder? {
        // 返回binder
        return messenger.binder
    }


}