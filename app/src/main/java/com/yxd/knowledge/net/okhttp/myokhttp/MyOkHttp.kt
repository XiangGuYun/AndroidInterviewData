package com.yxd.knowledge.net.okhttp.myokhttp

import com.google.gson.Gson
import java.nio.charset.Charset

object MyOkHttp {

    fun <T, R> sendJsonRequest(
        url: String,
        requestData: T?,
        response: Class<R>,
        onSuccess: (R) -> Unit
    ) {
        val request = JsonHttpRequest()
        val callback = JsonCallbackListener<R>(response, onSuccess)
        ThreadPoolManager.getInstance().addTask {
            request.setUrl(url)
            request.setListener(callback)
            val jsonString = Gson().toJson(requestData)
            request.setData(jsonString.toByteArray(Charset.forName("UTF-8")))
            request.execute()
        }
    }

}