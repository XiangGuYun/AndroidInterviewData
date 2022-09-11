package com.yxd.knowledge.sourcecode.okhttp.net.okhttp.myokhttp

import com.google.gson.Gson
import java.io.InputStream

class JsonCallbackListener<T>(
    private val responseClass: Class<T>,
    private val onSuccess: (T) -> Unit
) : Callback {

    override fun onSuccess(inputStream: InputStream) {
        onSuccess.invoke(Gson().fromJson(getContent(inputStream), responseClass))
    }

    private fun getContent(inputStream: InputStream): String {
        val br = inputStream.buffered().bufferedReader()
        val content = br.readText()
        br.close()
        return content
    }
}