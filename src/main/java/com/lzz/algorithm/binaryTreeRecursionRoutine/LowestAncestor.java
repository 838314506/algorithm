package com.lzz.algorithm.binaryTreeRecursionRoutine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 找到两个节点的最低交汇点
 */
public class LowestAncestor {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node lowestAncestor1(Node head, Node o1, Node o2) {
        if (head == null) {
            return null;
        }
        // key的父节点是value
        HashMap<Node, Node> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<Node> o1Set = new HashSet<>();
        Node cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(Node head, HashMap<Node, Node> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static Node lowestAncestor2(Node head,Node o1,Node o2){
        return process(head,o1,o2).ans;
    }

    public static TreeInfo process(Node x,Node o1,Node o2){
        if (x == null) {
            return new TreeInfo(false,false,null);
        }
        TreeInfo left = process(x.left, o1, o2);
        TreeInfo right = process(x.right, o1, o2);
        //在子树是否找到A，在子树是否找到B
        boolean findA = x == o1 || left.findA || right.findA;
        boolean findB = x == o2 || left.findB || right.findB;
        Node ans = null;
        if (left.ans != null){
            //如果左树上有结果
            ans = left.ans;
        }else if(right.ans != null){
            //如果右树上有结果
            ans = right.ans;
        }else {
            //两棵树都没有结果，但是找到了A也找到了B那么，当前节点就是交汇点
            if (findA && findB){
                ans = x;
            }
        }

        return new TreeInfo(findA,findB,ans);
    }

    public static class TreeInfo{
        boolean findA;
        boolean findB;
        Node ans;

        public TreeInfo(boolean findA,boolean findB,Node ans){
            this.findA = findA;
            this.findB = findB;
            this.ans = ans;
        }
    }


    // for test
    public static Node generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static Node generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        Node head = new Node((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

    // for test
    public static Node pickRandomOne(Node head) {
        if (head == null) {
            return null;
        }
        ArrayList<Node> arr = new ArrayList<>();
        fillPrelist(head, arr);
        int randomIndex = (int) (Math.random() * arr.size());
        return arr.get(randomIndex);
    }

    // for test
    public static void fillPrelist(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        arr.add(head);
        fillPrelist(head.left, arr);
        fillPrelist(head.right, arr);
    }

    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Node o1 = pickRandomOne(head);
            Node o2 = pickRandomOne(head);
            if (lowestAncestor1(head, o1, o2) != lowestAncestor2(head, o1, o2)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
