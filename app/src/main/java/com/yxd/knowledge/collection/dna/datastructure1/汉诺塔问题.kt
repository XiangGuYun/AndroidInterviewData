package 数据结构和算法.datastructure1

object 汉诺塔问题 {
    @JvmStatic
    fun main(args: Array<String>) {
        hnt(3, 'A', 'B', 'C')
    }

    /**
     * @param n: 共有N个盘子
     * @param fromChar: 开始的柱子
     * @param inChar: 中间的柱子
     * @param toChar: 目标柱子
     */
    fun hnt(n: Int, fromChar: Char, inChar: Char, toChar: Char) {
        if (n == 1) {
            println("将第1个盘子从${fromChar}移动到${toChar}")
        } else {
            // 无论有多少个盘子，都认为只有上下两个
            // 移动上面的盘子到中间
            hnt(n - 1, fromChar, toChar, inChar)
            // 移动下面的盘子到右侧
            println("将第${n}个盘子从${fromChar}移动到${toChar}")
            // 把上面的盘子移到右侧
            hnt(n-1, inChar, fromChar, toChar)
        }
    }
}