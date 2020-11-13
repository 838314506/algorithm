package com.lzz.algorithm.lru;

import com.lzz.algorithm.linkedlist.Node;

import java.util.HashMap;
import java.util.Objects;

/**
 * LRU算法
 */
public class LRUCache {

    private int capacity;

    private int length;

    private TwoForwardNode headNode;

    private HashMap<Integer,TwoForwardNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public TwoForwardNode getHeadNode() {
        return headNode;
    }

    public void setHeadNode(TwoForwardNode headNode) {
        this.headNode = headNode;
    }

    public static void main(String[] arg){
        LRUCache cache = new LRUCache(3);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        int v1 = cache.get(1);
        System.out.println("从缓存中取出1的值为：" + v1);
        cache.put(4,4);
        cache.put(3,5);
    }

    public int get(int key) {
        TwoForwardNode twoForwardNode = map.get(key);
        if (twoForwardNode != null){
            return key;
        }
        return -1;
    }

    public void put(int key, int value) {
    }
}
