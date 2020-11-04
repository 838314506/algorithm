package com.lzz.algorithm.lru;

/**
 * LRU的双向链表
 * @param
 */
public class TwoForwardNode {

    private int element;

    private TwoForwardNode next;

    private TwoForwardNode before;

    public TwoForwardNode(){
        this.next = null;
    }

    public TwoForwardNode getNext() {
        return next;
    }

    public void setNext(TwoForwardNode next) {
        this.next = next;
    }

    public TwoForwardNode getBefore() {
        return before;
    }

    public void setBefore(TwoForwardNode before) {
        this.before = before;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public TwoForwardNode(int element, TwoForwardNode before, TwoForwardNode next){
        this.element = element;
        this.before = before;
        this.next = next;
    }
}
