package com.lzz.algorithm.binaryTree;

/**
 * 二叉树某节点，返回该节点后继（下一个打印的结点）节点
 */
public class SuccessorNode {

    public static class Node{
        int value;
        Node left;
        Node right;
        Node parent;

        public Node(int value){
            this.value = value;
        }
    }

    public static Node getSuccessorNode(Node data){
        if (data == null){
            return data;
        }
        //如果右节点不为null,那么找到右节点的最左子节点，就是当前节点的后继节点
        if (data.right != null){
            return getLastLeft(data.right);
        }else {
            //如果parent是空说明该节点是这棵树的最右子节点
            //如果parent不为Null，并且当前节点不是父节点的右节点，那么该节点的后继节点某个父节点不是它父节点的右节点的节点‘<’类似这样的结构
            Node parent = data.parent;
            while (parent != null && data == parent.right){
                data = parent;
                parent = parent.parent;
            }
            return parent;
        }
    }

    public static Node getLastLeft(Node data){
        if (data == null){
            return data;
        }
        while (data.left != null){
            data = data.left;
        }
        return data;
    }

    public static void main(String[] args) {
        Node head = new Node(6);
        head.parent = null;
        head.left = new Node(3);
        head.left.parent = head;
        head.left.left = new Node(1);
        head.left.left.parent = head.left;
        head.left.left.right = new Node(2);
        head.left.left.right.parent = head.left.left;
        head.left.right = new Node(4);
        head.left.right.parent = head.left;
        head.left.right.right = new Node(5);
        head.left.right.right.parent = head.left.right;
        head.right = new Node(9);
        head.right.parent = head;
        head.right.left = new Node(8);
        head.right.left.parent = head.right;
        head.right.left.left = new Node(7);
        head.right.left.left.parent = head.right.left;
        head.right.right = new Node(10);
        head.right.right.parent = head.right;

        Node test = head.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.left.right.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.left;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right;
        System.out.println(test.value + " next: " + getSuccessorNode(test).value);
        test = head.right.right; // 10's next is null
        System.out.println(test.value + " next: " + getSuccessorNode(test));
    }
}
