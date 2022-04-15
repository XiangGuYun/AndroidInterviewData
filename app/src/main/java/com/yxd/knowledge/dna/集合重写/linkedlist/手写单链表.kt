package 数据结构和算法.集合重写.linkedlist

object 手写单链表 {

    @JvmStatic
    fun main(args: Array<String>) {
        val list = SingleLinkedList()
        list.add("刘备")
        list.add("关羽")
        list.add("张飞")
        list.add("赵云")
        list.add("孔明")

        println("=========演示添加=========")
        println(list.get(0)?.data)
        println(list.get(1)?.data)
        println(list.get(2)?.data)
        println(list.get(3)?.data)
        println(list.get(4)?.data)

        println("=========演示IndexedOf=========")
        println("孔明的下标是：" + list.indexedOf("孔明"))
        println("曹操的下标是：" + list.indexedOf("曹操"))

        println("=========演示删除=========")
        list.remove("刘备")
        list.removeAt(3) // 删除孔明
        println(list.get(0)?.data)
        println(list.get(1)?.data)
        println(list.get(2)?.data)

        println("=========演示Foreach=========")
        list.foreach {
            println(it?.data)
        }

        println("=========演示ForeachIndexed=========")
        list.foreachIndexed { i, node ->
            println("$i ${node?.data}")
        }

        println("=========演示Clear=========")
        list.clear()
        println(list.size())
    }
}

/**
 * 单链表类
 */
class SingleLinkedList {
    private var headNode: Node? = null
    private var nodeSize = 0

    /**
     * 单链表节点类
     * @property data Any?
     * @property next Node?
     * @constructor
     */
    data class Node(var data: Any? = null, var next: Node? = null)

    /**
     * 通过遍历获取链表的节点
     * @param i Int
     * @return Node?
     */
    fun get(position: Int): Node? {
        // 健壮性判断
        if (position < 0 || position >= nodeSize) {
            throw IndexOutOfBoundsException("索引越界：${position}")
        }
        // 定义一个临时节点指向头结点
        var node = headNode
        for (i in 0 until position) {
            node = node?.next
        }
        return node
    }

    fun foreach(callback: (Node?) -> Unit) {
        for (i in 0 until nodeSize) {
            callback.invoke(get(i))
        }
    }

    fun foreachIndexed(callback: (i: Int, node: Node?) -> Unit) {
        for (i in 0 until nodeSize) {
            callback.invoke(i, get(i))
        }
    }

    /**
     * 添加新节点
     * n1 n2 n3
     * 假设当前列表已有三个节点，然后希望在第2个节点后面插入一个新节点，变成这样：
     * n1 n2 newN n3
     * 那么，需要做的事情就是新建一个新节点，并将n2指向newN，将newN指向n3
     * @param i Int
     * @param any Any
     */
    fun add(i: Int, any: Any) {
        when {
            i < 0 || i > nodeSize -> {
                throw IndexOutOfBoundsException("索引越界：${i}")
            }
            i == 0 -> {
                // 创建第一个节点
                headNode = Node(data = any)
                nodeSize++
            }
            else -> {
                // 获得要插入位置的上一个节点
                val preNode = get(i - 1)
                // 新建节点
                val newNode = Node(any)
                // 将新节点指向上一个节点的下个节点
                newNode.next = preNode?.next
                // 将上一个节点指向新节点
                preNode?.next = newNode
                // 节点数量+1
                nodeSize++
            }
        }
    }

    fun add(any: Any) {
        add(nodeSize, any)
    }

    /**
     * 删除指定位置的节点
     * 思路：获取指定位置节点的上下两个节点，
     * 然后将上一个节点指向当前节点的下一个节点
     * 将当前节点设置为null
     * @param i Int
     * @return Boolean
     */
    fun removeAt(i: Int): Boolean {
        when {
            i < 0 || i >= nodeSize -> {
                return false
            }
            i == 0 -> {
                // 删除头节点
                headNode = headNode?.next
                nodeSize--
                return true
            }
            else -> {
                // 获取要删除位置的节点B
                var nodeForDelete = get(i)
                // 获得要删除节点的上一个节点A
                val preNode = get(i - 1)
                // 获得要删除节点的下一个节点C
                val nextNode = nodeForDelete?.next
                // 将节点A指向节点C
                preNode?.next = nextNode
                // 将节点B设置为null
                nodeForDelete = null
                // 节点数量-1
                nodeSize--
                return true
            }
        }
    }

    /**
     * 删除指定元素
     * @param any Any
     * @return Boolean
     */
    fun remove(any: Any): Boolean {
        if (indexedOf(any) == -1) {
            return false
        }
        removeAt(indexedOf(any))
        return true
    }

    fun clear() {
        for (i in nodeSize - 1 downTo  0) {
            println(i)
            get(i)?.data = null
            get(i)?.next = null
        }
        nodeSize = 0
    }

    fun size(): Int {
        return nodeSize
    }

    fun isEmpty(): Boolean {
        return nodeSize == 0
    }

    fun contains(any: Any): Boolean {
        return indexedOf(any) != -1
    }

    fun indexedOf(any: Any): Int {
        // 定义一个临时节点指向头结点
        var node = headNode
        if (node?.data == any) {
            return 0
        }
        for (i in 0 until nodeSize) {
            node = node?.next
            if (node?.data == any) {
                return i + 1
            }
        }
        return -1
    }
}