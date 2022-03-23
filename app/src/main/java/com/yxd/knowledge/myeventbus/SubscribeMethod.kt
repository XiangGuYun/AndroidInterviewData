package com.yxd.knowledge.myeventbus

import java.lang.reflect.Method

/**
 * 注册的方法信息
 * @property method Method 方法对象
 * @property messageType String 方法入参类型，全类名
 * @property threadMode ThreadMode 线程模式
 * @constructor
 */
data class SubscribeMethod(
    val method: Method,
    val messageType: String,
    val threadMode: ThreadMode
)