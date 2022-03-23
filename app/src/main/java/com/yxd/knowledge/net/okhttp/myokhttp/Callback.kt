package com.yxd.knowledge.net.okhttp.myokhttp

import java.io.InputStream

interface Callback {

    fun onSuccess(inputStream: InputStream)
}