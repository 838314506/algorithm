package com.lzz.algorithm.sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args){
        int[] arr = {7,3,5,1,6,2,8,9,4};
        sort(arr);
        print(arr);
    }

    //冒泡排序法优化，最好结果的时间复杂度为O（n）
    static void sort(int[] arr){
        for (int i = arr.length - 1 ;i > 0;i --){
            int swapCount = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapCount = 0;
                }
            }
            if (swapCount == 1){
                break;
            }
        }
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
