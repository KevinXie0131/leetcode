package com.answer.dynamic_programming;

public class Q416_Partition_Equal_Subset_Sum {

    /**
     * classic Knapsack problem
     * Approach 3: Bottom Up Dynamic Programming
     */
    public boolean canPartition_1(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        int target = sum / 2;

        int[][] dp = new int[nums.length + 1][target + 1];

        for(int i = 1; i <= nums.length; i++){
            for(int j = 1; j <= target; j++){
                if (j < nums[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - nums[i - 1]] + nums[i - 1]);
                }
            }
        }

        return dp[nums.length][target] == target;
    }
    /**
     * Approach 4: Optimised Dynamic Programming - Using 1D Array
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += nums[i];
        }
        if(sum % 2 == 1) return false;
        int target = sum / 2;

        int[] dp = new int[20001];

        for(int i = 0; i < nums.length; i++){
            for(int j= target; j >= nums[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + nums[i]);
            }
        }

        return dp[target] == target;
    }
}
