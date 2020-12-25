package com.lzz.algorithm.slowFastPointer;


/**
 * 输入头，奇数返回中点，偶数上中点
 */
public class BeforeMidPoint {

    private Node head;

    public static void main(String[] arg){
        BeforeMidPoint midPoint = new BeforeMidPoint();
        for (int i = 0;i < 7; i ++){
            midPoint.add(new Node(i,null));
        }
        Node midNode = midPoint.getMidNode(midPoint.head);
        System.out.println(midNode.getValue() + "=======");
    }

    public void add(Node node){
        if (head == null){
            head = node;
            return;
        }
        Node temp = head;
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        temp.setNext(node);
    }

    public Node getMidNode(Node head){
        if (head == null){
            return null;
        }

        if (head.getNext() == null || head.getNext().getNext() == null){
            return head;
        }

        Node slow = head;
        Node fast = head;

        while (fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

}
class Node{
    int value;
    Node next;

    public Node(int value,Node next){
        this.value = value;
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}