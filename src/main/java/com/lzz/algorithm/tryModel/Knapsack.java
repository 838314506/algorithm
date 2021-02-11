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

    /**
     * 将暴力递归改为动态规划(计划搜索)
     * 0 1 2 3 4 5 6 7 8 9
     * 1                 *
     * 2
     * 3
     * 4
     * - - - - - - - - - -
     * 5 0 0 0 0 0 0 0 0 0
     * *为最终的结果位置
     * x轴为w的长度
     * y轴为剩余重量，一定不会超过bag
     * 申请dp数组，初始值默认为0，下标5一列，表示在index == w.length的位置数据是有效的，并且当前层结果依赖于上一层的数据结果
     * 所以只需要把所有格子填满，最终通过上一层数据推出最终结果即可
     * @param w
     * @param v
     * @param bag
     * @return
     */
    public static int dynamicPlan(int[] w,int[] v,int bag){
        int N = w.length - 1;
        int[][] dp = new int[N + 1][bag + 1];
        for (int index = N - 1; index >= 0;index --){
            for (int rest = 0;rest <= bag;rest ++){
                //此部分全部由暴力递归中代码改造而来
                int p1 = dp[index + 1][rest];
                int p2 = -1;
                //为防止越界，先进行判断再进行p2的处理
                if (rest - w[index] >= 0){
                    p2 = v[index] + dp[index + 1][rest - w[index]];
                }
                dp[index][rest] = Math.max(p1,p2);
            }
        }
        return dp[0][bag];
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
        int dp2 = dynamicPlan(w,v,16);
        System.out.println(result);
        System.out.println(dp);
        System.out.println(dp2);
    }
}
