package com.lzz.algorithm.linkedlist;


/**
 * 基于单链表LRU算法
 */
/**
 *       分析时间和空间复杂度
 *      1、单链表反转
 *      2、链表中环的检测
 * TODO     3、两个有序的链表合并
 *      4、删除链表倒数第n个结点
 *      5、求链表的中间结点
 *      6、如果字符串是通过单链表来存储的，如何判断是一个回文串呢
 */
public class LRUBaseLinkedList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Node<T> headNode;

    private int capacity;

    private int lengh;

    public Node<T> getHeadNode() {
        return headNode;
    }

    public void setHeadNode(Node<T> headNode) {
        this.headNode = headNode;
    }

    public static void main(String[] arg){
        LRUBaseLinkedList<Integer> linkedList = new LRUBaseLinkedList<>(10);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        linkedList.add(11);
        LRUBaseLinkedList<Integer> linkedList2 = new LRUBaseLinkedList<>(10);
        linkedList2.add(3);
        linkedList2.add(2);
        linkedList2.add(1);
        linkedList2.add(0);
        linkedList2.add(4);
        linkedList2.add(5);
//        System.out.println("==============单链表的反转");
//        linkedList.reverse(linkedList.getHeadNode());
//        System.out.println(linkedList.isHoop(linkedList.getHeadNode()));
        
    }
    
   

    public LRUBaseLinkedList() {
        this.headNode = new Node<>();
        this.capacity = DEFAULT_CAPACITY;
        this.lengh = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        this.headNode = new Node<>();
        this.capacity = capacity;
        this.lengh = 0;
    }

   

    // TODO O(n)
    public Node contain(T data) {
        Node node = headNode;
        if (headNode.getElement() == data)
            return headNode;
        while (node.getNext() != null) {
            node = node.getNext();
            if (node != null && data.equals(node.getElement()))
                return node;
        }
        return null;
    }

    public void add(T node) {
        if (contain(node) != null) {
            delete(node);
            insert(node);
        }else {
            if (lengh >= capacity){
                deleteTailNode();
            }
            insert(node);
        }
    }

    // TODO O(n)
    public void deleteTailNode(){
        Node ptr = headNode;
        if (headNode.getNext() == null){
            return;
        }
        while (ptr.getNext().getNext() != null){
            ptr = ptr.getNext();
        }
        Node tmp = ptr;
        ptr.setNext(null);
        tmp = null;
        lengh --;
    }

    public void insert(T data) {
        if (lengh == 0){
            headNode.setElement(data);
        }else {
            headNode = new Node<>(data,headNode);
        }
        lengh++;
    }
    // TODO O(n)
    public void delete(T data) {
        Node node = headNode;
        if (node.getElement().equals(data)){
            node.setElement(null);
            if (node.getNext() != null){
                headNode = node.getNext();
            }
            return;
        }

       while (node.getNext() != null){
           Node nextNode = node.getNext();
           if (nextNode.getElement().equals(data)){
               nextNode.setElement(null);
               node.setNext(nextNode.getNext());
               return;
           }
           node = nextNode;
       }
    }


}
