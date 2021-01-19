package com.lzz.algorithm.greedyAlgorithm;

import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * 给定一个由字符串组成的数组strs
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果
 *
 * 深度遍历，要考虑现场是否需要清理
 * 贪心算法：核心贪心策略
 * 先找一种，再举反例
 * 暴力方法列出所有，再看
 */
public class LowestLexicography {

    //暴力方法
    public static String lowestString1(String[] arr){
        if (arr == null || arr.length == 0){
            return "";
        }
        TreeSet<String> ans = process(arr);
        return ans.size() == 0 ? "" : ans.first();
    }

    public static String[] removeIndexString(String[] strs,int index){
        String[] newStrs = new String[strs.length - 1];
        int count = 0;
        for (int i = 0;i < strs.length;i ++ ){
            if (i != index && count < newStrs.length){
                newStrs[count ++] = strs[i];
            }
        }
        return newStrs;
    }

    public static TreeSet<String> process(String[] arr){
        TreeSet<String> result = new TreeSet<>();
        //尝试遍历中止条件
        if (arr.length == 0){
            result.add("");
            return result;
        }
        for (int i = 0;i < arr.length;i ++){
            String cur = arr[i];
            //深度遍历，保留原有字符串后并去掉
            String[] newStrs = removeIndexString(arr, i);
            TreeSet<String> nexts = process(newStrs);
            for (String next : nexts){
                result.add(cur + next);
            }
        }
        return result;
    }

    public static class MyComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            //贪心策略，如果两个字符串相加进行比较，哪个比较大，在前面的那个字符串一定是最大的
            return (o1 + o2).compareTo(o2 + o1);
        }
    }

    //贪心算法
    public static String lowestString2(String[] arr){
        if (arr == null || arr.length == 0){
            return "";
        }
        Arrays.sort(arr,new MyComparator());
        String ans = "";
        for (int i = 0;i < arr.length;i ++){
            ans += arr[i];
        }
        return ans;
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 5);
            ans[i] = (Math.random() <= 0.5) ? (char) (65 + value) : (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    // for test
    public static String[] copyStringArray(String[] arr) {
        String[] ans = new String[arr.length];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = String.valueOf(arr[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 6;
        int strLen = 5;
        int testTimes = 10000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String[] arr1 = generateRandomStringArray(arrLen, strLen);
            String[] arr2 = copyStringArray(arr1);
            if (!lowestString1(arr1).equals(lowestString2(arr2))) {
                for (String str : arr1) {
                    System.out.print(str + ",");
                }
                System.out.println();
                System.out.println("Oops!");
            }
        }
        System.out.println("finish!");
    }
}
