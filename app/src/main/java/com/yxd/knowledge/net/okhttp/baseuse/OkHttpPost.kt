package com.yxd.knowledge.net.okhttp.baseuse

import android.os.Bundle
import android.view.View
import com.yxd.devlib.base.TestFragment
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

/**
 * 演示OKHTTP的POST请求
 */
class OkHttpPost: TestFragment() {
    override fun init(view: View, savedInstanceState: Bundle?) {

        //==========================================================================================
        // 构建一个表单体FormBody，用来提交表单
        //==========================================================================================
        val formBody = FormBody.Builder()
            .add("传参名", "传参值")
            .build()

        //==========================================================================================
        // 构建一个请求体RequestBody，用来提交JSON字符串
        //==========================================================================================
        val json = """
            {
                "deviceId": "202011090059065038545886339249615",
                "serviceKey":"RK3288"
            }
        """.trimIndent()
        val body = json.toRequestBody("application/json charset=utf-8".toMediaTypeOrNull())

        //==========================================================================================
        // 构建一个请求体RequestBody，用来上传文件
        //==========================================================================================
        val body1 = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("file", "fileName",
                File("filePath").asRequestBody("multipart/form-data".toMediaTypeOrNull()))
            .build()

        Request.Builder()
            .post(formBody)
            .url("https://www.baidu.com")
            .build()

        Request.Builder()
            .post(body)
            .url("https://www.baidu.com")
            .build()

        Request.Builder()
            .post(body1)
            .url("https://www.baidu.com")
            .build()

    }
}