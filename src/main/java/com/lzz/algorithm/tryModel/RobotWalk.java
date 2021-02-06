package com.lzz.algorithm.tryModel;

/**
 * 题目
 * 假设有排成一行的N个位置，记为1-N，N一定大于或等于2
 * 开始时机器人在其中的M位置上M一定是1-N中的一个
 * 如果机器人来到1位置，那么下一步只能往右来到2位置
 * 如果机器人来到N位置，那么下一步只能往左来到N-1位置
 * 如果机器人来到中间位置，那么下一步可以往左走或者往右走
 * 规定机器人必须走K步，最终能来到P位置（P也是1-N中的一个）的方法有多少种
 * 给定四个参数N、M、K、P,返回方法数
 */
public class RobotWalk {

    public static int way1(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return -1;
        }
        return walk1(N, M, K, P);
    }

    /**
     *
     * @param N 一共有多少个位置
     * @param cur 当前在哪个位置上
     * @param rest 还剩多少步可以走
     * @param P 最终要到达的位置
     * @return
     */
    public static int walk1(int N, int cur, int rest, int P) {
        //剩余步数为0不能再走了
        if (rest == 0) {
            //正好当前点是终点，返回1，否则返回0
            return cur == P ? 1 : 0;
        }
        //如果当前位置来到最右边，只能往左走
        if (cur == N) {
            return walk1(N,cur - 1,rest - 1,P);
        }
        //如果当前位置来到最左边，只能往右走
        if (cur == 1){
            return walk1(N,2,rest - 1,P);
        }
        //否则就是还可以走，并且处于中间位置,那么结果就是选择往走的结果和选择往右走的结果之和
        return walk1(N,cur - 1 ,rest - 1,P) + walk1(N,cur + 1,rest - 1,P);
    }

    public static int way2(int N, int M, int K, int P) {
        if (N < 2 || M < 1 || M > N || P < 1 || P > N || K < 1) {
            return -1;
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int row = 0;row < N + 1;row ++){
            for (int col = 0;col < K + 1;col ++){
                dp[row][col] = -1;
            }
        }
        return walkCache(N, M, K, P,dp);
    }

    public static int walkCache(int N, int cur, int rest, int P,int[][] dp) {
        if (dp[cur][rest] != -1){
            return dp[cur][rest];
        }
        if (rest == 0) {
            dp[cur][rest] = cur == P ? 1 : 0;
            return dp[cur][rest];
        }
        if (cur == N) {
            dp[cur][rest] = walkCache(N,cur - 1,rest - 1,P,dp);
            return dp[cur][rest];
        }
        if (cur == 1){
            dp[cur][rest] = walkCache(N,2,rest - 1,P,dp);
            return dp[cur][rest];
        }
        dp[cur][rest] = walkCache(N,cur - 1 ,rest - 1,P,dp) + walkCache(N,cur + 1,rest - 1,P,dp);
        return dp[cur][rest];
    }



    public static void main(String[] args) {
        System.out.println(way1(5, 2, 6, 4));
        System.out.println(way2(5, 2, 6, 4));
    }

}
