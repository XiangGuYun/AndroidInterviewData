package com.yxd.knowledge.component.service.code

import android.content.ComponentName
import android.content.Context.BIND_AUTO_CREATE
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import com.yxd.devlib.base.TestFragment

/**
 * 演示Service的启动和生命周期
 */
class StartServiceFragment : TestFragment() {

    override fun init(view: View, savedInstanceState: Bundle?) {
        addButton("Start方式启动"){
            requireActivity().startService(Intent(requireContext(), TestService::class.java))
        }

        val conn = object :ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d("Test", "onServiceConnected")
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("Test", "onServiceDisconnected")
            }
        }

        addButton("Bind方式启动"){
            requireActivity().bindService(
                Intent(
                    requireContext(),
                    TestService::class.java),
                conn,
                BIND_AUTO_CREATE
            )
        }

        addButton("杀死Start方式启动的服务"){
            // stopService不能停止绑定方式启动的服务
            requireActivity().stopService(Intent(requireContext(), TestService::class.java))
        }

        addButton("取消绑定已绑定的服务"){
            requireActivity().unbindService(conn)
        }
    }

}