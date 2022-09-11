package com.yxd.knowledge.sourcecode.okhttp.net.okhttp.myokhttp

import android.os.Bundle
import android.util.Log
import android.view.View
import com.yxd.devlib.base.TestFragment
import androidx.annotation.Keep


class MyOkHttpFragment : TestFragment() {

    override fun init(view: View, savedInstanceState: Bundle?) {
        addButton("测试") {
            MyOkHttp.sendJsonRequest(
                "http://binguoai.com/api/shop/queryDeviceServiceReleaseFilesInfo",
                ReqBody(
                    deviceId = "202011090059065038545886339249615",
                    serviceKey = "RK3288"
                ),
                ResBody::class.java
            ){
                Log.d("Test", "请求成功！${it}")
            }
        }
    }

    @Keep
    data class ReqBody(
        var deviceId: String? = null,
        var serviceKey: String? = null
    )

    @Keep
    data class ResBody(
        var code: Int? = null,
        var `data`: List<Data?>? = null,
        var message: String? = null
    ) {
        @Keep
        data class Data(
            var createDtme: Long? = null,
            var createUserCpuMac: Any? = null,
            var createUserId: Int? = null,
            var createUserName: Any? = null,
            var dataSign: Int? = null,
            var dataStatus: Int? = null,
            var delFilesOk: Any? = null,
            var filePath: String? = null,
            var fileServerType: Int? = null,
            var fileType: String? = null,
            var id: Int? = null,
            var lastUpdateUserId: Int? = null,
            var lastUpdtme: Long? = null,
            var localPath: String? = null,
            var name: String? = null,
            var processNameClosed: Any? = null,
            var releaseDtme: Long? = null,
            var releaseNote: String? = null,
            var remark: String? = null,
            var serviceKey: String? = null,
            var serviceNameOk: Any? = null,
            var servicePortOk: Any? = null,
            var startApps: Any? = null,
            var tenantNumId: Int? = null,
            var version: String? = null
        )
    }
}