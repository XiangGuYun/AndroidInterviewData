package com.yxd.knowledge.net.okhttp.baseuse

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

class OkHttpGet : TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {
        val client = OkHttpClient.Builder().build()

        val request = Request.Builder()
            .get()
            .url("https://www.baidu.com")
            .build()

        val tvInfo = addTextView()
        val handler = Handler(Looper.getMainLooper())

        addButton("同步请求"){
            Thread{
                val response = client.newCall(request).execute()
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
            client.newCall(request).enqueue(object : Callback {
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