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
        int start  = 0;

        for(int i = 0; i < nums.length; i++){
            if(i > 0 && nums[i - 1] >= nums[i]){
                start  = i;
            }
            max = Math.max(max, i - start  + 1);
        }

        return max;
    }
    /**
     * Greedy
     */
    public int findLengthOfLCIS_2(int[] nums) {
        int result = 1; // 连续⼦序列最少也是1
        int count = 1;

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i + 1] > nums[i]) { // 连续记录
                count++;
            } else { // 不连续，count从头开始
                count = 1;
            }
            if (count > result) {
                result = count;
            }
        }
        return result;
    }
}
