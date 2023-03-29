package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q674_Longest_Continuous_Increasing_Subsequence {

    /**
     * Dynamic Programming
     */
    public int findLengthOfLCIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i + 1] > nums[i]){
                dp[i + 1] = dp[i] + 1;
            }
        }

        int max = 1;
        for(int i : dp){
            max = Math.max(max, i);
        }
        return max;
    }
    /**
     * Approach #1: Sliding Window [Accepted]
     */
    public int findLengthOfLCIS_1(int[] nums) {
        int max = 0;
        int anchor = 0;

        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i - 1] >= nums[i]){
                anchor = i;
            }
            max = Math.max(max, i - anchor + 1);
        }

        return max;
    }
}
