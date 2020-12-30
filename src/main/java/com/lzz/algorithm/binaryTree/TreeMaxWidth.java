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
        int curLevelNodeCount = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(h);
        //node为key，层数为value
        Map<Node,Integer> map = new HashMap<>();
        map.put(h,1);
        while (! queue.isEmpty()){

        }


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
