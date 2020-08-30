package com.lzz.algorithm.linkedlist;
/**
 *       分析时间和空间复杂度
 *      1、单链表反转
 *      2、链表中环的检测
 *      3、两个有序的链表合并
 *      4、删除链表倒数第n个结点
 *      5、求链表的中间结点
 *  TODO    6、如果字符串是通过单链表来存储的，如何判断是一个回文串呢
 */
public class LinkedListUtil {

    public static void main(String[] args){
//        SNode list = new SNode(0, new SNode(5, new SNode(4, new SNode(6, new SNode(2, null)))));
//        deleteLastKth(list,2);
//        Node list = new Node<>('a',new Node('d',new Node('c',new Node('b',new Node('a',null)))));
        Node list = new Node<>('a',null);
        System.out.println(palindrome(list));
    }

    /**
     * 判断一个字符串是否为回文串
     * 找到链表的中点，同时将中点前的节点倒序保存
     * 再进行遍历比较
     * @param list
     * @return
     */
    public static boolean palindrome(Node list){
        if (list == null) return false;

        Node fast = list;
        Node slow = list;
        Node reverse = null;
        while (fast.next != null){
            fast = fast.next.next;
            Node next = slow.next;
            slow.next = reverse;
            reverse = slow;
            slow = next;
        }

        if (fast != null){
            slow = slow.next;
        }

        while (slow != null){
            if (slow.element != reverse.element){
                return false;
            }
            slow = slow.next;
            reverse = reverse.next;
        }
        return true;
    }

    /**
     * 删除链表倒数第n个结点
     * 利用倒数和正数位置的关系，建立快慢指针
     * 第一步：先让快指针到指定位置
     * 第二步：快慢指针一起跑，直到快指针next为空，此时慢指针指向删除节点的前一个位置
     * 第三步：将慢指针所指向的位置next替换为删除节点的next,完成删除
     *         同时考虑删除头结点是直接把第二个结点设为头结点
     * @param list
     * @param n
     * @return
     */
    public static SNode deleteLastKth(SNode list,int n){
        if (list == null) return null;
        SNode fast = list;
        int i = 1;
        while (i < n){
            fast = fast.next;
            i ++;
        }

        SNode slow = list;
        SNode prev = null;
        while (fast.next != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        if (prev == null) {
            list = list.next;
        }else {
            prev.next = prev.next.next;
        }
        return list;
    }

    /**
     * 求链表的中间结点
     * 通过快慢指针的思想来解决
     * 假设链表共N个结点
     * 那么快指针需要跳N/2次，快指针的next为空
     * N/2刚好是慢指针的位置，也刚好是链表的中间结点
     * @param list
     * @return
     */
    public static SNode midNode(SNode list){
        SNode slow = list;
        SNode fast = list;
        while (slow != null && fast.next != null){
            slow = slow.next;
            fast = list.next.next;
        }
        return slow;
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
    public static void print2(Node nodes){
        Node node = nodes;
        while (node != null){
            System.out.print(node.getElement() + "--->");
            node = node.getNext();
        }
    }
}
