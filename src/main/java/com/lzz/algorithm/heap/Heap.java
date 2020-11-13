package com.lzz.algorithm.heap;

public class Heap {

    public static class MyHeap{
        private int[] heap;
        private int limit;
        private int heapSize;

        public MyHeap(int limit){
            heap = new int[limit];
            this.limit = limit;
            this.heapSize = 0;
        }

        public boolean isEmpty(){
            return heapSize == 0;
        }

        public boolean isFull(){
            return heapSize == limit;
        }

        //TODO 验证 1、往堆内放入数据
        public void push(int value){
            if (heapSize == limit){
                throw new RuntimeException("the heap is full!");
            }
            heap[heapSize] = value;
            heapInsert(heap,heapSize ++);
        }

        //TODO 2、取出堆内最大的数并已删除
        public int pop(){
            int result = heap[0];
            swap(heap,0,--heapSize);
            heapify(heap,0,heapSize);
            return result;
        }


        //往堆内插入数据,从下往上找
        public void heapInsert(int[] arr,int index){
//            while (arr[(index - 1) / 2 ] < arr[index]){
//                swap(arr,(index - 1) / 2,index);
//                index = (index - 1) / 2;
//            }
            while (arr[index] > arr[(index - 1) / 2]) {
                swap(arr, index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        //删除最大的数，从上往下找
        public void heapify(int[] arr,int index,int heapSize){
            int left = index * 2 + 1;
            while (left < heapSize){
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                largest = arr[largest] > arr[index] ? largest : index;
                if (largest == index){
                    break;
                }
                swap(arr,index,largest);
                index = largest;
                left = 2 * index + 1;
            }
        }


        public void swap(int[] arr,int i,int j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }

        public static class RightMaxHeap {
            private int[] arr;
            private final int limit;
            private int size;

            public RightMaxHeap(int limit) {
                arr = new int[limit];
                this.limit = limit;
                size = 0;
            }

            public boolean isEmpty() {
                return size == 0;
            }

            public boolean isFull() {
                return size == limit;
            }

            public void push(int value) {
                if (size == limit) {
                    throw new RuntimeException("heap is full");
                }
                arr[size++] = value;
            }

            public int pop() {
                int maxIndex = 0;
                for (int i = 1; i < size; i++) {
                    if (arr[i] > arr[maxIndex]) {
                        maxIndex = i;
                    }
                }
                int ans = arr[maxIndex];
                arr[maxIndex] = arr[--size];
                return ans;
            }

        }

        public static void main(String[] args) {
            int value = 100;
            int limit = 100;
            int testTimes = 10;
            for (int i = 0; i < testTimes; i++) {
                int curLimit = (int) (Math.random() * limit) + 1;
                MyHeap my = new MyHeap(curLimit);
                RightMaxHeap test = new RightMaxHeap(curLimit);
                int curOpTimes = (int) (Math.random() * limit);
                for (int j = 0; j < curOpTimes; j++) {
                    if (my.isEmpty() != test.isEmpty()) {
                        System.out.println("empty Oops!");
                    }
                    if (my.isFull() != test.isFull()) {
                        System.out.println("full Oops!");
                    }
                    if (my.isEmpty()) {
                        int curValue = (int) (Math.random() * value);
                        my.push(curValue);
                        test.push(curValue);
                    } else if (my.isFull()) {
                        if (my.pop() != test.pop()) {
                            System.out.println(" full pop Oops!");
                        }
                    } else {
                        if (Math.random() < 0.5) {
                            int curValue = (int) (Math.random() * value);
                            my.push(curValue);
                            test.push(curValue);
                        } else {
                            if (my.pop() != test.pop()) {
                                System.out.println("pop Oops!");
                            }
                        }
                    }
                }
            }
            System.out.println("finish!");

        }
    }
}
