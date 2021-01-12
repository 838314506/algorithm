package com.lzz.algorithm.binaryTreeRecursionRoutine;

import java.util.LinkedList;

/**
 * 判断一棵树是否是完全二叉树
 */
public class IsCBT {
    
    public static class Node{
        int value;
        Node left;
        Node right;
        public Node(int value){
            this.value = value;
        }
    }

    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }
        LinkedList<Node> queue = new LinkedList<>();
        // 是否遇到过左右两个孩子不双全的节点
        boolean leaf = false;
        Node l = null;
        Node r = null;
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            l = head.left;
            r = head.right;
            if (
                // 如果遇到了不双全的节点之后，又发现当前节点不是叶节点
                    (leaf && (l != null || r != null)) || (l == null && r != null)

            ) {
                return false;
            }
            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }
            if (l == null || r == null) {
                leaf = true;
            }
        }
        return true;
    }


    public static boolean isCBT2(Node x){
        return process(x).isCBT;
    }

    public static TreeInfo process(Node x){
        if (x == null){
            return new TreeInfo(0,true,true);
        }
        TreeInfo left = process(x.left);
        TreeInfo right = process(x.right);

        int height = Math.max(left.height,right.height) + 1;
        //是否为满二叉树
        boolean isFull = left.isFull && right.isFull && left.height == right.height;
        boolean isCBT = false;
        if (left.isFull && right.isFull && left.height == right.height){
            //左树和右树均为满二叉树，且高度相同
            isCBT = true;
        }else if(left.isCBT && right.isFull && left.height == right.height + 1){
            //左树为完全二叉树但右树要为满二叉树，且高度差1，缺口在左树
            isCBT = true;
        }else if (left.isFull && right.isFull && left.height == right.height + 1){
            //左树为满二叉树，右树为满二叉树，且左右相关高度为1，缺口刚好是左右之间
            isCBT = true;
        }else if (left.isFull && right.isCBT && left.height == right.height){
            //左树为满二叉树，右树为完全二叉树，且高度相同，也就是缺口在右树
            isCBT = true;
        }

        return new TreeInfo(height,isFull,isCBT);
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

    public static void main(String[] args) {
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            boolean i1 = isCBT1(head);
            boolean i2 = isCBT2(head);
            if (i1 != i2) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }

    public static class TreeInfo{
        int height;
        boolean isFull;
        boolean isCBT;

        public TreeInfo(int height,boolean isFull,boolean isCBT){
            this.height = height;
            this.isFull = isFull;
            this.isCBT = isCBT;
        }
    }

}
