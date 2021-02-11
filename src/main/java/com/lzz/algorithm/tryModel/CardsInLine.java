package com.lzz.algorithm.tryModel;

/**
 * 范围上尝试的模型
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线，
 * 玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿起最左或最右的纸牌，
 * 玩家A和玩家B都绝顶聪明，请返回最后获胜者的分数
 */
public class CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(f(arr, 0, arr.length), s(arr, 0, arr.length));
    }

    //先手获得的最好的分数
    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        return Math.max(arr[L] + s(arr, L + 1, R), arr[R] + s(arr, L, R - 1));
    }

    //后手获得的最好分数
    public static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        return Math.min(f(arr, L + 1, R), f(arr, L, R - 1));
    }


    //暴力递归改动态规划
    public static int dp(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];
        //初始化对角线
        for (int i = 0; i < f.length; i++) {
            f[i][i] = arr[i];
        }

        for (int col = 1;col < N;col ++){
            int L = 0;
            int R = col;
            while (R < N){
                f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);
                L ++;
                R ++;
            }
        }
        return Math.max(f[0][N - 1], s[0][N - 1]);
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 7, 9, 5};
        System.out.println(f(arr, 0, arr.length - 1));
        System.out.println(s(arr, 0, arr.length - 1));
        System.out.println(dp(arr));
    }
}
