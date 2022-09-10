package 数据结构和算法.datastructure1

object 查找算法之二分查找 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 使用二分查找的前提是数组必须是有序的
        val arr = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        // 目标元素
        val targetNum = 8
        // 记录开始位置
        var begin = 0
        // 记录结束位置
        var end = arr.size - 1
        // 记录中间位置
        var mid = (begin + end) / 2
        // 记录目标位置
        var targetIndex = -1
        // 循环查找
        while (true) {
            // 判断中间元素是不是要查找的元素
            if (arr[mid] == targetNum) {
                targetIndex = mid
                break
            } else {
                // 判断中间元素是否大于目标元素
                if (arr[mid] > targetNum) {
                    // 把结束位置调整成中间位置的前一个位置
                    end = mid - 1
                } else {
                    // 把开始位置调整成中间位置的后一个位置
                    begin = mid + 1
                }
                // 获取新的中间位置
                mid = (begin + end) / 2
            }
        }
        println("元素的下标位置是${targetIndex}")
    }
}