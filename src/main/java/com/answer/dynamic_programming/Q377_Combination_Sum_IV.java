package com.answer.dynamic_programming;

public class Q377_Combination_Sum_IV {
    /**
     * Approach 2: Bottom-Up Dynamic Programming
     * 完全背包  (每个元素可以使用多次)
     * 二维数组
     */
    public int combinationSum4_0(int[] nums, int target) {
        int m = nums.length;
        int n = target;
        int[][] dp = new int[m][n + 1];
        // 初始化
        // dp[i][0] = 1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // 循环遍历
        for (int j = 1; j <= n; j++) {
            if (j >= nums[0]) {
                dp[0][j] = dp[m - 1][j - nums[0]];
            }
            for (int i = 1; i < m; i++) {
                if (j >= nums[i]) {
                    dp[i][j] = dp[i - 1][j] + dp[m - 1][j - nums[i]];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m - 1][n];
    }
    /**
     * 一维数组
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;

        for(int i = 0; i <= target; i++){ // 先遍历背包，再遍历物品,得到排列数。反之，得到组合数
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
