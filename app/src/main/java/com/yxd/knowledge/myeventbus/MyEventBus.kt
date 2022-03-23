package com.yxd.knowledge.myeventbus

import android.os.Handler
import android.os.Looper
import android.util.Log

/**
 * https://www.bilibili.com/video/BV1vy4y1y7ZZ?from=search&seid=3454744066357529560&spm_id_from=333.337.0.0
 */
class MyEventBus {

    // DCL单例
    companion object {
        // 声明一个类实例，添加Volatile注解
        @Volatile
        private var instance: MyEventBus? = null

        fun getDefault(): MyEventBus {
            // 第一次判空
            if (instance == null) {
                // 第二次判空，在同步代码块中执行
                synchronized(MyEventBus.javaClass) {
                    if (instance == null) {
                        // 经过两次判空后才初始化
                        instance = MyEventBus()
                    }
                }
            }
            // 返回实例
            return instance!!
        }
    }

    /**
     * 注册表
     * Any：代表注册对象，例如常用的Activity或Fragment等
     * List<SubscribeMethod>：代表注册方法列表，包含了方法入参，方法名，方法注解
     */
    private val cacheMap: HashMap<Any, List<SubscribeMethod>?> = HashMap()

    /**
     * 主线程Handler
     */
    private var handler = Handler(Looper.getMainLooper())

    /**
     * 添加注册者到注册表
     * @param registrant Any
     */
    fun register(registrant: Any) {
        // 判断注册者的注册数据是否为空
        if (cacheMap[registrant] == null) {
            // 如果为空则查找下注册类下的所有满足以下条件的方法
            // 条件1：方法包含Subscribe注解
            // 条件2：方法只有一个入参，代表消息
            val list = getSubscribeMethodsReflection(registrant)
            if (!list.isNullOrEmpty()) {
                // 如果查找的方法不为空，则添加进去
                cacheMap[registrant] = list
            }
        }
    }

    /**
     * 查找注册方法（满足条件的方法）
     * 这里通过while循环来获取当前类以及上层类所有的注册方法
     * @param registrant Any
     */
    private fun getSubscribeMethodsReflection(registrant: Any): List<SubscribeMethod> {
        val list = ArrayList<SubscribeMethod>()
        // 获取类对象
        var clazz: Class<*>? = registrant.javaClass
        while (clazz != null) {
            if (clazz.name.startsWith("java.") || clazz.name.startsWith("javax.")
                || clazz.name.startsWith("android.") || clazz.name.startsWith("androidx.")
            ) {
                break
            }
            // 获取当前类包含的所有public，protected，default，private方法
            // declaredMethods不包含从父类继承来的方法
            clazz.declaredMethods.forEach { method ->
                // 查找包含Subscribe注解的方法
                method.getAnnotation(Subscribe::class.java)?.let { subscribe ->
                    // 判断方法入参是否只有一个
                    if (method.parameterTypes.size == 1) {
                        val threadMode = subscribe.threadMode
                        val subscribeMethod = SubscribeMethod(
                            method,
                            method.parameterTypes[0].name, threadMode
                        )
                        Log.d("Test", "0=====${method.parameterTypes[0]}")
                        list.add(subscribeMethod)
                    }
                }
            }
            clazz = clazz.superclass
        }
        return list
    }

    /**
     * 发送消息
     * @param message Any 消息
     */
    fun post(message: Any) {
        // 遍历注册表
        cacheMap.keys.forEach { registrant ->
            cacheMap[registrant]?.forEach { subscribeMethod ->
                // 判断消息的类型是否跟注册方法的入参类型相一致
                if (subscribeMethod.messageType == message.javaClass.name) {
                    // 调用注册者的注册方法
                    when (subscribeMethod.threadMode) {
                        ThreadMode.MAIN -> {
                            // 判断post方法是否执行在主线程
                            if(Looper.myLooper() == Looper.getMainLooper()){
                                invoke(subscribeMethod, registrant, message)
                            } else {
                                // 切到主线程执行
                                handler.post {
                                    invoke(subscribeMethod, registrant, message)
                                }
                            }
                        }
                        ThreadMode.ASYNC -> {
                            Thread{
                                invoke(subscribeMethod, registrant, message)
                            }.start()
                        }
                    }
                }
            }
        }
    }

    fun unregister(registrant: Any) {
        cacheMap[registrant] = null
    }

    private fun invoke(subscribeMethod: SubscribeMethod, registrant: Any, message: Any) {
        val method = subscribeMethod.method
        method.isAccessible = true
        // 调用该注册方法
        method.invoke(registrant, message)
    }

}