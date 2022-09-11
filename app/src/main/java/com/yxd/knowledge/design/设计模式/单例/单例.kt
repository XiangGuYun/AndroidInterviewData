package com.yxd.knowledge.design.设计模式.单例

object 测试DCL单例的安全性 {

    @JvmStatic
    fun main(args: Array<String>) {
        for (i in 0 until 30) {
            Thread {
                println(
                    "DCL单例的hashcode：" + DclSingleInstance.getInstance().hashCode()
                    + "  懒汉单例的hashcode：" + LazySingleInstance.getInstance().hashCode()
                    + "  饿汉单例的hashcode：" + HungarySingleInstance.getInstance().hashCode()
                )
            }.start()
        }
    }

}