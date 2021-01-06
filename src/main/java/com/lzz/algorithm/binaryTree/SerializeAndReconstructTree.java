package com.lzz.algorithm.binaryTree;

import javafx.beans.binding.When;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树序列化与反序列化（前序、后序以及按层序列化）
 * 二叉树无法通过中序遍历的方式序列化与反序列化
 * 不同的两棵树按中序序列化以后数据相同，使用null补充空缺值也一样
 *           __2
 *          /
 *         1
 *         和
 *         1__
 *            \
 *             2
 *  中序序列化后，用null补充都{null,1,null,2,null}
 */
public class SerializeAndReconstructTree {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 先序遍历序列化二叉树
     * @param head
     * @return
     */
    public static Queue<String> preSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pre(head,queue);
        return queue;
    }

    public static void pre(Node head,Queue<String> queue){
        if (head == null){
            queue.add(null);
        }else {
            queue.add(String.valueOf(head.value));
            pre(head.left,queue);
            pre(head.right,queue);
        }
    }

    /**
     * 先序反序列化二叉树
     * @param pre
     * @return
     */
    public static Node buildByPreQueue(Queue<String> pre){
        if (pre == null || pre.size() == 0){
            return null;
        }
        return preb(pre);
    }

    public static Node preb(Queue<String> pre){
        String val = pre.poll();
        if (val == null){
            return null;
        }
        Node node = new Node(Integer.valueOf(val));
        node.left = preb(pre);
        node.right = preb(pre);
        return node;
    }

    /**
     * 中序遍历序列化
     * @param head
     * @return
     */
    public static Queue<String> inSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        in(head,queue);
        return queue;
    }

    public static void in(Node head,Queue<String> queue){
        if (head == null){
            queue.add(null);
        }else {
            in(head.left,queue);
            queue.add(String.valueOf(head.value));
            in(head.right,queue);
        }
    }

    /**
     * 后序遍历序列化二叉树
     * @param head
     * @return
     */
    public static Queue<String> posSerial(Node head) {
        Queue<String> queue = new LinkedList<>();
        pos(head,queue);
        return queue;
    }

    public static void pos(Node head,Queue<String> queue){
        if (head == null){
            queue.add(null);
        }else {
            pos(head.left,queue);
            pos(head.right,queue);
            queue.add(String.valueOf(head.value));
        }
    }

    /**
     * 将通过后序序列化的队列，反序列化
     * 将队列放入栈中，发现顺序是   头->右->左
     * 之后再通过递归还原二叉树
     * @param queue
     * @return
     */
    public static Node buildByPosQueue(Queue<String> queue){
        if (queue == null || queue.size() == 0){
            return null;
        }

        Stack<String> stack = new Stack<>();
        while (!queue.isEmpty()){
            stack.add(queue.poll());
        }
        return posb(stack);
    }

    public static Node posb(Stack<String> stack){
        String val = stack.pop();
        if (val == null){
            return null;
        }
        Node head = new Node(Integer.valueOf(val));
        head.right = posb(stack);
        head.left = posb(stack);
        return head;
    }

    /**
     * 按层进行序列化
     * @param head
     * @return
     */
    public static Queue<String> levelSerial(Node head) {
        Queue<String> queue = new LinkedList<>();

        level(head,queue);
        return queue;
    }

    /**
     * 通过按层遍历，遍历时加入到序列化队列中
     * @param head
     * @param queue
     */
    public static void level(Node head,Queue<String> queue){
        if (head == null){
            queue.add(null);
        }else {
            queue.add(String.valueOf(head.value));
            Queue<Node> nodes = new LinkedList<>();
            nodes.add(head);
            while (! nodes.isEmpty()){
                Node cur = nodes.poll();
                if (cur.left != null){
                    nodes.add(cur.left);
                    queue.add(String.valueOf(cur.left.value));
                }else {
                    queue.add(null);
                }
                if (cur.right != null){
                    nodes.add(cur.right);
                    queue.add(String.valueOf(cur.right.value));
                }else {
                    queue.add(null);
                }
            }
        }
    }

    /**
     * 序列化还原二叉树
     * @param queue
     * @return
     */
    public static Node buildByLevelQueue(Queue<String> queue){
        if (queue == null || queue.size() == 0){
            return null;
        }
        Node head = generateNode(queue.poll());
        Queue<Node> que = new LinkedList<>();
        if (head != null){
            que.add(head);
        }
        Node node = null;
        while (!que.isEmpty()){
            node = que.poll();
            node.left = generateNode(queue.poll());
            node.right = generateNode(queue.poll());
            if (node.left != null){
                que.add(node.left);
            }
            if (node.right != null){
                que.add(node.right);
            }
        }
        return head;
    }

    public static Node generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new Node(Integer.valueOf(val));
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
    public static boolean isSameValueStructure(Node head1, Node head2) {
        if (head1 == null && head2 != null) {
            return false;
        }
        if (head1 != null && head2 == null) {
            return false;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        if (head1.value != head2.value) {
            return false;
        }
        return isSameValueStructure(head1.left, head2.left) && isSameValueStructure(head1.right, head2.right);
    }

    // for test
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }

    public static void main(String[] arg){
        int maxLevel = 5;
        int maxValue = 100;
        int testTimes = 1000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            Queue<String> pre = preSerial(head);
            Queue<String> pos = posSerial(head);
            Queue<String> level = levelSerial(head);
            Node preBuild = buildByPreQueue(pre);
            Node posBuild = buildByPosQueue(pos);
            Node levelBuild = buildByLevelQueue(level);
            if (!isSameValueStructure(preBuild, posBuild) || !isSameValueStructure(posBuild, levelBuild)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish!");
    }




}
