package com.lzz.algorithm.linkedlist;

public class LinkedListUtil {

    public static void main(String[] args){

    }

    /**
     * 有序链表合并
     * @param a
     * @param b
     * @return
     */
    public static SNode merge(SNode a,SNode b){
        if (a == null) return b;
        if (b == null) return a;
        SNode sNode = new SNode(0);

        SNode left = a;
        SNode right = b;
        SNode mid = sNode;
        while (left != null && right != null ){
            if (left.getElement() > right.getElement()){
                sNode.setNext(right);
                left = left.getNext();
            }
            if (left.getElement() < right.getElement()){
                sNode.setNext(left);
                right = right.getNext();
            }
            mid = mid.getNext();
        }

        mid.setNext(left != null ? left : right);
        return sNode.getNext();
    }

    // 有序链表合并 Leetcode 21
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
//    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//        ListNode soldier = new ListNode(0); //利用哨兵结点简化实现难度 技巧三
//        ListNode p = soldier;
//
//        while ( l1 != null && l2 != null ){
//            if ( l1.val < l2.val ){
//                p.next = l1;
//                l1 = l1.next;
//            }
//            else{
//                p.next = l2;
//                l2 = l2.next;
//            }
//            p = p.next;
//        }
//
//        if (l1 != null) { p.next = l1; }
//        if (l2 != null) { p.next = l2; }
//        return soldier.next;
//    }


    /**
     * 检测环
     * @param node
     * @return
     */
    public static boolean isHoop(SNode node){
        if (node == null) return false;
        SNode slow = node;
        SNode fast = node.getNext();
        while (fast != null && slow != null){
            if (slow == fast){
                return true;
            }
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return false;
    }

    /**
     * 单链表的反转
     * @param nodes
     */
    public static void reverse(SNode nodes){
        SNode reverse = null;
        SNode node = nodes;
        while (node != null){
            SNode next = node.getNext();
            node.setNext(reverse);
            reverse = node;
            node = next;
        }
        print(reverse);
    }

    public static void print(SNode nodes){
        SNode node = nodes;
        while (node != null){
            System.out.print(node.getElement() + "--->");
            node = node.getNext();
        }
    }
}
