package com.lzz.algorithm.linkedlist;

import javax.sound.midi.Soundbank;

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
        System.out.println(linkedList.contain(5).getElement());
    }

    public LRUBaseLinkedList() {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.lengh = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        this.headNode = new SNode<>();
        this.capacity = DEFAULT_CAPACITY;
        this.lengh = 0;
    }

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
        if (headNode.getElement() == null && headNode.getNext() == null){
            insert(node);
            return;
        }

        if (contain(node) == null && lengh < capacity) {
            insert(node);
        }else {
            if (lengh >= capacity){
                delete(node);
            }
            insert(node);
        }
    }

    public void insert(T data) {
        headNode = new SNode<>(data,headNode);
        lengh++;
    }

    public void delete(T data) {
        SNode element = headNode;
        if (headNode.getElement().equals(data))
            headNode = headNode.getNext();
        while (element.getNext() != null) {
            if (element.getNext().getElement().equals(data))
                element.setNext(element.getNext().getNext());
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
