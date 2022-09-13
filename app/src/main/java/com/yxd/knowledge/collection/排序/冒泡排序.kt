package com.yxd.knowledge.collection.排序

object 冒泡排序 {
    private val arr = arrayOf(3, 9, -1, 10, -2)

    @JvmStatic
    fun main(args: Array<String>) {
        when (2) {
            0 -> 分次排序()
            1 -> 一次排序()
            2 -> 优化排序()
        }
    }

    /**
     * 如果在某次排序中，数组顺序没有发生改变，则可以取消之后的排序
     */
    private fun 优化排序() {
        val arr1 = arrayOf(1, 2, 3, 5, 4)
        var temp = 0
        for (i in 1 until arr1.size) {
            var hasChanged = false
            for (j in 0 until arr1.size - i) {
                // 如果前一个数大于后一个数，则互换位置
                if (arr1[j] > arr1[j + 1]) {
                    hasChanged = true
                    temp = arr1[j + 1]
                    arr1[j + 1] = arr1[j]
                    arr1[j] = temp
                }
            }
            if(!hasChanged){
                println("提前结束排序，总共进行了${i}轮排序")
                break
            }
        }
        println("优化排序结果：" + arr.contentToString())
    }

    private fun 一次排序() {
        var temp = 0
        for (i in 1 until arr.size) {
            for (j in 0 until arr.size - i) {
                // 如果前一个数大于后一个数，则互换位置
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j + 1]
                    arr[j + 1] = arr[j]
                    arr[j] = temp
                }
            }
        }
        println("一次排序结果：" + arr.contentToString())
    }

    private fun 分次排序() {
        // 第一次排序，将最大的数排到最后
        var temp = 0
        for (i in 0 until arr.size - 1) {
            // 如果前一个数大于后一个数，则互换位置
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1]
                arr[i + 1] = arr[i]
                arr[i] = temp
            }
        }
        println(arr.contentToString())

        // 第二次排序，将第二大的数排到倒数第二的位置
        for (i in 0 until arr.size - 2) {
            // 如果前一个数大于后一个数，则互换位置
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1]
                arr[i + 1] = arr[i]
                arr[i] = temp
            }
        }
        println(arr.contentToString())

        // 第三次排序，将第san1大的数排到倒数第三的位置
        for (i in 0 until arr.size - 3) {
            // 如果前一个数大于后一个数，则互换位置
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1]
                arr[i + 1] = arr[i]
                arr[i] = temp
            }
        }
        println(arr.contentToString())

        // 第四次排序，将第san1大的数排到倒数第四的位置
        for (i in 0 until arr.size - 4) {
            // 如果前一个数大于后一个数，则互换位置
            if (arr[i] > arr[i + 1]) {
                temp = arr[i + 1]
                arr[i + 1] = arr[i]
                arr[i] = temp
            }
        }
        println(arr.contentToString())
    }
}