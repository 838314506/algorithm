package com.lzz.algorithm.binaryTree;

/**
 *一段纸条竖着放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕
 * 是凹下去的，即折痕突起的方向指向纸箱的背面。如果从纸箱的下边向上方连续对折2次，
 * 压出折痕后展开，此时有三条折痕，从上到下依次是下折痕、下折痕和上折痕。
 *
 * 给定一个输入参数N，代表纸条都从下边向上方连续对折N次，请从上到下打印所有折痕的方向。
 *
 * 例如：N=1时，打印：down；N=2时，打印：down down up
 */
public class PaperFolding {

    /**
     * 按二叉树来看，这是一棵有规律的二叉树，每个节点的左节点都凹，右边都是凸
     * 只要按中序遍历二叉树遍历就可以得到结果，且不需要实际上的二叉树，只需要逻辑上的二叉树即可
     * @param N
     */
    public static void printAllFold(int N){
        printAllFold(1,N,true);
    }

    /**
     * 用递归实现中序遍历
     * @param i 表示节点层数
     * @param N 表示总层数
     * @param down 表示“凹”、“凸”
     */
    public static void printAllFold(int i,int N,boolean down){
        if (i > N){
            return;
        }
        printAllFold(i + 1,N,true);
        System.out.println(down ? "凹" : "凸");
        printAllFold(i + 1,N,false);
    }

    public static void main(String[] args){
        int N = 3;
        printAllFold(3);
    }

}
