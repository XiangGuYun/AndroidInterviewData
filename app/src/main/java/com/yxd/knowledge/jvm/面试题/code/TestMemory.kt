package com.yxd.knowledge.jvm.面试题.code

object TestMemory {
    @JvmStatic
    fun main(args: Array<String>) {
        // 返回Java虚拟机中可用内存的数量。调用gc方法可能会增加freemmemory返回的值。
        println("freeMemory is "+Runtime.getRuntime().freeMemory()/1024f/1024f/1024f)
        // 返回Java虚拟机中的内存总量。此方法返回的值可能会随时间而变化，这取决于宿主环境。 请注意，保存任何给定类型的对象所需的内存量可能与实现有关。
        println("totalMemory is "+Runtime.getRuntime().totalMemory()/1024f/1024f/1024f)
        // 返回Java虚拟机将尝试使用的最大内存量。如果没有固有的限制，则值Long.MAX_VALUE将被返回。
        println("maxMemory is "+Runtime.getRuntime().maxMemory()/1024f/1024f/1024f)
    }
}