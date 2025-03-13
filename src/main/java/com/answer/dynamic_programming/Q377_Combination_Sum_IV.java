package com.answer.dynamic_programming;

public class Q377_Combination_Sum_IV {
    /**
     * Approach 2: Bottom-Up Dynamic Programming
     * 完全背包  (每个元素可以使用多次)
     * 二维数组
     *
     * 本题题目描述说是求组合，但又说是可以元素相同顺序不同的组合算两个组合，其实就是求排列！
     * 组合不强调顺序，(1,5)和(5,1)是同一个组合。排列强调顺序，(1,5)和(5,1)是两个不同的排列。
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
     * 如果求组合数就是外层for循环遍历物品，内层for遍历背包。
     * 如果求排列数就是外层for遍历背包，内层for循环遍历物品。
     */
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1]; // dp[i]: 凑成目标正整数为i的排列个数为dp[i]
        dp[0] = 1; // dp[0]要初始化为1，这样递归其他dp[i]的时候才会有数值基础。

        // target（背包）放在外循环，将nums（物品）放在内循环，内循环从前到后遍历。
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
