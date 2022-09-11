package com.yxd.knowledge.ipc._01_aidl.code

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.yxd.devlib.base.TestFragment

/**
 * 客户端
 *
 * 在主进程中
 */
class AidlClient : TestFragment() {

    private lateinit var conn: ServiceConnection

    override fun init(view: View, savedInstanceState: Bundle?) {

        val et1 = addEditText("请输入数字a", isNumber = true)

        val et2 = addEditText("请输入数字b", isNumber = true)

        conn = object : ServiceConnection{
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                service?.let {
                    val aidl = IMyAidlInterface.Stub.asInterface(it)
                    val a = et1.text.toString().toInt()
                    val b = et2.text.toString().toInt()
                    val result = aidl.add(a, b)
                    toast("a+b=$result")
                }
            }

            override fun onServiceDisconnected(name: ComponentName?) {

            }
        }

        addButton("请求服务端计算a+b的和"){
            if(et1.text.isEmpty() || et2.text.isEmpty()){
                return@addButton
            }
            // 启动服务端
            requireActivity().bindService(
                Intent(requireContext(), AidlService::class.java),
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