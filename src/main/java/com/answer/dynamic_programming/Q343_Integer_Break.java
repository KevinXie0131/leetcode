package com.answer.dynamic_programming;

public class Q343_Integer_Break {
    public static void main(String[] args) {
        System.out.println(integerBreak(10));
    }
    /**
     * DP
     */
    public static int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;

        for(int i = 3; i < n + 1; i++){
            for(int j = 1; j < i - 1; j++){
                dp[i] = Math.max(dp[i], Math.max(dp[i-j]*j, (i-j)*j));
            }
        }

        return dp[n];
    }

    public int integerBreak_1(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;

        for(int i = 3; i < n + 1; i++){
            int max = 0;
            for(int j = 1; j < i - 1; j++){
                max = Math.max(max, Math.max(dp[i-j]*j, (i-j)*j));
            }
            dp[i] = max;
        }

        return dp[n];
    }
}
