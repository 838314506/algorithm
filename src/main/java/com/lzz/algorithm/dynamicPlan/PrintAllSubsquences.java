package com.lzz.algorithm.dynamicPlan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 字符串子序列打印
 * 子序列:从左往右依次拿字符，可要可不要，但是顺序不能乱
 */
public class PrintAllSubsquences {

    public static List<String> sub(String str){
        char[] chars = str.toCharArray();
        List<String> ans = new ArrayList<>();
        process(chars,ans,0,"");
        return ans;
    }

    /**
     *
     * @param c:固定参数
     * @param ans:结果集，将所有生成的子序列，放入到ans里去
     * @param index:位置；来到了c[index]的位置上，c[0……index-1]的位置都已经走过了
     * @param path:走过之前的位置后，形成的决定，连接到path
     */
    // str[index....]还能决定，之前已经确定，而后面还能自由选择的话，
    public static void process(char[] c,List<String> ans,int index,String path){
        if (index == c.length){
            ans.add(path);
            return;
        }
        String no = path;
        process(c,ans,index + 1,no);
        String yes = path + String.valueOf(c[index]);
        process(c,ans,index + 1,yes);
    }

    /**
     * 全部子序列，不出现重复子序列
     * @param strs
     * @return
     */
    public static List<String> subNoRepect(String strs){
        char[] str = strs.toCharArray();
        Set<String> ans =  new HashSet<>();
        process2(str,0,"",ans);
        List<String> result = new ArrayList<>();
        for (String s : ans){
            result.add(s);
        }
        return result;
    }

    public static void process2(char[] str,int index,String path,Set<String> ans){
        if (index == str.length){
            ans.add(path);
            return;
        }
        String no = path;
        process2(str,index + 1,no,ans);
        String yes = path + String.valueOf(str[index]);
        process2(str,index + 1,yes,ans);
    }

    public static void main(String[] args) {
        String test = "acccc";
        List<String> ans1 = sub(test);
        List<String> ans2 = subNoRepect(test);

        for (String str : ans1) {
            System.out.println(str);
        }
        System.out.println("=================");
        for (String str : ans2) {
            System.out.println(str);
        }
        System.out.println("=================");

    }

}
