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
        int way = process(str, index + 1);
        //可能性单一的情况下
        //a-z决定最高位一定是2，并且只有2位数字
        if (index + 1 < str.length && (str[index] - '0') * 10 + (str[index + 1] - '0') < 27) {
            //下一次决策从index+2开始
            way += process(str, index + 2);
        }
        return way;
    }

    public static int dpWay2(String s){
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[] dp = new int[N + 1];
        dp[N] = 1;
        for (int i = N - 1;i >= 0;i --){
            if (str[i] != '0') {
                dp[i] =  dp[i + 1];
                if (i + 1 < str.length && (str[i] - '0') * 10 + (str[i + 1] - '0') < 27) {
                    dp[i] += dp[i + 2];
                }
            }
        }
        return dp[0];
    }




    public static void main(String[] args) {
        String str = "7210231231232031203123";
        System.out.println(number(str));
        System.out.println(dpWay2(str));
    }
}
