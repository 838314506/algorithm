package com.lzz.algorithm.graph;

/**
 * 最小生成树算法之Kruskal
 * 1)总是从权值最小的边开始考虑，依次考察权值依次变大的边
 * 2)当前的边要么进入最小生成树的集合，要么丢弃
 * 3)如果当前的边进入最小生成树的集合中不会形成环，就要当前边
 * 4)如果当前的边进入最小生成树的集合中会形成环，就不要当前边
 * 5)考察完所有边之后，最小生成树的集合也得到了
 *
 * 利用交并集实现
 */
public class Kruskal {

    public static class UnionFindSet{
        public boolean isSameSet(){
            return false;
        }


    }
}
