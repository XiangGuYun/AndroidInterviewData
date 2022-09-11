package 数据结构和算法.datastructure1

import java.lang.RuntimeException

object 队列 {
    @JvmStatic
    fun main(args: Array<String>) {

    }
}

class MyQueue {
    private var elements = IntArray(0)

    fun add(element: Int) {
        val newArr = IntArray(elements.size + 1)
        elements.forEachIndexed { index, _ ->
            newArr[index] = elements[index]
        }
        newArr[newArr.lastIndex] = element
        elements = newArr
    }

    /**
     * 取出队首元素
     */
    fun poll(): Int {
        if (elements.isEmpty()) {
            throw RuntimeException("队列为空")
        }
        val element = elements[0]
        val tempArray = IntArray(elements.size - 1)
        for (i in tempArray.indices) {
            tempArray[i] = elements[i + 1]
        }
        elements = tempArray
        return element
    }
}