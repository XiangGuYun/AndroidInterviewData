package com.yxd.knowledge.sourcecode.okhttp.baseuse

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import com.yxd.devlib.base.TestFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * 演示使用自定义拦截器来：
 * 1.打印请求信息
 * 2.打印响应信息
 * 3.打印请求耗时
 */
class OkHttpCustomInterceptor: TestFragment(){

    /**
     * 自定义拦截器
     *
     * 1.继承okhttp3.Interceptor接口
     * 2.实现intercept方法
     */
    class CustomInterceptor : Interceptor {

        companion object {
            private const val TAG = "CustomInterceptor"
        }

        override fun intercept(chain: Interceptor.Chain): Response {
            // 获取拦截器链上的请求对象
            val request = chain.request()

            Log.d(TAG, "request is $request")
            val time1 = System.currentTimeMillis()

            // 获取响应对象
            val response = chain.proceed(request)
            Log.d(TAG, "response is $response")

            val time2 = System.currentTimeMillis()
            Log.d(TAG, "请求耗时：${time2 - time1}ms")

            return response
        }
    }

    override fun init(view: View, savedInstanceState: Bundle?) {
        val client = OkHttpClient.Builder()
            // 添加自定义拦截器
            //==========================================================================================
            // 打印结果
            // D/CustomInterceptor: request is Request{method=GET, url=https://www.baidu.com/}
            // D/CustomInterceptor: response is Response{protocol=http/1.1, code=200, message=OK, url=https://www.baidu.com/}
            // D/CustomInterceptor: 请求耗时：480ms
            //==========================================================================================
            .addInterceptor(CustomInterceptor())
            .build()

        val request = Request.Builder()
            .get()
            .url("https://www.baidu.com")
            .build()

        val tvInfo = addTextView()
        val handler = Handler(Looper.getMainLooper())

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