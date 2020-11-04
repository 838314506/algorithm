package com.lzz.algorithm.lru;

import com.lzz.algorithm.linkedlist.Node;

import java.util.HashMap;
import java.util.Objects;

/**
 * LRU算法
 */
public class LRUCache {

    private int capacity ;

    private int length;

    private TwoForwardNode headNode;

    private HashMap<Integer,TwoForwardNode> map;

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
//        int v1 = cache.get(1);
//        System.out.println("从缓存中取出1的值为：" + v1);
        cache.put(4,4);
        cache.put(3,5);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    public int get(int key) {

        return 0;
    }

    public void put(int key, int value) {
        if (Objects.isNull(headNode)){
            headNode = new TwoForwardNode(value, null, null);
            map.put(key,headNode);
            length ++;
            return;
        }
        if (length == capacity){
            map.remove(headNode.getElement());
            headNode = headNode.getNext();
            TwoForwardNode last = map.get(length);
            TwoForwardNode current = new TwoForwardNode(value, last, null);
            last.setNext(current);
            map.put(key,current);
            length ++;
            return;
        }
        if (map.containsKey(key)){
            TwoForwardNode twoForwardNode = map.get(key);
            twoForwardNode.setElement(value);
            if (!Objects.isNull(twoForwardNode.getBefore()) && !Objects.isNull(twoForwardNode.getNext())){
                TwoForwardNode last = map.get(length);
                TwoForwardNode before = twoForwardNode.getBefore();
                TwoForwardNode next = twoForwardNode.getNext();
                before.setNext(next);
                twoForwardNode.setNext(null);
                twoForwardNode.setBefore(last);
                last.setNext(twoForwardNode);
            }
            if (Objects.isNull(twoForwardNode.getBefore()) && !Objects.isNull(twoForwardNode.getNext())){
                TwoForwardNode next = twoForwardNode.getNext();
                headNode = next;
                TwoForwardNode last = map.get(length);
                last.setNext(twoForwardNode);
            }
            map.put(key,twoForwardNode);
            return;
        }
        TwoForwardNode before = map.get(length);
        TwoForwardNode current = new TwoForwardNode(value, before, null);
        before.setNext(current);
        map.put(key,current);
        length ++;
    }
}
