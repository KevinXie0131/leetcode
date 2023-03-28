package com.answer.dynamic_programming;

public class Q377_Combination_Sum_IV {
    /**
     * Approach 2: Bottom-Up Dynamic Programming
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int i = 0; i <= target; i++){
            for(int j = 0; j < nums.length; j++){
                if(i - nums[j] >= 0){
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        /**
         *  for (int i = 1; i <= target; i++) {
         *      for (int num : nums) {
         *         if (num <= i) {
         *             dp[i] += dp[i - num];
         *         }
         *      }
         *  }
         *
         */
        return dp[target];
    }
}
