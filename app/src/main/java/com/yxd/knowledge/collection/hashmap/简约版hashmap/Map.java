package com.yxd.knowledge.collection.hashmap.简约版hashmap;

public interface Map<K, V> {
    V put(K k, V v);
    V get(K k);
    int size();

    interface Entry<K, V>{
        K getKey();
        V getValue();
    }
}
