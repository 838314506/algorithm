package com.lzz.algorithm.unionFindSet;

/**
 * 1、有若干个样本a,b,c。。。类型假设是V
 * 2、在并查集中一开始认为每个元素都在单独的集合中
 * 3、用户可以在任何时候调用如下两个方法：
 *      boolean isSameSet(V x,V y)样本x和样本y是否属于同一个集合
 *      void union(V x,V y)把x和y各自所在的集合的所有样本合并成一个集合
 * 4、isSameSet和union的代价越低越好
 */
public class UnionFindSet {

    public static class Node<V>{
        V value;
        public Node(V v){
            this.value = v;
        }
    }



}
