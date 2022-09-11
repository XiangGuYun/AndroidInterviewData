package com.yxd.knowledge.sourcecode.okhttp.net.okhttp.myokhttp

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPoolManager private constructor() {

    companion object {
        @Volatile
        private var instance: ThreadPoolManager? = null

        fun getInstance(): ThreadPoolManager {
            if (instance == null) {
                synchronized(ThreadPoolManager.javaClass) {
                    if (instance == null) {
                        instance = ThreadPoolManager()
                    }
                }
            }
            return instance!!
        }
    }

    private val queue = LinkedBlockingQueue<Runnable>()

    fun addTask(runnable: Runnable?){
        queue.put(runnable)
    }

    private val threadPoolExecutor = ThreadPoolExecutor(
        3, 10, 15, TimeUnit.SECONDS,
        ArrayBlockingQueue<Runnable>(4)
    ) { r, _ -> addTask(r) }

    private val coreRunnable = {
        while (true){
            val runnable = queue.take()
            threadPoolExecutor.execute(runnable)
        }
    }

    init {
        threadPoolExecutor.execute(coreRunnable)
    }

}