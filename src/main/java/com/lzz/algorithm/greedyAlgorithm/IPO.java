package com.lzz.algorithm.greedyAlgorithm;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 输入：正数数组costs、正数数组profits、正数K、正数M
 * cost[i]表示i号项目的花费
 * profits[i]表示i号项目在扣除花费之后还能换到的钱（利润）
 * K表示你只能串行的最多做k个项目
 * M表示你初始的资金
 * 说明：每做完一个项目，马上获得的收益，可以支持你去做下一个项目。不能并行的做项目
 * 输出：你最后获得的最大钱数
 */
public class IPO {

    public static int findMaximizedCapital(int k,int m,int[] costs,int[] profits){
        //小根堆按花费存放数据
        PriorityQueue<Program> minCosts = new PriorityQueue<>(new MinCompareCost());
        PriorityQueue<Program> maxProfit = new PriorityQueue<>(new MaxCompareProfit());
        //组装数据
        for (int i = 0;i < costs.length;i ++){
            minCosts.add(new Program(profits[i],costs[i]));
        }
        for (int i = 0;i < k;i ++){
            while (!minCosts.isEmpty() && minCosts.peek().c <= m){
                //本金允许的范围内将利润放入大根堆
                maxProfit.add(minCosts.poll());
            }
            if (maxProfit.isEmpty()){
                //空表示，初始资金不满足任何项目的启动资金
                return m;
            }
            //弹出当前能取得的最大利润求和
            m += maxProfit.poll().p;
        }
        return m;
    }

    public static class MinCompareCost implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxCompareProfit implements Comparator<Program> {
        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }
    }

    public static class Program{
        int p;
        int c;
        public Program(int p,int c){
            this.p = p;
            this.c = c;
        }
    }

    public static void main(String[] arg){
        int[] cost = new int[]{1,3,5};
        int[] profit = new int[]{1,2,3};
        int result = findMaximizedCapital(2, 3,cost, profit);
        System.out.println(result);
    }
}
