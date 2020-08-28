package com.lzz.algorithm.linkedlist;


/**
 * 基于单链表LRU算法
 */
public class LRUBaseLinkedList<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private SNode<T> headNode;

    private int capacity;

    private int lengh;
    
    public static void main(String[] arg){
        LRUBaseLinkedList<Integer> linkedList = new LRUBaseLinkedList<>(10);
        linkedList.add(5);
        linkedList.add(6);
        linkedList.add(7);
        linkedList.add(8);
        linkedList.add(9);
        linkedList.add(11);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(1);
        linkedList.add(0);
        linkedList.add(4);
        linkedList.add(5);
        System.out.println(linkedList.contain(5).getElement());
    }

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.lengh = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        this.headNode = new SNode<>();
        this.capacity = capacity;
        this.lengh = 0;
    }

    // TODO O(n)
    public SNode contain(T data) {
        SNode node = headNode;
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
        SNode ptr = headNode;
        if (headNode.getNext() == null){
            return;
        }
        while (ptr.getNext().getNext() != null){
            ptr = ptr.getNext();
        }
        SNode tmp = ptr;
        ptr.setNext(null);
        tmp = null;
        lengh --;
    }

    public void insert(T data) {
        if (lengh == 0){
            headNode.setElement(data);
        }else {
            headNode = new SNode<>(data,headNode);
        }
        lengh++;
    }
    // TODO O(n)
    public void delete(T data) {
        SNode node = headNode;
        if (node.getElement().equals(data)){
            node.setElement(null);
            if (node.getNext() != null){
                headNode = node.getNext();
            }
            return;
        }

       while (node.getNext() != null){
           SNode nextNode = node.getNext();
           if (nextNode.getElement().equals(data)){
               nextNode.setElement(null);
               node.setNext(nextNode.getNext());
               return;
           }
           node = nextNode;
       }
    }


    class SNode<T> {
        T element;
        SNode next;

        public SNode(){
            this.element = null;
            this.next = null;
        }

        public SNode(T data,SNode next){
            this.element = data;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }
}
