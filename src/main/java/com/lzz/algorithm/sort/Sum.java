package com.lzz.algorithm.sort;

import java.util.HashMap;
import java.util.Map;

public class Sum {
    public static void main(String[] args){
        int[] nums = {3,2,4};
        int[] ints = twoSum(nums, 6);
        System.out.println(ints);
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> arr = new HashMap<>();
        for (int i = 0;i < nums.length;i ++){
            int j = target - nums[i];
            if (arr.containsKey(nums[i])) {
                return new int[]{arr.get(nums[i]),i};
            };
            arr.put(j,i);
        }
        return null;
    }
}
