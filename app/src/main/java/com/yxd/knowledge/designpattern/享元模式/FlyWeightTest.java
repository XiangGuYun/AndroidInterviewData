package com.yxd.knowledge.designpattern.享元模式;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FlyWeightTest {
    public static void main(String[] args) {
        TreeNode treeNode1 = new TreeNode(3, 4, TreeFactory.getTree("xxx", "xxxxx"));
        TreeNode treeNode2 = new TreeNode(3, 4, TreeFactory.getTree("xxx", "xxxxx"));
    }
}

class TreeNode{
    private int x;
    private int y;
    private Tree tree;

    public TreeNode(int x, int y, Tree tree) {
        this.x = x;
        this.y = y;
        this.tree = tree;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }
}

class Tree{
    // 定义成final，防止被修改
    private final String name;
    private final String data;

    public Tree(String name, String data) {
        this.name = name;
        this.data = data;
        System.out.println("创建了树：name 是 "+name+" data 是 "+data);
    }

    public String getName() {
        return name;
    }

    public String getData() {
        return data;
    }

}

class TreeFactory{
    private static Map<String, Tree> map = new ConcurrentHashMap<>();

    public static Tree getTree(String name, String data){
        if(map.containsKey(name)){
            return map.get(name);
        }
        Tree tree = new Tree(name, data);
        map.put(name, tree);
        return tree;
    }
}
