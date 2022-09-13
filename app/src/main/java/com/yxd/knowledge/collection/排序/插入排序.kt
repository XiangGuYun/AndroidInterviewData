package com.yxd.knowledge.collection.排序

object 插入排序 {

    private val arr = arrayOf(101, 34, 119, 1, 23, 76, 41)

    @JvmStatic
    fun main(args: Array<String>) {
        // 从第一个索引值开始，总共要进行size-1次排序
        for (i in 1 until arr.size){
            for (j in 0 until i){
                // 将待插入的数与之前的数进行比较，如果小于之前的数，则进行互换
                if(arr[i] < arr[j]){
                    val temp = arr[i]
                    arr[i] = arr[j]
                    arr[j] = temp
                }
            }
        }
        println(arr.contentToString())
    }

}