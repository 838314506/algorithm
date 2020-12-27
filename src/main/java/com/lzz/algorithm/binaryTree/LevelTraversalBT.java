package com.lzz.algorithm.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 按层遍历树
 */
public class LevelTraversalBT {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }

    public static void level(Node node){
        if (node == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        while (! queue.isEmpty()){
            Node poll = queue.poll();
            System.out.println(poll.value);
            if (poll.left != null){
                queue.add(poll.left);
            }
            if (poll.right != null){
                queue.add(poll.right);
            }
        }
    }

    public static void main(String[] args){
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        level(head);
        System.out.println("========");
    }
}
