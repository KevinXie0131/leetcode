package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q279_Perfect_Squares {
    public static void main(String[] args) {
        int n = 13;
        System.out.println(numSquares(n));
    }
    /**
     * 类似Q322 Coin Change
     */
    public static int numSquares_0(int n) { // Memory Limit Exceeded
        int MAX = n + 1;

        int[][] dp = new int[n + 1][n + 1];
        for (int a = 1; a <= n; a++) {
            dp[0][a] = MAX;
        }
        for (int i = 1; i <= n; i++) { // i*i是物品
            for (int a = 1; a <= n; a++) {  // 容量
                if (i * i > a) {
                    dp[i][a] = dp[i - 1][a];
                } else {
                    dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - i * i] + 1);
                }
            }
        }
        return dp[n][n] != MAX ? dp[n][n] : -1;
    }
    /**
     * 参考Q322 Coin Change，但是不能完全通过提交
     */
    public static int numSquares_2(int amt)  {
        int[] coins = {1,4,9,16,25,36,49,64,81,100,121,144,169}; // 物品
        int n = coins.length;
        int MAX = amt + 1;

        int[][] dp = new int[n + 1][amt + 1];
        for (int a = 1; a <= amt; a++) {
            dp[0][a] = MAX;
        }
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) { // 容量
                if (coins[i - 1] > a) {
                    dp[i][a] = dp[i - 1][a];
                } else {
                    dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amt] != MAX ? dp[n][amt] : -1;
    }
    /**
     * Approach 2: Dynamic Programming
     *  // 版本一，先遍历物品, 再遍历背包
     *  https://leetcode.com/problems/perfect-squares/Figures/279/279_dp.png
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
    /**
     * Another form of DP 版本二， 先遍历背包, 再遍历物品
     */
    public int numSquares_1(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i * i <= n; i++) { // 遍历物品
            for (int j = 1; j <= n; j++) { // 遍历背包
                if (j - i * i >= 0) {
                    dp[j] = Math.min(dp[j - i * i] + 1, dp[j]);
                }
            }
        }
        return dp[n];
    }

}
