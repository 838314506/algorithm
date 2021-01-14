package com.lzz.algorithm.unionFindSet;

import java.util.*;

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

    public static class UnionFindNode<V>{
        public Map<V,Node<V>> nodeInfo;
        public Map<Node<V>,Node<V>> parentNodes;
        public Map<Node<V>,Integer> size;

        public UnionFindNode(List<V> values){
            nodeInfo = new HashMap<>();
            parentNodes = new HashMap<>();
            size = new HashMap<>();
            for (V cur : values){
                Node<V> node = new Node<V>(cur);
                nodeInfo.put(cur,node);
                parentNodes.put(node,node);
                size.put(node,1);
            }
        }

        //给一个节点，找出它的父节点
        public Node findParent(Node<V> cur){
            //1找父节点，
            Stack<Node<V>> path = new Stack<>();
            while (cur != parentNodes.get(cur)){
                path.push(cur);
                cur = parentNodes.get(cur);
            }
            //2将节点链拍平，降低时间复杂度
            while (!path.isEmpty()){
                parentNodes.put(path.pop(),cur);
            }
            return cur;
        }

        public boolean isSameSet(V x,V y){
            //x或y不在集合中直接返回false
            if (!nodeInfo.containsKey(x) || !nodeInfo.containsKey(y)){
                return false;
            }
            return findParent(nodeInfo.get(x)) == findParent(nodeInfo.get(y));
        }

        public void union(V x,V y){
            //如果不存在这两个节点直接返回
            if (!nodeInfo.containsKey(x) || !nodeInfo.containsKey(y)){
                return;
            }

            Node xHead = findParent(nodeInfo.get(x));
            Node yHead = findParent(nodeInfo.get(y));

            if (xHead != yHead){
                int xSize = size.get(xHead);
                int ySize = size.get(yHead);
                Node big = xSize > ySize ? xHead : yHead;
                Node small = big == xHead ? yHead : xHead;
                parentNodes.put(small,big);
                size.put(big,xSize + ySize);
                size.remove(small);
            }
        }

        public static void main(String[] arg){
            List<Integer> nodes = new ArrayList<>();
            nodes.add(1);
            nodes.add(2);
            nodes.add(3);
            nodes.add(4);
            nodes.add(5);
            nodes.add(6);
            UnionFindNode ufn = new UnionFindNode(nodes);
            ufn.parentNodes.put(ufn.nodeInfo.get(1),ufn.nodeInfo.get(3));
            ufn.parentNodes.put(ufn.nodeInfo.get(2),ufn.nodeInfo.get(3));
            ufn.parentNodes.put(ufn.nodeInfo.get(4),ufn.nodeInfo.get(5));
            ufn.size.put(ufn.nodeInfo.get(3),3);
            ufn.size.put(ufn.nodeInfo.get(5),2);
//            System.out.println(ufn.isSameSet(1,2));
            ufn.union(3,5);
            System.out.println("finished");
        }
    }


}
