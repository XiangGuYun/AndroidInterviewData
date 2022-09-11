package 数据结构和算法.集合重写.arraylist

interface MyIterator<E> {
    fun hasNext(): Boolean
    fun next(): E
}