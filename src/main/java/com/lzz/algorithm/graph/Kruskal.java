package com.lzz.algorithm.graph;

import java.util.*;

/**
 * 最小生成树算法之Kruskal
 * 1)总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2)当前的边要么进入最小生成树的集合，要么丢弃
 * 3)如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4)如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5)考察完所有边之后，最小生成树的集合也得到了
 * <p>
 * 利用交并集实现
 */
public class Kruskal {

    //小根堆排序
    public static class MyComparator implements Comparator<Edge> {
        @Override
        public int compare(Edge o1, Edge o2) {
            return o1.weight - o2.weight;
        }
    }

    public static HashSet<Edge> kruskal(Graph graph) {
        //小根堆，每次取最小边
        PriorityQueue<Edge> queue = new PriorityQueue<>(new MyComparator());
        HashSet<Edge> result = new HashSet<>();
        UnionFindSet ufs = new UnionFindSet(graph);
        //将所有边放入队列
        for (Edge edge : graph.edges) {
            queue.add(edge);
        }
        while (!queue.isEmpty()) {
            Edge cur = queue.poll();
            //利用并查集，先在parent中是否已包含两个节点
            if (!ufs.isSameSet(cur.fromNode, cur.toNode)) {
                //不包含将节点合并
                ufs.union(cur.fromNode, cur.toNode);
                //对应的边是需要的边，放入结果集
                result.add(cur);
            }
        }
        return result;
    }


    public static class UnionFindSet {
        //from -> to父子关系
        public HashMap<Node, Node> parentNode;
        //合并，将节点少的合并到节点多的
        public HashMap<Node, Integer> sizeMap;

        //初始化并查集集合
        public UnionFindSet(Graph graph) {
            sizeMap = new HashMap<>();
            parentNode = new HashMap<>();
            for (Node node : graph.nodes.values()) {
                sizeMap.put(node, 1);
                parentNode.put(node, node);
            }
        }


        public Node findParent(Node n) {
            //栈记录该节点所有父节点，最后n一定是最顶点的父节点
            Stack<Node> stack = new Stack<>();
            while (n != parentNode.get(n)) {
                n = parentNode.get(n);
                stack.add(n);
            }
            //拍平，将所有经过的父节点与最顶点的父节点存放在集合中便于后继使用
            while (!stack.isEmpty()) {
                parentNode.put(stack.pop(), n);
            }
            return n;
        }

        public boolean isSameSet(Node x, Node y) {
            return findParent(x) == findParent(y);
        }

        public void union(Node x, Node y) {
            if (x == null || y == null) {
                return;
            }
            Node xP = findParent(x);
            Node yP = findParent(y);
            if (xP != yP) {
                int xSize = sizeMap.get(xP);
                int ySize = sizeMap.get(yP);
                if (xSize <= ySize) {
                    parentNode.put(xP,yP);
                    sizeMap.put(yP,xSize + ySize);
                    sizeMap.remove(xP);
                } else {
                    parentNode.put(yP,xP);
                    sizeMap.put(xP,xSize + ySize);
                    sizeMap.remove(yP);
                }
            }
        }

    }

    public static void main(String[] arg){
        int[][] matrix = new int[][]{{5 , 0 , 7},{3 , 0,  1},{4 , 0,  6},{2 , 6,  1},{2 , 1,  3},{1 , 7 , 3},{6 , 6 , 2},{7 , 2 , 3}};
        Graph graph = GraphGenerator.createGraph(matrix);
        HashSet<Edge> kruskal = Kruskal.kruskal(graph);
        kruskal.forEach(x -> System.out.println(x.weight));
    }
}
