package 数据结构和算法.datastructure1

import java.lang.IndexOutOfBoundsException

object 面向对象的数组 {
    @JvmStatic
    fun main(args: Array<String>) {
        val myArray = MyArray()
        println(myArray.size())
        myArray.add(1)
        println("添加后的数组长度：${myArray.size()}")
        println("添加后的结果：$myArray")
        myArray.removeAt(0)
        println("删除后的结果：$myArray")

        println("测试在数组的指定位置插入元素")
        myArray.add(1)
        myArray.add(2)
        myArray.add(3)
        println("插入前的结果是：$myArray")
        myArray.add(1, 99)
        println("插入后的结果是：$myArray")

        println("测试同时添加多个元素")
        myArray.addAll(arrayOf(-1, -2, -3))
        println("添加多个后的结果是：$myArray")

        println("测试在指定位置同时添加多个元素")
        myArray.addAll(1, arrayOf(-9, -8, -7))
        println("在指定位置添加多个后的结果是：$myArray")

        println("测试获取元素下标：${myArray.indexedOf(-9)}")

        myArray.set(0, 100)
        println("替换后的结果是：${myArray}")
    }
}

class MyArray {
    // 用于存储数据的数组
    private var element: IntArray = IntArray(0)

    /**
     * 获取数组长度
     */
    fun size(): Int {
        return element.size
    }

    /**
     * 添加一个新元素
     */
    fun add(i: Int) {
        // 创建一个新的数组
        val newArr = IntArray(element.size + 1)
        element.forEachIndexed { index, _ ->
            newArr[index] = element[index]
        }
        newArr[newArr.lastIndex] = i
        element = newArr
    }

    /**
     * 添加一个元素到指定位置
     */
    fun add(position: Int, i: Int) {
        if (position < 0 || position > element.size) {
            throw IndexOutOfBoundsException("数组越界")
        }
        val newArr = IntArray(element.size + 1)
        newArr.forEachIndexed { index, _ ->
            when {
                index == position -> {
                    newArr[index] = i
                }
                index > position -> {
                    newArr[index] = element[index - 1]
                }
                else -> {
                    newArr[index] = element[index]
                }
            }
        }
        element = newArr
    }

    /**
     * 添加多个元素
     */
    fun addAll(numArr: Array<Int>) {
        // 创建一个新的数组
        val newArr = IntArray(element.size + numArr.size)
        newArr.forEachIndexed { index, _ ->
            if (index <= element.size - 1) {
                newArr[index] = element[index]
            } else {
                newArr[index] = numArr[index - element.size]
            }
        }
        element = newArr
    }

    /**
     * 在指定位置插入多个元素
     */
    fun addAll(position: Int, numArray: Array<Int>) {
        if (position < 0 || position > element.size) {
            throw IndexOutOfBoundsException("数组越界")
        }
        val newArr = IntArray(element.size + numArray.size)
        newArr.forEachIndexed { index, _ ->
            when {
                index in position until position + numArray.size -> {
                    newArr[index] = numArray[index - position]
                }
                index > position + numArray.size -> {
                    newArr[index] = element[index - numArray.size]
                }
                else -> {
                    newArr[index] = element[index]
                }
            }
        }
        element = newArr
    }

    /**
     * 删除指定位置的元素
     */
    fun removeAt(i: Int): Boolean {
        if (i < 0 || i > element.size - 1) {
            return false
        }
        val newArr = IntArray(element.size - 1)
        newArr.forEachIndexed { index, _ ->
            if (index >= i) {
                newArr[index] = element[index + 1]
            } else {
                newArr[index] = element[index]
            }
        }
        element = newArr
        return true
    }

    /**
     * 删除指定元素
     */
    fun remove(num: Int): Boolean {
        val index = indexedOf(num)
        return if (index == -1) {
            false
        } else {
            removeAt(index)
            true
        }
    }

    /**
     * 清空所有元素
     */
    fun clear() {
        element = IntArray(0)
    }

    /**
     * 获取指定位置的元素
     */
    fun get(i: Int): Int {
        if (i !in element.indices) {
            throw IndexOutOfBoundsException("数组越界$i")
        }
        return element[i]
    }

    /**
     * 替换指定位置的元素
     */
    fun set(i: Int, newNum: Int) {
        if (i !in element.indices) {
            throw IndexOutOfBoundsException("数组越界$i")
        }
        element[i] = newNum
    }

    /**
     * 返回指定元素的下标，如果不存在则返回-1
     */
    fun indexedOf(num: Int): Int {
        var result = -1
        element.forEachIndexed { index, i ->
            if (num == i) {
                result = index
                return result
            }
        }
        return result
    }

    override fun toString(): String {
        return element.contentToString()
    }
}