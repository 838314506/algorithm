package com.lzz.algorithm.sort;

/**
 * 计数排序法
 * 1、非比较排序
 * 2、桶排序的一种
 * 3、算法思想：量大但范围小
 *         如：某企业上万员工年龄排序
 *         如：如何快速获取高考名次
 * 4、设计思路
 *         a、给两个数组，一个数组计数，下标为值，下标位置数据为次数
 *         b、另一个结果数组，长度与原数组相同
 *         c、将原数组中，每个数字出现的数量记录在计数数组中
 *         d、再将计数数组中的下标，按次数放入结果数组
 */
public class CountSort {
    public static void main(String[] args){
        int[] arr = {59,54,52,53,57,51,51,60,60,65,66,59,55};
        print(sort(arr));
    }

    static int[] sort(int[] arr){
        int max = 0;
        int min = 0;

        for (int i = 0;i < arr.length - 1;i ++){
            if (arr[i] > arr[max]) max = i;
            if (arr[i] < arr[min]) min = i;
        }
        max = arr[max];
        min = arr[min];

        int[] temp = new int[arr.length];
        int[] count = new int[max - min + 1];


        for (int i = 0;i < arr.length; i ++){
            count[arr[i] - min] ++;
        }

        int sum = 0;
        for (int i =0;i < count.length;i ++){
            count[i] = sum += count[i];
        }


        for (int i = arr.length - 1;i >=0;i --){
            temp[--count[arr[i] - min]] = arr[i];
        }

//        for (int j = count.length - 1,index = temp.length - 1;j >= 0;j --){
//            int num = 0;
//            if (j == 0){
//                num = count[j];
//            }else {
//                num = count[j] - count[j - 1];
//            }
//           while (num > 0 && index >= 0){
//               temp[index --] = min + j;
//               num --;
//           }
//        }
        return temp;
    }



    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
