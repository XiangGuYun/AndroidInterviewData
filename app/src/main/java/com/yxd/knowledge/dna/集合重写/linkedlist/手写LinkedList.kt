package 数据结构和算法.集合重写.linkedlist

object 手写LinkedList {
    @JvmStatic
    fun main(args: Array<String>) {

    }
}

class LinkedList {

    private data class Node(
        var data: Any? = null,
        var pre: Node? = null,
        var next: Node? = null
    )

    @Transient
    private var size = 0

    @Transient
    private var firstNode: Node? = null

    @Transient
    private var lastNode: Node? = null

    fun size(): Int {
        return size
    }

    fun isEmpty(): Boolean {
        return size == 0
    }

    fun get(i: Int): Any{
        return i
    }



}