package com.lzz.algorithm.binaryTree;

import java.util.Stack;

/**
 * 非递归遍历，利用栈进行遍历
 */
public class UnRecursiveTraversalBT {
    
    public static class Node{
        int value;
        Node left;
        Node right;
        
        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 先序遍历
     * @param head
     */
    public static void pre(Node head){
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            System.out.println(head.value);
            if (head.right != null){
                stack.add(head.right);
            }
            if (head.left != null){
                stack.add(head.left);
            }
        }
    }

    /**
     * 中序遍历
     * @param head
     */
    public static void in(Node head){
        if (head == null) return;
        Stack<Node> stack = new Stack<>();
//        stack.add(head);
        while (!stack.isEmpty() || head != null){
            if (head != null){
                stack.push(head);
                head = head.left;
            }else{
                head = stack.pop();
                System.out.println(head.value);
                head = head.right;
            }
        }
    }

    /**
     * 后序遍历
     * @param head
     */
    public static void pos1(Node head){
        if (head == null) return;
        if (head == null) return;

        Stack<Node> stack = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack.add(head);
        while (!stack.isEmpty()){
            head = stack.pop();
            stack2.push(head);
            if (head.left != null){
                stack.add(head.left);
            }
            if (head.right != null){
                stack.add(head.right);
            }
        }
        for (int n = stack2.size(),i = 0;i < n;i ++){
            System.out.println(stack2.pop().value);
        }
    }

    /**
     * 后序遍历，炫技版
     * @param h
     */
    public static void pos2(Node h){
        if (h == null) return;
        Stack<Node> stack = new Stack<>();
        Node c = h;
        stack.push(h);
        while (! stack.isEmpty()){
            c = stack.peek();
            if (c.left != null && h != c.left && h != c.right){
                stack.push(c.left);
            }else if (c.right != null && h != c.right){
                stack.push(c.right);
            }else {
                System.out.println(stack.pop().value);
                h = c;
            }
        }
    }

    public static void main(String[] arg){
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

//        pre(head);
        System.out.println("========");
        in(head);
        System.out.println("========");
        pos1(head);
        System.out.println("========");
        pos2(head);
        System.out.println("========");
    }
}
