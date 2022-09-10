package 数据结构和算法.datastructure1

object 递归和斐波那契数列 {
    @JvmStatic
    fun main(args: Array<String>) {
        print(10)

        println("===========================")

        // 斐波那契数列：1 1 2 3 5 8 13
        println(fbnq(5))
    }

    /**
     * 打印出斐波那契数列下标为i的值
     */
    private fun fbnq(i: Int): Int {
        if (i == 1 || i == 2) {
            return 1
        } else {
            return fbnq(i - 1) + fbnq(i - 1)
        }
    }

    private fun print(i: Int) {
        if (i > 0) {
            println(i)
            print(i - 1)
        }
    }

}