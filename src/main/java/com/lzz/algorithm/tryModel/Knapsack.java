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

    public static int maxValue(int[] w,int[] v,int bag){
        if (w == null || v == null || w.length != v.length || w.length == 0){
            return 0;
        }
        return process(w,v,bag,0);
    }

    public static int process(int[] w,int[] v,int rest,int index){
        if (rest < 0){
            return -1;
        }
        if (index == w.length){
            return 0;
        }
        return 0;
    }

}
