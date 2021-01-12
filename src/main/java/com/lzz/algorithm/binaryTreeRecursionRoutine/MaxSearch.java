package com.lzz.algorithm.binaryTreeRecursionRoutine;

import java.util.ArrayList;

/**
 * 最大子搜索二叉树的大小
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

    public static int getBSTSize(Node head) {
        if (head == null) {
            return 0;
        }
        ArrayList<Node> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).value <= arr.get(i - 1).value) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(Node head, ArrayList<Node> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(Node head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    public static int maxSubBSTSize2(Node head){
        TreeInfo result = process(head);
        if (result != null){
            return result.maxBSTSize;
        }
        return 0;
    }

    public static TreeInfo process(Node x){
        //如果x节点是空，直接给null
        if (x == null){
            return null;
        }
        //获取左子树的信息和右子树的信息
        TreeInfo left = process(x.left);
        TreeInfo right = process(x.right);
        //根据节点当前值、左子树信息和右子树信息得出当前树最大值和最小值信息
        int max = x.value;
        int min = x.value;
        if (left != null){
            max = Math.max(max,left.max);
            min = Math.min(min,left.min);
        }
        if (right != null){
            max = Math.max(max,right.max);
            min = Math.min(min,right.min);
        }
        int maxBSTSize = 0;
        boolean isAllBST = false;

        //与X节点无关时，分别取左子树或右子树的最大搜索数量
        if (left != null){
            maxBSTSize = left.maxBSTSize;
        }

        if (right != null){
            maxBSTSize = Math.max(maxBSTSize,right.maxBSTSize);
        }
        //与X节点有关时
        if (
                (left == null ? true : left.max < x.value)//左子树最大值小于当前节点值
                && (right == null ? true : right.min > x.value)//右子树最小值大于当前节点值
                && (left == null ? true : left.isAllBST)//左子树为最大搜索树
                && (right == null ? true : right.isAllBST)//右子树为最大搜索树
        ){
            //当前节点最大搜索树为左子树最大搜索数量+右子树最大搜索树数量+自己
            maxBSTSize =
                    (left == null ? 0 : left.maxBSTSize)
                    + (right == null ? 0 : right.maxBSTSize)
                    + 1;
            isAllBST = true;
        }
        return new TreeInfo(maxBSTSize,isAllBST,max,min);
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
        int maxBSTSize;
        boolean isAllBST;
        int max;
        int min;

        public TreeInfo(int maxBSTSize,boolean isAllBST,int max,int min){
            this.maxBSTSize = maxBSTSize;
            this.isAllBST = isAllBST;
            this.max = max;
            this.min = min;
        }
    }


    public static void main(String[] args) {
        int maxLevel = 4;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            int i1 = maxSubBSTSize1(head);
            int i2 = maxSubBSTSize2(head);
            if (i1 != i2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

}
