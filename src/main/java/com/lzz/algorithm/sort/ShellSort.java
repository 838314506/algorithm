package com.lzz.algorithm.sort;

/**
 * 希尔排序
 */
public class ShellSort {

    public static void main(String[] args){
        int[] arr = {9,6,11,3,5,12,8,7,10,15,14,4,1,13,2,20,17,18,16,19};
//        Random random = new Random();
//        int[] arr = new int[10000];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = random.nextInt(100000);
//        }
//        long start2 = System.currentTimeMillis();
//        sort2(arr);
//        System.out.println("Rnuth序列===》" + (System.currentTimeMillis() - start2));
//        long start = System.currentTimeMillis();
        sort3(arr);
//        System.out.println("对半撇排序===》" + (System.currentTimeMillis() - start));

        print(arr);
    }

    /**
     * Rnuth序列使用
     * @param arr
     */
    static void sort3(int[] arr){
        int h = 1;
        while (h <= arr.length / 3){
            h = 3 * h + 1;
        }
        for (int k = h;k > 0; k /= 2){
            for (int i = k;i < arr.length;i ++){
                for (int j = i;j > k - 1;j -= k){
                    if (arr[j] < arr[j - k])
                        swap(arr,j,j - k);
                }
            }
        }
    }

    //对半撇
    static void sort(int[] arr){
        for (int gap = arr.length >> 1; gap > 0 ;gap /= 2){
            for (int i = gap; i < arr.length ; i++) {
                for (int j = i; j > gap - 1; j-=gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
        }
    }

    //Knuth序列
    static void sort2(int[] arr){
        int h = 1;
        while (h <= arr.length / 3){
            h = 3 * h + 1;
        }
        for (int gap = h; gap > 0 ;gap = (gap - 1) / 3){
            for (int i = gap; i < arr.length ; i++) {
                for (int j = i; j > gap - 1; j-=gap) {
                    if (arr[j] < arr[j - gap]) {
                        swap(arr, j, j - gap);
                    }
                }
            }
        }
    }

    static void firtst(int[] arr,int grap){
        for (int j = 0;j < arr.length/grap + 1;j ++){
            for (int k = j;k > 0;k --){
                if (arr[k * grap] < arr[(k - 1) * grap]) swap(arr,k * grap , (k - 1) * grap);
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
