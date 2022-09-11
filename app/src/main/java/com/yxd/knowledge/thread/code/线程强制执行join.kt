package com.yxd.knowledge.thread.code

/**
 * 调用join方法的线程，会霸占CPU资源，造成其它线程阻塞
 *
 * 与yield相反，表示当前线程要抢占CPU资源
 */
object 线程强制执行join {
    @JvmStatic
    fun main(args: Array<String>) {
        val testJoin = TestJoin()
        val thread = Thread(testJoin)
        thread.start()

        (0 until 100).forEach {
            if(it == 10){
                thread.join()
            }
            println("主线程来了 $it")
        }
    }

    class TestJoin : Runnable{

        override fun run() {
            (0 until 100).forEach {
                println("线程VIP来了 $it")
            }
        }

    }
}