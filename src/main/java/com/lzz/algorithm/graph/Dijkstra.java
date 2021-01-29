package com.lzz.algorithm.graph;

import java.util.HashSet;
import java.util.Set;

/**
 * 1)Dijkstra算法必须指定一个源点
 * 2)生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，
 *   源点到其他所有点的最小距离都为正无穷大
 * 3)从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4)源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class Dijkstra {

    public static Set<Edge> dijkstra(Node node){
        Set<Edge> result = new HashSet<>();
        return result;
    }
}