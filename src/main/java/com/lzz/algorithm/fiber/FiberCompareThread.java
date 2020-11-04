package com.lzz.algorithm.fiber;

public class FiberCompareThread {
    
    public static void main(String[] arg) throws Exception{
        long start = System.currentTimeMillis();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                calc();
            }
        };

        int size = 100000;
        Thread[] threads = new Thread[size];
        for (int i = 0;i < threads.length;i ++){
            threads[i] = new Thread(r);
        }
        for (int i = 0;i < threads.length;i ++){
            threads[i].start();
        }
        for (int i = 0;i < threads.length;i ++){
            threads[i].join();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    static void calc() {
        int result = 0;
        for (int m = 0; m < 10000; m++) {
            for (int i = 0; i < 200; i++) result += i;
        }
    }
}
