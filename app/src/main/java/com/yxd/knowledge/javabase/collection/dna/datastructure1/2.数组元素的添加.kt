package 数据结构和算法.datastructure1

object 数组元素的添加 {
    @JvmStatic
    fun main(args: Array<String>) {
        // 解决数组长度不可变的问题
        var arr = intArrayOf(9, 8, 7)
        // 快速打印出数组中的所有元素
        println(arr.contentToString())
        // 要加入数组的目标元素
        val target = 6
        // 创建一个新数组，长度是原数组的长度+1
        val newArr = IntArray(arr.size+1)
        arr.forEachIndexed { index, i ->
            newArr[index] = arr[index]
        }
        newArr[arr.size] = target
        // 将新数组替换旧数组
        arr = newArr
        println(arr.contentToString())
    }
}