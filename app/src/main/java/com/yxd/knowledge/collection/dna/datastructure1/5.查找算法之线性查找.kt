package 数据结构和算法.datastructure1

object 查找算法之线性查找 {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(1, 3, 5, 7, 9, 2, 4, 6, 8, 10)
        // 目标元素
        val targetIndex = 8
        for (i in arr.indices){
            if(i == targetIndex){
                println("目标元素的值是：${arr[i]}")
            }
        }
    }
}