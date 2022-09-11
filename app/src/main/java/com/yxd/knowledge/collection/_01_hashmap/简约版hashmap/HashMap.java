package com.yxd.knowledge.collection._01_hashmap.简约版hashmap;

public class HashMap<K, V> implements Map<K, V>{

    /**
     * 定义数组
     */
    private Entry<K, V> table[];

    /**
     * 记录当前数组的元素个数，用于判断是否需要扩容
     */
    private int size;

    /**
     * 数组的容量，初始值为16
     */
    private int currentCapacity = 16;

    public HashMap() {
        this.table = new Entry[currentCapacity];
    }

    @Override
    public V put(K k, V v) {
        // 根据k来计算出数组下标
        int index = getIndex(k);
        // 获取对应下标的entry
        Entry<K, V> entry = table[index];
        if(null == entry){
            // 如果数组位置为空，则直接插入entry
            table[index] = new Entry<>(k, v, index, null);
            size++;
        } else {
            // 如果数组位置不为空，则将entry插入此位置，并将之前的entry接续在后面
            table[index] = new Entry<>(k, v, index, entry);
        }
        return table[index].v;
    }

    @Override
    public V get(K k) {
        if(size == 0) return null;
        // 根据k来计算出数组下标
        int index = getIndex(k);
        // 获取对应下标的entry
        Entry<K, V> entry = findValue(table[index], k);
        return entry == null? null : entry.getValue();
    }

    private Entry<K, V> findValue(Entry<K, V> entry, K k) {
        if(k.equals(entry.getKey()) || k == entry.getKey()){
            // 如果当前的k和对应数组下标的k相同，则直接返回entry
            return entry;
        } else {
            // 如果不同，则继续往链表上去查找
            if(entry.next != null){
                // 递归查找
                return findValue(entry.next, k);
            }
        }
        // 都找不到则返回null
        return null;
    }

    /**
     * 根据k来计算出数组下标
     * @param k
     * @return
     */
    private int getIndex(K k) {
        // 实际用的是移位而不是取模，因为移位性能高很多
        int index = k.hashCode() % currentCapacity;
        return index >= 0 ? index : -index;
    }

    @Override
    public int size() {
        return size;
    }

    class Entry<K, V> implements Map.Entry<K, V>{

        private K k;

        private V v;

        private int index;

        private Entry<K, V> next;

        public Entry(K k, V v, int index, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.index = index;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
