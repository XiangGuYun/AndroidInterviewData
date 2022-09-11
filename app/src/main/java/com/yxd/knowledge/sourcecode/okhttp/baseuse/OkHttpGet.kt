package com.yxd.knowledge.sourcecode.okhttp.baseuse

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import com.yxd.devlib.base.TestFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * 演示使用OkHttp执行Get请求
 */
class OkHttpGet : TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        // ①：构建OkHttpClient
        val client = OkHttpClient.Builder().build()

        // ②：构建Request
        val request = Request.Builder()
            .get()
            .url("https://www.baidu.com")
            .build()

        // ③：通过client和request获得一个RealCall对象
        val call = client.newCall(request)

        val tvInfo = addTextView()
        val handler = Handler(Looper.getMainLooper())

        addButton("同步请求"){
            Thread{
                // ④：调用execute执行同步请求
                val response = call.execute()
                if(response.isSuccessful){
                    val string = response.body?.string()
                    handler.post {
                        tvInfo.text = string
                    }
                } else {
                    logt(response.message)
                }
            }.start()
        }

        addButton("异步请求"){
            // ④：调用enqueue执行异步请求
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    logt(e.localizedMessage)
                }

                override fun onResponse(call: Call, response: Response) {
                    val string = response.body?.string()
                    handler.post {
                        tvInfo.text = string
                    }
                }
            })
        }
    }
}