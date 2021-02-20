package com.lzz.algorithm.dynamicPlan;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串str，给定一个字符串类型的数组arr.
 * arr里的每一个字符串，华表一张贴纸，你可以把单个字符剪开使用，目的是拼出str来。
 * 返回需要至少多少张贴纸可以完成这个任务。
 * 例子：str=“babac”,arr={"ba","c","abcd"}
 * 至少需要两张贴纸“ba”和“abcd”，因为使用这两张贴纸，把每一个字符单独剪开，含有2个a、2个b、1个c。
 * 是可以拼出str的。所以返回2。
,*/
public class SticketsToSpellWord {

    public static int sticker1(String[] sticket,String target){
        //参数校验
        if (sticket == null || sticket.length == 0){
            return 0;
        }
        int n = sticket.length;
        //将贴纸数组转为map，下标值对应a、b、c、d...
        int[][] map = new int[n][26];
        //初始化map数组
        for (int i = 0;i < n;i ++){
            char[] str = sticket[i].toCharArray();
            for (char c : str){
                map[i][c - 'a'] ++;
            }
        }
        //傻缓存
        Map<String,Integer> dp = new HashMap<>();
        dp.put("",0);
        return process1(map,dp,target);
    }

    public static int process1(int[][] map,Map<String,Integer> dp,String rest){
        //是否包含在傻缓存中，包含返回
        if (dp.containsKey(rest)){
            return dp.get(rest);
        }
        //给一个最大值，用来最后识别是否能将贴纸剪开
        int ans = Integer.MAX_VALUE;
        int n = map.length;
        //将rest转为int数组，下标代表字符串中的每个字符
        int[] tmap = new int[26];
        char[] target = rest.toCharArray();
        for (char c : target){
            tmap[c - 'a'] ++;
        }

        for (int i = 0;i < n;i ++){
            if (map[i][target[0] - 'a'] == 0){
                continue;
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0;j < 26;j ++){
                if (tmap[j] > 0){
                    for (int k = 0;k < Math.max(0,tmap[j] - map[i][j]);k ++){
                        sb.append((char)(j + 'a'));
                    }
                }
            }
            String s = sb.toString();
            int temp = process1(map, dp, s);
            if (temp != -1){
                ans = Math.min(ans,1 + temp);
            }
        }

        //判断是否剪开贴纸，放入缓存中
        dp.put(rest,ans == Integer.MAX_VALUE ? -1 : ans);
        return dp.get(rest);
    }

    public static void main(String[] args){
        String[] stickets = {"aaaa","bbaa","ccddd"};
        String target = "abcccccdddddbbbaaaaa";
        System.out.println(sticker1(stickets,target));
    }
}
