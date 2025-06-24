package com.answer.dynamic_programming;

public class Q1137_N_th_Tribonacci_Number {
    /**
     * 第 N 个泰波那契数
     * 泰波那契序列 Tn 定义如下：
     * T0 = 0, T1 = 1, T2 = 1, 且在 n >= 0 的条件下 Tn+3 = Tn + Tn+1 + Tn+2
     * 给你整数 n，请返回第 n 个泰波那契数 Tn 的值。
     *
     * 示例 1：
     * 输入：n = 4
     * 输出：4
     * 解释：T_3 = 0 + 1 + 1 = 2
     *      T_4 = 1 + 1 + 2 = 4
     */
    public static void main(String[] args) {
        int n = 4;
        System.out.println(tribonacci(n));
    }
    /**
     * Approach 3: Performance Optimisation : Dynamic Programming
     */
    public static int tribonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 1;
        }

        int[] dp = new int[n + 1];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n];
    }
    /**
     * Approach 2: Performance Optimisation : Recursion with Memoization
     */
    int[] nums = new int[38];

    public int tribonacci_1(int n) {

        if(n == 0) {
            return 0;
        }else if(n == 1) {
            return 1;
        }if(n == 2) {
            return 1;
        }


        nums[0] = 0;
        nums[1] = 1;
        nums[2] = 1;

        helper(n);

        return nums[n];
    }

    int helper(int k) {
        if (k == 0) return 0;
        if (nums[k] != 0) return nums[k];

        nums[k] = helper(k - 1) + helper(k - 2) + helper(k - 3);
        return nums[k];
    }
}
