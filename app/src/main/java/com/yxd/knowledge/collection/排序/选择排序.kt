package com.yxd.knowledge.collection.排序

/**
 * 对于长度为N的数组，外循环需要进行N-1次，每次需要找出剩余数中最小值的索引，
 * 找出最小值索引后与对应位置的值进行互换。
 */
object 选择排序 {

    private val arr = arrayOf(101, 34, 119, 1)
    private var temp = 0

    @JvmStatic
    fun main(args: Array<String>) {
        when (1) {
            0 -> 分次排序()
            1 -> 一次排序()
        }
    }

    private fun 一次排序() {
        for (i in 0 until arr.size-1){
            var minIndex = i
            for (j in i until arr.size-1){
                if (arr[minIndex] > arr[j + 1]) {
                    minIndex = j + 1
                }
            }
            temp = arr[i]
            arr[i] = arr[minIndex]
            arr[minIndex] = temp
        }
        println("一次排序结果是：${arr.contentToString()}")
    }

    private fun 分次排序() {
        // 第一轮
        // 原始的数组：101，34，119，1
        // 第一轮排序：1，101，34， 119
        // 假定最小值索引是0
        var minIndex = 0
        (minIndex until arr.size - 1).forEach {
            if (arr[minIndex] > arr[it + 1]) {
                minIndex = it + 1
            }
        }
        // 将最小值与第一个值进行交换
        temp = arr[0]
        arr[0] = arr[minIndex]
        arr[minIndex] = temp
        println("第一轮排序结果：${arr.contentToString()}")

        // 第二轮：找到第二小值🔍
        // 将假定最小值索引修改为1
        minIndex = 1
        for (i in (minIndex until arr.size - 1)) {
            if (arr[minIndex] > arr[i + 1]) {
                minIndex = i + 1
            }
        }
        // 将第二小值与第二个值进行交换
        temp = arr[1]
        arr[1] = arr[minIndex]
        arr[minIndex] = temp
        println("第二轮排序结果：${arr.contentToString()}")

        // 第三轮：找到第三小值🔍
        // 将假定最小值索引修改为2
        minIndex = 2
        for (i in (minIndex until arr.size - 1)) {
            if (arr[minIndex] > arr[i + 1]) {
                minIndex = i + 1
            }
        }
        // 将第三小值与第三个值进行交换
        temp = arr[2]
        arr[2] = arr[minIndex]
        arr[minIndex] = temp
        println("第三轮排序结果：${arr.contentToString()}")
    }


}