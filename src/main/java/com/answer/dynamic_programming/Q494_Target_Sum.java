package com.answer.dynamic_programming;

public class Q494_Target_Sum {

    /**
     * Approach 4: 1D Dynamic Programming
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(target > sum) return 0;
        if((target + sum) % 2 == 1) return 0;
        int size = (target + sum) / 2;
        if(size < 0) return 0;

        int[] dp = new int[size + 1];
        dp[0] = 1;

        for(int i = 0; i < nums.length; i++){
            for(int j= size; j >= nums[i]; j--){
                dp[j] += dp[j - nums[i]];
            }
        }

        return dp[size];
    }
}
