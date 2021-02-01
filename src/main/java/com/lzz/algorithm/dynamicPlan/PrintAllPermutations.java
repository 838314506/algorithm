package com.lzz.algorithm.dynamicPlan;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串打印全排列
 */
public class PrintAllPermutations {

    /**
     * 字符串的全部排列
     * @param str
     * @return
     */
    public static List<String> permutation1(String str){
        char[] strs = str.toCharArray();
        List<String> ans = new ArrayList<>();
        f(strs,ans,0);
        return ans;
    }

    public static void f(char[] strs,List<String> ans,int index){
        if (index == strs.length){
            ans.add(String.valueOf(strs));
        }
        for (int i = index; i < strs.length;i ++) {
            swap(strs,index,i);
            f(strs,ans,index + 1);
            swap(strs,index,i);
        }
    }

    /**
     * 字符串全部排列，不出现重复排列
     * 所有可能出现后筛选答案，属于暴力递归+过滤
     * 分支界限：暴力递归+人为限制，从源头直接过滤掉
     * @param str
     * @return
     */
    public static List<String> permutation3(String str){
        List<String> ans = new ArrayList<>();
        if (str == null || str.length() == 0){
            return ans;
        }
        char[] chars = str.toCharArray();
        g(chars,0,ans);
        return ans;
    }

    public static void g(char[] chars,int index,List<String> ans){
        if (index == chars.length){
            ans.add(String.valueOf(chars));
        }else {
            boolean[] visited = new boolean[265];
            for (int i = index;i < chars.length;i ++){
                if (!visited[chars[i]]){
                    visited[chars[i]] = true;
                    swap(chars,index,i);
                    g(chars,index + 1,ans);
                    swap(chars,index,i);
                }
            }
        }
    }

    public static void swap(char[] str,int index1,int index2){
        char c = str[index1];
        str[index1] = str[index2];
        str[index2] = c;
    }

    public static void main(String[] args) {
        String s = "acc";
        List<String> ans1 = permutation1(s);
        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=======");
//        List<String> ans2 = permutation2(s);
//        for (String str : ans2) {
//            System.out.println(str);
//        }
//        System.out.println("=======");
        List<String> ans3 = permutation3(s);
        for (String str : ans3) {
            System.out.println(str);
        }

    }

}
