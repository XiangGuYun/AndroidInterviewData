package com.yxd.knowledge.other.java基础

import java.lang.ref.PhantomReference
import java.lang.ref.ReferenceQueue
import java.lang.ref.WeakReference

object Java的引用 {
    @JvmStatic
    fun main(args: Array<String>) {
        val reference = WeakReference(People("诸葛亮", 27))
        println(reference.get())
        System.gc() //通GC回收资源
        println(reference.get())
    }

    data class People(val name:String, val age:Int)
}

object Java的引用1 {
    @JvmStatic
    fun main(args: Array<String>) {
        val people = People("诸葛亮", 20)
        val reference = WeakReference(people)
        println(reference.get())
        System.gc()
        println(reference.get())
    }

    data class People(val name:String, val age:Int)
}

object Java的引用2 {
    @JvmStatic
    fun main(args: Array<String>) {
        val queue: ReferenceQueue<String> = ReferenceQueue<String>()
        val pr: PhantomReference<String> = PhantomReference<String>(String("hello".toByteArray()), queue)
        println(pr.get())
    }
}