package com.lzz.algorithm.tryModel;

public class CoinsWay {

    /**
     *
     * @param arr n张任意面额的货币
     * @param aim 总共需要花费的金额
     * @return 每张货币可以使用n次，返回花完所有钱有多少种方法
     * 暴力递归
     */
    public static int way1(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim == 0){
            return 0;
        }
        return process1(arr,0,aim);
    }

    public static int process1(int[] arr,int index,int rest){
        if (index == arr.length){
            return rest == 0 ? 1 : 0;
        }

        int ways = 0;
        for (int zhang = 0;zhang * arr[index] <= rest;zhang ++){
            ways += process1(arr,index + 1,(rest - zhang * arr[index]));
        }
        return ways;
    }

    /**
     * 记忆搜索
     * @param arr
     * @param aim
     * @return
     */
    public static int way2(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim == 0){
            return 0;
        }
        int[][] dp = new int[arr.length + 1][aim + 1];
        for (int i = 0;i < dp.length;i ++){
            for (int j = 0;j < dp[0].length;j ++){
                dp[i][j] = -1;
            }
        }
        return process2(arr,0,aim,dp);
    }

    public static int process2(int[] arr,int index,int rest,int[][] dp){
        if (dp[index][rest] != -1){
            return dp[index][rest];
        }
        if (index == arr.length){
            dp[index][rest] = rest == 0 ? 1 : 0;
            return dp[index][rest];
        }

        int ways = 0;
        for (int zhang = 0;zhang * arr[index] <= rest;zhang ++){
            ways += process1(arr,index + 1,(rest - zhang * arr[index]));
        }
        dp[index][rest] = ways;
        return dp[index][rest];
    }

    /**
     * 经典动态规划
     * @param arr
     * @param aim
     * @return
     */
    public static int way3(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim == 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1;index >= 0;index --){
            for (int rest = 0;rest <= aim;rest ++){
                int ways = 0;
                for (int zhang = 0;zhang * arr[index] <= rest;zhang ++){
                    ways += dp[index + 1][rest - (zhang * arr[index])];
                }
                dp[index][rest] = ways;
            }
        }

        return dp[0][aim];
    }

    /**
     * 经典动态规划中列出枚举行为，进行一步精细化
     * @param arr
     * @param aim
     * @return
     */
    public static int way4(int[] arr,int aim){
        if (arr == null || arr.length == 0 || aim == 0){
            return 0;
        }
        int N = arr.length;
        int[][] dp = new int[N + 1][aim + 1];
        dp[N][0] = 1;
        for (int index = N - 1;index >= 0;index --){
            for (int rest = 0;rest <= aim;rest ++){
                dp[index][rest] = dp[index + 1][rest];
                if (rest - arr[index] >= 0){
                    dp[index][rest] += dp[index][rest - arr[index]];
                }
            }
        }

        return dp[0][aim];
    }

    public static void main(String[] args){
        int[] arr = {5,10,50,100};
        int aim = 1000;
        System.out.println(way1(arr,aim));
        System.out.println(way2(arr,aim));
        System.out.println(way3(arr,aim));
        System.out.println(way4(arr,aim));
    }
}
