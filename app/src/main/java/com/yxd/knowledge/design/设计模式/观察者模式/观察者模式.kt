package com.yxd.knowledge.design.设计模式.观察者模式

/**
 * 定义了对象间一对多的依赖关系，使得每当这个对象的状态发生改变时，
 * 其它依赖的对象会获得通知并自动更新
 */
object 观察者模式 {
    @JvmStatic
    fun main(args: Array<String>) {
        val bank = 银行()

        bank.借贷(object : 贷款人 {
            override fun 姓名(): String {
                return "张三"
            }

            override fun 还贷() {
                println(姓名() + "还贷了")
            }
        })

        bank.借贷(object : 贷款人 {
            override fun 姓名(): String {
                return "李四"
            }

            override fun 还贷() {
                println(姓名() + "还贷了")
            }
        })

        bank.收债()
    }
}

interface 债主 {
    fun 借贷(p: 贷款人)
    fun 收债()
}

interface 贷款人 {
    fun 姓名(): String
    fun 还贷()
}

class 银行 : 债主 {
    val list = mutableListOf<贷款人>()
    override fun 借贷(p: 贷款人) {
        list.add(p)
    }

    override fun 收债() {
        list.forEach {
            it.还贷()
        }
    }
}

