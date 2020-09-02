package com.lzz.algorithm.stack;

import com.lzz.algorithm.linkedlist.SNode;

public class StackBaseLinkedList {
    private int size;
    private SNode top;

    public static void main(String[] args){
        StackBaseLinkedList stack = new StackBaseLinkedList();
        stack.push(2);
        stack.push(3);
        stack.push(9);
        stack.push(5);
        stack.print();
        stack.pop();
        stack.print();
        stack.clear();
        stack.print();
    }

    public void clear(){
        this.top = null;
        this.size = 0;
    }

    public void print(){
        SNode node = top;
        while (node != null){
            System.out.print(node.getElement() + "-----》");
            node = node.getNext();
        }
        System.out.println();
    }

    public SNode createNode(int data,SNode next){
        return new SNode(data,next);
    }

    public boolean push(int data){
        SNode node = createNode(data, this.top);
        this.top = node;
        size ++;
        return true;
    }

    public int pop(){
        SNode node = this.top;
        if (node == null) {
            System.out.println("stack is empty");
            return 0;
        }
        this.top = node.getNext();
        size --;
        return node.getElement();
    }
}
