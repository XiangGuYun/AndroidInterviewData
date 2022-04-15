package 数据结构和算法.datastructure

object SparseArrayTest {

    // 原始数组
    private val arr = Array(11) { IntArray(11) }
    // 稀疏数组
    private lateinit var sparseArray: Array<IntArray>

    @JvmStatic
    fun main(args: Array<String>) {
        创建一个原始二维数组()
        将原始二维数组转换为稀疏数组()
        将稀疏数组转换回原始二位数组()
    }

    private fun 将稀疏数组转换回原始二位数组() {
        val arrNew = Array(sparseArray[0][0]) { IntArray(sparseArray[0][1]) }
        for (i in 1 until sparseArray.size){
            arrNew[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2]
        }
        println("=====恢复原始数组=====")
        arrNew.forEach { row ->
            row.forEach { data ->
                print("\t${data}")
            }
            println()
        }
    }

    private fun 将原始二维数组转换为稀疏数组() {
        var sum = 0
        // 1.先遍历二维数组，得到非0数组的个数
        for (i in 0 until 11) {
            for (j in 0 until 11) {
                if (arr[i][j] != 0) {
                    sum++
                }
            }
        }
        // 2.创建对应的稀疏数组
        sparseArray = Array(sum + 1) { IntArray(3) }
        // 给稀疏数组赋值
        sparseArray[0][0] = 11
        sparseArray[0][1] = 11
        sparseArray[0][2] = 2
        // 遍历二维数组，将非0的值存放到稀疏数组中
//        var count = 0 // 用于记录第几个非0数据
        for (i in 0 until 11) {
            for (j in 0 until 11) {
                if (arr[i][j] != 0) {
//                    count++
                    sparseArray[i][0] = i
                    sparseArray[i][1] = j
                    sparseArray[i][2] = arr[i][j]
                }
            }
        }
        println("=====输出稀疏数组=====")
        // 输出稀疏数组
        for (i in sparseArray.indices){
            println("${sparseArray[i][0]}\t${sparseArray[i][1]}\t${sparseArray[i][0]}\t")
        }
    }

    private fun 创建一个原始二维数组() {
        // 0表示无子，1代表黑子，2代表蓝子
        arr[1][2] = 1
        arr[2][3] = 2
        // 输出原始的二位数组
        arr.forEach { row ->
            row.forEach { data ->
                print("\t${data}")
            }
            println()
        }
    }
}