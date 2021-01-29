package com.lzz.algorithm.graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * 最小生成树算法之Prim
 * 1)可以从任意节点出发来寻找最小生成树
 * 2)某个点加入到被选取的点中后，解锁这个点出发的所有新的边
 * 3)在所有解锁的边中选最小的边，然后看看这个边会不会形成环
 * 4)如果会，不要当前边，继续考察剩下解锁的边中最小的边，重复3)
 * 5)如果不会，要当前边，将该边的指向点加入到被选取的点中，重复2)
 * 6)当所有点都被选取，最小生成树就得到了
 */
public class Prim {

    public static class MyPrimComparator implements Comparator<Edge> {

        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static Set<Edge> prim(Graph graph){
        //便于取出从某个节点出发的最小边
        PriorityQueue<Edge> queue = new PriorityQueue<>(new MyPrimComparator());
        //解锁过的点
        Set<Node> nodes = new HashSet<>();
        //解锁过的边
        Set<Edge> edges = new HashSet<>();

        //for循环防止一个图中包含多个不连接的图
        for (Node node : graph.nodes.values()){

            //如果解锁的点中不包含当前节点
            if (!nodes.contains(node)){
                //加入解锁的点中
                nodes.add(node);
                //把这个点的所有边放到队列里，以便拿到该点到其他点的最短距离
                for (Edge edge : graph.edges){
                    queue.add(edge);
                }
                while (!queue.isEmpty()){
                    Edge edge = queue.poll();
                    //当前边所指向的节点
                    Node toNode = edge.toNode;
                    //节点未解锁
                    if (!nodes.contains(toNode)){
                        //加入解锁集合
                        nodes.add(toNode);
                        //当前边一定是当前点到所有点最小的边，加入结果集
                        edges.add(edge);
                        //再将toNode所指向的边加入队列
                        for (Edge e : toNode.edges){
                            queue.add(e);
                        }
                    }
                }

            }



            break;
        }
        return edges;
    }


}
