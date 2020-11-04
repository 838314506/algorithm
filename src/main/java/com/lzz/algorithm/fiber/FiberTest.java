package com.lzz.algorithm.fiber;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;

import java.util.concurrent.ExecutionException;

/**
 * 使用两种方法比较方法二比方法一快1秒左右
 */
public class FiberTest {

    public static void main(String[] arg) throws Exception {
        long start = System.currentTimeMillis();
        int size = 1000000;
        //方法一、利用纤程执行多任务
//        Fiber[] fibers = new Fiber[size];
//        for (int i = 0; i < fibers.length; i++) {
//            fibers[i] = new Fiber<Void>(new SuspendableRunnable() {
//                @Override
//                public void run() throws SuspendExecution, InterruptedException {
//                    calc();
//                }
//            });
//        }
//        for (int k = 0; k < fibers.length; k++) {
//            fibers[k].start();
//        }
//        for (int k = 0; k < fibers.length; k++) {
//            fibers[k].join();
//        }
        //方法二、将纤程分为多个任务执行
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    Fiber[] fibers = new Fiber[200000];
                    for (int j = 0; j < fibers.length; j++) {
                        fibers[j] = new Fiber<Void>(new SuspendableRunnable() {
                            @Override
                            public void run() throws SuspendExecution, InterruptedException {
                                calc();
                            }
                        });
                    }
                    for (int k = 0; k < fibers.length; k++) {
                        fibers[k].start();
                    }
//                    for (int k = 0; k < fibers.length; k++) {
//                        try {
//                            fibers[k].join();
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
                }
            });
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threads.length; i++) {
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
