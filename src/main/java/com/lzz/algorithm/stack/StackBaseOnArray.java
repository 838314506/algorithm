package com.lzz.algorithm.stack;

public class StackBaseOnArray {

    public int[] temp;

    public int capacity;

    public int n;

    public StackBaseOnArray(int capacity){
        this.temp = new int[capacity];
        this.capacity = capacity;
        this.n = 0;
    }

    public static void main(String[] args){
        StackBaseOnArray data = new StackBaseOnArray(10);
        data.push(2);
        data.push(5);
        data.print();
        data.pop();
        data.push(5);
        data.print();
    }

    /**
     * 入栈操作
     * @param item
     * @return
     */
    public boolean push(int item){
        //数量与容量相同表示放满
        if (n == capacity) return false;
        temp[n] = item;
        ++n;
        return true;
    }

    /**
     * 出栈操作
     * @return
     */
    public int pop(){
        //数据为0表明栈为空，无需出栈
        if (n == 0) return -1;
        int result = temp[n - 1];
        -- n;
       return result;
    }

    public void print(){
        for (int i = 0;i < temp.length;i ++){
            System.out.print(temp[i]+"----->");
        }
        System.out.println();
    }
}
