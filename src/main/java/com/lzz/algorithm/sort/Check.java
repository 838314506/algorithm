package com.lzz.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 对数器
 */
public class Check {

    public static int[] generatorData() {
        Random random = new Random();
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++)
            arr[i] = random.nextInt(50);
        return arr;
    }

    public static void check() {
        int[] arr = generatorData();
//        int[] arr2 =  Arrays.copyOf(arr,arr.length);
        int[] arr2 = new int[arr.length];
        System.arraycopy(arr, 0, arr2, 0, arr.length);
        Arrays.sort(arr);
//        print(arr);
//        SelectionSort.sort(arr2);
//        BubbleSort.sort(arr2);
//        InsertionSort.sort3(arr2);
//        ShellSort.sort(arr2);
//        MergeSort.sort(arr2,0,arr2.length - 1);
//        QuickSort.sort(arr2,0,arr2.length - 1);
        int[] result = CountSort.sort(arr2);
        boolean equals = true;
        for (int i = 0; i < arr.length; i++) {
            equals = arr[i] == result[i] ? true : false;
        }
        System.out.println(equals == true ? "right" : "wrong");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            check();
        }
    }

    static void print(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
