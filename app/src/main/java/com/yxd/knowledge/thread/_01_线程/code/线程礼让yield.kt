package com.yxd.knowledge.thread._01_线程.code

/**
 * 礼让线程，让当前正在执行的线程暂停，但是不阻塞
 * 将线程从运行状态转为就绪状态
 * 让CPU重新调度，礼让不一定成功！看CPU心情
 *
 * 理解：相当于让出去一部分被CPU调度到的概率，但是这个概率大小不一定
 */
object 线程礼让yield {
    @JvmStatic
    fun main(args: Array<String>) {

        Thread(TestYield(), "A").start()

        Thread(TestYield(), "B").start()

    }

    class TestYield :Runnable{
        override fun run() {
            println("线程${Thread.currentThread().name} 开始 执行")
            // 线程礼让
            Thread.yield()
            println("线程${Thread.currentThread().name} 停止 执行")
        }

    }

}