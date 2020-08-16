package com.lzz.algorithm.sort;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 9, 10,2, 3, 5, 8, 7};
//        int[] arr = { 4, 6};
        sort(arr, 0, arr.length - 1);
        print(arr);
    }

    static void sort(int[] arr,int leftBound,int rightBound){
        if (leftBound >= rightBound) return;
        int mid = patiton(arr, leftBound, rightBound);
        sort(arr,leftBound,mid - 1);
        sort(arr,mid + 1,rightBound);
    }

    static int patiton(int[] arr,int leftBound,int rightBound){
        int provit = arr[rightBound];
        int left = leftBound;
        int right = rightBound - 1;

        while (left <= right){
            while (left <= right && arr[left] <= provit) if (arr[left] <= provit) left ++;
            while (left <= right && arr[right] > provit) if (arr[left] > provit) right --;
            if (left < right) swap(arr,left,right);
        }
        if (left < rightBound) swap(arr,left,rightBound);
        return left;
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
