package com.lzz.algorithm.binaryTree;

/**
 * 直观地打印一棵二叉树
 * 使用方法将二叉树垂直90逆时针旋转
 */
public class PrintBinaryTree {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static void printTree(Node head){
        System.out.println("BinaryTree Print: ");
        printInOrder(head,"H",0,17);
        System.out.println();
    }

    /**
     *
     * @param head 节点
     * @param sign 标识表示是头节点、右节点、左节点
     * @param height 节点所在高度，与第个节点x轴所在位置有关
     * @param len  为避免数据长短导致打印不对齐，在数字两边加padding补齐
     */
    public static void printInOrder(Node head,String sign,int height,int len){
        if (head == null){
            return;
        }
        printInOrder(head.right,"v",height + 1,len);

        String val = sign + head.value + sign;
        int lenVal = val.length();
        int lenL = (len - lenVal) / 2;
        int lenR = len - lenL - lenVal;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left,"^",height + 1,len);
    }

    public static String getSpace(int len){
        StringBuilder sb = new StringBuilder();
        for (int i = 0;i < len;i ++){
            sb.append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.left = new Node(-222222222);
        head.right = new Node(3);
        head.left.left = new Node(Integer.MIN_VALUE);
        head.right.left = new Node(55555555);
        head.right.right = new Node(66);
        head.left.left.right = new Node(777);
        printTree(head);

        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.right.left = new Node(5);
        head.right.right = new Node(6);
        head.left.left.right = new Node(7);
        printTree(head);

        head = new Node(1);
        head.left = new Node(1);
        head.right = new Node(1);
        head.left.left = new Node(1);
        head.right.left = new Node(1);
        head.right.right = new Node(1);
        head.left.left.right = new Node(1);
        printTree(head);

    }
}
