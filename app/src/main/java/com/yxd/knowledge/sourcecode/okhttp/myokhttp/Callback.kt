package com.yxd.knowledge.sourcecode.okhttp.net.okhttp.myokhttp

import java.io.InputStream

interface Callback {

    fun onSuccess(inputStream: InputStream)
}