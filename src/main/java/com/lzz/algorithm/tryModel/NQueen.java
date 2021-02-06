package com.lzz.algorithm.tryModel;

/**
 * 每个皇后必定不在同一行上
 */
public class NQueen {

    public static int num1(int n){
        if (n < 1){
            return 0;
        }
        int[] record = new int[n];//->record表示i列上皇后的位置，下标表示x轴，具体值表示y轴
        return process(record,0,n);
    }

    /**
     * 当前来到i行，一共是0~N-1行
     * 在i行上放皇后，所有列都尝试
     * 必须要保证跟之前所有的皇后不打架
     * int[] record record[x] = y 之前的第x行的皇后，放在了y列上
     * 返回：不关心i以上发生了什么，i.... 后续有多少合法的方法数
     * @param record->record表示之前的列上所有皇后的位置
     * @param i->i表示当前第几列上
     * @param n->n表示n皇后问题
     * @return
     */
    public static int process(int[] record,int i,int n){
        if (i == n){
            return 1;
        }

        int result = 0;
        // i行的皇后，放哪一列呢？j列，
        for (int k = 0;k < n;k ++){
            if (isValid(record,i,k)){
                record[i] = k;
                result += process(record,i + 1,n);
            }
        }
        return result;
    }

    public static boolean isValid(int[] record,int i,int j){
        boolean result = true;
        for (int k = 0;k < i;k ++){
            if (record[k] == j || Math.abs(record[k] - j) == Math.abs(k - i)){
                result = false;
                break;
            }
        }
        return result;
    }

    public static int num2(int n){
       if (n < 1 || n > 32){
           return 0;
       }
       //如果是13皇后的问题，limit最右13个1，其他都是0
       int limit = n == 32 ? -1 : (1 << n) - 1;
       return process2(limit,0,0,0);
    }

    /**
     *
     * @param limit 最后每一行都不能放的限制
     * @param colLim 之前皇后列的限制
     * @param leftDiaLim 之前皇后左对角线的限制
     * @param rightDiaLim 之前皇后右对角线的限制
     * @return
     */
    public static int process2(int limit,int colLim,int leftDiaLim,int rightDiaLim){
        if (colLim == limit){
            return 1;
        }
        int result = 0;
        //colLim | leftDiaLim | rightDiaLim 表示前一皇后影响下不能放的位置
        //~(colLim | leftDiaLim | rightDiaLim) 表示去年前一皇后影响下不能放的位置后，能放的位置
        //limit & 因为非一下可能导致前边的0都变成1，用limit切割一下排除干扰
        int pos = limit & (~(colLim | leftDiaLim | rightDiaLim));
        //当前数字最右边的1的位置
        int mostRightOne = 0;
        while (pos != 0){
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            result += process2(limit,
                            (colLim | mostRightOne),
                                //pos这个位置上的皇后往左对角线的限制
                    (leftDiaLim | mostRightOne) << 1,
                                //pos这个位置上的皇后往右对角线的限制
                    (rightDiaLim | mostRightOne) >> 1);
        }
        return result;
    }

    public static void main(String[] args){
        int n = 14;

        long start = System.currentTimeMillis();
        System.out.println(num2(n));
        long end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

        start = System.currentTimeMillis();
        System.out.println(num1(n));
        end = System.currentTimeMillis();
        System.out.println("cost time: " + (end - start) + "ms");

    }
}
