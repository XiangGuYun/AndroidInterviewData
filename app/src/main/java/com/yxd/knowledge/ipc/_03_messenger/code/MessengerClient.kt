package com.yxd.knowledge.ipc._03_messenger.code

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import android.view.View
import com.yxd.devlib.base.TestFragment

/**
 * 客户端
 */
class MessengerClient : TestFragment(){
    private lateinit var conn: ServiceConnection

    /**
     * 创建Handler
     */
    val handler = @SuppressLint("HandlerLeak")
    object : Handler(){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                2 -> {
                   Log.d("YXD", "客户端收到服务端的回信：${msg.data.get("key")}")
                }
            }
        }
    }

    override fun init(view: View, savedInstanceState: Bundle?) {

        val messengerClient = Messenger(handler);

        conn = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                service?.let {
                    // 获取服务端的messenger
                    val messengerService = Messenger(it)
                    // 向服务端发送消息
                    messengerService.send(Message.obtain().apply {
                        // 用于接受服务端回复的信封
                        replyTo = messengerClient
                        what = 1
                        data = Bundle().apply {
                            putString("key", "你好啊服务端，我是客户端")
                        }
                    })
                }
            }

            override fun onServiceDisconnected(name: ComponentName?) {

            }
        }

        addButton("测试"){
            // 启动服务端
            requireActivity().bindService(
                Intent(requireContext(), MessengerService::class.java),
                conn,
                Context.BIND_AUTO_CREATE
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        // 解绑服务端
        requireActivity().unbindService(conn)
    }
}