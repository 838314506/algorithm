package com.lzz.algorithm.slowFastPointer;


/**
 * 输入头，奇数返回中点，偶数下中点
 */
public class AfterMidPoint {

    private Node2 head;

    public static void main(String[] arg){
        AfterMidPoint midPoint = new AfterMidPoint();
        for (int i = 0;i < 8; i ++){
            midPoint.add(new Node2(i,null));
        }
        Node2 midNode2 = midPoint.getMidNode2(midPoint.head);
        System.out.println(midNode2.getValue() + "=======");
    }

    public void add(Node2 Node2){
        if (head == null){
            head = Node2;
            return;
        }
        Node2 temp = head;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(Node2);
    }

    public Node2 getMidNode2(Node2 head){
        if (head == null){
            return null;
        }

        if (head.getNext() == null || head.getNext().getNext() == null){
            return head;
        }

        Node2 slow = head;
        Node2 fast = head;

        while (fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if (fast.getNext() != null){
            slow = slow.getNext();
        }
        return slow;
    }

}

class Node2{
    int value;
    Node2 next;

    public Node2(int value,Node2 next){
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node2 getNext() {
        return next;
    }

    public void setNext(Node2 next) {
        this.next = next;
    }
}