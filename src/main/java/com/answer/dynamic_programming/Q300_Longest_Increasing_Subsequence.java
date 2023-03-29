package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q300_Longest_Increasing_Subsequence {
    /**
     * Approach 1: Dynamic Programming
     *
     * First, the question is asking for the maximum or minimum of something.
     * Second, we have to make decisions that may depend on previously made decisions,
     * which is very typical of a problem involving subsequences.
     */
    public int lengthOfLIS(int[] nums) {
        int[] dp= new int[nums.length];
        Arrays.fill(dp, 1);

        for(int i = 1; i < nums.length; i++){
            for(int j = 0; j < i; j++){
                if(nums[i] > nums[j]){
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int max = 1;
        for(int i = 0; i < nums.length; i++){
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}
