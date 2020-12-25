package com.lzz.algorithm.slowFastPointer;


/**
 * 输入头，奇数返回中点前一个节点，偶数上中点前一个节点
 */
public class BeforeMidPoint2 {

    private Node3 head;

    public static void main(String[] arg){
        BeforeMidPoint2 midPoint = new BeforeMidPoint2();
        for (int i = 0;i < 8; i ++){
            midPoint.add(new Node3(i,null));
        }
        Node3 midNode3 = midPoint.getMidNode3(midPoint.head);
        System.out.println(midNode3.getValue() + "=======");
    }

    public void add(Node3 Node3){
        if (head == null){
            head = Node3;
            return;
        }
        Node3 temp = head;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(Node3);
    }

    public Node3 getMidNode3(Node3 head){
        if (head == null){
            return null;
        }

        if (head.getNext() == null || head.getNext().getNext() == null){
            return head;
        }

        Node3 slow = head;
        Node3 fast = head;
        Node3 result = slow;
        while (fast.getNext() != null && fast.getNext().getNext() != null){
            result = slow;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        return result;
    }

}

class Node3{
    int value;
    Node3 next;

    public Node3(int value,Node3 next){
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node3 getNext() {
        return next;
    }

    public void setNext(Node3 next) {
        this.next = next;
    }
}