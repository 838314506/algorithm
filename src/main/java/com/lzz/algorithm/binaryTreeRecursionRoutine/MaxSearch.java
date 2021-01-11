package com.lzz.algorithm.binaryTreeRecursionRoutine;

/**
 * 最大搜索树
 */
public class MaxSearch {

    public static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static TreeInfo maxSearch(Node X){
        if (X == null){
            return null;
        }
        TreeInfo left = maxSearch(X.left);
        TreeInfo right = maxSearch(X.left);
        int size = 0;
        int max = 0;
        int min = 0;
        Node maxSearchNode = null;
        if (left != null){
            max = left.max;
            size = left.size;
        }
        if (right != null){
            min = right.min;
            size = Math.max(size,right.size);
        }

        return new TreeInfo(size,maxSearchNode,max,min);
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

    public static class TreeInfo{
        int size;
        Node maxSearchNode;
        int max;
        int min;

        public TreeInfo(int size,Node maxSearchNode,int max,int min){
            this.size = size;
            this.maxSearchNode = maxSearchNode;
            this.max = max;
            this.min = min;
        }
    }


    public static void main(String[] args){
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxSearch(head) != maxSearch(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
