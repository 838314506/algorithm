package com.lzz.algorithm.dynamicPlan;

import java.util.Stack;

/**
 * 逆序栈不使用其他额外的数据结构，只能使用递归函数
 */
public class ReverseStackUsingRecursive {

    public static void reverse(Stack<Integer> stack){
        if (stack.isEmpty()){
            return;
        }
        int result = f(stack);
        reverse(stack);
        stack.push(result);
    }

    /**
     * 通过递归返回函数进行处理
     * @param stack
     * @return
     */
    public static int f(Stack<Integer> stack){
        int result = stack.pop();
        if (stack.isEmpty()){
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        stack.add(6);
        reverse(stack);
        stack.forEach(x -> System.out.println(x));
    }
}
