package com.answer.dynamic_programming;

public class Q96_Unique_Binary_Search_Trees {

    /**
     * Approach 1: Dynamic Programming
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];

        dp[0] = 1;
        for(int i = 1; i < n+1; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[i-j] * dp[j-1];
            }
        }

        return dp[n];
    }
}
