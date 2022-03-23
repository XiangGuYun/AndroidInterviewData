package com.yxd.knowledge.thread

object WaitNotifyTest {
    @JvmStatic
    fun main(args: Array<String>) {
        //==========================================================================================
        // 创建一个对象，用来充当锁
        //==========================================================================================
        val obj = Object()

        //==========================================================================================
        // 创建线程A，通过obj.wait()进入阻塞状态
        //==========================================================================================

        Thread({
            println(Thread.currentThread().name + "开启")
            // 必须在同步代码块中执行等待，且必须和notify是同一把锁，否则会抛异常
            synchronized(obj) {
                println(Thread.currentThread().name + "等待")
                // 进入等待状态，同时释放锁，让其它线程可以获取锁并唤醒这个线程
                obj.wait()
            }
            println(Thread.currentThread().name + "结束")
        }, "线程A").start()

        //==========================================================================================
        // 创建线程B，也通过obj.wait()进入阻塞状态
        //==========================================================================================

        Thread({
            println(Thread.currentThread().name + "开启")
            synchronized(obj) {
                println(Thread.currentThread().name + "等待")
                obj.wait()
            }
            println(Thread.currentThread().name + "结束")
        }, "线程B").start()

        //==========================================================================================
        // 在延时2秒后，唤醒线程
        // ---- obj.notify()：随机唤醒一个等待队列线程
        // ---- obj.notifyAll()：唤醒所有处于等待队列的线程
        //==========================================================================================

        Thread.sleep(2000)
        // 必须在同步代码块中执行唤醒，且必须和wait是同一把锁，否则会抛异常
        synchronized(obj) {
            // 随机唤醒一个处于等待队列的线程
//            obj.notify()
            // 唤醒所有处于等待队列的线程
            obj.notifyAll()
        }
    }
}