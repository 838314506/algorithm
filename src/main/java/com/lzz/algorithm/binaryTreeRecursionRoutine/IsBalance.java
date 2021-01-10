package com.lzz.algorithm.binaryTreeRecursionRoutine;

/**
 * 判断一棵树是否为平衡二叉树
 * 二叉树的dp问题
 */
public class IsBalance {

    public static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static boolean isBalanced1(Node head) {
        boolean[] ans = new boolean[1];
        ans[0] = true;
        process1(head, ans);
        return ans[0];
    }

    public static int process1(Node head, boolean[] ans) {
        if (!ans[0] || head == null) {
            return -1;
        }
        int leftHeight = process1(head.left, ans);
        int rightHeight = process1(head.right, ans);
        if (Math.abs(leftHeight - rightHeight) > 1) {
            ans[0] = false;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static boolean isBalanced2(Node head){
        return process(head).isBalance;
    }

    public static TreeInfo process(Node head){
        if (head == null){
            return new TreeInfo(1,true);
        }
        TreeInfo left = process(head.left);
        TreeInfo right = process(head.right);
        int height = Math.max(left.height, right.height) + 1;
        boolean isBalance = true;
        if (!left.isBalance){
            isBalance = false;
        }
        if (!right.isBalance){
            isBalance = false;
        }
        if (Math.abs(left.height - right.height) > 1){
            isBalance = false;
        }
        return new TreeInfo(height, isBalance);
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


    public static void main(String[] args){
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (isBalanced1(head) != isBalanced2(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class TreeInfo{
        int height;
        boolean isBalance;
        
        public TreeInfo(int height,boolean isBalance){
            this.height = height;
            this.isBalance = isBalance;
        }
    }
}
