package com.answer.dynamic_programming;


import java.util.Arrays;

public class Q62_Unique_Paths {

    public static void main(String[] args) {
        int result = uniquePaths(3, 5);
        System.out.println(result);
    }
    /**
     * Approach 1: Dynamic Programming 动态规划
     */
    public static int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i++){
            dp[i][0] = 1; // 初始状态
        }
        for(int j = 0; j < n; j++){
            dp[0][j] = 1; // 初始状态
        }

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i-1][j] + dp[i][j-1]; // 状态转移方程
            }
        }
        System.out.println(Arrays.deepToString(dp));
        return dp[m-1][n-1]; // 终止状态
    }
}
