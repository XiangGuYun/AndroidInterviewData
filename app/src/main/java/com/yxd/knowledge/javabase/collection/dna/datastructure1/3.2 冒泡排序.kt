package 数据结构和算法.datastructure1

/**
 * 关键词：比大，后移，size-1轮
 */
object 冒泡排序 {
    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(5, 7, 10, 6, 4, 3, 8)
        println(bubbleSort(arr).contentToString())
    }

    private fun bubbleSort(arr: IntArray): IntArray {
        // 比较轮数
        for (i in 0 until arr.size-1){
            // 比较次数
            for (j in 0 until arr.size-1){
                if(arr[j] > arr[j+1]){
                    val temp = arr[j]
                    arr[j] = arr[j+1]
                    arr[j+1] = temp
                }
            }
        }
        return arr
    }
}