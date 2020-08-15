package com.lzz.algorithm.sort;

/**
 * 插入排序
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {7, 3, 5, 1, 6, 2, 8, 9, 4};
        sort3(arr);
        print(arr);
    }

    //插入排序法
    static void sort(int[] arr) {
        for (int i = 1; i < arr.length - 1; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    //插入排序法2.0
    static void sort2(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && arr[j] < arr[j - 1]; j--) {
                swap(arr, j, j - 1);
            }
        }
    }
    //插入排序法3.0
    static void sort3(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int minPos = arr[i];
            int lastPos = i;
            for (int j = i; j > 0; j--) {
                if (minPos < arr[j - 1]){
                    arr[j] = arr[j - 1];
                    lastPos = j - 1;
                }
            }
            arr[lastPos] = minPos;
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
