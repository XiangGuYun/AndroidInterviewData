package 数据结构和算法.datastructure1

object 数组元素的删除 {
    @JvmStatic
    fun main(args: Array<String>) {
        var arr = intArrayOf(9, 8, 7, 6, 5)
        val newArr = IntArray(arr.size - 1)
        newArr.forEachIndexed { index, i ->
            if(index >= 2){
                newArr[index] = arr[index+1]
            } else {
                newArr[index] = arr[index]
            }
        }
        arr = newArr
        println(arr.contentToString())
    }
}