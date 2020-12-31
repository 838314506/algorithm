package com.lzz.algorithm.binaryTree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

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
        int curLevel = 1;
        int curLevelNodeCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(h);
        //node为key，层数为value
        Map<Node,Integer> map = new HashMap<>();
        map.put(h,1);
        while (! queue.isEmpty()){
            Node currentNode = queue.poll();
            int curNodeLevel = map.get(currentNode);
            if (currentNode.left != null){
                map.put(currentNode.left,curLevel + 1);
                queue.add(currentNode.left);
            }
            if (currentNode.right != null){
                map.put(currentNode.right,curLevel + 1);
                queue.add(currentNode.right);
            }
            if (curNodeLevel == curLevel){
                curLevelNodeCount ++;
            }else {
                max = Math.max(max,curLevelNodeCount);
                curLevel ++;
                curLevelNodeCount = 1;
            }
        }
        max = Math.max(max,curLevelNodeCount);

        return max;
    }

    //不用map
    public static int maxWidthNoMap(Node h){
        if (h == null) return 0;
        int max = 0;
        int curLevelCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(h);
        while (! queue.isEmpty()){

        }
        return max;
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
        int maxLevel = 10;
        int maxValue = 100;
        int testTimes = 1000000;
        for (int i = 0; i < testTimes; i++) {
            Node head = generateRandomBST(maxLevel, maxValue);
            if (maxWidthUseMap(head) != maxWidthNoMap(head)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
