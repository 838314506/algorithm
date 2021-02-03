package com.lzz.algorithm.tryModel;

/**
 * 按从左往右尝试模型
 * 规定1和A对应，2和B对应，3和C对应：“111” ->AAA,KA,AK
 * 给定一个只有数字字符组成的字符串，str,返回多少种转化结果
 */
public class ConvertToLetterString {

    public static int number(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        return process(str.toCharArray(), 0);
    }

    public static int process(char[] str, int index) {
        //返回1，说明1种情况
        if (index == str.length) {
            return 1;
        }
        //如果是0说明，之前的处理有问题
        if (str[index] == '0') {
            return 0;
        }
        //可能性单一的情况下
        int way = process(str, index + 1);
        //a-z决定最高位一定是2，并且只有2位数字
        if (index + 1 < str.length && (str[index] - '0') * 10 + (str[index + 1] - '0') < 27) {
            //下一次决策从index+2开始
            way += process(str, index + 2);
        }
        return way;
    }

    //对数器
    public static int dp(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] strs = str.toCharArray();
        int N = strs.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1; i >= 0; i--) {
            int way = dp[i + 1];
            if (strs[i] != '0'){
                if (i + 1 < N && (strs[i] - '0') * 10 + strs[i + 1] - '0' < 27) {
                    way += dp[i + 2];
                }
                dp[i] = way;
            }
        }
        return dp[0];
    }


    public static void main(String[] args) {
        String str = "7210231231232031203123";
        System.out.println(number(str));
        System.out.println(dp(str));
    }
}
