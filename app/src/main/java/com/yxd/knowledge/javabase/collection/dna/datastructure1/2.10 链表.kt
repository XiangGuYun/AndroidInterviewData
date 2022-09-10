package com.yxd.knowledge.javabase.collection.dna.datastructure1

object TestNode{
    @JvmStatic
    fun main(args: Array<String>) {
        val node = Node().append(Node(1)).append(Node(3))
        println(node.next?.next?.data)
    }
}

/**
 * 节点类
 * @property data String? 节点数据
 * @property next Node? 下一个节点
 * @constructor
 */
class Node(var data:Int?=null){
    var next:Node?=null

    /**
     * 追加节点，可以连续追加
     * @param node Node
     */
    fun append(node:Node): Node {
        // 记录当前的节点
        var currentNode = this
        // 如果下个节点不为空，则继续往下找
        while(currentNode.next != null){
            currentNode = currentNode.next!!
        }
        // 找到最后一个节点，赋值next
        currentNode.next = node
        return this
    }

    /**
     * 判断当前节点是否是最后的节点
     * @return Boolean
     */
    fun isLast(): Boolean{
        return next == null
    }

    fun remove(i:Int){

    }

}

