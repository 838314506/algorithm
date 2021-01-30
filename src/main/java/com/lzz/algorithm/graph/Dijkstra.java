package com.lzz.algorithm.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 必须要求图的边不为负
 * 得到源点到其他所有点的最短距离
 * 1)Dijkstra算法必须指定一个源点
 * 2)生成一个源点到各个点的最小距离表，一开始只有一条记录，即原点到自己的最小距离为0，
 *   源点到其他所有点的最小距离都为正无穷大
 * 3)从距离表中拿出没拿过记录里的最小记录，通过这个点发出的边，更新源点到各个点的最小距离表，不断重复这一步
 * 4)源点到所有的点记录如果都被拿过一遍，过程停止，最小距离表得到了
 */
public class Dijkstra {

    public static HashMap<Node,Integer> dijkstra1(Node node){
        //结果集
        HashMap<Node,Integer> distanceMap = new HashMap<>();
        distanceMap.put(node,0);
        //黑名单
        Set<Node> selected = new HashSet<>();

        //从结果集中获取当前weight的最小节点
        Node minNode = getMinNodeAndUnSelectNode(distanceMap,selected);
        while (minNode != null){
            //最小节点的所有边
            for (Edge edge : minNode.edges){
                Node toNode = edge.toNode;
                int distance = distanceMap.get(minNode);
                if (!distanceMap.containsKey(toNode)){
                    //不存在加入集合
                    distanceMap.put(toNode,edge.weight);
                }else {
                    //存在更新最小值
                    distanceMap.put(toNode,Math.min(distanceMap.get(toNode),distance + edge.weight));
                }
            }
            //使用过了加入黑名单
            selected.add(minNode);
            //更换当前节点
            minNode = getMinNodeAndUnSelectNode(distanceMap,selected);
        }
        return distanceMap;
    }

    public static Node getMinNodeAndUnSelectNode(HashMap<Node,Integer> distanceMap,Set<Node> selected){
        int minDistance = Integer.MAX_VALUE;
        Node minNode = null;
        for(Map.Entry<Node,Integer> entry : distanceMap.entrySet()){
            Node node = entry.getKey();
            int distance = entry.getValue();
            if (!selected.contains(node) && distance < minDistance){
                minDistance = distance;
                minNode = node;
            }
        }
//        for (Node node : distanceMap.keySet()){
//            int oldDistc = distanceMap.get(node);
//            if (!selected.contains(node) && oldDistc < distance){
//                minNode = node;
//                distance = oldDistc;
//            }
//        }
        return minNode;
    }


    /**
     * 使用小根堆来实现迪杰特斯拉改进，hashset处每次都要进行遍历复杂度O(n)
     * 小根堆实现复杂度为O(logn)
     * @param node
     * @param size
     * @return
     */
    public static HashMap<Node,Integer> dijkstra2(Node node,int size){
        NodeHeap nodeHeap = new NodeHeap(size);
        nodeHeap.addOrUpdateOrIgnore(node,0);
        HashMap<Node,Integer> result = new HashMap<>();

        while (nodeHeap.isEmpty()){
            NodeRecord record = nodeHeap.pop();
            Node cur = record.node;
            int distance = record.distance;
            for (Edge edge : cur.edges){
                nodeHeap.addOrUpdateOrIgnore(edge.toNode,edge.weight + distance);
            }
            result.put(cur,distance);
        }

        return result;
    }

    /**
     * 用来标记节点和最小距离
     */
    public static class NodeRecord{
        public Node node;
        public int distance;

        public NodeRecord(Node node,int distance){
            this.node = node;
            this.distance = distance;
        }
    }
    //当系统提供的堆无法满足对堆的需求时，手写小根堆
    public static class NodeHeap{

        private Node[] heapNode; // 实际的堆结构
        // key 某一个node， value 上面堆中的位置
        private HashMap<Node, Integer> indexMap;
        // key 某一个节点， value 从源节点出发到该节点的目前最小距离
        private HashMap<Node, Integer> distanceMap;
        private int size; // 堆上有多少个点

        //初始化，赋默认值
        public NodeHeap(int size){
            this.heapNode = new Node[size];
            this.size = 0;
            this.distanceMap = new HashMap<>();
            this.indexMap = new HashMap<>();
        }

        public boolean isEmpty(){
            return size == 0;
        }

        /**
         * 交换
         * 注意在交换的时候不但要交换集合中的数据还要交换元素下标值
         * @param index1
         * @param index2
         */
        public void swap(int index1,int index2){
            indexMap.put(heapNode[index1],index2);
            indexMap.put(heapNode[index2],index1);
            Node temp = heapNode[index1];
            heapNode[index1] = heapNode[index2];
            heapNode[index2] = temp;
        }

        //删除节点
        public void heapify(int index,int size){
            int left = index * 2 + 1;
            while (left < size){
                int smallest = left + 1 < size && distanceMap.get(heapNode[left + 1]) < distanceMap.get(heapNode[left])
                        ? left + 1 : left;
                smallest = distanceMap.get(heapNode[smallest]) < distanceMap.get(heapNode[index]) ? smallest : index;
                if (smallest == index){
                    break;
                }
                swap(smallest,index);
                index = smallest;
                left = index * 2 + 1;
            }
        }

        /**
         * 弹出第一个堆顶数据
         * @return
         */
        public NodeRecord pop(){
            NodeRecord record = new NodeRecord(heapNode[0],distanceMap.get(heapNode[0]));
            swap(0,size - 1);
            distanceMap.remove(heapNode[size - 1]);
            indexMap.put(heapNode[size - 1],-1);
            heapNode[size - 1] = null;
            heapify(0,--size);
            return record;
        }

        //在堆上前提条件是进入过并且距离不为-1（未禁用）
        public boolean inHeap(Node node){
            return isEntered(node) && distanceMap.get(node) != -1;
        }

        //是否进入过
        public boolean isEntered(Node node){
            return indexMap.containsKey(node);
        }

        public void addOrUpdateOrIgnore(Node node,int distance){
            //如果在堆上说明是更新distance
            if (inHeap(node)){
                distanceMap.put(node,Math.min(distanceMap.get(node),distance));
                insertHeapify(node,indexMap.get(node));
            }
            //如果不在则新增
            if (!isEntered(node)){
                heapNode[size] = node;
                indexMap.put(node,size);
                distanceMap.put(node,distance);
                insertHeapify(node,size ++);
            }
            //否则忽略
        }

        //向堆中插入数据
        public void insertHeapify(Node node,int index){
            //从下往上找
            while (distanceMap.get(heapNode[index]) < distanceMap.get(heapNode[(index - 1) / 2])){
                swap(index,(index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

    }
}
