package com.lzz.algorithm.graph;

import java.util.*;
import java.util.zip.Adler32;

/**
 * 图的拓扑排序算法
 * 1)在图中找到所有入度为0的点输出
 * 2)把所有入度为0的点在图中删掉，继续找入度为0的点输出，周而复始
 * 3)图的所有点都被删除后，依次输出的顺序就是拓扑排序
 *
 * 要求：有向图且其中没有环
 * 应用：事件安排、编译顺序
 */
public class TopologySort {

    public static List<Node> sortTopology(Graph graph){
        //将图中的node及node对应的输入数拍平
        HashMap<Node,Integer> inMap = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        for (Node cur : graph.nodes.values()){
            inMap.put(cur,cur.in);
            if (cur.in == 0){
                queue.add(cur);
            }
        }
        if (queue.isEmpty()){
            return null;
        }
        List<Node> result = new ArrayList<>();
        while (!queue.isEmpty()){
            Node cur = queue.poll();
            result.add(cur);
            for (Node next : cur.nexts){
                inMap.put(next,inMap.get(next) - 1);
                if (inMap.get(next) == 0){
                    queue.add(next);
                }
            }
        }

        return result;
    }

    public static void main(String[] arg){
        int[][] matrix = new int[][]{{5 , 0 , 7},{3 , 0,  1},{4 , 0,  6},{2 , 6,  1},{2 , 1,  3}};
        Graph graph = GraphGenerator.createGraph(matrix);
        List<Node> nodes = TopologySort.sortTopology(graph);
        nodes.forEach(x -> System.out.println(x.value));
    }
}
