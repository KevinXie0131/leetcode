package com.answer.binarysearch;

public class Q441_Arranging_Coins {
    /**
     * 排列硬币
     * 总共有 n 枚硬币，并计划将它们按阶梯状排列。对于一个由 k 行组成的阶梯，其第 i 行必须正好有 i 枚硬币。阶梯的最后一行 可能 是不完整的。
     * You have n coins and you want to build a staircase with these coins. The staircase consists of k rows where the ith row has exactly i coins.
     * The last row of the staircase may be incomplete.
     * 给你一个数字 n ，计算并返回可形成 完整阶梯行 的总行数。
     * Given the integer n, return the number of complete rows of the staircase you will build.
     * 示例 1：
     *  输入：n = 5
     *  输出：2
     *  解释：因为第三行不完整，所以返回 2 。
     */
    public static void main(String[] args) {
        int res = arrangeCoins(1804289383);
        System.out.println(res);
    }
    /**
     * Approach 1: Binary Search
     */
    public static int arrangeCoins(int n) {
        long left = 1;
        long right = n;

        while(left <= right){
            long mid = (left + right) >>> 1;
            long sum = (mid + 1) * mid / 2;

            if(sum == n){
                return (int)mid;
            }else if(sum < n){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return (int)left - 1; // return (int)right;
    }
    /**
     * Brute force
     */
    public int arrangeCoins_1(int n) {
        long sum = 0;

        for(int i = 1; ; i++){
            sum += i;
            if(sum > n){
                return i - 1;
            }
        }
    }
}
