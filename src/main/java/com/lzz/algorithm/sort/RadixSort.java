package com.lzz.algorithm.sort;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args){
        int[] arr = {421,356,789,102,296,256,361};

        int maxLength = findMaxLength(arr);
        System.out.println("数组中最大长度：" + maxLength);

        sort(arr,maxLength);
    }

    static void sort(int[] arr,int maxLength){
        int[] result = new int[arr.length];
        int[] count = new int[10];

        for (int i = 0;i < maxLength;i ++){
            int division = (int)Math.pow(10,i);
            for (int j = 0;j < arr.length;j ++){
                int num = arr[j]/division%10;
                count[num] ++;
            }

            System.out.println("累积count前：");
            print(count);
            System.out.println();
            for (int j = 1;j < count.length;j ++){
                count[j] = count[j - 1] + count[j];
            }
            System.out.println("累积count后:");
            print(count);
            System.out.println();
            for (int j = arr.length - 1;j >= 0;j --){
                int num = arr[j]/division%10;
                result[--count[num]] = arr[j];
            }
            System.out.println("result数组结果：");
            print(result);
            System.arraycopy(result,0,arr,0,arr.length);
            Arrays.fill(count,0);
        }
        print(arr);
    }

    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    /**
     * 获取数组中最高位数
     * @param arr
     * @return
     */
    static int findMaxLength(int[] arr){
        int maxLength = 0;
        for (int i = 0;i < arr.length;i ++){
            int length = (arr[i] + "").length();
            if (length > maxLength) maxLength = length;
        }
        return maxLength;
    }
}
