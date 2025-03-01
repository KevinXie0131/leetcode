package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q1035_Uncrossed_Lines {
    public static void main(String[] args) {
       int[] nums1 = {1,4,2};
       int[] nums2 = {1,2,4};
        System.out.println(maxUncrossedLines(nums1, nums2));
    }
    /**
     * Approach 3: Dynamic Programming with Space Optimization
     * Note that the above recursive relation is exactly the same as in the classical problem, Longest Common Subsequence (LCS)
     * 和Q1143.最长公共子序列相同，代码一致
     */
    public static int maxUncrossedLines(int[] nums1, int[] nums2) {
        int M = nums1.length;
        int N = nums2.length;

        int[][] dp = new int[M + 1][N + 1];

        for(int i = 1; i <= M; i++){
            for(int j = 1; j <= N; j++){
                if(nums1[i - 1] == nums2[j - 1]){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else{
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }
        for(int[] arr : dp){
            System.out.println(Arrays.toString(arr));
        }
        return dp[M][N];
    }
}
