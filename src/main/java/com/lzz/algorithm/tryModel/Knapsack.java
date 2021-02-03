package com.lzz.algorithm.tryModel;

/**
 * 装包问题
 * 给定两个长度都为N的数组，weights和values
 * weight[i]和values[i]分别代表i号物品的重量和价值
 * 给定一个正数bag，表示一个载重bag的袋子，装的物品不能超过这个重量
 * 返回你能装下最多的价值的多少
 * 2种方法
 */
public class Knapsack {

    // 所有的货，重量和价值，都在w和v数组里
    // 为了方便，其中没有负数
    // bag背包容量，不能超过这个载重
    // 返回：不超重的情况下，能够得到的最大价值
    public static int maxValue(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length != v.length || w.length == 0){
            return 0;
        }
        return process(w,v,bag,0);
    }

    // index 0~N
    // rest 负~bag
    public static int process(int[] w,int[] v,int rest,int index){
        if (rest < 0){
            return -1;
        }
        if (index == w.length){
            return 0;
        }
        int p1 = process(w,v,rest,index + 1);
        int p2 = -1;
        int p2Next = process(w,v,rest - w[index],index + 1);
        if (p2Next != -1){
            p2 = v[index] + p2Next;
        }
        return Math.max(p1,p2);
    }


    //对数器
    public static int dp(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length != v.length || w.length == 0){
            return 0;
        }
        int N = w.length;
        int[][] dp = new int[N + 1][bag + 1];

        for (int index = N - 1;index >= 0;index --){
            for (int rest = 0;rest <= bag;rest ++){
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                int p2Next = rest - w[index] < 0 ? -1 : dp[index + 1][rest - w[index]];
                if (p2Next != -1){
                    p2 = v[index] + p2Next;
                }
                
                dp[index][rest] = Math.max(p2,p1);
            }
        }
        return dp[0][bag];
    }

    public static void main(String[] args){
        int[] w = { 3, 2, 4, 7, 3, 1, 7 };
        int[] v = { 5, 6, 3, 19, 12, 4, 2 };

        int result = maxValue(w, v, 16);
        int dp = dp(w, v, 16);
        System.out.println(result);
        System.out.println(dp);
    }
}
