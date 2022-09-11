package com.yxd.knowledge.sourcecode.okhttp.net.okhttp.myokhttp

interface IHttpRequest {

    fun setUrl(url: String)

    fun setData(data: ByteArray)

    fun setListener(callback: Callback)

    fun execute()

}