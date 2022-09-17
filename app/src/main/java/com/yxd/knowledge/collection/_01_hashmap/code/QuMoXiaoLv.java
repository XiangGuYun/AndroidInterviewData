package com.yxd.knowledge.collection._01_hashmap.code;

// &操作能代替%运算，必须满足一定的条件，也就是a%b=a&(b-1)仅当b是2的n次方的时候方能成立
class QuMoXiaoLv{
    public static void main(String[] args) {
        int result;

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            result = (16-1)&i;
        }
        System.out.println("使用&位运算符取模耗时"+(System.currentTimeMillis() - time1)+"ms");

        long time2 = System.currentTimeMillis();
        for (int i = 0; i < 1000000000; i++) {
            result = i%16;
        }
        System.out.println("使用%运算符取模耗时"+(System.currentTimeMillis() - time2)+"ms");
    }
}