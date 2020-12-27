package com.lzz.algorithm.binaryTree;

/**
 * 获取二叉树最宽层，节点数量
 * 重点：建立层结束机制
 */
public class TreeMaxWidth {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }

    //通过map
    public static int maxWidthUseMap(Node h){
        if (h == null) return 0;
        int max = 0;
        return max;
    }

    //不用map
    public static int maxWidthNoMap(Node h){
        if (h == null) return 0;
        int max = 0;
        return max;
    }

    public static void main(String[] args){

    }
}
