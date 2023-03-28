package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q279_Perfect_Squares {
    public static void main(String[] args) {
        int n = 13;
        System.out.println(numSquares(n));
    }
    /**
     * Approach 2: Dynamic Programming
     *  // 版本一，先遍历物品, 再遍历背包
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
