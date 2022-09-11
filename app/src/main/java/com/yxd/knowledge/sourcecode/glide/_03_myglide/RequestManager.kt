package com.yxd.knowledge.sourcecode.glide._03_myglide

import java.util.concurrent.LinkedBlockingQueue

class RequestManager private constructor() {

    companion object {
        @Volatile
        private var requestManager: RequestManager? = null
        fun getInstance(): RequestManager {
            if (requestManager == null) {
                synchronized(RequestManager.javaClass) {
                    if (requestManager == null) {
                        requestManager = RequestManager()
                    }
                }
            }
            return requestManager!!
        }
    }

    private val queue = LinkedBlockingQueue<BitmapRequest>()

    fun addBimapRequest(request: BitmapRequest) {
        if (!queue.contains(request))
            queue.add(request)
    }

    private var bitmapDispatchers: Array<BitmapDispatcher>? = null

    init {
        stop()
        createAndStartAllDispatchers()
    }

    private fun createAndStartAllDispatchers() {
        bitmapDispatchers = Array(10) {
            val dispatcher = BitmapDispatcher(queue)
            dispatcher.start()
            dispatcher
        }
    }

    private fun stop() {
        bitmapDispatchers?.forEach {
            if (!it.isInterrupted) {
                it.interrupt()
            }
        }
    }

}