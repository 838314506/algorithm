package com.lzz.algorithm.sort;

/**
 * 选择排序
 */
public class SelectionSort {


    public static void sort(int[] arr){
        //       n          n-1             n
        for (int j = 0; j < arr.length - 1; j++) {
            int minPos = j;
            for (int i = j + 1; i < arr.length; i++) {
                minPos = arr[i] < arr[minPos] ? i : minPos;
            }
            swap(arr,j,minPos);//n
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
