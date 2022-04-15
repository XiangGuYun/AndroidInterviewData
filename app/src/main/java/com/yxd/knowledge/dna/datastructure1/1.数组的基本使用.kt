package 数据结构和算法.datastructure1

object 数组的基本使用 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 创建一个长度为3的数组
        val arr = IntArray(3)
        // 获取数组的长度
        val length = arr.size
        println("数组长度是${length}")
        // 访问数组中的元素
        println("数组第一个元素的值是${arr[0]}")
        // 为数组中的元素赋值
        arr[0] = 99
        println("数组第一个元素的值是${arr[0]}")
        // 遍历数组
        arr.forEach {
            println("遍历${it}")
        }
        // 创建数组的同时为之赋值
        val arr2 = intArrayOf(1,2,3,4,5)
        println("arr2 's size is ${arr2.size}")
    }
}