package com.answer.dynamic_programming;

public class Q96_Unique_Binary_Search_Trees {
    /**
     * Approach 1: Dynamic Programming
     * 互不相同的 二叉搜索树 有多少种
     * For example: dp[3] = dp[0]*dp[2] + dp[1]*dp[1] + dp[2]*dp[0]
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1];

        dp[0] = 1; // 0个节点只有1种
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j <= i; j++){
                dp[i] += dp[i - j] * dp[j - 1]; // 总共i个节点，左面有j-1个节点，右面有i-j个节点
            }
        }

        return dp[n];
    }
}
