package com.lzz.algorithm.sort;

/**
 * 归并算法
 * 查找某个数的左边或右边小于该数的小和
 */
public class MergeSort {

    /**
     * 归并排序使用场景：由于归并排序比较稳定，所以java和python中使用归并排序来对对象进行排序
     * java中使用TimSort排序（多路归并排序--->改进版的归并排序）
     * 思路：将数组直接分为若干份，两两归并，同时还使用了二分插入法排序
     */


    public static void main(String[] args){
//        System.out.println(f(10));
        int[] arr = {1,4,7,8,3,6,9};
        sort(arr,0,arr.length - 1);
        print(arr);
    }

    static void sort(int[] arr,int left,int right){
        if (left == right) return;
        //边界鉴定，如果是左小于右报错
        if (left > right) throw new RuntimeException();
        //分两半
        int mid = left + ( right - left ) / 2;
        //左排序
        sort(arr,left,mid);
        //右排序
        sort(arr,mid + 1,right);
        //合并
        merge(arr,left,mid + 1,right);
    }

    static void merge(int[] arr,int leftPtr,int rightPtr,int rightBound){
        int[] temp = new int[rightBound - leftPtr + 1];
        int i = leftPtr;
        int mid = rightPtr - 1;
        int j = rightPtr;
        int k = 0;

        while (i <= mid && j <= rightBound){
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid) temp[k ++] = arr[i ++];
        while (j <= rightBound) temp[k ++] = arr[j ++];

//        print(temp);
        for (int m = 0;m < temp.length;m ++){
            arr[m + leftPtr] = temp[m];
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

    /**
     * 递归
     * @param n
     * @return
     */
    static int f(int n){
        if (n < 1) return -1;
        if (n == 1) return 1;
        return n + f(n - 1);
    }


}
