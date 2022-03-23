package com.yxd.knowledge.net.okhttp.myokhttp

import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

class JsonHttpRequest : IHttpRequest {

    private var callback: Callback? = null
    private var data: ByteArray? = null
    private var url: String? = null

    override fun setUrl(url: String) {
        this.url = url
    }

    override fun setData(data: ByteArray) {
        this.data = data
    }

    override fun setListener(callback: Callback) {
        this.callback = callback
    }

    override fun execute() {
        (URL(url).openConnection() as HttpURLConnection).apply {
            connectTimeout = 6000
            useCaches = false
            instanceFollowRedirects = true
            readTimeout = 3000
            doInput = true
            doOutput = true
            requestMethod = "POST"
            setRequestProperty("Content-Type", "application/json;charset=UTF-8")
            connect()
            outputStream.buffered().apply {
                write(data)
                flush()
                close()
            }
            if (responseCode == HttpURLConnection.HTTP_OK) {
                callback?.onSuccess(inputStream)
            } else {
                throw RuntimeException("请求失败！")
            }
            disconnect()
        }
    }
}