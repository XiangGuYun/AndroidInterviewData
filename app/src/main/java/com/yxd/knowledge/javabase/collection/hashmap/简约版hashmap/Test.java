package com.yxd.knowledge.javabase.collection.hashmap.简约版hashmap;

public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("monkey", "猴子");
        map.put("lb", "刘备");
        map.put("gy", "关羽");
        map.put("zf", "张飞");
        System.out.println(map.get("monkey"));
        System.out.println(map.get("lb"));
        System.out.println(map.get("gy"));
        System.out.println(map.get("zf"));
    }
}
