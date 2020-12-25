package com.lzz.algorithm.slowFastPointer;


/**
 * 输入头，奇数返回中点前一个，偶数下中点前一个节点
 */
public class AfterMidPoint2 {

    private Node4 head;

    public static void main(String[] arg){
        AfterMidPoint2 midPoint = new AfterMidPoint2();
        for (int i = 0;i < 8; i ++){
            midPoint.add(new Node4(i,null));
        }
        Node4 midNode4 = midPoint.getMidNode4(midPoint.head);
        System.out.println(midNode4.getValue() + "=======");
    }

    public void add(Node4 Node4){
        if (head == null){
            head = Node4;
            return;
        }
        Node4 temp = head;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(Node4);
    }

    public Node4 getMidNode4(Node4 head){
        if (head == null){
            return null;
        }

        if (head.getNext() == null || head.getNext().getNext() == null){
            return head;
        }

        Node4 slow = head;
        Node4 fast = head;
        Node4 result = slow;
        while (fast.getNext() != null && fast.getNext().getNext() != null){
            result = slow;
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        if (fast.getNext() != null){
            return slow;
        }
        return result;
    }

}

class Node4{
    int value;
    Node4 next;

    public Node4(int value,Node4 next){
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node4 getNext() {
        return next;
    }

    public void setNext(Node4 next) {
        this.next = next;
    }
}