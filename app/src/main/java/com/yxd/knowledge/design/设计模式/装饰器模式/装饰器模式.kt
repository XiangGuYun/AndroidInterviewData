package com.yxd.knowledge.design.设计模式.装饰器模式

object 装饰器模式 {
    @JvmStatic
    fun main(args: Array<String>) {
        val jim = object : Man {
            override fun name(): String {
                return "Jim"
            }

            override fun walk() {
                println("走路")
            }

            override fun eat() {
                println("吃饭")
            }

            override fun sleep() {
                println("睡觉")
            }
        }

        CarDriver(jim).drive()
    }
}

interface Man {
    fun name(): String
    fun walk()
    fun eat()
    fun sleep()
}

class CarDriver(private val man: Man) {
    fun drive() {
        println(man.name() + "学会了开车")
    }
}