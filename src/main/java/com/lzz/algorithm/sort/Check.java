package com.lzz.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 对数器
 */
public class Check {

    public static int[] generatorData() {
        Random random = new Random();
        int[] arr = new int[10000];
        for (int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(1000);
        return arr;
    }

    public static void check() {
        int[] arr = generatorData();
//        int[] arr2 =  Arrays.copyOf(arr,arr.length);
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        Arrays.sort(arr);
//        SelectionSort.sort(arr2);
//        BubbleSort.sort(arr2);
//        InsertionSort.sort3(arr2);
//        ShellSort.sort(arr2);
//        MergeSort.sort(arr2,0,arr2.length - 1);
        QuickSort.sort(arr2,0,arr2.length - 1);
        boolean equals = true;
        for (int i = 0; i < arr.length; i++) {
            equals = arr[i] == arr2[i] ? true : false;
        }
        System.out.println(equals == true ? "right" : "wrong");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            check();
        }
    }
}
