package com.lzz.algorithm.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 图按宽度遍历
 * 利用队列实现
 * 从源节点开始依次按照宽度进队列，然后弹出
 * 每弹出一个点，把该节点所有没有进过队列的邻接点放入队列
 * 直到队列变空
 * 先遍历离我最近的一层，然后下一层，某一层有多个点，不要求顺序
 */
public class BFS {

    public static void bfs(Node start){
        if (start == null){
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        //用set来存储遍历过的数据，否则会导致死循环
        HashSet set = new HashSet();
        queue.add(start);
        set.add(start);

        while (!queue.isEmpty()){
            Node cur = queue.poll();
            System.out.println(cur.value);
            for (Node next : cur.nexts){
                if (!set.contains(next)){
                    queue.add(next);
                    set.add(next);
                }
            }
        }

    }

    public static void main(String[] arg){
        int[][] matrix = new int[][]{{5 , 0 , 7},{3 , 0,  1},{4 , 0,  6},{2 , 6,  1},{2 , 1,  3}};
        Graph graph = GraphGenerator.createGraph(matrix);
        BFS.bfs(graph.nodes.get(0));
        System.out.println();
    }
}
