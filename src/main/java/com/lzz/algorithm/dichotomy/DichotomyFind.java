package com.lzz.algorithm.dichotomy;

/**
 * 二分查找
 */
public class DichotomyFind {

    public static void main(String[] arg){
        //最简单的二分查找法
//        int[] arr = new int[]{1,3,4,6,7,9,10};
//        System.out.println(simpleFind(arr,arr.length,7));
        //找数组中目标值第一次出现的下标位置
        int[] arr = new int[]{3,4,5,6,7,9};
//        System.out.println("第一次出现的位置：[" + firstIndex(arr,8) + "]");
//        System.out.println("最后一次出现的位置：[" + lastIndex(arr,8) + "]");
//        System.out.println("第一个出现大于给定值的位置：[" + firstMore(arr,8) + "]");
        System.out.println("最后一个小于等于给定值的位置：[" + lastLess(arr,8) + "]");
    }

    /**
     * 最后一个小于等于给定值的元素
     * @param arr
     * @param value
     * @return
     */
    public static int lastLess(int[] arr,int value){
        int low = 0;
        int high = arr.length - 1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if(arr[mid] > value){
                high = mid - 1;
            }else {
                if (mid == arr.length - 1 || arr[mid + 1] > value) return mid;
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 第一个出现大于给定值的位置
     * @param arr
     * @param value
     * @return
     */
    public static int firstMore(int[] arr,int value){
        int low = 0;
        int high = arr.length - 1;
        int firstMore = -1;
        while (low <= high){
            int mid = low + ((high - low) >> 1);
            if (arr[mid] >= value){
                if (mid == 0 || arr[mid - 1] < value) return mid;
                firstMore = mid;
                high = mid - 1;
            }else if(arr[mid] < value){
                low = mid + 1;
            }
        }
        return firstMore;
    }

    /**
     * 最后一个等于给定值的元素
     * @param arr
     * @param value
     * @return
     */
    public static int lastIndex(int[] arr,int value){
        int low = 0;
        int high = arr.length - 1;
        int firstIndex = -1;
        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (arr[mid] == value){
                if (mid == arr.length - 1 || arr[mid + 1] != value) return mid;
                firstIndex = mid;
                low = mid + 1;
            }else if(arr[mid] > value){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return firstIndex;
    }

    /**
     * 第一个等于给定值的元素
     * @param arr
     * @param value
     * @return
     */
    public static int firstIndex(int[] arr,int value){
        int low = 0;
        int high = arr.length - 1;
        int firstIndex = -1;
        while (low <= high){
            int mid = low + ((high - low)>>1);
            if (arr[mid] == value){
                if (mid == 0 || arr[mid - 1] != value) return mid;
                firstIndex = mid;
                high = mid - 1;
            }else if(arr[mid] > value){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }
        return firstIndex;
    }

    /**
     * 二分法查找
     * 思路：定义两个指针，将数组分为两部分
     *      如果指定值等于中间值，返回
     *      如果大于中间值，左指针指向中间值+1位置
     *      如果小于中间值，左路指指向中间值-1位置
     * 满足条件：1、数组
     *          2、有序
     *          3、静态数据，如果删除、插入操作过多则不适合使用
     * 适用场景：在“近似”查找问题
     * @param arr
     * @param n
     * @param value
     * @return
     */
    public static int simpleFind(int[] arr,int n,int value){
        int low = 0;
        int high = n - 1;
        while (low <= high){
            int mid =  (high + low)/2;
            if (arr[mid] == value){
                return mid;
            }else if (arr[mid] > value){
                high = mid - 1;
            }else {
                low = mid + 1;
            }
        }

        return 0;
    }

    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }
}
