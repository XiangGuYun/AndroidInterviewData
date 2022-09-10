package 数据结构和算法.datastructure1

object 快速排序{
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(5, 7, 10, 6, 4, 3, 8)

    }
}

fun quickSort(arr:IntArray, start:Int, end:Int){
    // 把数组中第0个数字作为标准数
    val standard = arr[start]
    // 记录需要排序的下标
    var low = start
    var high = end
}