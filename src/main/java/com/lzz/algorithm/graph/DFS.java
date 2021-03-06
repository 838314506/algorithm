package com.lzz.algorithm.graph;

import java.util.HashSet;
import java.util.Stack;

/**
 * 按深度遍历
 * 利用栈实现
 * 从源节点开始把节点按照深度放入栈，然后弹出
 * 每弹出一个点，把该节点下一个没有进过栈的邻接点放入栈
 * 直到栈变空
 * 一条路走到最后，再往上返回
 */
public class DFS {

    public static void dfs(Node start) {
        if (start == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        HashSet<Node> set = new HashSet<>();
        stack.add(start);
        set.add(start);
        System.out.println(start.value);
        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            for (Node next : cur.nexts){
                if (!set.contains(next)){
                    stack.add(cur);
                    stack.add(next);
                    set.add(next);
                    System.out.println(next.value);
                    break;
                }
            }
        }
    }
    public static void main(String[] arg){
        int[][] matrix = new int[][]{{5 , 0 , 7},{3 , 0,  1},{4 , 0,  6},{2 , 6,  1},{2 , 1,  3}};
        Graph graph = GraphGenerator.createGraph(matrix);
        DFS.dfs(graph.nodes.get(0));
        System.out.println();
    }
}
