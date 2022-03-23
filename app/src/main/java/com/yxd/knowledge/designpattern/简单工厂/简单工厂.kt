package com.yxd.knowledge.designpattern.简单工厂

/**
 * 优点：创建和使用分离，使用者不需要关心如何创建
 * 缺点：每次增加新的产品就要修改工厂类
 */
object 简单工厂 {
    @JvmStatic
    fun main(args: Array<String>) {
        val product1 = SimpleFactory.createProduct("A")
        val product2 = SimpleFactory.createProduct("B")
        product1?.printInfo()
        product2?.printInfo()
    }
}

object SimpleFactory {
    /**
     * 根据不同的类型参数返回不同的产品
     * @param type String
     * @return Product?
     */
    fun createProduct(type: String): Product? {
        return when (type) {
            "A" -> ProductA()
            "B" -> ProductB()
            else -> null
        }
    }
}

interface Product {
    fun printInfo()
}

class ProductA : Product {
    override fun printInfo() {
        println("产品A")
    }
}

class ProductB : Product {
    override fun printInfo() {
        println("产品B")
    }
}