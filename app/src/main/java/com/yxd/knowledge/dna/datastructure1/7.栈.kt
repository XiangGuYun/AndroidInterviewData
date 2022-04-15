package 数据结构和算法.datastructure1

import java.lang.RuntimeException

object 栈 {
    @JvmStatic
    fun main(args: Array<String>) {
        val myStack = MyStack()
        myStack.push(1)
        myStack.push(2)
        myStack.push(3)
        println(myStack.pop())
        println(myStack.pop())
        println(myStack.pop())
    }
}

class MyStack {

    private var elements = IntArray(0)

    /**
     * 压入元素
     */
    fun push(element: Int) {
        val newArr = IntArray(elements.size + 1)
        elements.forEachIndexed { index, _ ->
            newArr[index] = elements[index]
        }
        newArr[newArr.lastIndex] = element
        elements = newArr
    }

    /**
     * 取出栈顶元素
     */
    fun pop(): Int {
        if (elements.isEmpty()) {
            throw RuntimeException("栈空")
        }
        // 取出
        val elementLast = elements[elements.size - 1]
        var tempArray = IntArray(elements.size - 1)
        for (i in tempArray.indices) {
            tempArray[i] = elements[i]
        }
        elements = tempArray
        return elementLast
    }

    /**
     * 查看栈顶元素
     */
    fun peek(): Int {
        return elements[elements.size - 1]
    }

    fun isEmpty(): Boolean{
        return elements.isEmpty()
    }
}