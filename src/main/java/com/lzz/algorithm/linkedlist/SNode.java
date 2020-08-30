package com.lzz.algorithm.linkedlist;

public class SNode {
    int element;
    SNode next;

    public SNode(){
        this.next = null;
    }

    public SNode(int data){
        this.element = data;
        this.next = null;
    }

    public SNode(int data, SNode next){
        this.element = data;
        this.next = next;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public SNode getNext() {
        return next;
    }

    public void setNext(SNode next) {
        this.next = next;
    }
}
