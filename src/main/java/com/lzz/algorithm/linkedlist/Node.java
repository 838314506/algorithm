package com.lzz.algorithm.linkedlist;

public class Node<T> {
    T element;
    Node next;

    public Node(){
        this.next = null;
    }

    public Node(T element,Node next){
        this.element = element;
        this.next = next;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
